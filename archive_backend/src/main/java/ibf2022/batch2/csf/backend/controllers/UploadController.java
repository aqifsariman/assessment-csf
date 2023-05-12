package ibf2022.batch2.csf.backend.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.repositories.ArchiveRepository;
import ibf2022.batch2.csf.backend.repositories.ImageRepository;
import ibf2022.batch2.csf.backend.services.UnzipService;

@RestController
@RequestMapping(path = "/upload")
public class UploadController {

    @Autowired
    ImageRepository imageRepo;

    @Autowired
    UnzipService unzipSvc;

    @Autowired
    ArchiveRepository archiveRepo;

    // TODO: Task 2, Task 3, Task 4
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postUpload(@RequestPart MultipartFile file, @RequestPart String name,
            @RequestPart String title, @RequestPart String comments) {

        List<String> keyList = new LinkedList<String>();
        try {
            File[] filesExtracted = unzipSvc.unzip(
                    "/Users/aqifsariman/ibf/assessment/assessment-csf/photos" + File.separator
                            + file.getOriginalFilename(),
                    "/Users/aqifsariman/ibf/assessment/assessment-csf/archive_backend/extracted-photos");
            for (File singleFile : filesExtracted) {
                FileInputStream fIS = new FileInputStream(singleFile);
                String fileName = singleFile.getName();
                String oriName = fileName.substring(0, fileName.lastIndexOf('.'));
                imageRepo.upload(fIS, fileName, oriName, title, comments);
                String endpoint = "https://bucket-hat.sgp1.digitaloceanspaces.com/" + fileName;
                keyList.add(endpoint);
            }

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        String payload = archiveRepo.recordBundle(name, title, comments, keyList);

        System.out.println("Payload: " + payload.toString());
        return ResponseEntity.status(201).body(payload);
    }

    // TODO: Task 5
    @GetMapping(path = "/bundle/:bundleId")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> uploadSuccess(@RequestParam String bundleId) {
        return ResponseEntity.ok().body(archiveRepo.getBundleByBundleId(bundleId).toJson());
    }

    // TODO: Task 6
}
