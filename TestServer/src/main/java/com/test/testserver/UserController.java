package com.test.testserver;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @PostMapping("/")
    public Result<User> regist(@RequestBody User newUser) {
        newUser = userRepository.save(newUser);
        System.out.println("newUser" + newUser.toString());
        return new Result(newUser);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user, HttpSession session, HttpServletResponse response) {
        try {
            User loginUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).get();
            if (loginUser != null) {
                session.setAttribute("loginUserId", loginUser.getId());
                System.out.println("Login User: " + loginUser);
                return loginUser;
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return null;
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }

    @GetMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @GetMapping("/check")
    public void isLoggedIn(HttpSession session, HttpServletResponse response) {
        if (session.getAttribute("loginUserId") == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
