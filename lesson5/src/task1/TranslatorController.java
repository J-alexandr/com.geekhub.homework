package task1;

import org.xml.sax.SAXException;
import task1.source.SourceLoader;
import task1.source.URLSourceProvider;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class TranslatorController {

    public static void main(String[] args) throws IOException {
        //initialization
        SourceLoader sourceLoader = new SourceLoader();
        Translator translator = new Translator(new URLSourceProvider());

        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();

        while (!"exit".equals(command)) {
            //So, the only way to stop the application is to do that manually or type "exit"
            try {
                String source = sourceLoader.loadSource(command);
                String translation = translator.translate(source);

                System.out.println("Original: " + source);
                System.out.println("Translation: " + translation);
            } catch (NullPointerException e) {
                System.out.println("Exception: provided source path isn't a local file or Internet link.\n" +
                        "You must provide absolute path to a local file or full URL starts with http:// or https:// or ftp://");
            } catch (SAXException e) {
                System.out.println("Exception: got bad response from Yandex Translator API service. Can't parse it. Try again or try to change API key.");
            } catch (ParserConfigurationException e) {
                System.out.println("Exception: got serious configuration error at DocumentBuilderFactory." +
                        "\nPlease contact developer with next message:\n" +
                        e.getMessage());
            }

            command = scanner.next();
        }
    }
}