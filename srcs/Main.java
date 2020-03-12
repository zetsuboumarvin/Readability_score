import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Read content of the file
        boolean sql = false;
        String fileName = "";
        String rawText = "";
        if (args.length == 0) {
            System.out.println("Filename is missing. Usage: java Main [fileName]");
            System.exit(1);
        }
        for (String s : args) {
            if ("-s".equals(s))
                sql = true;
            else {
                fileName = s;
                break;
            }
        }
        try {
            rawText = Files.readString(Paths.get(fileName));
        } catch (IOException e) {
            System.out.println("ERROR in reading file:");
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("The text is:");
        System.out.println(rawText);
        System.out.println();

        //Create Text object with text data and print it
        Text text = new Text(rawText);
        System.out.println("Words: " + text.getWords());
        System.out.println("Sentences: " + text.getSentences());
        System.out.println("Characters: " + text.getCharacters());
        System.out.println("Syllables: " + text.getSyllables());
        System.out.println("Polysyllables: " + text.getPolysyllables());
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        sc.close();
        System.out.println();

        //Create Index object in the basis of text and table with difficulty analysis
        ReadabilityIndex index = new ReadabilityIndex(text);

        if (sql) {
            ARITable tab = new ARITable();
            tab.createTable();
            if ("ARI".equals(option) || "all".equals(option))
                System.out.printf(Locale.ENGLISH, "Automated Readability Index: " +
                                "%.2f (about %d year olds).\n",
                        index.getARIScore(), tab.returnAge(index.getARIScore()));
            if ("FK".equals(option) || "all".equals(option))
                System.out.printf(Locale.ENGLISH, "Flesch–Kincaid readability tests: " +
                                "%.2f (about %d year olds).\n",
                        index.getFKscore(), tab.returnAge(index.getFKscore()));
            if ("SMOG".equals(option) || "all".equals(option))
                System.out.printf(Locale.ENGLISH, "Simple Measure of Gobbledygook: " +
                                "%.2f (about %d year olds).\n",
                        index.getSMOGscore(), tab.returnAge(index.getSMOGscore()));
            if ("CL".equals(option) || "all".equals(option))
                System.out.printf(Locale.ENGLISH, "Coleman–Liau index: %.2f (about %d year olds).\n",
                        index.getCLscore(), tab.returnAge(index.getCLscore()));
            if ("all".equals(option)) {
                System.out.println();
                System.out.printf(Locale.ENGLISH, "This text should be understood in average " +
                                "by %.2f year olds.\n",
                        (tab.returnAge(index.getARIScore()) + tab.returnAge(index.getFKscore()) +
                                tab.returnAge(index.getSMOGscore()) + tab.returnAge(index.getCLscore())) / 4.0);
            }
        } else {
            if ("ARI".equals(option) || "all".equals(option))
                System.out.printf(Locale.ENGLISH, "Automated Readability Index: " +
                                "%.2f (about %d year olds).\n",
                        index.getARIScore(), index.getAge(index.getARIScore()));
            if ("FK".equals(option) || "all".equals(option))
                System.out.printf(Locale.ENGLISH, "Flesch–Kincaid readability tests: " +
                                "%.2f (about %d year olds).\n",
                        index.getFKscore(), index.getAge(index.getFKscore()));
            if ("SMOG".equals(option) || "all".equals(option))
                System.out.printf(Locale.ENGLISH, "Simple Measure of Gobbledygook: " +
                                "%.2f (about %d year olds).\n",
                        index.getSMOGscore(), index.getAge(index.getSMOGscore()));
            if ("CL".equals(option) || "all".equals(option))
                System.out.printf(Locale.ENGLISH, "Coleman–Liau index:: %.2f (about %d year olds).\n",
                        index.getCLscore(), index.getAge(index.getCLscore()));
            if ("all".equals(option)) {
                System.out.println();
                System.out.printf(Locale.ENGLISH, "This text should be understood in average " +
                                "by %.2f year olds.\n",
                        (index.getAge(index.getARIScore()) + index.getAge(index.getFKscore()) +
                                index.getAge(index.getSMOGscore()) + index.getAge(index.getCLscore())) / 4.0);
            }
        }
    }


}
