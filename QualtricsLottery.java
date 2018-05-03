import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class QualtricsLottery {

private static int numWinners;

public static void main(String[] args) {
        if (args.length != 2) {
                System.out.println("An incorrect number of arguments have been passed. " + args.length + " were passed, exactly 2 required.");
                System.exit(1);
        }

        try {
                numWinners = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be an integer.");
                System.exit(1);
        }

        ArrayList<String> emails = readFile(args[1]);

        if (numWinners > emails.size()) {
                System.err.println("There are not enough participants for that many winners.");
                System.exit(1);
        }

        for (int i = 0; i < numWinners; i++) {
                emails = pickWinner(emails);
        }
}

private static ArrayList<String> readFile(String fileName) {
        BufferedReader br = null;
        String email = "";
        String cvsSplitBy = ",";

        // arrayList to hold emails
        ArrayList<String> emails = new ArrayList<String>();

        try {
                br = new BufferedReader(new FileReader(fileName));
                while ((email = br.readLine()) != null) {
                        emails.add(email);
                }
        } catch (FileNotFoundException e) {
                System.out.println("The file was not found.");
        } catch (IOException e) {
                System.out.println("There was a problem reading the file.");
        } finally {
                if (br != null) {
                        try {
                                br.close();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
                return emails;
        }
}

private static ArrayList<String> pickWinner(ArrayList<String> emails) {
        Random rand = new Random();

        int pick = rand.nextInt(emails.size()) + 1;
        System.out.println("The winner is " + emails.get(pick));
        emails.remove(pick);
        return emails;

}
}
