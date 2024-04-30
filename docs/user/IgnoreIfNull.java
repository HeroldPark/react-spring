package shane.blog.domain.user;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface IgnoreIfNull {
    // Marker annotation

}