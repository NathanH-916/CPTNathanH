import arc.*;

public class Cpt {
	public static void main(String[] args) {
		Console con = new Console();

		// Variables
		String[] strThemes = new String[5];
		String[] strWords = new String[5];
		String strHidden = "";
		String strGuess;
		String strAnswer;
		String strUser;
		int intTries;
		int intPoints;
		int intCount;
		int intPos;
		char chrLetter;
		boolean blnCorrect;

		// Load theme from file
		TextInputFile themeFile = new TextInputFile("themes.txt");
		intCount = 0;
		while (themeFile.eof() == false && intCount < 5) {
			strThemes[intCount] = themeFile.readLine();
			strWords[intCount] = themeFile.readLine();
			intCount++;
		}
		themeFile.close();

		// Ask for user name
		con.print("Enter your name: ");
		strUser = con.readLine();

		boolean blnRunning = true;
		while (blnRunning) {
			con.println("\nMain Menu:");
			con.println("1. Start Game");
			con.println("2. View Leaderboard");
			con.println("3. Exit");
			con.print("Enter your choice: ");
			int intMenuChoice = con.readInt();

			if (intMenuChoice == 1) {
				// Pick theme
				con.println("Choose a theme:");
				for (int intThemeIndex = 0; intThemeIndex < strThemes.length; intThemeIndex++) {
					if (strThemes[intThemeIndex] != null) {
						con.println((intThemeIndex + 1) + ". " + strThemes[intThemeIndex]);
					}
				}
				int intChoice = con.readInt();
				strAnswer = strWords[intChoice - 1];

				// Initialize hidden word
				strHidden = "";
				for (int intCharIndex = 0; intCharIndex < strAnswer.length(); intCharIndex++) {
					strHidden = strHidden + "_";
				}

				intTries = 6;
				intPoints = 0;

				while (intTries > 0 && strHidden.indexOf("_") != -1) {
					con.println("Word: " + strHidden);
					con.print("Guess a letter: ");
					strGuess = con.readLine();
					chrLetter = strGuess.charAt(0);

					blnCorrect = false;
					for (int intCharIndex = 0; intCharIndex < strAnswer.length(); intCharIndex++) {
						if (strAnswer.substring(intCharIndex, intCharIndex + 1).equalsIgnoreCase(strGuess)) {
							strHidden = strHidden.substring(0, intCharIndex) + strAnswer.substring(intCharIndex, intCharIndex + 1) + strHidden.substring(intCharIndex + 1);
							blnCorrect = true;
						}
					}

					if (blnCorrect) {
						intPoints = intPoints + 10;
						con.println("Correct!");
					} else {
						intTries = intTries - 1;
						con.println("Wrong! Tries left: " + intTries);
					}
				}

				if (strHidden.equalsIgnoreCase(strAnswer)) {
					con.println("You win! The word was: " + strAnswer);
				} else {
					con.println("Game over! The word was: " + strAnswer);
				}

				// Leaderboard update
				TextInputFile leaderIn = new TextInputFile("leaderboard.txt");
				String[] strNames = new String[10];
				int[] intScores = new int[10];
				intCount = 0;
				while (leaderIn.eof() == false && intCount < 10) {
					strNames[intCount] = leaderIn.readLine();
					intScores[intCount] = leaderIn.readInt();
					intCount++;
				}
				leaderIn.close();

				strNames[intCount] = strUser;
				intScores[intCount] = intPoints;
				intCount++;

				// Bubble sort
				for (int intOuter = 0; intOuter < intCount - 1; intOuter++) {
					for (int intInner = 0; intInner < intCount - 1 - intOuter; intInner++) {
						if (intScores[intInner] < intScores[intInner + 1]) {
							int intTemp = intScores[intInner];
							intScores[intInner] = intScores[intInner + 1];
							intScores[intInner + 1] = intTemp;
							String strTemp = strNames[intInner];
							strNames[intInner] = strNames[intInner + 1];
							strNames[intInner + 1] = strTemp;
						}
					}
				}

				TextOutputFile leaderOut = new TextOutputFile("leaderboard.txt");
				for (int intLeaderIndex = 0; intLeaderIndex < 10 && strNames[intLeaderIndex] != null; intLeaderIndex++) {
					leaderOut.println(strNames[intLeaderIndex]);
					leaderOut.println(intScores[intLeaderIndex]);
				}
				leaderOut.close();

			} else if (intMenuChoice == 2) 
				// Display leaderboard
	



}
