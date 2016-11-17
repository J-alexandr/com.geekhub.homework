package task2;

import java.util.ArrayList;
import java.util.List;

class StringSimplifier {

    private List<String> inputWords;

    StringSimplifier() {
        inputWords = new ArrayList<>();
    }

    void addInputWord(String word) {
        inputWords.add(word);
    }

    List<String> getSimpleWords() {
        List<String> simpleWords = new ArrayList<>();
        for (String word : inputWords) {
            if (word.length() >= 10)
                simpleWords.add(simplify(word));
            else
                simpleWords.add(word);
        }
        return simpleWords;
    }

    private String simplify(String word) {
        return String.valueOf(word.charAt(0)) +
                (word.length() - 2) +
                word.charAt(word.length() - 1);
    }
}
