// package shane.blog.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import shane.blog.entity.Member;
// import shane.blog.entity.PrincipalDetails;
// import shane.blog.repository.MemberRepository;

// @Service
// public class PrincipalDetailsService implements UserDetailsService {

//     @Autowired
//     private MemberRepository memberRepository;
    
//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         Member memberEntity = memberRepository.findByUsername(username);
//         if (memberEntity != null) {
//             return new PrincipalDetails(memberEntity);
//         }
//         return null;
//     }
// }