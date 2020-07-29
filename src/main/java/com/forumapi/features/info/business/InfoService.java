package com.forumapi.features.info.business;

import com.forumapi.documents.UserDocument;
import com.forumapi.features.info.model.InfoFilter;
import com.forumapi.features.info.model.UserInfoVO;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InfoService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public InfoService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<UserInfoVO> search(InfoFilter infoFilter){
        Query query = new Query();
        query.addCriteria(new Criteria()
                .andOperator(
                        Criteria.where("name").regex(".*"+ Strings.nullToEmpty(infoFilter.getUserName())+".*", "i"),
                        Criteria.where("email").regex(".*"+Strings.nullToEmpty(infoFilter.getEmail())+".*", "i"),
                        Criteria.where("questions.description").regex(".*"+Strings.nullToEmpty(infoFilter.getQuestionDescription())+".*", "i")));

        query.skip(infoFilter.getPage() -1 * infoFilter.getSize());
        query.limit(infoFilter.getSize());

        List<UserDocument> userDocument = mongoTemplate.find(query, UserDocument.class);

        return userDocument
                .stream()
                .map(UserInfoVO::new)
                .collect(Collectors.toList());
    }

}
