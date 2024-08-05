package org.ohmystomach.ohmystomach_server.domain.toiletmyplace.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.apache.catalina.User;
import org.ohmystomach.ohmystomach_server.domain.toilet.domain.Toilet;

@Entity
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserToilet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    private User user;

//    @ManyToOne
//    private Toilet toilet;

    private String name;  // 이름
    private String category;  // 구분
    private Double wsg84x;  // WSG84X
    private Double wsg84y;  // WSG84Y

    public UserToilet(Long userId, Toilet toilet) {
        this.userId = userId;
//        this.toilet = toilet;
    }
}