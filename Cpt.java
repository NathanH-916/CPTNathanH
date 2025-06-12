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
        String playerName;
        String[] themeList = new String[20];
        String[] wordList = new String[50];
        String[] leaderboardNames = new String[100];
        int[] leaderboardScores = new int[100];
        int themeCount;
        int wordCount;
        int attemptsLeft;
        int leaderboardCount = 0;
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
            userChoice = con.readLine();

            if (userChoice.equals("joke")) {
                con.println("What do you call a fish with no eyes, fsh 😂");
                continue;
            }
            if(userChoice.equals("help")){
				con.println("This is the help menu: Help");
				continue;
			}

            if (userChoice.equals("1")) {
                // PLAY GAME
                if (themeCount == 0) {
                    con.println("No themes found. Add a theme first!");
                    continue;
                }

                // Show available themes
                con.println("Select a theme:");
                for (int intCount = 0; intCount < themeCount; intCount = intCount + 1) {
                    con.println((intCount + 1) + ". " + themeList[intCount]);
                }
                con.print("Enter theme number: ");
                int themeChoice = con.readInt() - 1;

                if (themeChoice < 0 || themeChoice >= themeCount) {
                    con.println("Invalid theme choice.");
                    continue;
                }

                // Ask for player name after theme selected
                con.print("Enter your name: ");
                playerName = con.readLine();

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
                for (int intCount = 0; intCount < wordToGuess.length(); intCount = intCount + 1) {
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
                    for (int intCount = 0; intCount < wordToGuess.length(); intCount = intCount + 1) {
                        if (wordToGuess.substring(intCount, intCount + 1).equals(guessedLetter)) {
                            updatedDisplay = updatedDisplay + guessedLetter;
                            letterFound = true;
                        } else {
                            updatedDisplay = updatedDisplay + guessedDisplay.substring(intCount, intCount + 1);
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

                // Load leaderboard to arrays
                leaderboardCount = 0;
                TextInputFile lbInput = new TextInputFile("leaderboard.txt");
                while (!lbInput.eof()) {
                    String line = lbInput.readLine();
                    if (line != null && line.length() > 0) {
                        String[] parts = line.split(" ");
                        if (parts.length >= 2) {
                            leaderboardNames[leaderboardCount] = parts[0];
                            leaderboardScores[leaderboardCount] = Integer.parseInt(parts[1]);
                            leaderboardCount = leaderboardCount + 1;
                        }
                    }
                }
                lbInput.close();

                // Calculate score: 1 if won, 0 if lost
                int playerScore = (!guessedDisplay.contains("_")) ? 1 : 0;

                // Update leaderboard or add new
                boolean foundPlayer = false;
                for (int intCount = 0; intCount < leaderboardCount; intCount = intCount + 1) {
                    if (leaderboardNames[intCount].equals(playerName)) {
                        leaderboardScores[intCount] = leaderboardScores[intCount] + playerScore;
                        foundPlayer = true;
                        break;
                    }
                }
                if (!foundPlayer && playerScore > 0) {
                    leaderboardNames[leaderboardCount] = playerName;
                    leaderboardScores[leaderboardCount] = playerScore;
                    leaderboardCount = leaderboardCount + 1;
                }

                // Write leaderboard back sorted by score descending
                // Bubble sort
                for (int intCount = 0; intCount < leaderboardCount - 1; intCount = intCount + 1) {
                    for (int intCount2 = 0; intCount2 < leaderboardCount - intCount - 1; intCount2 = intCount2 + 1) {
                        if (leaderboardScores[intCount2] < leaderboardScores[intCount2 + 1]) {
                            // Swap scores
                            int tempScore = leaderboardScores[intCount2];
                            leaderboardScores[intCount2] = leaderboardScores[intCount2 + 1];
                            leaderboardScores[intCount2 + 1] = tempScore;
                            // Swap names
                            String tempName = leaderboardNames[intCount2];
                            leaderboardNames[intCount2] = leaderboardNames[intCount2 + 1];
                            leaderboardNames[intCount2 + 1] = tempName;
                        }
                    }
                }

                // Save leaderboard file
                TextOutputFile lbOutput = new TextOutputFile("leaderboard.txt");
                for (int intCount = 0; intCount < leaderboardCount; intCount = intCount + 1) {
                    lbOutput.println(leaderboardNames[intCount] + " " + leaderboardScores[intCount]);
                }
                lbOutput.close();

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
                themeName = con.readLine();

                TextOutputFile themeOutput = new TextOutputFile("themes.txt", true);
                themeOutput.println(themeName);
                themeOutput.close();

                TextOutputFile newThemeFile = new TextOutputFile(themeName + ".txt");
                con.println("Enter words for this theme (type 'done' when finished):");
                while (true) {
                    con.print("Add word: ");
                    String newWord = con.readLine().toLowerCase();
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
