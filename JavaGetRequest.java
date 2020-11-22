import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JavaGetRequest {
    private static HttpURLConnection con;

    public static void main(String[] args) throws IOException {

        var url = "https://api.imgflip.com/get_memes";

        try {

            var myurl = new URL(url);

            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");

            StringBuilder stuff;

            //this should get output from the api
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {

                String line;
                stuff = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    stuff.append(line);
                    stuff.append(System.lineSeparator());
                }
            }
            //this is for a test, should be replaced with a function that gives urls to display
            System.out.println((stuff.toString()));
        } finally {
            con.disconnect();
        }
    }
}