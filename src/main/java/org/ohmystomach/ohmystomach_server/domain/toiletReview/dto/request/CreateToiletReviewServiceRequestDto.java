package org.ohmystomach.ohmystomach_server.domain.toiletReview.dto.request;

import org.ohmystomach.ohmystomach_server.domain.toiletReview.domain.ToiletReview;
import org.ohmystomach.ohmystomach_server.domain.toilet.domain.Toilet;

public record CreateToiletReviewServiceRequestDto(
        String username,  // 작성자 이름
        String content,  // 후기 내용
        int rating,  // 별점 (1~5)
        Long toiletId
) {
    public ToiletReview toEntity(Toilet toilet) {
        return ToiletReview.builder()
                .username(username)
                .content(content)
                .rating(rating)
                .toilet(toilet)
                .build();
    }
}
