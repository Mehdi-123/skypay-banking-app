package com.bank.service;

import com.bank.model.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Account implements AccountService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final List<Transaction> transactions = new ArrayList<>();
    private int balance = 0;

    private Supplier<LocalDate> dateSupplier;

    public Account(Supplier<LocalDate> dateSupplier) {
        this.dateSupplier = dateSupplier;
    }

    public void setDateSupplier(Supplier<LocalDate> dateSupplier) {
        this.dateSupplier = dateSupplier;
    }

    @Override
    public void deposit(int amount) {
        validateAmount(amount);
        balance += amount;
        transactions.add(new Transaction(dateSupplier.get(), amount, balance));
    }

    @Override
    public void withdraw(int amount) {
        validateAmount(amount);
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
        transactions.add(new Transaction(dateSupplier.get(), -amount, balance));
    }

    @Override
    public void printStatement() {
        System.out.println("Date        Amount  Balance");

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            System.out.println(
                    FORMATTER.format(t.date()) + "  " +
                            t.amount() + "    " +
                            t.balance()
            );
        }
    }

    private void validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
}
