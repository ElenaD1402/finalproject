package org.elena.finalproject.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Post {
    private String title;
    private String block;
}
