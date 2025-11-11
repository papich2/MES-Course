import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                displayMenu();
                System.out.print("Введите команду > ");
                String command = scanner.nextLine().trim();

                switch (command.toLowerCase()) {
                    case "1":
                    case "read":
                        handleReadFileCommand(scanner);
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
        System.out.println("1. Прочитать содержимое файла (команда: 1 или read)");
        System.out.println("0. Выход (команда: 0 или exit)");
        System.out.println("========================");
    }

    private static void handleReadFileCommand(Scanner scanner) {
        System.out.print("Пожалуйста, введите полный или относительный путь к файлу: ");
        String filePath = scanner.nextLine().trim();

        readFileWithBufferedReader(filePath);
    }

    private static void readFileWithBufferedReader(String filePath) {
        System.out.println("\n--- Начало чтения файла: " + filePath + " ---");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean empty = true;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                empty = false;
            }
            if (empty) {
                System.out.println("[Файл пуст]");
            }
        } catch (FileNotFoundException e) {
            System.err.println("[ОШИБКА]: Файл не найден. Пожалуйста, проверьте правильность пути: " + filePath);
        } catch (IOException e) {
            System.err.println("[ОШИБКА]: Произошла ошибка при чтении файла. Детали: " + e.getMessage());
        } finally {
            System.out.println("--- Чтение файла завершено ---\n");
        }
    }

    private static void readFileWithFilesReadAllLines(String filePath) {
        System.out.println("\n--- Начало чтения файла (метод Files.readAllLines): " + filePath + " ---");
        try {
            List<String> allLines = Files.readAllLines(Paths.get(filePath));
            if (allLines.isEmpty()) {
                System.out.println("[Файл пуст]");
            } else {
                for (String line : allLines) {
                    System.out.println(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("[ОШИБКА]: Файл не найден. Проверьте путь: " + filePath);
        } catch (IOException e) {
            System.err.println("[ОШИБКА]: Произошла ошибка при чтении файла. Детали: " + e.getMessage());
        } finally {
            System.out.println("--- Чтение файла завершено ---\n");
        }
    }
}