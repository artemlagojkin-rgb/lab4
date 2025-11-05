import java.time.LocalDate;
import java.util.Set;

/**
 * Класс для представления аниме-сериалов
 * @author Artem
 * @version 1.0
 */
public class TVSeries extends Anime {
    private int seasons;
    
    /**
     * Конструктор TVSeries
     * @param genres жанры
     * @param rating рейтинг
     * @param episodes количество серий
     * @param releaseDate дата выхода
     * @param seasons количество сезонов
     */
    public TVSeries(Set<String> genres, double rating, int episodes, 
                   LocalDate releaseDate, int seasons) {
        super(genres, rating, episodes, releaseDate);
        this.seasons = seasons;
    }
    
    public int getSeasons() { return seasons; }
    public void setSeasons(int seasons) { this.seasons = seasons; }
    
    @Override
    public String toString() {
        return String.format("TVSeries[rating=%.1f, episodes=%d, seasons=%d, releaseDate=%s]", 
                           getRating(), getEpisodes(), seasons, getReleaseDate());
    }
}