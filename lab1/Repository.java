package lab1;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс репозитория для управления коллекцией абитуриентов
 * РЕФАКТОРИНГ: Добавлен для расширения функциональности управления данными
 * Обеспечивает методы добавления, удаления, изменения и поиска элементов
 */
public class Repository<T> {
    private List<T> items;

    public Repository() {
        items = new ArrayList<>();
    }

    /**
     * РЕФАКТОРИНГ: Метод добавления элемента в коллекцию
     * @param item - элемент для добавления
     */
    public void add(T item) {
        items.add(item);
        System.out.println("Элемент успешно добавлен в репозиторий");
    }

    /**
     * РЕФАКТОРИНГ: Метод удаления элемента из коллекции
     * @param item - элемент для удаления
     */
    public void remove(T item) {
        if (items.remove(item)) {
            System.out.println("Элемент успешно удален из репозитория");
        } else {
            System.out.println("Элемент не найден в репозитории");
        }
    }

    /**
     * РЕФАКТОРИНГ: Метод изменения элемента по индексу
     * @param index - индекс изменяемого элемента
     * @param newItem - новый элемент
     */
    public void update(int index, T newItem) {
        if (index >= 0 && index < items.size()) {
            items.set(index, newItem);
            System.out.println("Элемент с индексом " + index + " успешно обновлен");
        } else {
            System.out.println("Неверный индекс элемента");
        }
    }

    /**
     * РЕФАКТОРИНГ: Метод получения всех элементов коллекции
     * @return список всех элементов
     */
    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    /**
     * РЕФАКТОРИНГ: Метод получения элемента по индексу
     * @param index - индекс элемента
     * @return элемент или null если индекс неверный
     */
    public T get(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    /**
     * РЕФАКТОРИНГ: Метод получения размера коллекции
     * @return количество элементов
     */
    public int size() {
        return items.size();
    }

    /**
     * РЕФАКТОРИНГ: Поиск элементов по критерию
     * @param filter - фильтр для поиска
     * @return список найденных элементов
     */
    public List<T> find(java.util.function.Predicate<T> filter) {
        List<T> result = new ArrayList<>();
        for (T item : items) {
            if (filter.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
}