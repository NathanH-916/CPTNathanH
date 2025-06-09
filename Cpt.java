import arc.*;

public class Cpt{
	public static void main (String[] args){
		Console con = new Console();

      
        String themeName;        
         
        String word;           
        String shownWord = "";  
        int wordLength;           
        int points;               
        boolean isComplete = false; 
        String userInput;        
        char guessedLetter;       
        String newShownWord;      
        boolean found;            // True if guessed letter is in the word
        int letter;               // Loop variable for going through letters
        char realLetter;          // The actual letter in the original word
        char currentLetter;       // The character currently shown (dash or guessed letter)
        // ============================================

        // Ask for theme file (e.g., food.txt)
        con.print("Enter theme name (no .txt): ");
        themeName = con.readLine();

   

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

            // Go through the word letter by letter
            for (letter = 0; letter < wordLength; letter++) {
                realLetter = word.charAt(letter);
                currentLetter = shownWord.charAt(letter);

                if (currentLetter != '-') {
                    newShownWord += currentLetter; // Keep already guessed letters
                } else if (realLetter == guessedLetter) {
                    newShownWord += guessedLetter; // Correct guess
                    found = true;
                } else {
                    newShownWord += "-"; // Still hidden
                }
            }

            shownWord = newShownWord;

            if (!found) {
                points--;
            }

            // Check if the word is fully guessed
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
