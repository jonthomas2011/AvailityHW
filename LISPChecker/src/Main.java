import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        //since comments are not needed to check for syntax, we remove them from the content string
        String commentsRemoved = content.replaceAll(";.*", "");
        output = parenChecker(commentsRemoved);
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
            System.exit(1);
        }
        return content;
    }

    // checks to see if correct number of parenthesis exist
    private static boolean parenChecker(String content){

        // will keep a counter to record every time we see a left and right parenthesis.
        int count = 0;

        char c;
        for (int i = 0; i < content.length(); i++) {
            c = content.charAt(i);

            /* if the count is 0, we assume we've ended the final parenthesis for that section. If the next character
               is not a '(' its an invalid start to a new code block, so we return false. Also, if count is 0 and the
               next char is a line feed, carriage return, or tab, it "ignores" it and continues searching
               through the content
             */
            if(count == 0
                    && c != '('
                    && c != ' '
                    && c != '\r'
                    && c != '\n'
                    && c != '\t'
            ){
                return false;
            }
            else if(c == '(') {
                count++;
            }
            else if (c == ')') {
                count--;
            }
        }

        if (count == 0)
            return true;
        else
            return false;
    }
}