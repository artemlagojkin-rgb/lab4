import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;

/**
 * Базовый класс для представления аниме
 * @author Artem
 * @version 1.0
 */
public class Anime {
    private Set<String> genres;
    private double rating;
    private int episodes;
    private LocalDate releaseDate;
    
    /**
     * Конструктор класса Anime
     * @param genres набор жанров
     * @param rating рейтинг аниме
     * @param episodes количество серий
     * @param releaseDate дата выхода
     */
    public Anime(Set<String> genres, double rating, int episodes, LocalDate releaseDate) {
        this.genres = new HashSet<>(genres);
        this.rating = rating;
        this.episodes = episodes;
        this.releaseDate = releaseDate;
    }
    
    // Getters and setters
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
    
    @Override
    public String toString() {
        return String.format("Anime[rating=%.1f, episodes=%d, releaseDate=%s, genres=%s]", 
                           rating, episodes, releaseDate, genres);
    }
}