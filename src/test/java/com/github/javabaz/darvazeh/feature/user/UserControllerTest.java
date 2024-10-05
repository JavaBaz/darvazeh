package com.github.javabaz.darvazeh.feature.user;

import com.github.javabaz.darvazeh.feature.user.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private UserEntity mockUser;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        mockUser = new UserEntity();
        mockUser.setUsername("testUser");
        mockUser.setUserRole(UserRole.MEMBER);

        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("testUser");
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testGetCurrentUser() {
        when(userService.getUserByUsername("testUser")).thenReturn(mockUser);
        UserEntity currentUser = userController.getCurrentUser();
        assertEquals("testUser", currentUser.getUsername());
        assertEquals(UserRole.MEMBER, currentUser.getUserRole());
        verify(userService).getUserByUsername("testUser");
    }
}
