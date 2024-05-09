package shane.blog.domain.city;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import shane.blog.domain.city.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

// @RunWith(SpringRunner.class)
@SpringBootTest
public class CityMapperTest {

    @Autowired
    private CityMapper cityMapper;

    @Test
    public void findByStateTest() {
        City city = cityMapper.findByState("CA");

        assertThat(city.getName()).isEqualTo("San Francisco");
        assertThat(city.getState()).isEqualTo("CA");
        assertThat(city.getCountry()).isEqualTo("US");
    }

}