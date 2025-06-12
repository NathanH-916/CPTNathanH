import arc.*;

public class cpt {
    public static void main(String[] args) {
        Console con = new Console();

        // Variables
        String userChoice;
        String themeName;
        String wordToGuess;
        String guessedDisplay;
        String guessedLetter;
        String[] themeList = new String[20];
        String[] wordList = new String[50];
        int themeCount;
        int wordCount;
        int attemptsLeft;
        boolean letterFound;
        
        while (true) {
            // Load themes each time before showing menu
            themeCount = 0;
            TextInputFile themeInput = new TextInputFile("themes.txt");
            while (!themeInput.eof()) {
                themeList[themeCount] = themeInput.readLine();
                themeCount = themeCount + 1;
            }
            themeInput.close();

            // Main menu display
            con.println("\n=== MAIN MENU ===");
            con.println("1. Play Game");
            con.println("2. View Leaderboard");
            con.println("3. Add Theme");
            con.println("4. Quit");
            con.print("Enter your choice (1–4): ");
            userChoice = con.readLine();  // Use readLine for consistency

            if (userChoice.equals("1")) {
                // PLAY GAME
                if (themeCount == 0) {
                    con.println("No themes found. Add a theme first!");
                    continue;
                }

                // Show available themes
                con.println("Select a theme:");
                for (int index = 0; index < themeCount; index = index + 1) {
                    con.println((index + 1) + ". " + themeList[index]);
                }
                con.print("Enter theme number: ");
                int themeChoice = con.readInt() - 1;

                if (themeChoice < 0 || themeChoice >= themeCount) {
                    con.println("Invalid theme choice.");
                    continue;
                }

                themeName = themeList[themeChoice];
                TextInputFile wordInput = new TextInputFile(themeName + ".txt");
                wordCount = 0;
                while (!wordInput.eof()) {
                    wordList[wordCount] = wordInput.readLine().toLowerCase();
                    wordCount = wordCount + 1;
                }
                wordInput.close();

                if (wordCount == 0) {
                    con.println("No words found in this theme.");
                    continue;
                }

                // Random word selection
                int randomIndex = (int)(Math.random() * wordCount);
                wordToGuess = wordList[randomIndex];
                guessedDisplay = "";
                for (int index = 0; index < wordToGuess.length(); index = index + 1) {
                    guessedDisplay = guessedDisplay + "_";
                }

                attemptsLeft = wordToGuess.length();
                con.println("Start guessing! You have " + attemptsLeft + " chances.");

                while (attemptsLeft > 0 && guessedDisplay.contains("_")) {
                    con.println("\nWord: " + guessedDisplay);
                    con.print("Guess a letter: ");
                    guessedLetter = con.readLine().toLowerCase();

                    if (guessedLetter.length() != 1) {
                        con.println("Please enter exactly one letter.");
                        continue;
                    }

                    letterFound = false;
                    String updatedDisplay = "";
                    for (int index = 0; index < wordToGuess.length(); index = index + 1) {
                        if (wordToGuess.substring(index, index + 1).equals(guessedLetter)) {
                            updatedDisplay = updatedDisplay + guessedLetter;
                            letterFound = true;
                        } else {
                            updatedDisplay = updatedDisplay + guessedDisplay.substring(index, index + 1);
                        }
                    }

                    guessedDisplay = updatedDisplay;

                    if (letterFound) {
                        con.println("Nice! Letter found.");
                    } else {
                        attemptsLeft = attemptsLeft - 1;
                        con.println("Wrong! Attempts left: " + attemptsLeft);
                    }
                }

                if (!guessedDisplay.contains("_")) {
                    con.println("You win! The word was **" + wordToGuess + "**!");
                } else {
                    con.println("Game over. The word was **" + wordToGuess + "**.");
                }

            } else if (userChoice.equals("2")) {
                // VIEW LEADERBOARD
                TextInputFile lbInput = new TextInputFile("leaderboard.txt");
                con.println("\n=== LEADERBOARD ===");
                while (!lbInput.eof()) {
                    con.println(lbInput.readLine());
                }
                lbInput.close();

            } else if (userChoice.equals("3")) {
                // ADD THEME
                con.print("Enter new theme name (no spaces): ");
                themeName = con.readLine().trim();

                TextOutputFile themeOutput = new TextOutputFile("themes.txt", true);
                themeOutput.println(themeName);
                themeOutput.close();

                TextOutputFile newThemeFile = new TextOutputFile(themeName + ".txt");
                con.println("Enter words for this theme (type 'done' when finished):");
                while (true) {
                    con.print("Add word: ");
                    String newWord = con.readLine().toLowerCase().trim();
                    if (newWord.equals("done")) break;
                    if (newWord.length() >= 4) {
                        newThemeFile.println(newWord);
                    } else {
                        con.println("Word too short (min 4 letters).");
                    }
                }
                newThemeFile.close();
                con.println("Theme '" + themeName + "' added.");

            } else if (userChoice.equals("4")) {
                con.println("Goodbye!");
                break;

            } else {
                con.println("Invalid choice. Please enter 1–4.");
            }
        }
    }
}
