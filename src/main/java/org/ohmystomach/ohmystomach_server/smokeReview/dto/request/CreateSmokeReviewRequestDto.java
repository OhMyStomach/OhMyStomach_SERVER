package org.ohmystomach.ohmystomach_server.smokeReview.dto.request;

public record CreateSmokeReviewRequestDto(
        String username,  // 작성자 이름
        String content,  // 후기 내용
        int rating,  // 별점 (1~5)
        Long toiletId
) {
    public CreateSmokeReviewServiceRequestDto toServiceRequest() {
        return new CreateSmokeReviewServiceRequestDto(username, content, rating, toiletId);
    }
}
