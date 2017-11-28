package com.example.demo.domain.validator;


import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.demo.domain.controller.impl.UserController;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class RequestValidator {
    List<Map.Entry<String,String>> errors;

    List<Map.Entry<String,Object>> items;

    public RequestValidator() {
        errors = new ArrayList();
        items = new ArrayList();
    }

    public void addItem(String name, Object value){
        Map.Entry<String,Object> item = new AbstractMap.SimpleEntry<>(name,  value);
        items.add(item);
    }

    public boolean validate(){
        boolean isValid = true;
        for (Map.Entry<String,Object> pairItem : items) {
            if (pairItem.getValue().getClass() == String.class) {
                if (pairItem.getValue() == null || StringUtils.trimToNull(pairItem.getValue().toString()) == null) {
                    errors.add(new AbstractMap.SimpleEntry<>(pairItem.getKey(), " is empty"));
                    isValid = false;
                }
            }

            if (pairItem.getValue().getClass() == Long.class){
                if (Long.valueOf(pairItem.getValue().toString()) <= 0){
                    errors.add(new AbstractMap.SimpleEntry<>(pairItem.getKey(), " is invalid"));
                    isValid = false;
                }
            }
        }

        return isValid;
    }

    public List<Map.Entry<String, String>> getErrors() {
        return errors;
    }
}
