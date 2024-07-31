package org.ohmystomach.ohmystomach_server.domain.smokeReview.dto.request;

import org.ohmystomach.ohmystomach_server.domain.smoke.domain.Smoke;
import org.ohmystomach.ohmystomach_server.domain.smokeReview.domain.SmokeReview;
import org.ohmystomach.ohmystomach_server.domain.toilet.domain.Toilet;

public record CreateSmokeReviewServiceRequestDto(
        String username,  // 작성자 이름
        String content,  // 후기 내용
        int rating,  // 별점 (1~5)
        Long smokeId
) {
    public SmokeReview toEntity(Smoke smoke) {
        return SmokeReview.builder()
                .username(username)
                .content(content)
                .rating(rating)
                .smoke(smoke)
                .build();
    }
}
