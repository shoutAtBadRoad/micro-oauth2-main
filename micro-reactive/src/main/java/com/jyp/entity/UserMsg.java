package com.jyp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "userMsg")
public class UserMsg {

    @Id
    private long id;

    private long fromId;

    private long toId;

    private String text;

    private String timestamp;
}
