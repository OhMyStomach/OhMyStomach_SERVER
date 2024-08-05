package org.ohmystomach.ohmystomach_server.domain.smokemyplace.dto.request;

import org.ohmystomach.ohmystomach_server.domain.smokemyplace.domain.UserSmoke;
import org.ohmystomach.ohmystomach_server.domain.toiletmyplace.domain.UserToilet;

public record CreateUserSmokeServiceRequestDto(
    String uuid,
    String name,
    String location,
    String detailLocation,
    String memo
) {
    public UserSmoke toEntity() {
        return UserSmoke.builder()
                //uuid
                .name(name)
                .location(location)
                .detailLocation(detailLocation)
                .memo(memo)
                .build();
    }
}
