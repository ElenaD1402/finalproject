package org.elena.finalproject.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Category {
    private String name;
    private String slug;
    private String description;
}
