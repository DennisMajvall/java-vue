package web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class FileUploader {
    private static String currentWorkingDirectory = System.getProperty("user.dir");
    private static String uploadDirectory = currentWorkingDirectory + "/src/main/resources/static/uploads/";

    @PostConstruct
    public void createFolderIfMissing() {
        File dirPath = new File(uploadDirectory);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    @PostMapping("/upload-files")
    public ModelAndView handleFileUpload(@RequestParam List<MultipartFile> files) {
        for (MultipartFile file : files) {
            File targetLocation = new File(uploadDirectory + file.getOriginalFilename());

            try {
                file.transferTo(targetLocation);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return new ModelAndView("redirect:/");
    }
}
