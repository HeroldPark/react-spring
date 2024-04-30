package shane.blog.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shane.blog.common.BaseTimeEntity;

import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Board extends BaseTimeEntity {

    // 이런 방법으로 ID를 생성하면 DB에서 board_seq table을 생성해야 한다.
    // 비추천
    @Id
    // @GeneratedValue
    // @Column(name = "BOARD_ID")
    // private Long id; // 'react-spring.board_seq' is not a SEQUENCE 오류 발생
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_id;

    @Column(nullable = false)
    private String title;

    private String content;

    @Column(name = "VIEW_COUNT")
    private int viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    public Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    public List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    public List<FileEntity> files = new ArrayList<>();

    @Builder
    public Board(Long board_id, String title, String content, int viewCount, Member member) {
        this.board_id = board_id;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.member = member;
    }

    //== 조회수 증가 ==//
    public void upViewCount() {
        this.viewCount++;
    }

    //== 수정 Dirty Checking ==//
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //== Member & Board 연관관계 편의 메소드 ==//
    public void setMappingMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }
}
