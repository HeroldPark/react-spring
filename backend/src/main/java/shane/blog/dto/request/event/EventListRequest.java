package shane.blog.dto.request.event;

import lombok.Data;

// Request DTO
@Data
public class EventListRequest {
    private String startTime;
    private String endTime;

    // getters and setters
}