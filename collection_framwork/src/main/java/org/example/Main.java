import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                displayMenu();
                System.out.print("Введите команду > ");
                String command = scanner.nextLine().trim();

                switch (command.toLowerCase()) {
                    case "1":
                    case "words":
                        handleUniqueWordsTask(scanner);
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

    private static void displayMenu() {
        System.out.println("===== Главное меню =====");
        System.out.println("1. Найти уникальные слова в тексте (команда: 1 или words)");
        System.out.println("0. Выход (команда: 0 или exit)");
        System.out.println("========================");
    }

    private static void handleUniqueWordsTask(Scanner scanner) {
        System.out.println("\n--- Задача: Поиск и сортировка уникальных слов ---");
        System.out.print("Введите текст > ");
        String text = scanner.nextLine();

        if (text == null || text.trim().isEmpty()) {
            System.out.println("Результат: Введен пустой текст. Уникальных слов нет.");
            System.out.println("--- Задача завершена ---\n");
            return;
        }

        String cleanedText = text.toLowerCase().replaceAll("[^a-zA-Zа-яА-Я\\s]", "");

        String[] words = cleanedText.trim().split("\\s+");

        if (words.length == 1 && words[0].isEmpty()) {
            System.out.println("Результат: Текст не содержит слов. Уникальных слов нет.");
            System.out.println("--- Задача завершена ---\n");
            return;
        }

        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));

        List<String> sortedWords = new ArrayList<>(uniqueWords);
        Collections.sort(sortedWords);

        System.out.println("Результат (уникальные слова, отсортированные по алфавиту):");
        System.out.println(sortedWords);
        System.out.println("--- Задача завершена ---");
    }
}