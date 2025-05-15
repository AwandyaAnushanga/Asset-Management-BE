package com.nexeyo.erp.jwt.controllers;

import com.nexeyo.erp.Config.ConfigRepo;
//import com.nexeyo.erp.Email.DemoMessage;
//import com.nexeyo.erp.Email.EmailConnection;
import com.nexeyo.erp.Email.DemoMessage;
import com.nexeyo.erp.Email.EmailConnection;
import com.nexeyo.erp.EmployeeAddress.EmployeeAddress;
import com.nexeyo.erp.EmployeeAddress.EmployeeAddressRepo;
import com.nexeyo.erp.HRUserFetch.HRUserFetch;
import com.nexeyo.erp.HRUserFetch.HRUserFetchRepo;
import com.nexeyo.erp.UsersLocation.UsersLocation;
import com.nexeyo.erp.UsersLocation.UsersLocationRepository;
import com.nexeyo.erp.jwt.exception.TokenRefreshException;
import com.nexeyo.erp.jwt.models.ERole;
import com.nexeyo.erp.jwt.models.RefreshToken;
import com.nexeyo.erp.jwt.models.Role;
import com.nexeyo.erp.jwt.models.User;
import com.nexeyo.erp.jwt.payload.request.LoginRequest;
import com.nexeyo.erp.jwt.payload.request.SignupRequest;
import com.nexeyo.erp.jwt.payload.request.TokenRefreshRequest;
import com.nexeyo.erp.jwt.payload.response.JwtResponse;
import com.nexeyo.erp.jwt.payload.response.MessageResponse;
import com.nexeyo.erp.jwt.payload.response.TokenRefreshResponse;
import com.nexeyo.erp.jwt.repository.RoleRepository;
import com.nexeyo.erp.jwt.repository.UserRepository;
import com.nexeyo.erp.jwt.security.jwt.JwtUtils;
import com.nexeyo.erp.jwt.security.services.RefreshTokenService;
import com.nexeyo.erp.jwt.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    EmployeeAddressRepo employeeAddressRepo;
    @Autowired
    private UsersLocationRepository usersLocationRepository;
    @Autowired
    private ConfigRepo configRepo;
    @Autowired
    private HRUserFetchRepo hRUserFetchRepo;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        User user = userRepository.findById(userDetails.getId()).get();
//        user.setPassword("");
        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), user, roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        try {
            if (signUpRequest.getHr_user_id() == null) {
                signUpRequest.setHr_user_id(0);
            }
        } catch (Exception e) {
        }

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getFirstname(), signUpRequest.getLastname(), signUpRequest.getPhoneNumber(), signUpRequest.getCountry(), signUpRequest.getIs_verified(), signUpRequest.getCompany(), signUpRequest.getBranch(), signUpRequest.getEmployeeAddress(), signUpRequest.getUser_location_id(), signUpRequest.getUsersLocationList(), signUpRequest.getHr_user_id());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    case "pos":
                        Role posRole = roleRepository.findByName(ERole.ROLE_POS)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(posRole);
                        break;
                    case "measurement":
                        Role measurementRole = roleRepository.findByName(ERole.ROLE_MEASUREMENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(measurementRole);
                        break;
                    case "store_in_charge":
                        Role store_in_charge = roleRepository.findByName(ERole.ROLE_STORES_INCHARGE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(store_in_charge);
                        break;
                    case "finance_assistant":
                        Role finance_assistant = roleRepository.findByName(ERole.ROLE_FIN_ASSISTANT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(finance_assistant);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        EmployeeAddress employeeAddress = user.getEmployeeAddress();
        user.setEmployeeAddress(null);
        User user1 = userRepository.save(user);
        System.out.println(user1.toString());
        employeeAddress.setId(user1.getId());
        EmployeeAddress employeeAddress1 = new EmployeeAddress();
        employeeAddress1 = employeeAddress;
        System.out.println(employeeAddress1);
        employeeAddressRepo.save(employeeAddress);

        try {
            Optional<HRUserFetch> hrUserFetchOptional = hRUserFetchRepo.findById(signUpRequest.getHr_user_id());
            if (hrUserFetchOptional.isPresent()) {
                hrUserFetchOptional.get().setPicked_for_erp(true);
            }
            hRUserFetchRepo.save(hrUserFetchOptional.get());
        } catch (Exception e) {
        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/demo-signup")
    public ResponseEntity<?> demoRegisterUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getFirstname(), signUpRequest.getLastname(), signUpRequest.getPhoneNumber(), signUpRequest.getCountry(), signUpRequest.getIs_verified(), signUpRequest.getCompany(), signUpRequest.getBranch(), signUpRequest.getEmployeeAddress(), signUpRequest.getUser_location_id(), signUpRequest.getUsersLocationList(), signUpRequest.getHr_user_id());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    case "pos":
                        Role posRole = roleRepository.findByName(ERole.ROLE_POS)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(posRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() ->  new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        EmployeeAddress employeeAddress = user.getEmployeeAddress();
        user.setEmployeeAddress(null);
        User user1 = userRepository.save(user);
        System.out.println(user1.toString());
        employeeAddress.setId(user1.getId());
        UsersLocation usersLocation = new UsersLocation();
        usersLocation.setUser_id(user1.getId());
        usersLocation.setLocation_id(1);
        usersLocationRepository.save(usersLocation);
        EmployeeAddress employeeAddress1 = new EmployeeAddress();
        employeeAddress1 = employeeAddress;
        System.out.println(employeeAddress1);
        employeeAddressRepo.save(employeeAddress);

        EmailConnection emailConnection = new EmailConnection(configRepo);
        DemoMessage demoMessage = new DemoMessage();
        emailConnection.MailSendWithoutFile("Welcome to the ERPNEX", demoMessage.getContent(), signUpRequest.getEmail());
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping(path = "/update")
    ResponseEntity<?> Update(@RequestBody User user) {

        try {
            Optional<User> oldData = userRepository.findById(user.getId());
            if (!oldData.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found !");
            }
            User user1 = user;
            user1.setRoles(oldData.get().getRoles());
            EmployeeAddress employeeAddress = new EmployeeAddress();
            employeeAddress = user1.getEmployeeAddress();
            user1.setPassword(encoder.encode(user1.getPassword()));
            user1.setEmployeeAddress(null);
            userRepository.save(user1);
            employeeAddressRepo.save(employeeAddress);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e);
        }
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = userDetails.getId();
        refreshTokenService.deleteByUserId(userId);
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }

}
