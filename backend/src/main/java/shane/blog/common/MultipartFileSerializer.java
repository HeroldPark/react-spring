// package shane.blog.common;

// import com.fasterxml.jackson.core.JsonGenerator;
// import com.fasterxml.jackson.databind.JsonSerializer;
// import com.fasterxml.jackson.databind.SerializerProvider;
// import org.springframework.boot.jackson.JsonComponent;
// import org.springframework.web.multipart.MultipartFile;

// import java.io.IOException;

// @JsonComponent
// public class MultipartFileSerializer extends JsonSerializer<MultipartFile> {

//     @Override
//     public void serialize(MultipartFile value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//         gen.writeStartObject();
//         gen.writeStringField("originalFilename", value.getOriginalFilename());
//         // serialize other fields if needed
//         gen.writeEndObject();
//     }
// }
