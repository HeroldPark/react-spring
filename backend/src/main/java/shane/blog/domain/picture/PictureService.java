package shane.blog.domain.picture;

import shane.blog.domain.common.dto.SearchDto;
import shane.blog.domain.common.paging.Pagination;
import shane.blog.domain.common.paging.PagingResponse;
import shane.blog.domain.commoncode.CodeService;
import shane.blog.domain.commoncode.CommonResponse;
import shane.blog.utils.CustomUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class PictureService {

    @Autowired
    CodeService codeService;

    private final PictureMapper pictureMapper;

    private String uploadPath = "C:\\upload\\files\\";

    /**
     * 게시글 저장
     * @param params - 게시글 정보
     * @return Generated PK
     */
    @Transactional
    public Long savePicture(final PictureRequest params) {
        pictureMapper.save(params);
        return params.getId();
    }

    /**
     * 게시글 상세정보 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    public PictureResponse findPictureById(final Long id) {
        return pictureMapper.findById(id);
    }

    /**
     * 이미지 파일 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    public String findFilePath(PictureRequest params) {
        CustomUtil customUtil = new CustomUtil();
        String osArch = customUtil.getOperatingSystem();
        List<CommonResponse> codeList = codeService.getSubCodeList("FILE_CONFIG", "PUB", "Y", "N");
        for (int i = 0; i < codeList.size(); i++) {
            if (osArch.equals("linux")) {
                if (codeList.get(i).getCommonCode().equals("FILE_UPLOAD_PATH")) {
                    uploadPath = codeList.get(i).getReferId1();
                }
            }
            if (osArch.equals("windows")) {
                if (codeList.get(i).getCommonCode().equals("FILE_UPLOAD_DEFAULT")) {
                    uploadPath = codeList.get(i).getReferId1();
                }
            }
        }

        if(params.getFilePath().charAt(0) == '/') { // "/picture/john-lee.jpg"에서 첫번째 '/' 삭제
            String fileString = params.getFilePath().replaceFirst("/", "");
            params.setFilePath(fileString);
        }
        Path filePath = Paths.get(uploadPath).resolve(params.getFilePath());
        String filePathString = filePath.toString();

        return filePathString;
    }

    /**
     * 게시글 수정
     * @param params - 게시글 정보
     * @return PK
     */
    @Transactional
    public Long updatePicture(final PictureRequest params) {
        pictureMapper.update(params);
        return params.getId();
    }

    /**
     * 게시글 삭제
     * @param id - PK
     * @return PK
     */
    public Long deletePicture(final Long id) {
        pictureMapper.deleteById(id);
        return id;
    }

    /**
     * 게시글 리스트 조회
     * @param params - search conditions
     * @return list & pagination information
     */
    public PagingResponse<PictureResponse> findList(final SearchDto params) {
        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = pictureMapper.count(params);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);
        pagination.setPageSize(params.getPageSize());

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<PictureResponse> list = pictureMapper.findList(params);
        return new PagingResponse<>(list, pagination);
    }

    public PictureResponse detail(Long id) {
        PictureResponse pictureResponse = pictureMapper.findById(id);

        PictureRequest pictureRequest = new PictureRequest();
        pictureRequest.setId(id);
        // pictureRequest.setViewCnt(pictureResponse.getViewCnt() + 1);
        // pictureMapper.update(pictureRequest);

        return pictureResponse;
    }

}
