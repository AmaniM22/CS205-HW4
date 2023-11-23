package Stack;
import java.io.*;
import java.util.Stack;

public class StackPalindrome {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Please provide a single argument: the properties file path.");
            System.exit(1);
        }

        String filePath = args[0];
        String logFile = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] keyValue = line.split("=");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    if (key.equals("log_file_name")) {
                        logFile = value;
                    } else if (key.equals("words")) {
                        String[] wordArray = value.split(",");
                        for (String word : wordArray) {
                            if (checkPalindrome(word.trim())) {
                                System.out.println(word + " is a palindrome");
                            } else {
                                System.out.println(word + " is not a palindrome");
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error - unable to read properties file: " + e.getMessage());
        }

        try {
            PrintStream logFileStream = new PrintStream(logFile);
            System.setOut(logFileStream);
            System.setErr(logFileStream);
        } catch (IOException e) {
            System.err.println("Error - unable to open log file: " + e.getMessage());
            System.exit(1);
        }
    }

    private static boolean checkPalindrome(String word) {
        Stack<Character> letterStack = new Stack<Character>();
        StringBuilder reverseWord = new StringBuilder();

        for (char ch : word.toCharArray()) {
            letterStack.push(ch);
        }

        while (!letterStack.isEmpty()) {
            reverseWord.append(letterStack.pop());
        }

        return word.equals(reverseWord.toString());
    }
}