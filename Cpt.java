import arc.*;

public class Cpt {

    public static void main(String[] args) {
        Console con = new Console();

        int intChoice = 0;
        String strPlayerName;

        while (intChoice != 4) {
            con.println("===== Word Guessing Game =====");
            con.println("1. Play Game");
            con.println("2. View Leaderboard");
            con.println("3. Add Quiz");
            con.println("4. Quit");
            con.println("Choose an option: ");
            intChoice = con.readInt();
     
            
            
            if (intChoice == 1) {
                con.print("Enter your name: ");
                strPlayerName = con.readLine();
               }

         }
           
      }
}
