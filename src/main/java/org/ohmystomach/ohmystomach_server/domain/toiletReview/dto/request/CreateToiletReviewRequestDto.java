package org.ohmystomach.ohmystomach_server.domain.toiletReview.dto.request;

public record CreateToiletReviewRequestDto(
        String token,  // 작성자 정보
        String content,  // 후기 내용
        int rating,  // 별점 (1~5)
        Long toiletId
) {
    public CreateToiletReviewServiceRequestDto toServiceRequest(String uuid) {
        return new CreateToiletReviewServiceRequestDto(uuid, content, rating, toiletId);
    }
}
