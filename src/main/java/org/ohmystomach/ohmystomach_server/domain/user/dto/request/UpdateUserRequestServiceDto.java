package org.ohmystomach.ohmystomach_server.domain.user.dto.request;

public record UpdateUserRequestServiceDto(
        String uuid,
        String nickname
) {
}
