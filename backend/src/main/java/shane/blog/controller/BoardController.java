package shane.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shane.blog.dto.request.board.BoardUpdateDto;
import shane.blog.dto.request.board.BoardWriteDto;
import shane.blog.dto.request.board.SearchData;
import shane.blog.dto.response.board.ResBoardDetailsDto;
import shane.blog.dto.response.board.ResBoardListDto;
import shane.blog.dto.response.board.ResBoardWriteDto;
import shane.blog.entity.Member;
import shane.blog.service.BoardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 페이징 목록
    @GetMapping("/list")
    public ResponseEntity<Page<ResBoardListDto>> boardList(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestHeader("Authorization") String authorizationHeader) {

        // JWT 인증 토큰 확인을 위해서 추가
        log.debug("Authorization Header: " + authorizationHeader);

        Page<ResBoardListDto> listDTO = boardService.getAllBoards(pageable);

        log.debug("boardList: " + listDTO.toString());

        return ResponseEntity.status(HttpStatus.OK).body(listDTO);
    }

    // 페이징 검색 , Get 요청 @RequestBody 사용할 수 없음
    @GetMapping("/search")
    public ResponseEntity<Page<ResBoardListDto>> search(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String writerName) {
        SearchData searchData = SearchData.createdSearchData(title, content, writerName);
        Page<ResBoardListDto> searchBoard = boardService.search(searchData, pageable);
        return  ResponseEntity.status(HttpStatus.OK).body(searchBoard);
    }

    @PostMapping("/write")
    public ResponseEntity<ResBoardWriteDto> write(
            @RequestBody BoardWriteDto boardDTO,
            @AuthenticationPrincipal Member member) {
        Thread currentThread = Thread.currentThread();
        log.info("현재 실행 중인 스레드: " + currentThread.getName());
        ResBoardWriteDto saveBoardDTO = boardService.write(boardDTO, member);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveBoardDTO);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<ResBoardDetailsDto> detail(@PathVariable("boardId") Long boardId) {
        log.debug("BoardController.detail: 시작");

        ResBoardDetailsDto findBoardDTO = boardService.detail(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(findBoardDTO);
    }

    // 상세보기 -> 수정
    @PatchMapping("/{boardId}/update")
    public ResponseEntity<ResBoardDetailsDto> update(
            @PathVariable Long boardId,
            @RequestBody BoardUpdateDto boardDTO) {
        ResBoardDetailsDto updateBoardDTO = boardService.update(boardId, boardDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateBoardDTO);
    }

    // 상세보기 -> 삭제
    @DeleteMapping("/{boardId}/delete")
    public ResponseEntity<Long> delete(@PathVariable Long boardId) {
        boardService.delete(boardId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
