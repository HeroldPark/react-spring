package shane.blog.domain.feedback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shane.blog.domain.common.dto.SearchDto;
import shane.blog.domain.common.paging.PagingResponse;
import shane.blog.domain.post.PostResponse;
import shane.blog.domain.post.PostService;
import shane.blog.entity.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/feedback/{id}/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final PostService postService;
    private final FeedbackService feedbackService;

    // @GetMapping("/list")
    // public ResponseEntity<PagingResponse<PostResponse>> feedbackList(@ModelAttribute("params") final SearchDto params) {

    //     log.debug("FeedbackController.feedbackList: 시작");
        
    //     PagingResponse<PostResponse> postList = postService.findList(params);
    //     return ResponseEntity.status(HttpStatus.OK).body(postList);
    // }

    @GetMapping("/list")
    public ResponseEntity<PagingResponse<FeedbackResponse>> feedbackList(@ModelAttribute("params") final SearchDto params) {

        log.debug("FeedbackController.feedbackList: 시작");
        
        PagingResponse<FeedbackResponse> feedbackList = feedbackService.findList(params);
        return ResponseEntity.status(HttpStatus.OK).body(feedbackList);
    }

    @PostMapping("/write")
    public ResponseEntity<FeedbackResponse> write(
            @AuthenticationPrincipal Member member,
            @PathVariable Long id,
            @RequestBody FeedbackRequest feedbackRequest) {

        // FeedbackResponse feedbackResponse = feedbackService.write(id, member, feedbackDto);
        FeedbackResponse feedbackResponse = new FeedbackResponse();
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackResponse);
    }

    @PatchMapping("/update/{feedbackId}")
    public ResponseEntity<FeedbackResponse> update(
            @PathVariable Long feedbackId,
            @RequestBody FeedbackRequest feedbackDto) {

        // FeedbackResponse updateFeedbackDTO = feedbackService.update(feedbackId, feedbackDto);
        FeedbackResponse updateFeedbackDTO = new FeedbackResponse();
        return ResponseEntity.status(HttpStatus.OK).body(updateFeedbackDTO);
    }

    @DeleteMapping("/delete/{feedbackId}")
    public ResponseEntity<Long> delete(@PathVariable Long feedbackId) {

        // feedbackService.delete(feedbackId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
