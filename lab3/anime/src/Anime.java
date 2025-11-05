import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;

/**
 * Базовый класс для представления аниме
 * РЕФАКТОРИНГ: Добавлены новые методы для расширенной функциональности
 * - Проверка популярности
 * - Категоризация по длине
 * - Работа с жанрами
 * @author Artem
 * @version 2.0
 */
public class Anime {
    private Set<String> genres;
    private double rating;
    private int episodes;
    private LocalDate releaseDate;
    
    public Anime(Set<String> genres, double rating, int episodes, LocalDate releaseDate) {
        this.genres = new HashSet<>(genres);
        this.rating = rating;
        this.episodes = episodes;
        this.releaseDate = releaseDate;
    }
    
    // Геттеры и сеттеры
    public Set<String> getGenres() { return genres; }
    public void setGenres(Set<String> genres) { this.genres = genres; }
    
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    
    public int getEpisodes() { return episodes; }
    public void setEpisodes(int episodes) { this.episodes = episodes; }
    
    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
    
    /**
     * Проверяет, является ли аниме недавно вышедшим
     * @return true если аниме вышло в последние 2 года
     */
    public boolean isRecent() {
        return releaseDate.isAfter(LocalDate.now().minusYears(2));
    }
    
    /**
     * РЕФАКТОРИНГ: Проверяет, является ли аниме популярным
     * @return true если рейтинг выше 8.0
     */
    public boolean isPopular() {
        return rating > 8.0;
    }
    
    /**
     * РЕФАКТОРИНГ: Возвращает категорию длины аниме
     * @return "Короткое", "Среднее" или "Длинное"
     */
    public String getLengthCategory() {
        if (episodes <= 12) return "Короткое";
        if (episodes <= 24) return "Среднее";
        return "Длинное";
    }
    
    /**
     * РЕФАКТОРИНГ: Проверяет, относится ли аниме к указанному жанру
     * @param genre жанр для проверки
     * @return true если аниме имеет указанный жанр
     */
    public boolean hasGenre(String genre) {
        return genres.contains(genre);
    }
    
    /**
     * РЕФАКТОРИНГ: Возвращает строковое представление жанров
     * @return строка с перечислением жанров
     */
    public String getGenresString() {
        return String.join(", ", genres);
    }
    
    @Override
    public String toString() {
        return String.format("Anime[rating=%.1f, episodes=%d, releaseDate=%s, genres=%s]", 
                           rating, episodes, releaseDate, genres);
    }
}