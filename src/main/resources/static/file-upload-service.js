export default function (formData) {
    return fetch('http://localhost:8070/upload-files', {
        method: 'POST',
        body: formData
    })
        .then(response => response.json());
}