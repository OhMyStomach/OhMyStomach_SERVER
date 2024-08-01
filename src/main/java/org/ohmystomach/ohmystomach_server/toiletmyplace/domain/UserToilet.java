package org.ohmystomach.ohmystomach_server.toiletmyplace.domain;

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
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Toilet toilet;
}