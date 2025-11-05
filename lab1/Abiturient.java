package lab1;

import java.io.BufferedReader;
import java.io.IOException;
public class Abiturient {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String address;
    private String phone;
    private int[] grades;
    
    // Конструкторы
    public Abiturient() {
    }
    
    public Abiturient(int id, String lastName, String firstName, String middleName, 
                     String address, String phone, int[] grades) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.address = address;
        this.phone = phone;
        this.grades = grades;
    }
    
    // Геттеры и сеттеры
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getMiddleName() {
        return middleName;
    }
    
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public int[] getGrades() {
        return grades;
    }
    
    public void setGrades(int[] grades) {
        this.grades = grades;
    }
    
    // Метод для вычисления среднего балла
    public double getAverageGrade() {
        if (grades == null || grades.length == 0) {
            return 0.0;
        }
        
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.length;
    }
    
    // Метод для проверки наличия неудовлетворительных оценок
    public boolean hasUnsatisfactoryGrades() {
        if (grades == null) return false;
        
        for (int grade : grades) {
            if (grade < 3) { // считаем оценку < 3 неудовлетворительной
                return true;
            }
        }
        return false;
    }
    
    // Метод toString
    @Override
    public String toString() {
        return String.format("ID: %d, %s %s %s, Адрес: %s, Телефон: %s, Средний балл: %.2f", 
                           id, lastName, firstName, middleName, address, phone, getAverageGrade());
    }
    
    // Метод для создания абитуриента с вводом данных от пользователя
    public static Abiturient createAbiturientFromInput(BufferedReader reader, int id) throws IOException {
        System.out.println("\n=== Ввод данных для абитуриента " + id + " ===");
        
        System.out.print("Введите фамилию: ");
        String lastName = reader.readLine();
        
        System.out.print("Введите имя: ");
        String firstName = reader.readLine();
        
        System.out.print("Введите отчество: ");
        String middleName = reader.readLine();
        
        System.out.print("Введите адрес: ");
        String address = reader.readLine();
        
        System.out.print("Введите телефон: ");
        String phone = reader.readLine();
        
        System.out.print("Введите количество оценок: ");
        int gradeCount = Integer.parseInt(reader.readLine());
        
        int[] grades = new int[gradeCount];
        for (int i = 0; i < gradeCount; i++) {
            System.out.print("Введите оценку " + (i + 1) + ": ");
            grades[i] = Integer.parseInt(reader.readLine());
        }
        
        return new Abiturient(id, lastName, firstName, middleName, address, phone, grades);
    }
}