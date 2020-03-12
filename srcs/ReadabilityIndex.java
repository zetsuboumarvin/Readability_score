import java.util.HashMap;

public class ReadabilityIndex {
    private double ARIscore;
    private double FKscore;
    private double SMOGscore;
    private double CLscore;
    private static HashMap<Integer, Integer> map = new HashMap<>();

    static {
        map.put(1, 6);
        map.put(2, 7);
        map.put(3, 9);
        map.put(4, 10);
        map.put(5, 11);
        map.put(6, 12);
        map.put(7, 13);
        map.put(8, 14);
        map.put(9, 15);
        map.put(10, 16);
        map.put(11, 17);
        map.put(12, 18);
        map.put(13, 24);
        map.put(14, 24);
    }

    protected ReadabilityIndex(Text text) {
        long c = text.getCharacters();
        long w = text.getWords();
        long s = text.getSentences();
        long syl = text.getSyllables();
        long polysyl = text.getPolysyllables();
        this.ARIscore = 4.71 * c / w + 0.5 * w / s - 21.43;
        this.FKscore = 0.39 * w / s + 11.8 * syl / w - 15.59;
        this.SMOGscore = 1.043 * Math.sqrt(polysyl * 30.0 / s) + 3.1291;
        this.CLscore = 0.0588 * text.getAvgChar() - 0.296 * text.getAvgSent() - 15.8;
    }

    protected double getARIScore() {
        return this.ARIscore;
    }

    protected double getFKscore() { return this.FKscore; }

    protected double getSMOGscore() { return this.SMOGscore; }

    protected double getCLscore() { return this.CLscore; }

    public int getAge(double score) {
        int sc = (int)Math.round(score);
        return map.get(sc);
    }

}
