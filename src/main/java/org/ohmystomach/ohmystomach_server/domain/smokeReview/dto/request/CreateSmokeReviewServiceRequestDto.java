package org.ohmystomach.ohmystomach_server.domain.smokeReview.dto.request;

import org.ohmystomach.ohmystomach_server.domain.smoke.domain.Smoke;
import org.ohmystomach.ohmystomach_server.domain.smokeReview.domain.SmokeReview;
import org.ohmystomach.ohmystomach_server.domain.toilet.domain.Toilet;
import org.ohmystomach.ohmystomach_server.domain.user.domain.User;

public record CreateSmokeReviewServiceRequestDto(
        String uuid,  // 작성자 아이디
        String content,  // 후기 내용
        int rating,  // 별점 (1~5)
        Long smokeId
) {
    public SmokeReview toEntity(User user, Smoke smoke) {
        return SmokeReview.builder()
                .user(user)
                .content(content)
                .rating(rating)
                .smoke(smoke)
                .build();
    }
}
