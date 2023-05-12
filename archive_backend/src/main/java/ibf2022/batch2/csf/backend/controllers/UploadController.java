package ibf2022.batch2.csf.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/upload")
public class UploadController {
    // TODO: Task 2, Task 3, Task 4
    @PostMapping
    public ResponseEntity<String> postUpload(@RequestPart MultipartFile archive, @RequestPart String name,
            @RequestPart String title, @RequestPart String comments) {
        String archiveName = archive.getName();
        String originalName = archive.getOriginalFilename();
        String mediaType = archive.getContentType();
        System.out.println("Name: " + name);
        System.out.println("Title: " + title);
        System.out.println("Comments: " + comments);
        System.out.println("File Uploaded Name: " + archiveName);
        System.out.println("File Uploaded Original Name: " + originalName);
        System.out.println("File Uploaded Media Type: " + mediaType);
        return null;
    }

    // TODO: Task 5

    // TODO: Task 6
}
