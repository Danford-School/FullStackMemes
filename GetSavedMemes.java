import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GetSavedMemes {
    public static void main(String[] args) {

    }

    //this will return an arraylist of the meme urls that have been saved to an external file.
    //currently it will only check "savedmemes.txt" which is what GetSavedMemes.SaveMemes() creates
    public static ArrayList<String> getYourMemes() {
        ArrayList<String> array = new ArrayList<>();
        try {
            File file = new File("savedmemes.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                array.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException g) {
            System.out.println("Saved file not found :(");
        }
        return array;
    }
    //this will delete a saved meme from the external arraylist. 
    public static void DeleteSavedMeme(String url) {
        ArrayList<String> replacement = new ArrayList<>();
        ArrayList<String> original = getYourMemes();
        for (String s : original) {
            if (s.equals(url)) {
                continue;
            } else {
                replacement.add(s);
            }
        }
        File file = new File("savedmemes.txt");
        if(file.delete()) {
            System.out.println("Deleted original file!");
        } else {
            System.out.println("Failed to delete original file...");
        }
        for(int j = 0; j<original.size(); j++) {
            SaveMemes(replacement.get(j));
        }
    }

    //this takes a url and saves it to an external file called "savedmemes.txt" Perhaps we should change the path to be
    //less obvious. Its feedback is through the terminal.
    public static void SaveMemes(String url) {
        try {
            File file = new File("savedmemes.txt");
            if (file.createNewFile()) {
                System.out.println("Created new save file!");
            } else {
                System.out.println("File already exists!");
            }
        } catch (IOException e) {
            System.out.println("Something crazy happened :(");
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter("savedmemes.txt");
            writer.append(url);
            writer.append("\n");
            writer.close();
            System.out.println("Successfully saved!");
        } catch (IOException f) {
            System.out.println("Something crazy happened :(");
            f.printStackTrace();
        }
    }


}
