package ibf2022.batch2.csf.backend.repositories;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ArchiveRepository {

    public static final String C_ARCHIVES = "archives";

    @Autowired
    private MongoTemplate mongoTemplate;

    // TODO: Task 4
    public String recordBundle(String name, String title, String comments, List<String> urls) {
        String bundleId = UUID.randomUUID().toString()
                .substring(0, 8);
        String uploadDateTime = LocalDateTime.now().toString();

        Map<String, Object> mongoMap = new HashMap<>();
        mongoMap.put("bundleId", bundleId);
        mongoMap.put("date", uploadDateTime);
        mongoMap.put("title", title);
        mongoMap.put("name", name);
        mongoMap.put("comments", comments);
        mongoMap.put("urls", urls);

        Document toInsert = new Document(mongoMap);
        mongoTemplate.insert(toInsert, C_ARCHIVES).toJson();
        return bundleId;
    }

    // TODO: Task 5
    public Document getBundleByBundleId(String bundleId) {
        Criteria criterial = Criteria.where("bundleId").is(bundleId);
        Query query = Query.query(criterial);
        return mongoTemplate.findOne(query, Document.class, C_ARCHIVES);

    }

    // TODO: Task 6
    // You are free to change the parameter and the return type
    // Do not change the method's name
    // Write the native mongo query that you will be using in this method
    //
    //
    public Object getBundles(/* any number of parameters here */) {
        return null;
    }

}
