package org.ohmystomach.ohmystomach_server.domain.smokemyplace.dto.request;

public record UpdateUserSmokeServiceRequestDto(
    String uuid,
    Long smokeId,
    String name,
    String location,
    String detailLocation,
    String memo
) {
}
