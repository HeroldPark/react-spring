package shane.blog.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shane.blog.common.BaseTimeEntity;

@Entity
@Table(name = "FILE")
@Getter
@NoArgsConstructor
public class FileEntity extends BaseTimeEntity {

    @Id
    // @Column(name = "FILE_ID")
    // private Long id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long file_id;

    @Column(name = "ORIGIN_FILE_NAME")
    private String originFileName;

    @Column(name = "FILE_TYPE")
    private String fileType;

    @Column(name = "FILE_PATH")
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    public Board board;

    @Builder
    public FileEntity(Long file_id, String originFileName, String filePath, String fileType) {
        this.file_id = file_id;
        this.originFileName = originFileName;
        this.filePath = filePath;
        this.fileType = fileType;
    }

    public void setMappingBoard(Board board) {
        this.board = board;
    }
}
