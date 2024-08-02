package org.ohmystomach.ohmystomach_server.domain.smokeReview.dto.request;

public record UpdateSmokeReviewRequestDto(
        String username,  // 작성자 이름
        String content,  // 후기 내용
        int rating  // 별점 (1~5)
) {
    public UpdateSmokeReviewServiceRequestDto toServiceRequest() {
        return new UpdateSmokeReviewServiceRequestDto(username, content, rating);
    }
}
