package shane.blog.domain.city;

import org.junit.jupiter.api.Test;
// import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import shane.blog.domain.city.City;

import static org.assertj.core.api.Assertions.assertThat;

// @RunWith(SpringRunner.class)
@SpringBootTest
@MybatisTest
@Import(CityDao.class)
public class CityDaoTest {

    @Autowired
    private CityDao cityDao;

    @Test
    public void selectCityByIdTest() {
        City city = cityDao.selectCityById(1);
        assertThat(city.getName()).isEqualTo("San Francisco");
        assertThat(city.getState()).isEqualTo("CA");
        assertThat(city.getCountry()).isEqualTo("US");
    }

}