package shane.blog.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shane.blog.common.BaseTimeEntity;
import shane.blog.common.Role;
import shane.blog.dto.request.member.MemberUpdateDto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_member")
@NoArgsConstructor
public class Member extends BaseTimeEntity implements UserDetails {

    @Id
    // @GeneratedValue
    // @Column(name = "MEMBER_ID")
    // private Long id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;

    // 이메일로 로그인함
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Enumerated(EnumType.STRING)
    private Role roles;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false)
    private String provider;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Comment> comments = new ArrayList<>();

    //== 생성자 Builder ==//
    @Builder
    public Member(String email, String password, String username, Role roles, String picture, String provider) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.roles = roles;
        this.picture = picture;
        this.provider = provider;
    }

    //== update ==//
    public void update(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public void update2(String password, MemberUpdateDto dto) {
        this.password = password;
        this.email = dto.getEmail();
        this.username = dto.getUsername();
        this.roles = dto.getRoles();
    }

    // 필드에 직접 접근하는 메서드 추가
    public Long getMemberld() {
        return member_id;
    }

    //========== UserDetails implements ==========//
    /**
     * Token을 고유한 Email 값으로 생성합니다
     * return은 반드시 email로 설정해야 합니다.(UserDetails에서 username은 email로 설정했기 때문에)
     * JwtTokenUtil.generateToken()에서 email을 가져와 토큰을 생성합니다.
     * @return email;
     */
    @Override
    public String getUsername() {
        return email;
    }

    // 필드에 직접 접근하는 메서드 추가
    public String getUsernameField() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add( new SimpleGrantedAuthority("ROLE_" + this.roles.name()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isPresent() {
        return true;
    }
}
