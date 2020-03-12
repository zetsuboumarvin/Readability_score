public class Text {
    private String text;
    private long sentences;
    private long words;
    private long characters;
    private long syllables;
    private long polysyllables;
    private double avgChar;
    private double avgSent;
    private static String exampleSyllables = "aeiuoy";

    protected Text(String text) {
        this.text = text;
        countSentences();
        countWords();
        countCharacters();
        this.avgChar = 100.0 * this.characters / this.words;
        this.avgSent = 100.0 * this.sentences / this.words;
    }

    protected long getSentences() {
        return this.sentences;
    }

    protected long getWords() {
        return this.words;
    }

    protected long getCharacters() {
        return this.characters;
    }

    protected long getSyllables() { return this.syllables; }

    protected long getPolysyllables() { return this.polysyllables; }

    protected double getAvgChar() { return this.avgChar; }

    protected double getAvgSent() { return this.avgSent; }

    private void countSentences() {
        String[] rawSentences = text.split("[\\w](\\.|\\?|!)+\\s*");
        this.sentences = rawSentences.length;
    }

    private void countWords() {
        String[] rawWords = text.split("\\s");
        long countSyllables = 0;
        long countPolysyllables = 0;
        for (String s :rawWords) {
            int temp = countSyllablesInWord(s);
            if (temp > 2)
                countPolysyllables++;
            countSyllables += temp;
        }
        this.syllables = countSyllables;
        this.polysyllables = countPolysyllables;
        this.words = rawWords.length;
    }

    private void countCharacters() {
        long count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.substring(i, i + 1).matches("[\\S]"))
                count++;
        }
        this.characters = count;
    }

    private int countSyllablesInWord(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && exampleSyllables.contains(s.substring(i, i + 1))
                    && !exampleSyllables.contains(s.substring(i + 1, i + 2)))
                count++;
            else if (i == s.length() - 1 && s.charAt(i) != 'e'
                    && exampleSyllables.contains(s.substring(i, i + 1)))
                count++;
        }
        if (count == 0)
            return 1;
        else
            return count;
    }
}
