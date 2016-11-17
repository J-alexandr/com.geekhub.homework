package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    private static StringSimplifier simplifier;

    public static void main(String[] args) {
        simplifier = new StringSimplifier();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter number from 1 to 100:");
            int n = Integer.parseInt(reader.readLine());
            System.out.println("Enter " + n + " word(s):");
            readWords(n, reader);
            printSimplifiedWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printSimplifiedWords() {
        List<String> simpleWords = simplifier.getSimpleWords();
        simpleWords.forEach(System.out::println);
    }

    private static void readWords(int n, BufferedReader reader) throws IOException {
        for (int i = 0; i < n; i++) {
            simplifier.addInputWord(reader.readLine());
        }
    }
}