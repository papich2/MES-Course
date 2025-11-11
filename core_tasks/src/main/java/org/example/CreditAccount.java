package org.example;

public class CreditAccount extends BankAccount {
    private double creditLimit;
    private static final double INTEREST_RATE_ON_DEBT = 0.20;

    public CreditAccount(String accountNumber, double initialBalance, double creditLimit) {
        super(accountNumber, initialBalance);
        if (creditLimit < 0) {
            throw new IllegalArgumentException("Кредитный лимит не может быть отрицательным.");
        }
        this.creditLimit = creditLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Сумма снятия должна быть положительной.");
            return;
        }
        if (getBalance() - amount < -creditLimit) {
            System.out.println("Превышен кредитный лимит. Операция отклонена.");
        } else {
            setBalance(getBalance() - amount);
            System.out.printf("Со счета %s снято %.2f. Новый баланс: %.2f\n", getAccountNumber(), amount, getBalance());
        }
    }

    @Override
    public void accrueInterest() {
        if (getBalance() < 0) {
            double debt = -getBalance();
            double interest = debt * INTEREST_RATE_ON_DEBT;
            setBalance(getBalance() - interest);
            System.out.printf("Начислены проценты на кредитную задолженность в размере %.2f. Новый баланс: %.2f\n", interest, getBalance());
        } else {
            System.out.println("Проценты на кредитную задолженность не начисляются, так как баланс положительный.");
        }
    }

    @Override
    public String toString() {
        return String.format("[Кредитный] %s, Кредитный лимит: %.2f", super.toString(), creditLimit);
    }
}
