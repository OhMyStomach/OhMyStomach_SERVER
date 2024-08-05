package org.ohmystomach.ohmystomach_server.domain.toiletmyplace.dto.request;

public record UpdateUserToiletRequestDto(
    String token,
    Long toiletId,
    String name,
    String location,
    String detailLocation,
    String password,
    String time,
    String toiletPaper,
    String memo
) {
    public UpdateUserToiletServiceRequestDto toServiceDto(String uuid) {
        return new UpdateUserToiletServiceRequestDto(uuid, toiletId, name, location, detailLocation, password, time, toiletPaper, memo);
    }
}
