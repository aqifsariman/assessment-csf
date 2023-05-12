package ibf2022.batch2.csf.backend.repositories;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Repository
public class ImageRepository {

    // TODO: Task 3
    @Autowired
    private AmazonS3 s3Client;

    @Value("${DO_STORAGE_BUCKETNAME}")
    private String bucketName;

    public String upload(FileInputStream file, String name, String originalName, String title, String comments)
            throws IOException {
        Map<String, String> userData = new HashMap<>();
        userData.put("name", name);
        userData.put("uploadDateTime", LocalDateTime.now().toString());
        userData.put("originalFilename", originalName);
        userData.put("title", title);
        userData.put("comments", comments);

        ObjectMetadata metadata = new ObjectMetadata();

        metadata.setUserMetadata(userData);

        PutObjectRequest putRequest = new PutObjectRequest(
                bucketName, "%s".formatted(name), file, metadata);
        putRequest.withCannedAcl(CannedAccessControlList.PublicRead);
        s3Client.putObject(putRequest);
        return "%s".formatted(name);
    }
}
