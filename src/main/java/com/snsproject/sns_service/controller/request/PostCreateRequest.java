package com.snsproject.sns_service.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@AllArgsConstructor
public class PostCreateRequest {

    private String title;
    private String body;
}
