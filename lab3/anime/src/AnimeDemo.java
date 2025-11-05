import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import java.io.*;

/**
 * Демонстрационное приложение для работы с аниме
 * РЕФАКТОРИНГ: Улучшена архитектура без использования Repository
 * - Добавлены методы для расширенной фильтрации
 * - Улучшена обработка ошибок
 * - Добавлена статистика
 * - Улучшена модульность кода
 * @author Artem
 * @version 2.0
 */
public class AnimeDemo {
    private List<Anime> animeList;
    
    public AnimeDemo() {
        animeList = new ArrayList<>();
        initializeAnime();
    }
    
    /**
     * Инициализация списка аниме
     * РЕФАКТОРИНГ: Разделена на методы для лучшей читаемости
     */
    private void initializeAnime() {
        initializeTVSeries();
        initializeONA();
        initializeOVA();
        System.out.println("РЕФАКТОРИНГ: Инициализация завершена. Загружено " + animeList.size() + " аниме");
    }
    
    /**
     * РЕФАКТОРИНГ: Вынесена инициализация TV Series в отдельный метод
     */
    private void initializeTVSeries() {
        animeList.add(new TVSeries(Set.of("Action", "Adventure", "Fantasy"), 8.9, 24, 
                                 LocalDate.of(2020, 4, 5), 1));
        animeList.add(new TVSeries(Set.of("Drama", "Romance"), 7.8, 12, 
                                 LocalDate.of(2019, 7, 12), 1));
        animeList.add(new TVSeries(Set.of("Comedy", "Slice of Life"), 8.2, 13, 
                                 LocalDate.of(2021, 1, 10), 1));
        animeList.add(new TVSeries(Set.of("Action", "Sci-Fi"), 8.7, 26, 
                                 LocalDate.of(2018, 10, 1), 2));
    }
    
    /**
     * РЕФАКТОРИНГ: Вынесена инициализация ONA в отдельный метод
     */
    private void initializeONA() {
        animeList.add(new ONA(Set.of("Fantasy", "Mystery"), 8.1, 8, 
                            LocalDate.of(2023, 12, 15), "Netflix"));
        animeList.add(new ONA(Set.of("Comedy", "School"), 7.5, 10, 
                            LocalDate.of(2022, 9, 20), "Crunchyroll"));
        animeList.add(new ONA(Set.of("Action", "Supernatural"), 8.4, 12, 
                            LocalDate.of(2023, 11, 5), "Amazon Prime"));
    }
    
    /**
     * РЕФАКТОРИНГ: Вынесена инициализация OVA в отдельный метод
     */
    private void initializeOVA() {
        animeList.add(new OVA(Set.of("Action", "Mecha"), 7.9, 6, 
                            LocalDate.of(2021, 5, 10), 1, false));
        animeList.add(new OVA(Set.of("Romance", "Drama"), 8.0, 4, 
                            LocalDate.of(2022, 12, 1), 1, true));
        animeList.add(new OVA(Set.of("Comedy", "Ecchi"), 6.8, 3, 
                            LocalDate.of(2020, 8, 15), 1, false));
    }
    
    /**
     * Вывод аниме с рейтингом больше 7
     * РЕФАКТОРИНГ: Улучшена обработка пустых результатов
     */
    public void showHighRatedAnime() {
        System.out.println("=== Аниме с рейтингом больше 7 ===");
        List<Anime> highRated = animeList.stream()
                .filter(anime -> anime.getRating() > 7.0)
                .collect(Collectors.toList());
        
        if (!highRated.isEmpty()) {
            highRated.forEach(System.out::println);
            System.out.println("Найдено: " + highRated.size() + " аниме");
        } else {
            System.out.println("Аниме с рейтингом больше 7 не найдено.");
        }
        System.out.println();
    }
    
    /**
     * Вывод недавно вышедших аниме
     * РЕФАКТОРИНГ: Добавлена статистика по результатам
     */
    public void showRecentAnime() {
        System.out.println("=== Недавно вышедшие аниме (последние 2 года) ===");
        List<Anime> recentAnime = animeList.stream()
                .filter(Anime::isRecent)
                .collect(Collectors.toList());
        
        if (!recentAnime.isEmpty()) {
            recentAnime.forEach(System.out::println);
            System.out.println("Найдено: " + recentAnime.size() + " аниме");
        } else {
            System.out.println("Недавно вышедших аниме не найдено.");
        }
        System.out.println();
    }
    
    /**
     * РЕФАКТОРИНГ: Новый метод - поиск аниме по жанру
     * @param genre жанр для поиска
     */
    public void findAnimeByGenre(String genre) {
        System.out.println("=== Поиск аниме по жанру: " + genre + " ===");
        List<Anime> genreAnime = animeList.stream()
                .filter(anime -> anime.getGenres().contains(genre))
                .collect(Collectors.toList());
        
        if (!genreAnime.isEmpty()) {
            genreAnime.forEach(System.out::println);
            System.out.println("Найдено: " + genreAnime.size() + " аниме");
        } else {
            System.out.println("Аниме с жанром '" + genre + "' не найдено.");
        }
        System.out.println();
    }
    
    /**
     * РЕФАКТОРИНГ: Новый метод - топ аниме по рейтингу
     * @param limit количество аниме в топе
     */
    public void showTopRatedAnime(int limit) {
        System.out.println("=== Топ " + limit + " аниме по рейтингу ===");
        animeList.stream()
                .sorted((a1, a2) -> Double.compare(a2.getRating(), a1.getRating()))
                .limit(limit)
                .forEach(anime -> System.out.printf("%.1f - %s\n", 
                    anime.getRating(), anime.getClass().getSimpleName()));
        System.out.println();
    }
    
    /**
     * Вывод самых популярных жанров
     * РЕФАКТОРИНГ: Улучшена визуализация результатов
     */
    public void showMostCommonGenres() {
        System.out.println("=== Статистика по жанрам ===");
        
        Map<String, Long> genreFrequency = animeList.stream()
                .flatMap(anime -> anime.getGenres().stream())
                .collect(Collectors.groupingBy(genre -> genre, Collectors.counting()));
        
        // РЕФАКТОРИНГ: Улучшено форматирование вывода
        genreFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> {
                    int percentage = (int) ((entry.getValue() * 100) / animeList.size());
                    System.out.printf("%-15s: %d раз (%.0f%%)\n", 
                        entry.getKey(), entry.getValue(), percentage);
                });
        System.out.println();
    }
    
    /**
     * РЕФАКТОРИНГ: Новый метод - общая статистика по коллекции
     */
    public void showCollectionStatistics() {
        System.out.println("=== Общая статистика коллекции ===");
        System.out.println("Всего аниме: " + animeList.size());
        
        long tvCount = animeList.stream().filter(a -> a instanceof TVSeries).count();
        long onaCount = animeList.stream().filter(a -> a instanceof ONA).count();
        long ovaCount = animeList.stream().filter(a -> a instanceof OVA).count();
        
        System.out.printf("TV Series: %d (%.0f%%)\n", tvCount, (tvCount * 100.0 / animeList.size()));
        System.out.printf("ONA: %d (%.0f%%)\n", onaCount, (onaCount * 100.0 / animeList.size()));
        System.out.printf("OVA: %d (%.0f%%)\n", ovaCount, (ovaCount * 100.0 / animeList.size()));
        
        double avgRating = animeList.stream()
                .mapToDouble(Anime::getRating)
                .average()
                .orElse(0.0);
        System.out.printf("Средний рейтинг: %.2f\n", avgRating);
        System.out.println();
    }
    
    /**
     * Основной метод демонстрации
     * РЕФАКТОРИНГ: Полностью переработан с новыми функциями
     */
    public void demonstrate() {
        System.out.println("=== Демонстрация работы системы аниме ===");
        System.out.println("РЕФАКТОРИНГ: Добавлены новые методы фильтрации и статистики\n");
        
        showCollectionStatistics();
        showHighRatedAnime();
        showRecentAnime();
        showTopRatedAnime(5);
        showMostCommonGenres();
        
        // РЕФАКТОРИНГ: Демонстрация поиска по жанрам
        findAnimeByGenre("Action");
        findAnimeByGenre("Comedy");
    }
    
    /**
     * Сохранение данных в XML файл
     * РЕФАКТОРИНГ: Улучшена обработка ошибок и структура кода
     */
    public void saveToXML() {
        try {
            Document doc = createXMLDocument();
            saveDocumentToFile(doc);
            System.out.println("РЕФАКТОРИНГ: XML файл успешно сохранен как 'anime.xml'!");

        } catch (Exception e) {
            System.err.println("Ошибка при сохранении XML: " + e.getMessage());
        }
    }
    
    /**
     * РЕФАКТОРИНГ: Вынесено создание XML документа в отдельный метод
     */
    private Document createXMLDocument() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element rootElement = doc.createElement("animeCollection");
        doc.appendChild(rootElement);

        for (Anime anime : animeList) {
            Element animeElement = createAnimeElement(doc, anime);
            if (animeElement != null) {
                rootElement.appendChild(animeElement);
            }
        }
        
        return doc;
    }
    
    /**
     * РЕФАКТОРИНГ: Вынесено создание элемента аниме в отдельный метод
     */
    private Element createAnimeElement(Document doc, Anime anime) {
        Element animeElement = null;
        String type = "";

        if (anime instanceof TVSeries) {
            TVSeries tv = (TVSeries) anime;
            animeElement = doc.createElement("TVSeries");
            type = "TVSeries";
            addElement(doc, animeElement, "seasons", String.valueOf(tv.getSeasons()));
        } else if (anime instanceof ONA) {
            ONA ona = (ONA) anime;
            animeElement = doc.createElement("ONA");
            type = "ONA";
            addElement(doc, animeElement, "platform", ona.getPlatform());
        } else if (anime instanceof OVA) {
            OVA ova = (OVA) anime;
            animeElement = doc.createElement("OVA");
            type = "OVA";
            addElement(doc, animeElement, "seasons", String.valueOf(ova.getSeasons()));
            addElement(doc, animeElement, "isSpecial", String.valueOf(ova.isSpecial()));
        }

        if (animeElement != null) {
            // РЕФАКТОРИНГ: Добавлен тип аниме для лучшей идентификации
            addElement(doc, animeElement, "type", type);
            addElement(doc, animeElement, "genres", String.join(", ", anime.getGenres()));
            addElement(doc, animeElement, "rating", String.valueOf(anime.getRating()));
            addElement(doc, animeElement, "episodes", String.valueOf(anime.getEpisodes()));
            addElement(doc, animeElement, "releaseDate", anime.getReleaseDate().toString());
        }

        return animeElement;
    }
    
    /**
     * РЕФАКТОРИНГ: Вынесено сохранение документа в отдельный метод
     */
    private void saveDocumentToFile(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("anime.xml"));
        transformer.transform(source, result);
    }

    /**
     * Вспомогательный метод для добавления элементов в XML
     */
    private void addElement(Document doc, Element parent, String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        element.setTextContent(textContent);
        parent.appendChild(element);
    }

    public static void main(String[] args) {
        AnimeDemo demo = new AnimeDemo();
        demo.demonstrate();
        demo.saveToXML();
    }
}