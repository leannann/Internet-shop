package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    private final List<Searchable> items = new ArrayList<>();

    public void add(Searchable item) {
        items.add(item);
    }

    public List<Searchable> search(String query) {
        List<Searchable> results = new ArrayList<>();
        for (Searchable item : items) {
            if (item.getSearchTerm().contains(query)) {
                results.add(item);
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
}
