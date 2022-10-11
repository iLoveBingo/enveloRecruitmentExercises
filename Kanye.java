import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Scanner;

public class Kanye {

    private final String STRING_URL = "https://api.kanye.rest/text";
    private final HashSet<String> kayneHashSet = new HashSet<>();
    private String currentKayneQuote;
    private boolean kayneRepeatsHimself = false;

    public Kanye() {
        askKayneForQuote();
        showCurrentKayneQuote();
        while (isKayneRepeatsHimself() || nextAction() ) {
            askKayneForQuote();
            if(isKayneRepeatsHimself()) {
                continue;
            }
            showCurrentKayneQuote();
        }
    }

    public boolean isKayneRepeatsHimself() {
        return kayneRepeatsHimself;
    }
    public void setKayneRepeatsHimself(boolean kayneRepeatsHimself) {
        this.kayneRepeatsHimself = kayneRepeatsHimself;
    }
    public String getCurrentKayneQuote() {
        return currentKayneQuote;
    }
    public void setCurrentKayneQuote(String currentKayneQuote) {
        this.currentKayneQuote = currentKayneQuote;
    }

    private void askKayneForQuote() {
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(STRING_URL))
                    .GET()
                    .build();
            String response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .join();
            setCurrentKayneQuote(response);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (kayneHashSet.contains(getCurrentKayneQuote())) {
            setKayneRepeatsHimself(true);
        } else if (isKayneRepeatsHimself()) {
            setKayneRepeatsHimself(false);
        }
    }
    private void showCurrentKayneQuote() {
        kayneHashSet.add(getCurrentKayneQuote());
        System.out.println(getCurrentKayneQuote());
    }
    private boolean nextAction() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next().equals("next");
    }

    public static void main(String[] args) {
        new Kanye();
    }
}