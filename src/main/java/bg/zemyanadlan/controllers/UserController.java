package bg.zemyanadlan.controllers;

import bg.zemyanadlan.dtos.ChangePasswordRequest;
import bg.zemyanadlan.dtos.RegisterUserRequest;
import bg.zemyanadlan.dtos.UpdateUserRequest;
import bg.zemyanadlan.dtos.UserDto;
import bg.zemyanadlan.entities.Role;
import bg.zemyanadlan.mappers.UserMapper;
import bg.zemyanadlan.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserDto> getAllUsers(
            @RequestParam(required = false, defaultValue = "", name = "sort") String sort
    ) {
        return userRepository.findAll(Sort.by(sort))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal")
    @GetMapping ("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id ) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriBuilder) {
        if (userRepository.existsByEmail(request.getEmail())){
            return ResponseEntity.badRequest().body(
                    Map.of("email" , "Email is already in use.")
            );
        }

        var user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        var userDto = userMapper.toDto(user);
        var location = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).body(userDto);
    }

    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateUserRequest request){
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userMapper.update(request, user);
        userRepository.save(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("#id == authentication.principal")
    @PostMapping("/{id}/change-password")
    public ResponseEntity<UserDto> changePassword(
            @PathVariable Long id,
            @RequestBody ChangePasswordRequest request) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        if (!user.getPassword().equals(request.getOldPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        user.setPassword(request.getNewPassword());
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }
}
