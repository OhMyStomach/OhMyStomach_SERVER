package org.ohmystomach.ohmystomach_server.domain.toiletmyplace.dto.request;

import org.ohmystomach.ohmystomach_server.domain.toiletmyplace.domain.UserToilet;
import org.ohmystomach.ohmystomach_server.domain.user.domain.User;

public record CreateUserToiletServiceRequestDto(
    String uuid,
    String name,
    String location,
    String detailLocation,
    String password,
    String time,
    String toiletPaper,
    String memo
) {
    public UserToilet toEntity(User user) {
        return UserToilet.builder()
                .user(user)
                .name(name)
                .location(location)
                .detailLocation(detailLocation)
                .password(password)
                .time(time)
                .toiletPaper(toiletPaper)
                .memo(memo)
                .build();
    }
}
