package org.ohmystomach.ohmystomach_server.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.smokeReview.domain.SmokeReview;
import org.ohmystomach.ohmystomach_server.domain.smokemyplace.domain.UserSmoke;
import org.ohmystomach.ohmystomach_server.domain.toiletReview.domain.ToiletReview;
import org.ohmystomach.ohmystomach_server.domain.toiletmyplace.domain.UserToilet;
import org.ohmystomach.ohmystomach_server.domain.user.dto.request.UpdateUserRequestServiceDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;

    private Long accessToken;

    private Long refreshToken;

    private String nickname;

    private String email;

    private String profileImageUrl;

    // 이미지 파일 이름 유무로 사진이 카카오 이미지인지 아닌지 구분
    private String profileImageFileName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<UserSmoke> userSmokes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<UserToilet> userToilets = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SmokeReview> smokeReviews = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ToiletReview> toiletReviews = new ArrayList<>();

    public void update(UpdateUserRequestServiceDto dto, String profileImageFileName, String profileImageUrl) {
        this.nickname = dto.nickname();
        this.profileImageFileName = profileImageFileName;
        this.profileImageUrl = profileImageUrl;
    }
}
