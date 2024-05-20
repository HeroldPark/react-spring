package shane.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import shane.blog.service.LoginService;

@Slf4j
@RestController
@RequestMapping(value = "/login/oauth2", produces = "application/json")
public class LoginController {

    LoginService  loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // @PostMapping("/code/{registrationId}")
    // public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
    //     loginService.socialLogin(code, registrationId);
    // }

    // @GetMapping(value = "/code/{registrationId}")
    // public String googleLogin(@RequestParam String code, @PathVariable String registrationId) {
    //     log.debug("LoginController.googleLogin() 시작");

    //     System.out.println("code = " + code);
    //     System.out.println("registrationId = " + registrationId);

    //     // loginService.socialLogin(code, registrationId);

    //     return "redirect:/login/oauth2/authorize/" + registrationId + "?code=" + code;
    // }

    // @GetMapping("/code/{registrationId}")
    // public @ResponseBody String testOauthLogin( Authentication authentication, @AuthenticationPrincipal OAuth2User oauth) {
    //     System.out.println("=================/test/oauth/login");

    //     OAuth2User oauth2user = (OAuth2User) authentication.getPrincipal();
    //     System.out.println("oauth2user: "+oauth2user.getAttributes());
    //     System.out.println("oauth: "+oauth.getAttributes());

    //     return "authentication";
    // }

    @RequestMapping(value = "/error", method = { RequestMethod.GET, RequestMethod.POST })
    public String error(HttpServletRequest request) {
        log.debug("LoginController.error Processing. \t {}", request);

        return "error";
    }
}