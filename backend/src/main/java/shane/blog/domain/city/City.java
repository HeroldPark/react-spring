package shane.blog.domain.city;

import java.io.Serializable;

import lombok.Data;

@Data
public class City implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String name;

  private String state;

  private String country;

  @Override
  public String toString() {
    return getId() + "," + getName() + "," + getState() + "," + getCountry();
  }

}