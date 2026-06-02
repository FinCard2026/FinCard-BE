package com.example.fincard.domain.user.entity;

import com.example.fincard.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@SQLDelete(sql = "UPDATE users SET deleted_at = NOW() WHERE id = ?")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    // 온보딩 정보
    private String job;
    private Integer age;
    private Long income;
    private String region;

    @Column(nullable = false)
    @Builder.Default
    private Long coin = 0L;

    public void onboarding(String job, Integer age, Long income, String region) {
        this.job = job;
        this.age = age;
        this.income = income;
        this.region = region;
    }

    public void update(String job, Integer age, Long income, String region) {
        if (job != null) this.job = job;
        if (age != null) this.age = age;
        if (income != null) this.income = income;
        if (region != null) this.region = region;
    }
}