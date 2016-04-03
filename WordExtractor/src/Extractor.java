import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Max on 2016-04-02.
 */
public class Extractor {

    private HashSet<String> words;

    public Extractor(){
        words = new HashSet<String>();
    }

    public void saveWord(String word){
        word = "<content word=\"" + word + "\"/>\n";

        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("saved.txt", true)))) {
            out.println(word);

        }catch (IOException e) {
            System.out.println("Couldnt save word: " + word);
        }

        /*
        try{
            PrintWriter pr = new PrintWriter(new FileWriter("saved.txt"));

            pr.append("<content word=\""+ word+"\"/>\n");
            pr.flush();

            System.out.println(word+" saved!");
        }catch(Exception e){
            System.out.println("Couldnt save word: " + word);
        }
        */
    }

    public void loadWords(){

        try{

            BufferedReader br = new BufferedReader(new FileReader("words.txt"));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                line = br.readLine();
                String[] arr;
                while (line != null && line.length() > 0) {
                    arr = line.split("/[^A-Za-z]/");
                    for(int i = 0; i < arr.length; i++){
                        words.add(arr[i]);
                    }
                    line = br.readLine();
                }
                System.out.println("Words Loaded: " + words.size());
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("COULDNT LOAD WORDS");
            }
            finally {

                br.close();
            }

        }catch(Exception e){
            System.out.println("No file were found.");
        }
    }

    public HashSet<String> getWords(){
        return words;
    }
}
