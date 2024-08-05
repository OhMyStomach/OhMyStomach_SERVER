package org.ohmystomach.ohmystomach_server.domain.smokemyplace.dto.request;

import org.ohmystomach.ohmystomach_server.domain.smokemyplace.domain.UserSmoke;
import org.ohmystomach.ohmystomach_server.domain.user.domain.User;

public record CreateUserSmokeServiceRequestDto(
    String uuid,
    String name,
    String location,
    String detailLocation,
    String memo
) {
    public UserSmoke toEntity(User user) {
        return UserSmoke.builder()
                .user(user)
                .name(name)
                .location(location)
                .detailLocation(detailLocation)
                .memo(memo)
                .build();
    }
}
