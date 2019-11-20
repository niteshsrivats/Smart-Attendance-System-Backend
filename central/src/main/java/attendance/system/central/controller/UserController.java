//package attendance.system.central.controller;
//
//import attendance.system.central.models.entities.User;
//import attendance.system.central.models.payload.JwtAuthenticationResponse;
//import attendance.system.central.models.payload.LoginRequest;
//import attendance.system.central.security.CurrentUser;
//import attendance.system.central.security.JwtTokenProvider;
//import attendance.system.central.security.UserPrincipal;
//import attendance.system.central.service.UserService;
//import org.hibernate.validator.constraints.Currency;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import java.util.List;
//
//import static attendance.system.central.named.Endpoints.Users;
//
///**
// * @author Nitesh (niteshsrivats.k@gmail.com)
// */
//
//@RestController
//@RequestMapping(Users)
//@CrossOrigin
//public class UserController {
//
//    private final UserService userService;
//    private final AuthenticationManager authenticationManager;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    public UserController(UserService userService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
//        this.userService = userService;
//        this.authenticationManager = authenticationManager;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @GetMapping
//    public List<User> getUsers() {
//        return userService.getUsers();
//    }
//
//    @GetMapping(path = "{id}")
//    public User getUserById(@PathVariable @NotBlank String id) {
//        return userService.getUserById(id);
//    }
//
//    @PostMapping
//    public User registerUser(@RequestBody @Valid @NotNull User user) {
//        System.out.println(user);
//        System.out.println(user.getId());
//        System.out.println(user.getPassword());
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userService.addUser(user);
//    }
//
//    @PostMapping(path = "signin")
//    public JwtAuthenticationResponse authenticateUser(@RequestBody @Valid @NotNull LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getId(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String jwt = jwtTokenProvider.generateToken(authentication);
//        return new JwtAuthenticationResponse(jwt);
//    }
//
////    @DeleteMapping(path = Id)
////    public void deleteUserById(@PathVariable @NotBlank String id) {
////        userService.deleteUserById(id);
////    }
//}
