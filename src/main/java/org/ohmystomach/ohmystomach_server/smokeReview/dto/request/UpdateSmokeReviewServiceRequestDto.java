package org.ohmystomach.ohmystomach_server.smokeReview.dto.request;

public record UpdateSmokeReviewServiceRequestDto(
        String username,  // 작성자 이름
        String content,  // 후기 내용
        int rating  // 별점 (1~5)
) {
}
