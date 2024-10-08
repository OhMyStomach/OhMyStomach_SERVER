package org.ohmystomach.ohmystomach_server.domain.smokemyplace.dto.request;

public record CreateUserSmokeRequestDto(
    String name,
    String location,
    String detailLocation,
    String memo
) {
    public CreateUserSmokeServiceRequestDto toServiceDto(String uuid) {
        return new CreateUserSmokeServiceRequestDto(uuid, name, location, detailLocation, memo);
    }
}
