package com.xq.es.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author xq
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName="index_entity", type="tstype")
public class UserEsDto implements Serializable {
    String username;
    int age;
    String id;

    @Override
    public String toString() {
        return "UserEsDto{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
