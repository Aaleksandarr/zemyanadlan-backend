package bg.zemyanadlan.services;

import bg.zemyanadlan.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getCurrentUser(){
        // TO DO: Implement actual user retrieval logic based on your security setup
        // In a real application, this would fetch the authenticated user from the security context
        // For this example, we'll return a dummy user
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        return user;
    }
}
