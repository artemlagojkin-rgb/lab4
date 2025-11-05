import java.time.LocalDate;
import java.util.Set;

/**
 * Класс для представления OVA (Original Video Animation) аниме
 * @author Artem
 * @version 1.0
 */
public class OVA extends TVSeries {
    private boolean isSpecial;
    
    /**
     * Конструктор OVA
     * @param genres жанры
     * @param rating рейтинг
     * @param episodes количество серий
     * @param releaseDate дата выхода
     * @param seasons количество сезонов
     * @param isSpecial является ли специальным выпуском
     */
    public OVA(Set<String> genres, double rating, int episodes, 
              LocalDate releaseDate, int seasons, boolean isSpecial) {
        super(genres, rating, episodes, releaseDate, seasons);
        this.isSpecial = isSpecial;
    }
    
    public boolean isSpecial() { return isSpecial; }
    public void setSpecial(boolean special) { isSpecial = special; }
    
    @Override
    public String toString() {
        return String.format("OVA[rating=%.1f, episodes=%d, special=%s, releaseDate=%s]", 
                           getRating(), getEpisodes(), isSpecial, getReleaseDate());
    }
}