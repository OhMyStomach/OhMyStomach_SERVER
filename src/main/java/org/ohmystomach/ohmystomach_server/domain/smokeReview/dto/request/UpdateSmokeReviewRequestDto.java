package org.ohmystomach.ohmystomach_server.domain.smokeReview.dto.request;

public record UpdateSmokeReviewRequestDto(
        String content,  // 후기 내용
        int rating  // 별점 (1~5)
) {
    public UpdateSmokeReviewServiceRequestDto toServiceRequest() {
        return new UpdateSmokeReviewServiceRequestDto(content, rating);
    }
}
