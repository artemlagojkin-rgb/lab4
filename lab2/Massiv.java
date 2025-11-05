package lab2;

import java.util.Arrays;
import java.util.Random;

/**
 * Класс для работы с одномерными массивами целых чисел
 */
public class Massiv {
    private int[] array;
    
    // Конструкторы
    public Massiv() {
        this.array = new int[0];
    }
    
    public Massiv(int[] array) {
        this.array = array != null ? array.clone() : new int[0];
    }
    
    public Massiv(int size) {
        this.array = new int[size];
    }
    
    public Massiv(int size, int minValue, int maxValue) {
        this.array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            this.array[i] = random.nextInt(maxValue - minValue + 1) + minValue;
        }
    }
    
    // Геттеры и сеттеры
    public int[] getArray() {
        return array.clone();
    }
    
    public void setArray(int[] array) {
        this.array = array != null ? array.clone() : new int[0];
    }
    
    public int getElement(int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Индекс за пределами массива: " + index);
        }
        return array[index];
    }
    
    public void setElement(int index, int value) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Индекс за пределами массива: " + index);
        }
        array[index] = value;
    }
    
    public int getSize() {
        return array.length;
    }
    
    // Создание массива
    public static Massiv createMassiv(int size) {
        return new Massiv(size);
    }
    
    public static Massiv createRandomMassiv(int size, int min, int max) {
        return new Massiv(size, min, max);
    }
    
    // Заполнение массива случайными числами
    public void fillRandom(int min, int max) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
    }
    
    // Заполнение массива одним значением
    public void fillValue(int value) {
        Arrays.fill(array, value);
    }
    
    // Вывод массива на консоль
    public void printMassiv() {
        System.out.println("Массив: " + this.toString());
    }
    
    public void printMassiv(String title) {
        System.out.println(title + ": " + this.toString());
    }
    
    // === ОПЕРАЦИИ ПО ЗАДАНИЮ ===
    
    // 1. Сложение всех элементов массива
    public int sumAllElements() {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }
    
    // 2. Перемножение всех элементов массива
    public long multiplyAllElements() {
        if (array.length == 0) return 0;
        
        long product = 1;
        for (int value : array) {
            product *= value;
        }
        return product;
    }
    
    // 3. Вычитание заданного числа от всех элементов массива
    public Massiv subtractFromAllElements(int number) {
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] - number;
        }
        return new Massiv(result);
    }
    
    // Дополнительные полезные методы
    public double average() {
        if (array.length == 0) return 0.0;
        return (double) sumAllElements() / array.length;
    }
    
    public int max() {
        if (array.length == 0) throw new IllegalStateException("Массив пуст");
        
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
    
    public int min() {
        if (array.length == 0) throw new IllegalStateException("Массив пуст");
        
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }
    
    // Метод equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Massiv massiv = (Massiv) obj;
        return Arrays.equals(array, massiv.array);
    }
    
    // Метод hashCode
    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
    
    // Метод toString
    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}