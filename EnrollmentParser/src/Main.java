import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main (String args[]){
        File file = new File (args[0]);
        String fileOutputPath = args[1];
        ArrayList<String> userID = new ArrayList<String>();
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<String> version = new ArrayList<String>();
        ArrayList<String> insurance = new ArrayList<String>();

        parseFile(file,userID,name,version,insurance);
        createFiles(insurance, fileOutputPath);
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
    public static void createFiles(ArrayList insurance, String fileOutputPath) {
        List<String> distinctCompanies = (List<String>) insurance.stream().distinct().collect(Collectors.toList());

        for (String company : distinctCompanies) {
            File file = new File(fileOutputPath + company +".csv");
            boolean fvar;
            try {
                if (file.createNewFile()) fvar = true;
                else fvar = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

