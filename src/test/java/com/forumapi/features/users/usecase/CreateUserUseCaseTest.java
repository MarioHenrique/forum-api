package com.forumapi.features.users.usecase;

import com.forumapi.entities.User;
import com.forumapi.features.users.business.NewUserValidator;
import com.forumapi.features.users.business.PasswordGenerator;
import com.forumapi.features.users.business.UserBuilder;
import com.forumapi.features.users.business.UserCacheEventPublisher;
import com.forumapi.features.users.infra.UserRepository;
import com.forumapi.features.users.model.NewUserTO;
import com.forumapi.features.users.model.UserVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserUseCaseTest {

    private static final String PASSWORD = "password";
    private static final String EMAIL = "mrohenrique@gmail.com";
    private static final String NAME = "mario";
    private static final LocalDate BIRTH_DAY = LocalDate.now();
    public static final String PASSWORD_ENCRYPTED = "passwordEncrypted";

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordGenerator passwordGenerator;

    @Mock
    private NewUserValidator newUserValidator;

    @Mock
    private UserBuilder userBuilder;

    @Mock
    private UserCacheEventPublisher userCacheEventPublisher;

    @Captor
    private ArgumentCaptor<User> newUserCaptor;

    private InOrder inOrder;

    @Before
    public void setUp(){
        inOrder = inOrder(newUserValidator,
                passwordGenerator,
                userBuilder,
                userRepository,
                userCacheEventPublisher);

        when(passwordGenerator.generatePassword(anyString())).thenReturn(PASSWORD_ENCRYPTED);
        when(userBuilder.createUser(any(), anyString()))
                .thenReturn(
                        new User(EMAIL,
                                 NAME,
                                 PASSWORD_ENCRYPTED,
                                 BIRTH_DAY));
    }

    @Test
    public void createUser(){
        NewUserTO newUserTO = givingANewUser();
        UserVO userCreated = whenCreateUser(newUserTO);
        thenValidateUser(newUserTO);
        thenGeneratePassword(newUserTO.getPassword());
        thenCreateUser(newUserTO);
        thenSaveUser(newUserTO);
        thenPublisheUserForCache();
        thenReturnUser(newUserTO, userCreated);
    }

    private void thenReturnUser(NewUserTO newUserTO, UserVO userCreated) {
        assertEquals(newUserTO.getEmail(), userCreated.getEmail());
        assertEquals(newUserTO.getName(), userCreated.getName());
        assertEquals(newUserTO.getBirthDay(), userCreated.getBirthDay());
    }

    private void thenPublisheUserForCache() {
        inOrder.verify(userCacheEventPublisher).onCreateUser(any(User.class));
    }

    private void thenSaveUser(NewUserTO newUserTO) {
        inOrder.verify(userRepository).save(newUserCaptor.capture());
        User user = newUserCaptor.getValue();

        assertEquals(newUserTO.getEmail(), user.getEmail());
        assertEquals(newUserTO.getName(), user.getName());
        assertEquals(PASSWORD_ENCRYPTED, user.getPassword());
        assertEquals(newUserTO.getBirthDay(), user.getBirthDay());
    }

    private void thenCreateUser(NewUserTO newUserTO) {
        inOrder.verify(userBuilder).createUser(newUserTO, PASSWORD_ENCRYPTED);
    }

    private void thenGeneratePassword(String password) {
        inOrder.verify(passwordGenerator).generatePassword(password);
    }

    private void thenValidateUser(NewUserTO newUserTO) {
        inOrder.verify(newUserValidator).validate(newUserTO);
    }

    private UserVO whenCreateUser(NewUserTO newUserTO) {
        return createUserUseCase.create(newUserTO);
    }

    private NewUserTO givingANewUser() {
        NewUserTO newUserTO = new NewUserTO();
        newUserTO.setPassword(PASSWORD);
        newUserTO.setEmail(EMAIL);
        newUserTO.setName(NAME);
        newUserTO.setBirthDay(BIRTH_DAY);
        return newUserTO;
    }

}