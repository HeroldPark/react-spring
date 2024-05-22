package shane.blog.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import shane.blog.common.exception.ResourceNotFoundException;
import shane.blog.entity.Member;
import shane.blog.repository.MemberRepository;

/**
 * DaoAuthenticationProvider 구현
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepo;

    @Override
    public UserDetails loadUserByUsername(String param) throws UsernameNotFoundException {
        if(param.contains("@")) {
            return this.memberRepo.findByEmail(param).orElseThrow(
                    () -> new ResourceNotFoundException("Member", "Member Email : ", param));
        }
        else {
            return this.memberRepo.findByUsername(param).orElseThrow(
                    () -> new ResourceNotFoundException("Member", "Member Username : ", param));
        }
    }
}
