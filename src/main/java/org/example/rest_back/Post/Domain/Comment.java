package org.example.rest_back.Post.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

// DB 테이블과 매칭될 클래스
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
// @NoArgsConstructor : 파라미터가 없는 디폴트 생성자를 자동으로 생성
// @AllArgsConstructor : 모든 필드 값을 파라미터로 받는 생성자 자동으로 생성
@Builder
// 어떤 파라미터가 전달되었는지 정확하게 파악하기 위해, 필요한 값만 넣기 위해 builder 패턴 사용
// 사용 방법
// Post post = new post.builder()
//              .Title("안녕하세요")
//              .Content("저는 홍길동입니다")
//              .build();
public class Comment {

    // 기본키
    // 각 댓글의 고유한 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long comment_id;

    // 좋아요수
    private int likes;

    // 댓글 내용
    private String content;

    // 댓글 생성 시간
    // @CreationTimestamp : insert 쿼리 발생 시, 즉 데이터가 생성된 시점에 대한 생성 시간 저장
    @CreationTimestamp
    private LocalDateTime comment_Create_Time;

    // 댓글 수정 시간
    // @UpdateTimestamp : update 쿼리 발생 시, 즉 수정이 발생할 때마다 마지막 수정 시간을 업데이트
    @UpdateTimestamp
    private LocalDateTime comment_Update_Time;

    //@ManyToOne
    //@JoinColumn(name = "user_id")
    //private User user;

    // 여러개의 Comment가 하나의 Post와 연결될 수 있음 => 다대일 관계
    // @JoinColumn : 외래키 지정
    // post_id는 Comment 테이블에서 Post 테이블을 참조하는 외래키 컬럼
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
