package com.example.fincard.domain.user.entity;

import com.example.fincard.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.List;

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
    private String ageRange;
    private String incomeRange;
    private String region;

    @ElementCollection
    @CollectionTable(name = "user_interests", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "interest")
    @Builder.Default
    private List<String> interests = new ArrayList<>();

    @Column(nullable = false)
    @Builder.Default
    private Long coin = 0L;

    public void onboarding(String job, String ageRange, String incomeRange, String region, List<String> interests) {
        this.job = job;
        this.ageRange = ageRange;
        this.incomeRange = incomeRange;
        this.region = region;
        this.interests = interests;
    }

    public void update(String job, String ageRange, String incomeRange, String region, List<String> interests) {
        if (job != null) this.job = job;
        if (ageRange != null) this.ageRange = ageRange;
        if (incomeRange != null) this.incomeRange = incomeRange;
        if (region != null) this.region = region;
        if (interests != null) this.interests = interests;
    }

    public void addCoin(Long amount) {
        this.coin += amount;
    }
}
