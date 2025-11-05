package lab2;

import java.util.Scanner;

/**
 * Демонстрационный класс для работы с одномерными массивами
 */
public class MassivDemo {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== РАБОТА С ОДНОМЕРНЫМИ МАССИВАМИ ===");
        
        // Создание массива пользователем
        System.out.println("\n1. СОЗДАНИЕ МАССИВА");
        
        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();
        
        System.out.print("Введите минимальное значение: ");
        int min = scanner.nextInt();
        
        System.out.print("Введите максимальное значение: ");
        int max = scanner.nextInt();
        
        // Создаем массив пользователя
        Massiv userMassiv = Massiv.createRandomMassiv(size, min, max);
        userMassiv.printMassiv("Ваш массив");
        
        // Вывод информации о массиве
        System.out.println("\n2. ИНФОРМАЦИЯ О МАССИВЕ");
        System.out.println("Размер массива: " + userMassiv.getSize());
        System.out.println("Минимальный элемент: " + userMassiv.min());
        System.out.println("Максимальный элемент: " + userMassiv.max());
        System.out.println("Среднее значение: " + userMassiv.average());
        
        // Операции с массивом
        System.out.println("\n3. ОПЕРАЦИИ С МАССИВОМ");
        
        // 1. Сложение всех элементов
        int sum = userMassiv.sumAllElements();
        System.out.println("Сумма всех элементов: " + sum);
        
        // 2. Перемножение всех элементов
        long product = userMassiv.multiplyAllElements();
        System.out.println("Произведение всех элементов: " + product);
        
        // 3. Вычитание числа от всех элементов
        System.out.print("\nВведите число для вычитания от всех элементов: ");
        int subtractNumber = scanner.nextInt();
        
        Massiv subtractedMassiv = userMassiv.subtractFromAllElements(subtractNumber);
        subtractedMassiv.printMassiv("Массив после вычитания " + subtractNumber);
        
        // Показываем изменения
        System.out.println("Сумма элементов после вычитания: " + subtractedMassiv.sumAllElements());
        System.out.println("Произведение элементов после вычитания: " + subtractedMassiv.multiplyAllElements());
        
        // Демонстрация методов equals, hashCode, toString
        System.out.println("\n4. ДЕМОНСТРАЦИЯ МЕТОДОВ");
        System.out.println("Массив в строковом представлении: " + userMassiv.toString());
        System.out.println("Хэш-код массива: " + userMassiv.hashCode());
        
        // Создаем копию для демонстрации equals
        Massiv copyMassiv = new Massiv(userMassiv.getArray());
        System.out.println("userMassiv.equals(copyMassiv): " + userMassiv.equals(copyMassiv));
        
        scanner.close();
        
        System.out.println("\n=== ПРОГРАММА ЗАВЕРШЕНА ===");
    }
}