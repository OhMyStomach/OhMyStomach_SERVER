package org.ohmystomach.ohmystomach_server.review.dto.request;

import org.ohmystomach.ohmystomach_server.review.domain.ToiletReview;

public record CreateToiletReviewServiceRequestDto(
        String username,  // 작성자 이름
        String content,  // 후기 내용
        int rating  // 별점 (1~5)
) {
    public ToiletReview toEntity() {
        return ToiletReview.builder()
                .username(username)
                .content(content)
                .rating(rating)
                .build();
//        return (username, content, rating);
    }
}
