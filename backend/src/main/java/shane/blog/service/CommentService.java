package shane.blog.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import shane.blog.common.exception.ResourceNotFoundException;
import shane.blog.dto.request.comment.CommentDto;
import shane.blog.dto.response.comment.ResCommentDto;
import shane.blog.entity.Board;
import shane.blog.entity.Comment;
import shane.blog.entity.Member;
import shane.blog.repository.BoardRepository;
import shane.blog.repository.CommentRepository;
import shane.blog.repository.MemberRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Page<ResCommentDto> getAllComments(Pageable pageable, Long boardId) {
        Page<Comment> comments = commentRepository.findAllWithMemberAndBoard(pageable, boardId);
        List<ResCommentDto> commentList = comments.getContent().stream()
                .map(ResCommentDto::fromEntity)
                .collect(Collectors.toList());
        return new PageImpl<>(commentList, pageable, comments.getTotalElements());
    }

    public ResCommentDto write(Long boardId, Member member, CommentDto writeDto) {
        // board 정보 검색
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new ResourceNotFoundException("Board", "Board id", String.valueOf(boardId))
        );
        // member(댓글 작성자) 정보 검색
        Member commentWriter = memberRepository.findById(member.getMember_id()).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member id", String.valueOf(member.getMember_id()))
        );
        // Entity 변환, 연관관계 매핑
        Comment comment = CommentDto.ofEntity(writeDto);
        comment.setBoard(board);
        comment.setMember(commentWriter);

        Comment saveComment = commentRepository.save(comment);
        return ResCommentDto.fromEntity(saveComment);
    }

    public ResCommentDto update(Long commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findByIdWithMemberAndBoard(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "Comment Id", String.valueOf(commentId))
        );
        comment.update(commentDto.getContent());
        return ResCommentDto.fromEntity(comment);
    }

    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
