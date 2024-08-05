package org.ohmystomach.ohmystomach_server.domain.smokemyplace.dto.request;

import org.ohmystomach.ohmystomach_server.domain.toiletmyplace.dto.request.CreateUserToiletServiceRequestDto;

public record CreateUserSmokeRequestDto(
    String token,
    String name,
    String location,
    String detailLocation,
    String memo
) {
    public CreateUserSmokeServiceRequestDto toServiceDto(String uuid) {
        return new CreateUserSmokeServiceRequestDto(uuid, name, location, detailLocation, memo);
    }
}
