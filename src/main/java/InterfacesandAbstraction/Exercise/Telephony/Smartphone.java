package InterfacesandAbstraction.Exercise.Telephony;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Smartphone implements Callable, Browsable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String call() {
        StringBuilder sb = new StringBuilder();
        String regex = "(?<number>^[0-9]+$)";
        Pattern pattern = Pattern.compile(regex);
        for (String number : numbers) {
            Matcher matcher = pattern.matcher(number);
            boolean isValid = false;
            while (matcher.find()) {
                String currentNumber = matcher.group("number");
                sb.append("Calling... ").append(currentNumber)
                        .append(System.lineSeparator());
                isValid = true;
            }
            if (!isValid) {
                sb.append("Invalid number!")
                        .append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }

    @Override
    public String browse() {
        StringBuilder sb = new StringBuilder();
        String regex = "(?<urls>http:\\/\\/[A-Za-z]{1,}.[A-Za-z]{2,})";
        Pattern pattern = Pattern.compile(regex);
        for (String url : urls) {
            Matcher matcher = pattern.matcher(url);
            boolean isValid = false;
            while (matcher.find()) {
                String currentUrls = matcher.group("urls");
                sb.append("Browsing: ").append(currentUrls)
                        .append("!")
                        .append(System.lineSeparator());
                isValid = true;
            }
            if (!isValid) {
                sb.append("Invalid URL!")
                        .append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }
}
