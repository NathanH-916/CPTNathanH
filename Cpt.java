import arc.*;

public class Cpt {
    public static void main(String[] args) {
        Console con = new Console();

        String themeName;
        String word = "";
        String shownWord = "";
        int wordLength;
        int points;
        boolean isComplete = false;
        String userInput;
        char guessedLetter;
        String newShownWord;
        boolean found;
        int letter;
        char realLetter;
        char currentLetter;

        // Ask for theme name
        con.print("Enter theme name (food, space, sports): ");
        themeName = con.readLine();

        // Use hardcoded words instead of reading from file
        if (themeName.equals("food")) {
            word = "pizza";
        } else if (themeName.equals("space")) {
            word = "galaxy";
        } else if (themeName.equals("sports")) {
            word = "soccer";
        } else {
            word = "banana"; // default fallback word
            con.println("Unknown theme. Using default word.");
        }

        wordLength = word.length();
        points = wordLength;

        // Fill shownWord with dashes
        for (letter = 0; letter < wordLength; letter++) {
            shownWord += "-";
        }

        while (points > 0 && !isComplete) {
            con.println("Word: " + shownWord);
            con.println("Points left: " + points);
            con.print("Guess a letter: ");
            userInput = con.readLine();

            if (userInput.length() != 1) {
                con.println("Enter just one letter!");
                continue;
            }

            guessedLetter = userInput.charAt(0);
            newShownWord = "";
            found = false;

            for (letter = 0; letter < wordLength; letter++) {
                realLetter = word.charAt(letter);
                currentLetter = shownWord.charAt(letter);

                if (currentLetter != '-') {
                    newShownWord += currentLetter;
                } else if (realLetter == guessedLetter) {
                    newShownWord += guessedLetter;
                    found = true;
                } else {
                    newShownWord += "-";
                }
            }

            shownWord = newShownWord;

            if (!found) {
                points--;
            }

            if (shownWord.equals(word)) {
                isComplete = true;
            }
        }

        if (isComplete) {
            con.println("🎉 You guessed it! The word was: " + word);
        } else {
            con.println("❌ Out of points! The word was: " + word);
        }

        con.println("Game over.");
    }
}



