package org.skypro.skyshop.search;

import java.util.*;

public class SearchEngine {
    private final Set<Searchable> items = new HashSet<>();

    public void add(Searchable item) {
        items.add(item);
    }

    public Set<Searchable> search(String query) {
        Comparator<Searchable> searchResultComparator = (s1, s2) -> {
            int lengthCompare = Integer.compare(s2.getName().length(), s1.getName().length());
            if (lengthCompare != 0) {
                return lengthCompare;
            }
            return s1.getName().compareTo(s2.getName());
        };

        Set<Searchable> results = new TreeSet<>(searchResultComparator);

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

    public static void printSearchResults(SearchEngine engine, String query) {
        Set<Searchable> results = engine.search(query);

        System.out.println("Результаты поиска по запросу \"" + query + "\":");
        if (results.isEmpty()) {
            System.out.println("Ничего не найдено.");
        } else {
            for (Searchable item : results) {
                System.out.println(item); // item.toString(), где имя уже присутствует
            }
        }
    }
}
