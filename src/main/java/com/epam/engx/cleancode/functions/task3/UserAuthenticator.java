package com.epam.engx.cleancode.functions.task3;

import com.epam.engx.cleancode.functions.task3.thirdpartyjar.SessionManager;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.User;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.UserService;

public abstract class UserAuthenticator implements UserService {

    private SessionManager sessionManager;

    public User login(String userName, String password) {
        return loginUser(getUserByName(userName), password);
    }

    private User loginUser(User user, String password) {
        if (isPasswordCorrect(user, password)) {
            setSessionWithUser(user);
            return user;
        }
        return null;
    }

    private void setSessionWithUser(User user) {
        sessionManager.setCurrentUser(user);
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
}
