package org.ohmystomach.ohmystomach_server.smokeReview.dto.request;

import org.ohmystomach.ohmystomach_server.smokeReview.domain.SmokeReview;
import org.ohmystomach.ohmystomach_server.toilet.domain.Toilet;

public record CreateSmokeReviewServiceRequestDto(
        String username,  // 작성자 이름
        String content,  // 후기 내용
        int rating,  // 별점 (1~5)
        Long toiletId
) {
    public SmokeReview toEntity(Toilet toilet) {
        return SmokeReview.builder()
                .username(username)
                .content(content)
                .rating(rating)
                .toilet(toilet)
                .build();
    }
}
