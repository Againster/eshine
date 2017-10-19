/**
 * Created by root on 8/22/17.
 */
public class CVWordAttr {
    String word;
    String consonant;
    String vowel_first;
    String vowel_second;
    CVSplit.TYPE consonant_type;
    CVSplit.TYPE vowel_first_type;
    CVSplit.TYPE vowel_second_type;
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
    public String getConsonant() {
        return consonant;
    }

    public void setConsonant(String consonant) {
        this.consonant = consonant;
    }

    public String getVowel_first() {
        return vowel_first;
    }

    public void setVowel_first(String vowel_first) {
        this.vowel_first = vowel_first;
    }

    public String getVowel_second() {
        return vowel_second;
    }

    public void setVowel_second(String vowel_second) {
        this.vowel_second = vowel_second;
    }

    public CVSplit.TYPE getConsonant_type() {
        return consonant_type;
    }

    public void setConsonant_type(CVSplit.TYPE consonant_type) {
        this.consonant_type = consonant_type;
    }

    public CVSplit.TYPE getVowel_first_type() {
        return vowel_first_type;
    }

    public void setVowel_first_type(CVSplit.TYPE vowel_first_type) {
        this.vowel_first_type = vowel_first_type;
    }

    public CVSplit.TYPE getVowel_second_type() {
        return vowel_second_type;
    }

    public void setVowel_second_type(CVSplit.TYPE vowel_second_type) {
        this.vowel_second_type = vowel_second_type;
    }
}
