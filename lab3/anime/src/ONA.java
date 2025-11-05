import java.time.LocalDate;
import java.util.Set;

/**
 * Класс для представления ONA (Original Net Animation) аниме
 * РЕФАКТОРИНГ: Добавлены методы для работы с платформами
 * @author Artem
 * @version 2.0
 */
public class ONA extends Anime {
    private String platform;
    
    public ONA(Set<String> genres, double rating, int episodes, 
              LocalDate releaseDate, String platform) {
        super(genres, rating, episodes, releaseDate);
        this.platform = platform;
    }
    
    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }
    
    /**
     * РЕФАКТОРИНГ: Проверяет, является ли платформа популярной
     * @return true если платформа Netflix или Crunchyroll
     */
    public boolean isPopularPlatform() {
        return "Netflix".equals(platform) || "Crunchyroll".equals(platform);
    }
    
    /**
     * РЕФАКТОРИНГ: Возвращает категорию платформы
     * @return "Крупная платформа" или "Нишевая платформа"
     */
    public String getPlatformCategory() {
        return isPopularPlatform() ? "Крупная платформа" : "Нишевая платформа";
    }
    
    @Override
    public String toString() {
        return String.format("ONA[rating=%.1f, episodes=%d, platform=%s, releaseDate=%s]", 
                           getRating(), getEpisodes(), platform, getReleaseDate());
    }
}