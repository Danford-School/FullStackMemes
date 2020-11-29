import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class GenerateMeme {

    static String url = "https://api.imgflip.com/caption_image";

    //I am not sure what to do with main other than testing
    public static void main(String[] args) throws IOException, InterruptedException {
        //this is a test
        PostBuilder("61579", "One does not simply", "make a meme program for fun");
    }
    //this creates the string for the post. We can add font, font size, and box location if desired.
    //change void to string when we have something to send it to
    public static void PostBuilder(String id, String topText, String bottomText) throws IOException, InterruptedException {
        Credentials cred = new Credentials();

        Map<Object, Object> parameters = new HashMap<>();
        parameters.put("template_id", id);
        parameters.put("username", cred.username);
        parameters.put("password", cred.password);
        parameters.put("text0", topText);
        parameters.put("text1", bottomText);

        HttpRequest sendMe = HttpRequest.newBuilder()
                .POST(BuildHttpDataFromMap(parameters))
                .uri(URI.create(url))
                .build();

        HttpResponse<String> postMe = HttpClient.newHttpClient().send(sendMe, HttpResponse.BodyHandlers.ofString());

        //error checking
        System.out.println(postMe.statusCode());

        //what does it say?
        System.out.println(postMe.body());
    }

    private static HttpRequest.BodyPublisher BuildHttpDataFromMap(Map<Object, Object> map) {
        var builder = new StringBuilder();
        for(Map.Entry<Object, Object> entry : map.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.US_ASCII));
            builder.append(":");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.US_ASCII));
            //entry.
        }
        System.out.println(builder.toString());
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
}
