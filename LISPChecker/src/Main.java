import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;


public class Main {
    public static void main(String[] args) {

        String content = "";
        boolean output = false;

        //  Option 1: gives the user the ability to input content via a .txt file if they prefer.
        if (args[0].endsWith(".txt")) {
            content = readFile(args[0]);
        }

        /*  Option 2: if user decides to input entire string directly in command argument
            if so, arguments will contain spaces, this concatenates all arguments into 1 string
         */
        else {
            for (String eachArg : args) {
                content += eachArg + " ";
            }
        }
            output = parenChecker(content);
            System.out.println(String.valueOf(output));
    }

    //  reads all the content from the input file provided
    private static String readFile(String filePath)
    {
        String content = "";
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content;
    }

    // checks to see if correct number of parenthesis exist
    private static boolean parenChecker(String fileContent){

        //Stack <Character> stack = new Stack<Character>();
        int countLP = 0, countRP = 0;

        for (char i : fileContent.toCharArray()) {
            if(i == '(') {
                countLP++;
            }
            else if (i == ')') {
                countRP++;
            }
        }

        if (countLP == countRP)
            return true;
        else
            return false;
    }
}
