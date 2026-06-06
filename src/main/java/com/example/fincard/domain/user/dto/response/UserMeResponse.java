package com.example.fincard.domain.user.dto.response;

import com.example.fincard.domain.user.entity.User;
import lombok.Getter;

import java.util.List;

@Getter
public class UserMeResponse {

    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String job;
    private final String ageRange;
    private final String incomeRange;
    private final String region;
    private final List<String> interests;
    private final Long coin;

    public UserMeResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.job = user.getJob();
        this.ageRange = user.getAgeRange();
        this.incomeRange = user.getIncomeRange();
        this.region = user.getRegion();
        this.interests = user.getInterests();
        this.coin = user.getCoin();
    }
}
