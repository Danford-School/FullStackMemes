import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class JavaGetRequest {


    // use main for testing I guess
    public static void main(String[] args) throws IOException {
    //    String data = GetMemes();
    //    System.out.println(data);
    }

    //This makes an API call and returns the JSON response as a string. It need parsed, but that will probably be easier
    //in javascript.
    public static String GetMemes() throws IOException {
        //we need these
        HttpURLConnection con = null;
        StringBuilder stuff;

        var url = "https://api.imgflip.com/get_memes";

        try {

            var myurl = new URL(url);

            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");

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
            assert con != null;
            con.disconnect();
        }
        return stuff.toString();
    }
}