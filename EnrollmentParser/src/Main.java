import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
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
        ArrayList reorderedNames = reorderNames(name);
        sortContent(userID,reorderedNames,version, insurance);
    }

    // reads the entire file and separates the columns into different ArrayLists
    public static void parseFile(File file, ArrayList userID, ArrayList name, ArrayList version, ArrayList insurance)
    {
        int index = 0;
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        reader.useDelimiter("[,\\r\\n]+");
        while(reader.hasNext())
        {
            userID.add(index,reader.next());
            name.add(index,reader.next());
            version.add(index, reader.next());
            insurance.add(index, reader.next());
            index++;
        }
    }
    public static void createFiles(ArrayList insurance, String fileOutputPath) {
        List<String> distinctCompanies = (List<String>) insurance.stream().distinct().collect(Collectors.toList());

        for (String company : distinctCompanies) {
            File file = new File(fileOutputPath + company +".txt");
            boolean fvar;
            try {
                if (file.createNewFile()) fvar = true;
                else fvar = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static ArrayList<String> reorderNames(ArrayList<String> name ){

        ArrayList reordered = new ArrayList();
        for (String n : name) {
            String fName = n.substring(0,n.indexOf(" "));
            String lName = n.substring(n.indexOf(" "));
            reordered.add(lName + " " + fName);
        }
        return reordered;
    }
    public static void sortContent(ArrayList userID, ArrayList name, ArrayList version, ArrayList insurance) {

        String[] content = new String[name.size()];

        for (int i = 0; i < name.size(); i++){
            content[i] = (String) name.get(i) + ',' + (String) userID.get(i) + ',' + (String) version.get(i) + ',' + (String) insurance.get(i);
        }

        for(int i = 0; i< name.size(); i++) {
            for (int j = i+1; j<content.length; j++) {
                if(content[i].compareTo(content[j])>0) {
                    String temp = content[i];
                    content[i] = content[j];
                    content[j] = temp;
                }
            }
        }
    }
}

