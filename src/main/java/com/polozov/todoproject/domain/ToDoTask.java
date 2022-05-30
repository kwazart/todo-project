package com.polozov.todoproject.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Getter
@Setter
public class ToDoTask {

    @Id
    private String id;

    @JsonProperty("text")
    @Field("text")
    private String text;

    private int priority;


    public ToDoTask(String text, int priority) {
        this.text = text;
        this.priority = priority;
    }


}
