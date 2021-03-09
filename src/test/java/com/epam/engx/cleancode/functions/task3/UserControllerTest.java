package com.epam.engx.cleancode.functions.task3;

import org.junit.Test;

public class UserControllerTest {

    private static final String ADMIN = "admin";
    private static final String PASSWORD = "123";

    private final UserControllerMock userController = new UserControllerMock();

    @Test
    public void shouldNotAuthenticateUser() {
        userController.setUserAuthenticator(new FalseUserAuthenticator());
        userController.authenticateUser(ADMIN, PASSWORD);
        userController.assertGenerateFailLoginResponseCalled();
    }

    @Test
    public void shouldAuthenticateUser() {
        TrueUserAuthenticatorMock trueUserAuthenticatorMock = new TrueUserAuthenticatorMock();
        userController.setUserAuthenticator(trueUserAuthenticatorMock);
        userController.authenticateUser(ADMIN, PASSWORD);
        userController.assertGenerateSuccessLoginResponseCalled();
        trueUserAuthenticatorMock.assertSetCurrentUserToSession();
    }

}
