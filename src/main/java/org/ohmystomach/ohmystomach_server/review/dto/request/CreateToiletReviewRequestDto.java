package org.ohmystomach.ohmystomach_server.review.dto.request;

public record CreateToiletReviewRequestDto(
        String username,  // 작성자 이름
        String content,  // 후기 내용
        int rating  // 별점 (1~5)
) {
    public CreateToiletReviewServiceRequestDto toServiceRequest() {
        return new CreateToiletReviewServiceRequestDto(username, content, rating);
    }
}
