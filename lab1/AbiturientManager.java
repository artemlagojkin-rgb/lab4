package lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class AbiturientManager {
    // РЕФАКТОРИНГ: Замена массива на репозиторий для улучшения управления данными
    private Repository<Abiturient> repository;

    // Конструктор
    public AbiturientManager(Abiturient[] abiturients) {
        // РЕФАКТОРИНГ: Инициализация репозитория и заполнение его переданными абитуриентами
        this.repository = new Repository<>();
        for (Abiturient a : abiturients) {
            repository.add(a);
        }
    }

    // Метод для создания массива абитуриентов с вводом от пользователя
    public static Abiturient[] createAbiturientsArrayFromInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите количество абитуриентов: ");
        int count = Integer.parseInt(reader.readLine());

        Abiturient[] abiturients = new Abiturient[count];

        for (int i = 0; i < count; i++) {
            abiturients[i] = Abiturient.createAbiturientFromInput(reader, i + 1);
        }

        return abiturients;
    }

    // a) Список абитуриентов с неудовлетворительными оценками
    public void printAbiturientsWithUnsatisfactoryGrades() {
        System.out.println("\n=== Абитуриенты с неудовлетворительными оценками ===");
        
        // РЕФАКТОРИНГ: Использование метода find репозитория вместо ручной фильтрации
        List<Abiturient> unsatisfactoryAbiturients = repository.find(Abiturient::hasUnsatisfactoryGrades);
        
        if (!unsatisfactoryAbiturients.isEmpty()) {
            for (Abiturient abiturient : unsatisfactoryAbiturients) {
                System.out.println(abiturient);
            }
        } else {
            System.out.println("Абитуриентов с неудовлетворительными оценками не найдено.");
        }
    }

    // b) Список абитуриентов со средним баллом выше заданного
    public void printAbiturientsWithAverageAbove(double threshold) {
        System.out.println("\n=== Абитуриенты со средним баллом выше " + threshold + " ===");
        
        // РЕФАКТОРИНГ: Использование лямбда-выражения для фильтрации
        List<Abiturient> highAchievers = repository.find(a -> a.getAverageGrade() > threshold);
        
        if (!highAchievers.isEmpty()) {
            for (Abiturient abiturient : highAchievers) {
                System.out.println(abiturient);
            }
        } else {
            System.out.println("Абитуриентов со средним баллом выше " + threshold + " не найдено.");
        }
    }

    // c) Выбор n абитуриентов с самым высоким средним баллом
    public void printTopNAbiturients(int n) {
        System.out.println("\n=== " + n + " абитуриентов с самым высоким средним баллом ===");

        // РЕФАКТОРИНГ: Замена массива на список из репозитория и использование ArrayList для сортировки
        List<Abiturient> sortedAbiturients = new ArrayList<>(repository.getAll());
        
        // РЕФАКТОРИНГ: Улучшена сортировка с использованием Comparator
        sortedAbiturients.sort((a1, a2) -> Double.compare(a2.getAverageGrade(), a1.getAverageGrade()));

        // Выводим топ n абитуриентов
        for (int i = 0; i < Math.min(n, sortedAbiturients.size()); i++) {
            System.out.println((i + 1) + ". " + sortedAbiturients.get(i));
        }

        // Определяем полупроходной балл (балл последнего из выбранных)
        if (n <= sortedAbiturients.size() && n > 0) {
            double semiPassingGrade = sortedAbiturients.get(n - 1).getAverageGrade();
            System.out.println("\n=== Абитуриенты с полупроходным баллом (" +
                    String.format("%.2f", semiPassingGrade) + ") ===");

            for (Abiturient abiturient : sortedAbiturients) {
                if (Math.abs(abiturient.getAverageGrade() - semiPassingGrade) < 0.01) {
                    System.out.println(abiturient);
                }
            }
        }
    }

    // Метод для вывода всех абитуриентов
    public void printAllAbiturients() {
        System.out.println("\n=== Все абитуриенты ===");
        // РЕФАКТОРИНГ: Замена массива на список из репозитория
        List<Abiturient> allAbiturients = repository.getAll();
        for (int i = 0; i < allAbiturients.size(); i++) {
            System.out.println((i + 1) + ". " + allAbiturients.get(i));
        }
    }

    // РЕФАКТОРИНГ: Добавлен метод для демонстрации работы репозитория
    public void demonstrateRepositoryOperations() {
        System.out.println("\n=== Демонстрация операций репозитория ===");
        System.out.println("Текущее количество абитуриентов в репозитории: " + repository.size());
        
        if (repository.size() > 0) {
            Abiturient first = repository.get(0);
            System.out.println("Первый абитуриент в репозитории: " + first);
        }
    }

    // Метод для чтения числа с консоли
    private static double readDouble(BufferedReader reader) throws IOException {
        while (true) {
            try {
                return Double.parseDouble(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.print("Ошибка! Введите число: ");
            }
        }
    }

    // Метод для чтения целого числа с консоли
    private static int readInt(BufferedReader reader) throws IOException {
        while (true) {
            try {
                return Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.print("Ошибка! Введите целое число: ");
            }
        }
    }

    // Основной метод
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            // Создаем массив объектов Abiturient с вводом от пользователя
            System.out.println("=== Ввод данных об абитуриентах ===");
            Abiturient[] abiturients = createAbiturientsArrayFromInput();

            AbiturientManager manager = new AbiturientManager(abiturients);

            // Выводим всех абитуриентов
            manager.printAllAbiturients();

            // Демонстрация работы репозитория
            manager.demonstrateRepositoryOperations();

            // a) Абитуриенты с неудовлетворительными оценками
            manager.printAbiturientsWithUnsatisfactoryGrades();

            // b) Абитуриенты со средним баллом выше заданного
            System.out.print("\nВведите минимальный средний балл для фильтрации: ");
            double threshold = readDouble(reader);
            manager.printAbiturientsWithAverageAbove(threshold);

            // c) Топ n абитуриентов
            System.out.print("\nВведите количество абитуриентов для топа: ");
            int n = readInt(reader);
            manager.printTopNAbiturients(n);

        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода: " + e.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}