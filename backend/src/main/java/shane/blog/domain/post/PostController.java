package shane.blog.domain.post;

import shane.blog.domain.common.dto.MessageDto;
import shane.blog.domain.common.dto.SearchDto;
import shane.blog.domain.common.file.FileUtils;
import shane.blog.domain.common.paging.PagingResponse;
import shane.blog.domain.file.FileRequest;
import shane.blog.domain.file.FileResponse;
import shane.blog.domain.member.MemberResponse;
import shane.blog.dto.request.board.BoardUpdateDto;
import shane.blog.dto.request.board.BoardWriteDto;
import shane.blog.dto.request.board.SearchData;
import shane.blog.dto.response.board.ResBoardDetailsDto;
import shane.blog.dto.response.board.ResBoardListDto;
import shane.blog.dto.response.board.ResBoardWriteDto;
import shane.blog.entity.Member;
import shane.blog.service.BoardService;
import shane.blog.domain.file.FileApiService;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final PostService postService;
    private final FileApiService fileService;
    private final FileUtils fileUtils;

    // 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }

    // 쿼리 스트링 파라미터를 Map에 담아 반환
    private Map<String, Object> queryParamsToMap(final SearchDto queryParams) {
        Map<String, Object> data = new HashMap<>();
        data.put("page", queryParams.getPage());
        data.put("recordSize", queryParams.getRecordSize());
        data.put("pageSize", queryParams.getPageSize());
        data.put("keyword", queryParams.getKeyword());
        data.put("searchType", queryParams.getSearchType());
        return data;
    }

    // 게시글 리스트 페이지
    // @GetMapping("/list")
    // public String postList(@ModelAttribute("params") final SearchDto params, Model model) {
    //     PagingResponse<PostResponse> response = postService.findAllPost(params);
    //     model.addAttribute("response", response);
    //     return "post/list";
    // }
    @RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<PagingResponse<PostResponse>> postList(@ModelAttribute("params") final SearchDto params) {

        logger.debug("/list 시작. \t {}", new Date());

        // 1. 회원 정보 조회
        PagingResponse<PostResponse> response = postService.findList(params);
        logger.debug("/list 종료. \t {}", response);

        // 2. 조회 결과 리턴
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 게시글 상세 페이지
    @GetMapping("/{id}")
    public String postDetail(@RequestParam final Long id, Model model) {
        PostResponse post = postService.findPostById(id);
        model.addAttribute("post", post);
        return "post/view";
    }

    // 신규 게시글 생성
    @PostMapping("/save")
    public String savePost(final PostRequest params, Model model) {
        Long id = postService.savePost(params);
        List<FileRequest> files = fileUtils.uploadFiles(params.getFiles());
        fileService.saveFiles(id, files);
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    // 게시글 작성 페이지
    @PostMapping("/write")
    public ResponseEntity<?> postWrite(
            @RequestBody PostRequest postRequest,
            @AuthenticationPrincipal Member member) {
        Thread currentThread = Thread.currentThread();
        log.info("현재 실행 중인 스레드: " + currentThread.getName());
        Long id = postService.write(postRequest, member);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    // @PostMapping("/write")
    // public String postWrite(@RequestParam(value = "id", required = false) final Long id, Model model) {
    //     if (id != null) {
    //         PostResponse post = postService.findPostById(id);
    //         model.addAttribute("post", post);
    //     }
    //     // return "post/write";
    //     MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
    //     return showMessageAndRedirect(message, model);
    // }

    // 기존 게시글 수정
    @PostMapping("/update")
    public String updatePost(final PostRequest params, final SearchDto queryParams, Model model) {

        // 1. 게시글 정보 수정
        postService.updatePost(params);

        // 2. 파일 업로드 (to disk)
        List<FileRequest> uploadFiles = fileUtils.uploadFiles(params.getFiles());

        // 3. 파일 정보 저장 (to database)
        fileService.saveFiles(params.getId(), uploadFiles);

        // 4. 삭제할 파일 정보 조회 (from database)
        List<FileResponse> deleteFiles = fileService.findAllFileByIds(params.getRemoveFileIds());

        // 5. 파일 삭제 (from disk)
        fileUtils.deleteFiles(deleteFiles);

        // 6. 파일 삭제 (from database)
        fileService.deleteAllFileByIds(params.getRemoveFileIds());

        MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/post/list.do", RequestMethod.GET, queryParamsToMap(queryParams));
        return showMessageAndRedirect(message, model);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<ResBoardDetailsDto> detail(@PathVariable("boardId") Long boardId) {
    //     ResBoardDetailsDto findBoardDTO = postService.detail(boardId);
    //     return ResponseEntity.status(HttpStatus.OK).body(findBoardDTO);
    // }

    // // 상세보기 -> 수정
    // @PatchMapping("/{id}/update")
    // public ResponseEntity<ResBoardDetailsDto> update(
    //         @PathVariable Long boardId,
    //         @RequestBody BoardUpdateDto boardDTO) {
    //     ResBoardDetailsDto updateBoardDTO = postService.update(boardId, boardDTO);
    //     return ResponseEntity.status(HttpStatus.OK).body(updateBoardDTO);
    // }

    // // 상세보기 -> 삭제
    // @DeleteMapping("/{id}/delete")
    // public ResponseEntity<Long> delete(@PathVariable Long boardId) {
    //     postService.delete(boardId);
    //     return ResponseEntity.status(HttpStatus.OK).build();
    // }

    // 게시글 삭제
    @PostMapping("/delete")
    public String deletePost(@RequestParam final Long id, final SearchDto queryParams, Model model) {
        postService.deletePost(id);
        MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/post/list.do", RequestMethod.GET, queryParamsToMap(queryParams));
        return showMessageAndRedirect(message, model);
    }

}
