package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    private static Map<String, BankAccount> accounts = new HashMap<>();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                displayMainMenu();
                System.out.print("Введите команду > ");
                String command = scanner.nextLine().trim();

                switch (command.toLowerCase()) {
                    case "1":
                    case "bank":
                        handleBankingTask(scanner);
                        break;
                    case "0":
                    case "exit":
                        System.out.println("Завершение работы программы. До свидания!");
                        return;
                    default:
                        System.out.println("Неизвестная команда. Пожалуйста, выберите пункт из меню.");
                        break;
                }
                System.out.println();
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("===== Главное меню =====");
        System.out.println("1. Управление банковскими счетами (команда: 1 или bank)");
        System.out.println("0. Выход (команда: 0 или exit)");
        System.out.println("========================");
    }

    private static void handleBankingTask(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Модуль: Управление банковскими счетами ---");
            System.out.println("1. Создать сберегательный счет");
            System.out.println("2. Создать кредитный счет");
            System.out.println("3. Выбрать счет для операций");
            System.out.println("4. Показать все счета");
            System.out.println("5. Начислить проценты на все счета");
            System.out.println("0. Вернуться в главное меню");
            System.out.println("---------------------------------------------");
            System.out.print("Выберите действие > ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    createAccount(scanner, "savings");
                    break;
                case "2":
                    createAccount(scanner, "credit");
                    break;
                case "3":
                    selectAndManageAccount(scanner);
                    break;
                case "4":
                    listAllAccounts();
                    break;
                case "5":
                    accrueInterestOnAllAccounts();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void createAccount(Scanner scanner, String type) {
        try {
            System.out.print("Введите номер счета: ");
            String accNum = scanner.nextLine().trim();
            if (accounts.containsKey(accNum)) {
                System.out.println("Счет с таким номером уже существует.");
                return;
            }
            System.out.print("Введите начальный баланс: ");
            double balance = Double.parseDouble(scanner.nextLine().trim());

            if ("savings".equals(type)) {
                accounts.put(accNum, new SavingsAccount(accNum, balance));
                System.out.println("Сберегательный счет успешно создан!");
            } else if ("credit".equals(type)) {
                System.out.print("Введите кредитный лимит: ");
                double limit = Double.parseDouble(scanner.nextLine().trim());
                accounts.put(accNum, new CreditAccount(accNum, balance, limit));
                System.out.println("Кредитный счет успешно создан!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введено неверное числовое значение. Попробуйте снова.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка создания счета: " + e.getMessage());
        }
    }

    private static void selectAndManageAccount(Scanner scanner) {
        System.out.print("Введите номер счета для управления: ");
        String accNum = scanner.nextLine().trim();
        BankAccount account = accounts.get(accNum);

        if (account == null) {
            System.out.println("Счет с номером " + accNum + " не найден.");
            return;
        }

        while (true) {
            System.out.println("\n--- Управление счетом: " + accNum + " ---");
            System.out.println("Текущий баланс: " + String.format("%.2f", account.getBalance()));
            System.out.println("1. Пополнить (deposit)");
            System.out.println("2. Снять (withdraw)");
            System.out.println("0. Вернуться к списку счетов");
            System.out.print("Выберите операцию > ");
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                    case "deposit":
                        System.out.print("Введите сумму для пополнения: ");
                        double depositAmount = Double.parseDouble(scanner.nextLine().trim());
                        account.deposit(depositAmount);
                        break;
                    case "2":
                    case "withdraw":
                        System.out.print("Введите сумму для снятия: ");
                        double withdrawAmount = Double.parseDouble(scanner.nextLine().trim());
                        account.withdraw(withdrawAmount);
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Неверная операция.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введено неверное числовое значение.");
            }
        }
    }

    private static void listAllAccounts() {
        System.out.println("\n--- Список всех счетов ---");
        if (accounts.isEmpty()) {
            System.out.println("Счета еще не созданы.");
        } else {
            for (BankAccount acc : accounts.values()) {
                System.out.println(acc.toString());
            }
        }
        System.out.println("--------------------------");
    }

    private static void accrueInterestOnAllAccounts() {
        System.out.println("\n--- Начисление процентов на все счета ---");
        if (accounts.isEmpty()) {
            System.out.println("Нет счетов для начисления процентов.");
            return;
        }
        for (BankAccount acc : accounts.values()) {
            acc.accrueInterest();
        }
        System.out.println("--------------------------------------");
    }
}