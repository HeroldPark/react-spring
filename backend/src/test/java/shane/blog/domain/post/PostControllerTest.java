package shane.blog.domain.post;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import shane.blog.domain.common.dto.SearchDto;

// PostController.java 우클릭 -> Java:Create/Open Test class
@DisplayName("Post Controller 테스트")
@SpringBootTest
public class PostControllerTest {

	private PostController postController;

	@Before
	public void setup() {
		this.postController = new PostController();
	}

	@Test
	public void shouldOpenPostWrite() {
		// TODO: initialize args
		Long id;
		Model model;

		String actualValue = postController.openPostWrite(id, model);

		// TODO: assert scenario
	}

	@Test
	public void shouldOpenPostList() {
		// TODO: initialize args
		SearchDto params;
		Model model;

		String actualValue = postController.openPostList(params, model);

		// TODO: assert scenario
	}

	@Test
	public void shouldOpenPostView() {
		// TODO: initialize args
		Long id;
		Model model;

		String actualValue = postController.openPostView(id, model);

		// TODO: assert scenario
	}

	@Test
	public void shouldSavePost() {
		// TODO: initialize args
		PostRequest params;
		Model model;

		String actualValue = postController.savePost(params, model);

		// TODO: assert scenario
	}

	@Test
	public void shouldUpdatePost() {
		// TODO: initialize args
		PostRequest params;
		SearchDto queryParams;
		Model model;

		String actualValue = postController.updatePost(params, queryParams, model);

		// TODO: assert scenario
	}

	@Test
	public void shouldDeletePost() {
		// TODO: initialize args
		Long id;
		SearchDto queryParams;
		Model model;

		String actualValue = postController.deletePost(id, queryParams, model);

		// TODO: assert scenario
	}
}
