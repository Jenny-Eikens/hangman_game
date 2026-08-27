import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filePath = "words.txt";
        ArrayList<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file");
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }

        Random random = new Random();
        String word = words.get(random.nextInt(words.size()));

        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> wordState = new ArrayList<>();
        ArrayList<Character> seen = new ArrayList<>();
        int wrongGuesses = 0;

        for (int i = 0; i < word.length(); i++) {
            wordState.add('_');
        }

        System.out.println("************************");
        System.out.println("Welcome to Hangman!");
        System.out.println("************************");
        System.out.println("""
                
                """);

        while (wrongGuesses < 6) {
            System.out.print("Word: ");
            for (char c : wordState) {
                System.out.print(c + " ");
            }
            System.out.println("""
                    
                    """);
            System.out.print("Guess a letter: ");
            char guess = scanner.next().toLowerCase().charAt(0);
            if (!seen.contains(guess)) {
                    seen.add(guess);
            }
            if (seen.contains(guess)) {
                System.out.println("Already guessed!");
                System.out.println("************************");
                System.out.println();
            } else {
                if (word.indexOf(guess) > -1) {
                System.out.println("Correct guess!");
                System.out.println("************************");
                System.out.println();
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess) {
                        wordState.set(i, guess);
                    }
                }
            } else {
                System.out.println("Wrong guess!");
                wrongGuesses++;
                System.out.println(getHangmanArt(wrongGuesses));
                System.out.println("************************");
            }

            if (!wordState.contains('_')) {
                System.out.println("YOU WIN!");
                System.out.print("The word was: ");
                for (char c : wordState) {
                    System.out.print(c + " ");
                }
                break;
            }
        }

        if (wrongGuesses >= 6) {
            System.out.print(getHangmanArt(wrongGuesses));
            System.out.println("GAME OVER!");
            System.out.println("The word was: " + word);
        }

        scanner.close();
    }

    static String getHangmanArt(int wrongGuesses) {
        String result;
        switch(wrongGuesses) {
        case 0: 
            result = """
                


                """;
            break;
        case 1: 
            result = """
                    o
                
                
                    """;
            break;
        case 2: 
            result = """
                    o
                    |
                
                    """;
            break;
        case 3: 
            result = """
                     o
                    /|
                
                    """;
            break;
        case 4: 
            result = """
                     o
                    /|\\
                
                    """;
            break;
        case 5: 
            result = """
                     o
                    /|\\
                    /
                    """;
            break;
        case 6: 
            result = """
                     o
                    /|\\
                    / \\
                    """;
            break;
        default: 
            result = "";
            break;
    };
    return result;
    }
}
