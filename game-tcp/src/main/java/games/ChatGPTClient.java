package games;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

public class ChatGPTClient {
    private static final String API_KEY ="";
    private static final String API_ENDPOINT = "https://api.openai.com/v1/engines/davinci-codex/completions";
    // private static final String API_ENDPOINT =
    // "https://api.openai.com/v1/engine/chat/completions";
    private static final String INPUT_TEXT = "Hello, how are you? need help with java api";
    private static final int MAX_TOKENS = 100;

    public static void sendMessage(String message) throws URISyntaxException {
        try {
            URI uri = new URI(API_ENDPOINT);
            URL url = uri.toURL();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + API_KEY);
            con.setRequestProperty("Content-Type", "application/json");

            String inputJson = "{\"prompt\": \"" + URLEncoder.encode(message, "UTF-8") + "\", \"max_tokens\": "
                    + MAX_TOKENS + "}";
            con.setDoOutput(true);
            con.getOutputStream().write(inputJson.getBytes("UTF-8"));

            InputStream reader = con.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(reader));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
