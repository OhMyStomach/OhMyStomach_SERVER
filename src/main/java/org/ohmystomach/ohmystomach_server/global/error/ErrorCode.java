package org.ohmystomach.ohmystomach_server.global.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common

    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "Request Body를 통해 전달된 값이 유효하지 않습니다."),

    // S3
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 문제로 S3 이미지 업로드에 실패하였습니다."),
    NOT_EXIST_IMAGE_FILE(HttpStatus.BAD_REQUEST, "업로드 할 이미지가 존재하지 않습니다."),

    ERROR_S3_DELETE_OBJECT(HttpStatus.INTERNAL_SERVER_ERROR, "서버 문제 S3 이미지 삭제에 실패하였습니다."),
    ERROR_S3_UPDATE_OBJECT(HttpStatus.INTERNAL_SERVER_ERROR, "서버 문제로 S3 이미지 업로드에 실패하였습니다."),

    // oAuth
    UNAUTHORIZED_ERROR(HttpStatus.UNAUTHORIZED, "승인되지 않은 사용자입니다."),
    KAKAO_LOGOUT_FAILED(HttpStatus.UNAUTHORIZED, "카카오 로그아웃에 실패했습니다."),

    // user
    INVALID_USER_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 USER 식별자입니다."),

    // review
    INVALID_REVIEW_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 review 식별자입니다."),

    // userToilet
    INVALID_USER_TOILET_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 userToilet 식별자입니다."),

    // userSmoke
    INVALID_USER_SMOKE_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 userSmoke 식별자입니다.");

    private final HttpStatus status;
    private final String message;

}