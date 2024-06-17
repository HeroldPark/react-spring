package shane.blog.domain.feedback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shane.blog.domain.common.dto.SearchDto;
import shane.blog.domain.common.paging.PagingResponse;
import shane.blog.domain.post.PostService;
import shane.blog.entity.Member;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final PostService postService;
    private final FeedbackService feedbackService;

    // @GetMapping("/list")
    // public ResponseEntity<PagingResponse<PostResponse>>
    // feedbackList(@ModelAttribute("params") final SearchDto params) {

    // log.debug("FeedbackController.feedbackList: 시작");

    // PagingResponse<PostResponse> postList = postService.findList(params);
    // return ResponseEntity.status(HttpStatus.OK).body(postList);
    // }

    @PostMapping("/list.do")
    public ResponseEntity<PagingResponse<FeedbackResponse>> feedbackList(
            @ModelAttribute("params") final SearchDto params,
            @RequestBody FeedbackRequest feedbackRequest) {

        log.debug("FeedbackController.feedbackList: 시작");

        params.setPostId(feedbackRequest.getPostId());
        PagingResponse<FeedbackResponse> feedbackList = feedbackService.finAll(params);
        return ResponseEntity.status(HttpStatus.OK).body(feedbackList);
    }

    @PostMapping("/write.do")
    public ResponseEntity<Long> write(
            @AuthenticationPrincipal Member member,
            // @PathVariable Long id,
            @RequestBody FeedbackRequest feedbackRequest) {

        Long id = feedbackService.write(feedbackRequest, member);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PatchMapping("/update.do")
    public ResponseEntity<FeedbackResponse> update(@RequestBody FeedbackRequest feedbackRequest) {
        log.info("FeedbackController.update() : {}", feedbackRequest);

        FeedbackResponse feedbackResponse = feedbackService.update(feedbackRequest);
        return ResponseEntity.status(HttpStatus.OK).body(feedbackResponse);
    }

    @PostMapping("/delete.do")
    public ResponseEntity<Long> delete(@RequestBody FeedbackRequest feedbackRequest) {
        log.info("FeedbackController.delete() : {}", feedbackRequest);

        Long id = feedbackRequest.getId();
        feedbackService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 답글 상세 페이지
    @GetMapping("/detail.do/{id}")
    public ResponseEntity<List<FeedbackResponse>> feedbackDetail(@PathVariable("id") Long postId) {
        log.info("FeedbackController.feedbackDetail() : {}" + postId);

        List<FeedbackResponse> feedbackResponse = feedbackService.detail(postId);
        return ResponseEntity.status(HttpStatus.OK).body(feedbackResponse);
    }

}
