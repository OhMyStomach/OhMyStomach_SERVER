package org.ohmystomach.ohmystomach_server.domain.toiletmyplace.dto.request;

import org.ohmystomach.ohmystomach_server.domain.toiletmyplace.domain.UserToilet;

public record UpdateUserToiletServiceRequestDto(
    String uuid,
    Long toiletId,
    String name,
    String location,
    String detailLocation,
    String password,
    String time,
    String toiletPaper,
    String memo
) {
}
