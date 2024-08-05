package org.ohmystomach.ohmystomach_server.domain.toiletmyplace.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.ohmystomach.ohmystomach_server.domain.toiletmyplace.dto.request.UpdateUserToiletServiceRequestDto;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserToilet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    private User user;

//    @ManyToOne
//    private Toilet toilet;

//    private String name;  // 이름
//    private String category;  // 구분
//    private Double wsg84x;  // WSG84X
//    private Double wsg84y;  // WSG84Y

//    private String uuid;
    private String userId;

    private String name;
    private String location;
    private String detailLocation;
    private String password;
    private String time;
    private String toiletPaper;
    private String memo;

//    public UserToilet(Long userId, Toilet toilet) {
////        this.userId = userId;
////        this.toilet = toilet;
//    }

    public void update(UpdateUserToiletServiceRequestDto dto) {
        this.name = dto.name();
        this.location = dto.location();
        this.detailLocation = dto.detailLocation();
        this.password = dto.password();
        this.time = dto.time();
        this.toiletPaper = dto.toiletPaper();
        this.memo = dto.memo();
    }
}