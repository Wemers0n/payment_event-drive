package com.example.notificationservice.config.utils;

import com.example.notificationservice.core.dtos.event.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public JsonUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String toJson(Object object){
        try{
            return objectMapper.writeValueAsString(object);
        } catch (Exception e){
            return "";
        }
    }

    public Event toEvent(String json){
        try{
            return objectMapper.readValue(json, Event.class);
        } catch (Exception e){
            return null;
        }
    }
}
