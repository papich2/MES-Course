package org.example;

public abstract class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double initialBalance) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Номер счета не может быть пустым.");
        }
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Сумма пополнения должна быть положительной.");
            return;
        }
        this.balance += amount;
        System.out.printf("Счет %s пополнен на %.2f. Новый баланс: %.2f\n", accountNumber, amount, this.balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Сумма снятия должна быть положительной.");
            return;
        }
        if (amount > this.balance) {
            System.out.println("Недостаточно средств на счете.");
            return;
        }
        this.balance -= amount;
        System.out.printf("Со счета %s снято %.2f. Новый баланс: %.2f\n", accountNumber, amount, this.balance);
    }

    public abstract void accrueInterest();

    @Override
    public String toString() {
        return String.format("Счет №: %s, Баланс: %.2f", accountNumber, balance);
    }
}