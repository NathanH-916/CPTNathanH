import arc.*;

public class Cpt {

    public static void main(String[] args) {
        Console con = new Console();

        int intChoice = 0;
        String strPlayerName;
        int intWord;

        while (intChoice != 4) {
            con.println("===== Word Guessing Game =====");
            con.println("1. Play Game");
            con.println("2. View Leaderboard");
            con.println("3. Add Quiz");
            con.println("4. Quit");
            con.println("Choose an option: ");
            intChoice = con.readInt();
     
            
            //Saves the name to the leaderboard
            if (intChoice == 1) {
                con.print("Enter your name: ");
                strPlayerName = con.readLine();
                length();
                while (points > 0) {
                    // Show dashes or letters, if wrong then lose points but add dash if correct remove
                    con.print("Word: ");
                    boolean allGuessed = true;
                    for (int intWord = 0; intWord < word.length(); intWord++) {
                        if (guessed[intWord]) {
                            con.print(word.charAt(intWord));
                        } else {
                            con.print("-");
                            allGuessed = false;
                        }
                    }

		   }
	
	


         
           
      

