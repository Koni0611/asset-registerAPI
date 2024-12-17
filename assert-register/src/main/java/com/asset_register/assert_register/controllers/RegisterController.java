package com.asset_register.assert_register.controllers;

import com.asset_register.assert_register.models.ComputerDetails;
import com.asset_register.assert_register.models.DongleAndWifi;
import com.asset_register.assert_register.models.Login;
import com.asset_register.assert_register.models.MobilePhoneDetails;
import com.asset_register.assert_register.models.Printer;
import com.asset_register.assert_register.models.Register;
import com.asset_register.assert_register.repositories.ComputerDetailsRepository;
import com.asset_register.assert_register.repositories.DongleAndWifiRepository;
import com.asset_register.assert_register.repositories.LoginRepository;
import com.asset_register.assert_register.repositories.MobilePhoneDetailsRepository;
import com.asset_register.assert_register.repositories.PrinterRepository;
import com.asset_register.assert_register.repositories.RegisterRepository;
import com.asset_register.assert_register.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Value("${FIRST_ADMIN_KEY}") // Inject the admin key from properties or environment variables
    private String firstAdminKey;

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private ComputerDetailsRepository computerDetailsRepository;

    @Autowired
    private DongleAndWifiRepository dongleWifiRepository;

    @Autowired
    private MobilePhoneDetailsRepository mobilePhoneDetailsRepository;

    @Autowired
    private PrinterRepository printerRepository;

    private Object adminKey;

    @GetMapping("/user-details/{identityNumber}")
    public ResponseEntity<?> getUserDetails(@PathVariable String identityNumber) {
        // Fetch the user from the repository
        Optional<Register> user = registerRepository.findByIdentityNumber(identityNumber);

        // If user is not found, return a NOT_FOUND response
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with identity number " + identityNumber + " not found.");
        }

        // Return the user details
        return ResponseEntity.ok(user.get());
    }

    // Admin-only endpoint to get all users
    @GetMapping("/all")
    public ResponseEntity<List<Register>> getAllUsers() {
        if (!authService.isAdmin()) {
            return ResponseEntity.status(403).body(null); // Forbidden
        }
        List<Register> users = registerRepository.findAll();
        return ResponseEntity.ok(users);
    }

    // Endpoint to get current user info
    @GetMapping("/user-info")
    public ResponseEntity<Register> getUserInfo(@RequestParam(required = false) String username) {
        try {
            Register currentUser = username != null
                    ? authService.getCurrentUser()
                    : authService.getCurrentUser();
            return ResponseEntity.ok(currentUser);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> registerUser(@RequestBody Register register,
            @RequestParam(required = false) String adminKey) {

        if (register.getUsername() == null || register.getPassword() == null || register.getRole() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Username, password, and role are required.");
        }

        if ("admin".equalsIgnoreCase(register.getRole())) {
            if (!registerRepository.findByRole("admin").isEmpty()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("Admin registration is restricted to existing admins.");
            }

            if (adminKey == null || !adminKey.equals(firstAdminKey)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("Invalid admin key.");
            }

            System.setProperty("FIRST_ADMIN_KEY", "INVALID"); // Disable key after first
            // admin registration

            // Optional: Disable the key in the application context
            // Object firstAdminKey = null; // In-memory invalidation
        }

        register.setToken(null);

        if ("user".equalsIgnoreCase(register.getRole())) {
            register.setIsApproved(true); // Regular users are auto-approved
        }

        // Save the user
        try {
            registerRepository.save(register);

            // Create a login entry for the user
            Login login = new Login();
            login.setUsername(register.getUsername());
            login.setPassword(register.getPassword());
            login.setRole(register.getRole());
            loginRepository.save(login);

            return ResponseEntity.ok("User registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while saving the user: " + e.getMessage());
        }
    }

    @PostMapping("/add-admin")
    public ResponseEntity<String> addAdmin(
            @RequestBody Register register,
            @RequestHeader("Authorization") String adminToken) {

        try {
            // Authenticate existing admin
            Register adminUser = authService.getCurrentUserByToken(adminToken);

            if (!"admin".equalsIgnoreCase(adminUser.getRole())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admins can add new admins.");
            }

            if (!"admin".equalsIgnoreCase(register.getRole())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This endpoint is only for adding admins.");
            }

            register.setIsApproved(true);
            registerRepository.save(register);

            Login login = new Login();
            login.setUsername(register.getUsername());
            login.setPassword(register.getPassword()); // In a real app, you should hash the password before saving
            login.setRole(register.getRole());

            loginRepository.save(login);

            // Optionally, associate the new login with the register entity (if needed)
            register.setLogin(login); // if you want to keep the relationship between Register and Login entities

            registerRepository.save(register);

            return ResponseEntity.ok("Admin added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or user not authorized.");
        }
    }

    @PutMapping("/update/{identityNumber}")
    public ResponseEntity<?> updateUser(@PathVariable String identityNumber, @RequestBody Register register) {

        if (!authService.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied. Only admins can update user details.");
        }
        System.out.println("Received identityNumber: " + identityNumber);
        Optional<Register> optionalUser = registerRepository.findByIdentityNumber(identityNumber);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with identity number " + identityNumber + " not found.");
        }

        Register existingUser = optionalUser.get();
        if (register.getName() != null)
            existingUser.setName(register.getName());
        if (register.getSurname() != null)
            existingUser.setSurname(register.getSurname());
        if (register.getGender() != null)
            existingUser.setGender(register.getGender());
        if (register.getEmail() != null)
            existingUser.setEmail(register.getEmail());
        if (register.getPhoneNumber() != null)
            existingUser.setPhoneNumber(register.getPhoneNumber());

        try {
            Register updatedUser = registerRepository.save(existingUser);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating user: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{identityNumber}")
    public ResponseEntity<?> deleteUser(@PathVariable String identityNumber) {
        if (!authService.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied. Only admins can delete users.");
        }

        Optional<Register> optionalUser = registerRepository.findByIdentityNumber(identityNumber);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with identity number " + identityNumber + " not found.");
        }

        try {
            registerRepository.delete(optionalUser.get());
            return ResponseEntity.ok("User with identity number " + identityNumber + " deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting user: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, List<Object>>> getUserAssets(@PathVariable Long userId) {
        Map<String, List<Object>> assets = new HashMap<>();

        List<ComputerDetails> computers = computerDetailsRepository.findByUserId(userId);
        List<MobilePhoneDetails> mobiles = mobilePhoneDetailsRepository.findByUserId(userId);
        List<Printer> printers = printerRepository.findByUserId(userId);
        List<DongleAndWifi> dongles = dongleWifiRepository.findByUserId(userId);

        assets.put("computers", new ArrayList<>(computers));
        assets.put("mobiles", new ArrayList<>(mobiles));
        assets.put("printers", new ArrayList<>(printers));
        assets.put("dongles", new ArrayList<>(dongles));

        return ResponseEntity.ok(assets);
    }

}