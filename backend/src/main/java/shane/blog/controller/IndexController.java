// package shane.blog.controller;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ResponseBody;

// import shane.blog.entity.PrincipalDetails;

// @Controller
// public class IndexController {

// 	@GetMapping("/test/login")
//     public @ResponseBody String testLogin(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails ) {
//         if (authentication != null) {
//             System.out.println("authentication: "+authentication);
//             PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
//             System.out.println("principalDetails: "+principal.getMember().getUsername());
//             System.out.println("userdetails: "+principalDetails.getMember());
//         } else {
//             System.out.println("no auth");
//         }
//         return "authentication";
//     }

// }