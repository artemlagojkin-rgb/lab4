import java.time.LocalDate;
import java.util.Set;

/**
 * Класс для представления аниме-сериалов
 * РЕФАКТОРИНГ: Добавлены методы для работы с сезонами
 * @author Artem
 * @version 2.0
 */
public class TVSeries extends Anime {
    private int seasons;
    
    public TVSeries(Set<String> genres, double rating, int episodes, 
                   LocalDate releaseDate, int seasons) {
        super(genres, rating, episodes, releaseDate);
        this.seasons = seasons;
    }
    
    public int getSeasons() { return seasons; }
    public void setSeasons(int seasons) { this.seasons = seasons; }
    
    /**
     * РЕФАКТОРИНГ: Проверяет, является ли сериал многосезонным
     * @return true если количество сезонов больше 1
     */
    public boolean isMultiSeason() {
        return seasons > 1;
    }
    
    /**
     * РЕФАКТОРИНГ: Рассчитывает среднее количество эпизодов на сезон
     * @return среднее количество эпизодов
     */
    public double getEpisodesPerSeason() {
        return (double) getEpisodes() / seasons;
    }
    
    @Override
    public String toString() {
        return String.format("TVSeries[rating=%.1f, episodes=%d, seasons=%d, releaseDate=%s]", 
                           getRating(), getEpisodes(), seasons, getReleaseDate());
    }
}