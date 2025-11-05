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
 * @author Artem
 * @version 1.0
 */
public class AnimeDemo {
    private List<Anime> animeList;
    
    public AnimeDemo() {
        animeList = new ArrayList<>();
        initializeAnime();
    }
    
    /**
     * Инициализация списка аниме
     */
    private void initializeAnime() {
        // TVSeries
        animeList.add(new TVSeries(Set.of("Action", "Adventure", "Fantasy"), 8.9, 24, 
                                 LocalDate.of(2020, 4, 5), 1));
        animeList.add(new TVSeries(Set.of("Drama", "Romance"), 7.8, 12, 
                                 LocalDate.of(2019, 7, 12), 1));
        animeList.add(new TVSeries(Set.of("Comedy", "Slice of Life"), 8.2, 13, 
                                 LocalDate.of(2021, 1, 10), 1));
        animeList.add(new TVSeries(Set.of("Action", "Sci-Fi"), 8.7, 26, 
                                 LocalDate.of(2018, 10, 1), 2));
        
        // ONA
        animeList.add(new ONA(Set.of("Fantasy", "Mystery"), 8.1, 8, 
                            LocalDate.of(2023, 12, 15), "Netflix"));
        animeList.add(new ONA(Set.of("Comedy", "School"), 7.5, 10, 
                            LocalDate.of(2022, 9, 20), "Crunchyroll"));
        animeList.add(new ONA(Set.of("Action", "Supernatural"), 8.4, 12, 
                            LocalDate.of(2023, 11, 5), "Amazon Prime"));
        
        // OVA
        animeList.add(new OVA(Set.of("Action", "Mecha"), 7.9, 6, 
                            LocalDate.of(2021, 5, 10), 1, false));
        animeList.add(new OVA(Set.of("Romance", "Drama"), 8.0, 4, 
                            LocalDate.of(2022, 12, 1), 1, true));
        animeList.add(new OVA(Set.of("Comedy", "Ecchi"), 6.8, 3, 
                            LocalDate.of(2020, 8, 15), 1, false));
    }
    
    /**
     * Вывод аниме с рейтингом больше 7
     */
    public void showHighRatedAnime() {
        System.out.println("=== Аниме с рейтингом больше 7 ===");
        animeList.stream()
                .filter(anime -> anime.getRating() > 7.0)
                .forEach(System.out::println);
        System.out.println();
    }
    
    /**
     * Вывод недавно вышедших аниме
     */
    public void showRecentAnime() {
        System.out.println("=== Недавно вышедшие аниме ===");
        animeList.stream()
                .filter(Anime::isRecent)
                .forEach(System.out::println);
        System.out.println();
    }
    
    /**
     * Вывод самых популярных жанров
     */
    public void showMostCommonGenres() {
        System.out.println("=== Самые популярные жанры ===");
        
        Map<String, Long> genreFrequency = animeList.stream()
                .flatMap(anime -> anime.getGenres().stream())
                .collect(Collectors.groupingBy(genre -> genre, Collectors.counting()));
        
        genreFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " раз"));
        System.out.println();
    }
    
    /**
     * Основной метод демонстрации
     */
    public void demonstrate() {
        System.out.println("Всего аниме в базе: " + animeList.size());
        System.out.println();
        
        showHighRatedAnime();
        showRecentAnime();
        showMostCommonGenres();
    }
    
    public static void main(String[] args) {
        AnimeDemo demo = new AnimeDemo();
        demo.demonstrate();
        demo.saveToXML();
    }

    public void saveToXML() {
    try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        // Создаем корневой элемент
        Element rootElement = doc.createElement("animeCollection");
        doc.appendChild(rootElement);

        // Проходим по всем аниме в списке
        for (Anime anime : animeList) {
            Element animeElement = null;

            // Определяем тип аниме и создаем соответствующий элемент
            if (anime instanceof TVSeries) {
                TVSeries tv = (TVSeries) anime;
                animeElement = doc.createElement("TVSeries");
                addElement(doc, animeElement, "seasons", String.valueOf(tv.getSeasons()));
            } else if (anime instanceof ONA) {
                ONA ona = (ONA) anime;
                animeElement = doc.createElement("ONA");
                addElement(doc, animeElement, "platform", ona.getPlatform());
            } else if (anime instanceof OVA) {
                OVA ova = (OVA) anime;
                animeElement = doc.createElement("OVA");
                addElement(doc, animeElement, "seasons", String.valueOf(ova.getSeasons()));
                addElement(doc, animeElement, "isSpecial", String.valueOf(ova.isSpecial()));
            }

            if (animeElement != null) {
                // Добавляем общие элементы для всех типов
                addElement(doc, animeElement, "genres", String.join(", ", anime.getGenres()));
                addElement(doc, animeElement, "rating", String.valueOf(anime.getRating()));
                addElement(doc, animeElement, "episodes", String.valueOf(anime.getEpisodes()));
                addElement(doc, animeElement, "releaseDate", anime.getReleaseDate().toString());

                rootElement.appendChild(animeElement);
            }
        }

        // Записываем содержимое в XML файл
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("anime.xml"));

        transformer.transform(source, result);

        System.out.println("XML файл сохранен!");

    } catch (Exception e) {
        e.printStackTrace();
    }
}

// Вспомогательный метод для добавления элементов
private void addElement(Document doc, Element parent, String tagName, String textContent) {
    Element element = doc.createElement(tagName);
    element.setTextContent(textContent);
    parent.appendChild(element);
}

}