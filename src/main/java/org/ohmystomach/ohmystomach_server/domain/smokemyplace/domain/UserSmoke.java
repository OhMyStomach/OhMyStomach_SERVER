package org.ohmystomach.ohmystomach_server.domain.smokemyplace.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.smokemyplace.dto.request.UpdateUserSmokeServiceRequestDto;
import org.ohmystomach.ohmystomach_server.domain.user.domain.User;

@Entity
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
//    private String userId;


    private String name;
    private String location;
    private String detailLocation;
    private String memo;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonBackReference
    private User user;

//    public UserSmoke(Long userId, Smoke smoke) {
////        this.userId = userId;
////        this.smoke = smoke;
//    }

    public void update(UpdateUserSmokeServiceRequestDto dto) {
        this.name = dto.name();
        this.location = dto.location();
        this.detailLocation = dto.detailLocation();
        this.memo = dto.memo();
    }
}

