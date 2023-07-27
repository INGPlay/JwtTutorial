package security.jwt.tutorial.controller;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import security.jwt.tutorial.domain.UserDto;
import security.jwt.tutorial.entity.User;
import security.jwt.tutorial.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.signup(userDto), HttpStatus.OK);
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<User> getMyUserInfo(){
        return new ResponseEntity<>(userService.getMyUserWithAuthorities().get(), HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> getUserInfo(@PathVariable String username){
        return new ResponseEntity<>(userService.getUserWithAuthorities(username).get(), HttpStatus.OK);
    }
}
