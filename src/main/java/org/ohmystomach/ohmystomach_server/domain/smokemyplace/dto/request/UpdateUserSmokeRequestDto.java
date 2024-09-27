package org.ohmystomach.ohmystomach_server.domain.smokemyplace.dto.request;

import org.ohmystomach.ohmystomach_server.domain.toiletmyplace.dto.request.UpdateUserToiletServiceRequestDto;

public record UpdateUserSmokeRequestDto(
    Long smokeId,
    String name,
    String location,
    String detailLocation,
    String memo
) {
    public UpdateUserSmokeServiceRequestDto toServiceDto(String uuid) {
        return new UpdateUserSmokeServiceRequestDto(uuid, smokeId, name, location, detailLocation, memo);
    }
}
