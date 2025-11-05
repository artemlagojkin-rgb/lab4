import java.time.LocalDate;
import java.util.Set;

/**
 * Класс для представления ONA (Original Net Animation) аниме
 * @author Artem
 * @version 1.0
 */
public class ONA extends Anime {
    private String platform;
    
    /**
     * Конструктор ONA
     * @param genres жанры
     * @param rating рейтинг
     * @param episodes количество серий
     * @param releaseDate дата выхода
     * @param platform платформа распространения
     */
    public ONA(Set<String> genres, double rating, int episodes, 
              LocalDate releaseDate, String platform) {
        super(genres, rating, episodes, releaseDate);
        this.platform = platform;
    }
    
    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }
    
    @Override
    public String toString() {
        return String.format("ONA[rating=%.1f, episodes=%d, platform=%s, releaseDate=%s]", 
                           getRating(), getEpisodes(), platform, getReleaseDate());
    }
}