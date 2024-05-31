package shane.blog.domain.file;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileDownloadRes {
    
    private String filename;
    private String fileType;
    private byte[] content;

}
