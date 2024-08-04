package org.ohmystomach.ohmystomach_server.domain.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.user.dto.request.UpdateUserRequestServiceDto;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;

    private String nickname;

    private String email;

    private String profileImageUrl;

    // 이미지 파일 이름 유무로 사진이 카카오 이미지인지 아닌지 구분
    private String profileImageFileName;

    public void update(UpdateUserRequestServiceDto dto, String profileImageFileName, String profileImageUrl) {
        this.nickname = dto.nickname();
        this.profileImageFileName = profileImageFileName;
        this.profileImageUrl = profileImageUrl;
    }
}
