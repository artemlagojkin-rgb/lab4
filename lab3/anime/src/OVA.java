import java.time.LocalDate;
import java.util.Set;

/**
 * Класс для представления OVA (Original Video Animation) аниме
 * РЕФАКТОРИНГ: Добавлены методы для работы с типами выпусков
 * @author Artem
 * @version 2.0
 */
public class OVA extends TVSeries {
    private boolean isSpecial;
    
    public OVA(Set<String> genres, double rating, int episodes, 
              LocalDate releaseDate, int seasons, boolean isSpecial) {
        super(genres, rating, episodes, releaseDate, seasons);
        this.isSpecial = isSpecial;
    }
    
    public boolean isSpecial() { return isSpecial; }
    public void setSpecial(boolean special) { isSpecial = special; }
    
    /**
     * РЕФАКТОРИНГ: Получение типа выпуска
     * @return "Специальный выпуск" или "Обычный выпуск"
     */
    public String getReleaseType() {
        return isSpecial ? "Специальный выпуск" : "Обычный выпуск";
    }
    
    /**
     * РЕФАКТОРИНГ: Проверяет, является ли OVA коллекционным
     * @return true если это специальный выпуск и рейтинг выше 7.5
     */
    public boolean isCollectorsEdition() {
        return isSpecial && getRating() > 7.5;
    }
    
    @Override
    public String toString() {
        return String.format("OVA[rating=%.1f, episodes=%d, special=%s, releaseDate=%s]", 
                           getRating(), getEpisodes(), isSpecial, getReleaseDate());
    }
}