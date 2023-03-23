package com.ll.sbb;

import org.springframework.data.domain.Sort;

public class PageRequest {
    private static final int MINIMUM_PAGE = 1;
    private static final int DEFAULT_SIZE = 10;
    private static final int MAX_SIZE = 50;
    private static final Sort.Direction DEFAULT_DIRECTION = Sort.Direction.DESC;
    private static final String DEFAULT_PROPERTIES = "createDate";
    private int page = MINIMUM_PAGE;
    private int size = DEFAULT_SIZE;
    private Sort.Direction direction = DEFAULT_DIRECTION;
    private String properties = DEFAULT_PROPERTIES;

    public void setPage(int page) {
        this.page = Math.min(page, MINIMUM_PAGE);
    }

    public void setSize(int size) {
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page - 1, size, direction, properties);
    }
}
