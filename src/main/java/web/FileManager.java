package web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class FileManager {

    public void createAnEmptyFile(String filename){
        System.out.println("creating a file with the name: " + filename);
    }


}
