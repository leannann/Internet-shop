package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchEngine {
    private final List<Searchable> items = new ArrayList<>();

    public void add(Searchable item) {
        items.add(item);
    }

    public Map<String, Searchable> search(String query) {
        Map<String, Searchable> results = new TreeMap<>();
        for (Searchable item : items) {
            if (item.getSearchTerm().contains(query)) {
                results.put(item.getName(), item);
            }
        }
        return results;
    }

    // Поиск самого подходящего элемента
    public Searchable findBestMatch(String search) throws BestResultNotFound {
        Searchable bestMatch = null;
        int maxCount = 0;

        for (Searchable item : items) {
            String term = item.getSearchTerm();
            int count = countOccurrences(term, search);
            if (count > maxCount) {
                maxCount = count;
                bestMatch = item;
            }
        }

        if (maxCount == 0) {
            throw new BestResultNotFound(search);
        }

        return bestMatch;
    }

    // Подсчёт количества вхождений подстроки
    private int countOccurrences(String text, String sub) {
        if (sub == null || sub.isEmpty() || text == null || text.isEmpty()) {
            return 0;
        }

        int count = 0;
        int index = 0;
        while ((index = text.indexOf(sub, index)) != -1) {
            count++;
            index += sub.length();
        }
        return count;
    }

    public static void printSearchResults(Map<String, Searchable> results) {
        if (results.isEmpty()) {
            System.out.println("Ничего не найдено.");
        } else {
            for (Map.Entry<String, Searchable> entry : results.entrySet()) {
                System.out.println("Имя: " + entry.getKey() + " | Объект: " + entry.getValue());
            }
        }
    }
}
