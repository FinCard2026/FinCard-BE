package com.example.fincard.domain.user.dto.response;

import com.example.fincard.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserMeResponse {

    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String job;
    private final Integer age;
    private final Long income;
    private final String region;
    private final Long coin;

    public UserMeResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.job = user.getJob();
        this.age = user.getAge();
        this.income = user.getIncome();
        this.region = user.getRegion();
        this.coin = user.getCoin();
    }
}
