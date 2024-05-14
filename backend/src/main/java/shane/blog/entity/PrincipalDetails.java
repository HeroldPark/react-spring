// package shane.blog.entity;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Table;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;
// import shane.blog.common.BaseTimeEntity;
// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.List;

// @Getter
// @Setter
// @Entity
// @Table(name = "tb_member")
// @NoArgsConstructor
// public class PrincipalDetails extends BaseTimeEntity implements UserDetails {
    
//     private Member member;

//     public PrincipalDetails(Member member) {
//         this.member = member;
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         Collection<GrantedAuthority> collect = new ArrayList<>();
//         collect.add(new GrantedAuthority() {
//             @Override
//             public String getAuthority() {
//                 return member.getUsername();
//             }
//         });
//         return collect;
//     }

//     @Override
//     public String getPassword() {
//         return member.getPassword();
//     }

//     @Override
//     public String getUsername() {
//         return member.getUsername();
//     }
    
//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }
    
//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }
//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }
//     @Override
//     public boolean isEnabled() {
//         return true;
//     }

// }