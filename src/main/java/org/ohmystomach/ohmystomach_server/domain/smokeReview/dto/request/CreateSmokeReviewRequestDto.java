package org.ohmystomach.ohmystomach_server.domain.smokeReview.dto.request;

public record CreateSmokeReviewRequestDto(
        String token,  // 작성자 정보
        String content,  // 후기 내용
        int rating,  // 별점 (1~5)
        Long smokeId
) {
    public CreateSmokeReviewServiceRequestDto toServiceRequest(String uuid) {
        return new CreateSmokeReviewServiceRequestDto(uuid, content, rating, smokeId);
    }
}
