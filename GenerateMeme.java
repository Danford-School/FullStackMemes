import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;


public class GenerateMeme {
    private static HttpURLConnection con;
    static String url = "https://api.imgflip.com/caption_image?";

    //I am not sure what to do with main other than testing
    public static void main(String[] args) throws IOException, InterruptedException {

        //this is a test
        PostBuilder("61579", "One does not simply", "make a meme program for fun");
    }
    //this creates the string for the post. We can add font, font size, and box location if desired.
    //change void to string when we have something to send it to
    public static String PostBuilder(String id, String topText, String bottomText) throws IOException, InterruptedException {
        Credentials cred = new Credentials();
        HttpResponse<String> postMe;
        try {

            var myurl = new URL(url);

            con = (HttpURLConnection) myurl.openConnection();

            //con.setRequestMethod("POST");

            Map<Object, Object> parameters = new LinkedHashMap<>();
            parameters.put("template_id", id);
            parameters.put("username", cred.username);
            parameters.put("password", cred.password);
            parameters.put("text0", topText);
            parameters.put("text1", bottomText);

            //this is because post does not work like a normal api
            String realURL = url.concat(BuildHttpDataFromMap(parameters));

            HttpRequest sendMe = HttpRequest.newBuilder()
                    .uri(URI.create(realURL))
                    .build();

            postMe = HttpClient.newHttpClient().send(sendMe, HttpResponse.BodyHandlers.ofString());
            //error checking
            System.out.println(postMe.statusCode());

            //what does it say?
            System.out.println(postMe.body());
        } finally {
            con.disconnect();
        }
        return postMe.body();
    }
// not sure how to do this yet
    public static void searchMemes(String searchString) {

    }

    //helper function to ensure that data is passed correctly
    private static String BuildHttpDataFromMap(Map<Object, Object> map) {
        var builder = new StringBuilder();
        for(Map.Entry<Object, Object> entry : map.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), Charset.defaultCharset()));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), Charset.defaultCharset()));
        }
        System.out.println(builder);
        return builder.toString();
    }
}
