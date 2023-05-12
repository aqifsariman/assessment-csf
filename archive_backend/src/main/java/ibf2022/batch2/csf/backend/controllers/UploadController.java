package ibf2022.batch2.csf.backend.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.services.S3Service;
import ibf2022.batch2.csf.backend.services.UnzipService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path = "/upload")
public class UploadController {

    @Autowired
    S3Service s3Svc;

    @Autowired
    UnzipService unzipSvc;

    // TODO: Task 2, Task 3, Task 4
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postUpload(@RequestPart MultipartFile file, @RequestPart String name,
            @RequestPart String title, @RequestPart String comments) {

        String key = "";
        try {
            File[] filesExtracted = unzipSvc.unzip(
                    "/Users/aqifsariman/ibf/assessment/assessment-csf/photos" + File.separator
                            + file.getOriginalFilename(),
                    "/Users/aqifsariman/ibf/assessment/assessment-csf/archive_backend/extracted-photos");
            for (File singleFile : filesExtracted) {
                // String fileName = singleFile.getName();
                // String oriName = fileName.substring(0, fileName.lastIndexOf('.'));
                // String contentType = "image/%s"
                //         .formatted(fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length()));
                // // byte[] content = Files.readAllBytes(singleFile.toPath());
                // InputStream is = new FileInputStream(singleFile);

                // System.out.println("FileName: " + fileName);
                // System.out.println("oriName: " + oriName);
                // System.out.println("contentType: " + contentType);
                // MultipartFile mpFile = new MockMultipartFile(fileName, oriName, contentType,
                //         is);
                // key = s3Svc.upload(mpFile, name, title, comments);
            }

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        JsonObject payload = Json.createObjectBuilder().add("fileKey", key).build();

        System.out.println("PAYLOAD: " + payload.toString());
        return ResponseEntity.ok(payload.toString());
    }

    // TODO: Task 5

    // TODO: Task 6
}
