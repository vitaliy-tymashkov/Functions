package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;
import org.junit.Before;
import org.junit.Test;

public class AccountRegistrationServiceTest {

    private AccountManagerMock accountManagerMock;

    private final AccountRegistrationService accountRegistrationService = new AccountRegistrationService();
    private final Account validAccountStub = new ValidAccountStub();
    private final ValidAccountMock validAccountMock = new ValidAccountMock();

    @Before
    public void setUp() throws Exception {
        accountManagerMock = new AccountManagerMock();
        accountRegistrationService.setAccountManager(accountManagerMock);
        accountRegistrationService.setPasswordChecker(new OkPasswordChecker());
    }

    @Test
    public void shouldRegisterAccount() {
        accountRegistrationService.register(validAccountStub);
        accountManagerMock.assertAccountRegistered(validAccountStub);
    }

    @Test
    public void shouldPopulateAccountWhenCreate() {
        accountRegistrationService.register(validAccountMock);
        validAccountMock.assertCreationDateExist();
        validAccountMock.assertHomeAddressInAddresses();
        validAccountMock.assertWorkAddressInAddresses();
        validAccountMock.assertAdditionalAddressInAddresses();
    }

    @Test (expected = WrongAccountNameException.class)
    public void shouldThrowExceptionWhenNameIsTooShort() {
        accountRegistrationService.register(new ShortNameAccountStub());
    }

    @Test (expected = TooShortPasswordException.class)
    public void shouldThrowExceptionWhenPasswordIsTooShort() {
        accountRegistrationService.register(new ShortPasswordAccountStub());
    }


    @Test (expected = WrongPasswordException.class)
    public void shouldThrowExceptionWhenPasswordIsNotOk() {
        accountRegistrationService.setPasswordChecker(new NotOkPasswordChecker());
        accountRegistrationService.register(validAccountStub);
    }

}
