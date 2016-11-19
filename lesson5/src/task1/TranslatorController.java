package task1;

import task1.exceptions.SourceLoadingException;
import task1.exceptions.TranslateException;
import task1.source.SourceLoader;
import task1.source.URLSourceProvider;

import java.io.IOException;
import java.util.Scanner;

public class TranslatorController {

    public static void main(String[] args) throws IOException {
        SourceLoader sourceLoader = new SourceLoader();
        Translator translator = new Translator(new URLSourceProvider());

        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();

        while (!"exit".equals(command)) {
            try {
                String source = sourceLoader.loadSource(command);
                if (source == null) {
                    throw new SourceLoadingException();
                }

                String translation = translator.translate(source);
                System.out.println("Original: " + source);
                System.out.println("Translation: " + translation);
            } catch (TranslateException e) {
                System.out.println("Exception: error while processing response from Yandex Translator API service. " +
                        "Can't parse it. Try again or try to change API key.");
            } catch (SourceLoadingException e) {
                System.out.println("Exception: provided source path isn't a local file or Internet link.\n" +
                        "You must provide absolute path to a local file or full URL starts with http:// or https:// or ftp://");
            }

            command = scanner.next();
        }
    }
}