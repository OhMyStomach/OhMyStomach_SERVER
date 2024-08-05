package org.ohmystomach.ohmystomach_server.domain.smokeReview.dto.request;

public record UpdateSmokeReviewServiceRequestDto(
        String content,  // 후기 내용
        int rating  // 별점 (1~5)
) {
}
