package com.example.fincard.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateUserRequest {

    private String job;
    private String ageRange;
    private String incomeRange;
    private String region;
    private List<String> interests;
}
