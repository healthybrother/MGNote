package com.mgnote.mgnote.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.domain.Sort;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    private Sort.Direction direction;
    private String property;


    public Order(Sort.Direction direction, String property) {
        this.direction = direction;
        this.property = property;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "Order{" +
                "direction=" + direction +
                ", property='" + property + '\'' +
                '}';
    }
}
