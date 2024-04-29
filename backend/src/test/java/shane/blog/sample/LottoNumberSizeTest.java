package shane.blog.sample;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("로또 번호 갯수 테스트")
// @Test
public class LottoNumberSizeTest {
    // given
    final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
    final int price = 1000;

    // when
    final List<Integer> lottoNumber = lottoNumberGenerator.generate(price);

    // then
    // assertThat(lotto.size()).isEqualTo(6);
}
