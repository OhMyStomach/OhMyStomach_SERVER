package org.ohmystomach.ohmystomach_server.domain.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

import java.util.ArrayList;

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

    public <E> User(String username, String password, ArrayList<E> es) {
    }
}
