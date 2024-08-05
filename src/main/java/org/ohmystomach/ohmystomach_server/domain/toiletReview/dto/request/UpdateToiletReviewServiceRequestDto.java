package org.ohmystomach.ohmystomach_server.domain.toiletReview.dto.request;

public record UpdateToiletReviewServiceRequestDto(
        String content,  // 후기 내용
        int rating  // 별점 (1~5)
) {
}
