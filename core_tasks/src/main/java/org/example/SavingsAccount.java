package org.example;

public class SavingsAccount extends BankAccount {
    private static final double INTEREST_RATE = 0.03;

    public SavingsAccount(String accountNumber, double initialBalance) {
        super(accountNumber, initialBalance);
    }

    @Override
    public void accrueInterest() {
        double interest = getBalance() * INTEREST_RATE;
        deposit(interest);
        System.out.printf("Начислены проценты по сберегательному счету в размере %.2f\n", interest);
    }

    @Override
    public String toString() {
        return "[Сберегательный] " + super.toString();
    }
}
