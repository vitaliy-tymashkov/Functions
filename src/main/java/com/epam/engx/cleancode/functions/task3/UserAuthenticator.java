package com.epam.engx.cleancode.functions.task3;

import com.epam.engx.cleancode.functions.task3.thirdpartyjar.SessionManager;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.User;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.UserService;

import javax.naming.AuthenticationException;

public abstract class UserAuthenticator implements UserService {

    private SessionManager sessionManager;

    public void login(String userName, String password) throws AuthenticationException {
        loginUser(getUserByName(userName), password);
    }

    private void loginUser(User user, String password) throws AuthenticationException {
        if (isPasswordCorrect(user, password)) {
            setSessionWithUser(user);
        } else {
            throw new AuthenticationException("Incorrect Username or Password");
        }
    }

    private void setSessionWithUser(User user) {
        sessionManager.setCurrentUser(user);
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
}
