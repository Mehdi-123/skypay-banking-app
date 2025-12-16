package com.bank;

import com.bank.service.Account;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        // Dates are hardcoded to match the test requirements.
        // In a real application, this supplier would simply return LocalDate.now(),
        // or it could be removed entirely and LocalDate.now() used directly.

        Account account = new Account(() -> LocalDate.of(2012, 1, 10));
        account.deposit(1000);

        account.setDateSupplier(() -> LocalDate.of(2012, 1, 13));
        account.deposit(2000);

        account.setDateSupplier(() -> LocalDate.of(2012, 1, 14));
        account.withdraw(500);

        account.printStatement();
    }
}
