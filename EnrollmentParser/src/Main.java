import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main (String args[]){
        File file = new File (args[0]);
        ArrayList<String> userID = new ArrayList<String>();
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<String> version = new ArrayList<String>();
        ArrayList<String> insurance = new ArrayList<String>();

        parseFile(file,userID,name,version,insurance);
    }

    // reads the entire file and separates the columns into different ArrayLists
    public static void parseFile(File file, ArrayList userID, ArrayList name, ArrayList version, ArrayList insurance)
    {
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        reader.useDelimiter("[;\\r\\n]+");
        while(reader.hasNext())
        {
            userID.add(reader.next());
            name.add(reader.next());
            version.add(reader.next());
            insurance.add(reader.next());
        }
    }
}
