package com.epam.engx.cleancode.functions.task1;

import static com.epam.engx.cleancode.functions.task1.thirdpartyjar.CheckStatus.OK;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;

public class AccountRegistrationService {

    public static final int ACCOUNT_NAME_MIN_LENGTH = 5;
    public static final int PASSWORD_MIN_LENGTH = 8;

    private PasswordChecker passwordChecker;
    private AccountManager accountManager;

    public void register(Account account) {
        validateAccountCredentials(account);
        setAccountDetails(account);
        createNewAccount(account);
    }

    private void createNewAccount(Account account) {
        accountManager.createNewAccount(account);
    }

    private void validateAccountCredentials(Account account) {
        validateAccountNameLength(account.getName());
        validatePasswordLength(account.getPassword());
        validatePassword(account.getPassword());
    }

    private void setAccountDetails(Account account) {
        setCreationDate(account);
        setAddresses(account);
    }

    private void setCreationDate(Account account) {
        account.setCreatedDate(new Date());
    }

    private void setAddresses(Account account) {
        account.setAddresses(getAddresses(account));
    }

    private List<Address> getAddresses(Account account) {
        List<Address> addresses = new ArrayList<>();
        addresses.add(account.getHomeAddress());
        addresses.add(account.getWorkAddress());
        addresses.add(account.getAdditionalAddress());
        return addresses;
    }

    private void validateAccountNameLength(String name) {
        if (name.length() <= ACCOUNT_NAME_MIN_LENGTH){
            throw new WrongAccountNameException();
        }
    }

    private void validatePasswordLength(String password) {
        if (password.length() <= PASSWORD_MIN_LENGTH) {
            throw new TooShortPasswordException();
        }
    }

    private void validatePassword(String password) {
        if (checkPassword(password)) {
            throw new WrongPasswordException();
        }
    }

    private boolean checkPassword(String password) {
        return passwordChecker.validate(password) != OK;
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void setPasswordChecker(PasswordChecker passwordChecker) {
        this.passwordChecker = passwordChecker;
    }
}
