import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main (String args[]) throws IOException {
        File file = new File (args[0]);
        String fileOutputPath = args[1];

        var userID = new ArrayList<String>();
        var name = new ArrayList<String>();
        var version = new ArrayList<String>();
        var insurance = new ArrayList<String>();

        parseFile(file,userID,name,version,insurance);
        createFiles(insurance, fileOutputPath);
        ArrayList<String> reorderedNames = reorderNames(name);
        var content = sortContent(userID,reorderedNames,version, insurance);
        removeDuplicates(content);
        writeToFiles(content, fileOutputPath);
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

    /* initial step to create the file. creating it here allows us to go straight into append mode when actually writing
    to the file, not having to worry about the issues with opening new FileWriters which overwrite existing files.
    */
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
    //will split first name last name and reorder  them to be last name first name
    public static ArrayList<String> reorderNames(ArrayList<String> name ){

        ArrayList reordered = new ArrayList();
        for (String n : name) {
            String fName = n.substring(0,n.indexOf(" "));
            String lName = n.substring(n.indexOf(" "));
            reordered.add(lName + " " + fName);
        }
        return reordered;
    }

    //grabs the values from the multiple arrays with finalized values and joins them back together
    public static String[] sortContent(ArrayList userID, ArrayList name, ArrayList version, ArrayList insurance) {

        String[] content = new String[name.size()];

        for (int i = 0; i < name.size(); i++){
            content[i] = (String) name.get(i) + ',' + (String) userID.get(i) + ',' + (String) version.get(i) + ',' + (String) insurance.get(i);
        }

        for(int i = 0; i < name.size(); i++) {
            for (int j = i + 1; j < content.length; j++) {
                if(content[i].compareTo(content[j]) > 0) {
                    String temp = content[i];
                    content[i] = content[j];
                    content[j] = temp;
                }
            }
        }
        return content;
    }
    public static void removeDuplicates(String[] content){
        for (String line : content) {

            String segments[] = line.split(",");
            String userID = segments[1];
            String insurance = segments[3];
        }
        /* needed additional logic to get this working right. Plan was to compare userID and insurance and find duplicate
        * values through some means of a hashmap/ list. if found, it would grab the version of both values and compare to see
        * which was one higher and then use that.
        * */
    }
    public static void writeToFiles(String[] content, String outputPath) throws IOException {

        int ch;

        for (String line : content) {

            String segments[] = line.split(",");
            String fileContent = segments[0] + ',' + segments[1] + ',' + segments[2];
            String insurance = segments[3];

            FileReader reader = new FileReader(outputPath + insurance + ".txt");
            boolean isEmpty = false;
            while ((ch = reader.read())!= -1) {
                isEmpty = true;
            }
            reader.close();

            FileWriter writer = new FileWriter(outputPath + insurance + ".txt", true);

            if(isEmpty)
                writer.write(fileContent + "\r\n");
            else
                writer.append(fileContent + "\r\n");
            writer.close();
        }
    }
}

