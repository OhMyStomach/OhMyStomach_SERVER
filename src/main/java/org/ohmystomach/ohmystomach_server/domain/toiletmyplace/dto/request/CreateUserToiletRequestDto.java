package org.ohmystomach.ohmystomach_server.domain.toiletmyplace.dto.request;

public record CreateUserToiletRequestDto (
    String name,
    String location,
    String detailLocation,
    String password,
    String time,
    String toiletPaper,
    String memo
) {
    public CreateUserToiletServiceRequestDto toServiceDto(String uuid) {
        return new CreateUserToiletServiceRequestDto(uuid, name, location, detailLocation, password, time, toiletPaper, memo);
    }
}
