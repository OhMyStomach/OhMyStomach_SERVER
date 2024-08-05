package org.ohmystomach.ohmystomach_server.domain.smokemyplace.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.apache.catalina.User;
import org.ohmystomach.ohmystomach_server.domain.smoke.domain.Smoke;
import org.ohmystomach.ohmystomach_server.domain.smokemyplace.dto.request.UpdateUserSmokeServiceRequestDto;
import org.ohmystomach.ohmystomach_server.domain.toilet.domain.Toilet;

@Entity
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSmoke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    private User user;

    private String name;
    private String location;
    private String detailLocation;
    private String memo;

    public UserSmoke(Long userId, Smoke smoke) {
//        this.userId = userId;
//        this.smoke = smoke;
    }

    public void update(UpdateUserSmokeServiceRequestDto dto) {
        this.name = dto.name();
        this.location = dto.location();
        this.detailLocation = dto.detailLocation();
        this.memo = dto.memo();
    }
}

