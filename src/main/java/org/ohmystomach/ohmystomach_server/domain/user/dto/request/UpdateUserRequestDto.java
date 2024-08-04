package org.ohmystomach.ohmystomach_server.domain.user.dto.request;

public record UpdateUserRequestDto(
        String token,
        String nickname
) {
    public UpdateUserRequestServiceDto toServiceRequest(String uuid) {
        return new UpdateUserRequestServiceDto(uuid, nickname);
    }
}
