/*
***************************
    "Work Rank Utility"

    @won_tonsoup 2019
***************************
*/

package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Word {
    String wordProperty;
    int countProperty;

    public Word(String word, Integer count) {
        this.wordProperty = word;
        this.countProperty = count;
    }

    public Word(String word) {
        this.wordProperty = word;
        this.countProperty = 0;
    }

    public String getWordProperty() {
        return wordProperty;
    }

    public int getCountProperty() {
        return countProperty;
    }

    public String getWord() {
        String wordString = wordProperty + Integer.toString(countProperty);
        return wordString;
    }

    public void setCountProperty(int countProperty) {
        this.countProperty = countProperty;
    }

    public void setWordProperty(String wordProperty) {
        this.wordProperty = wordProperty;
    }
}
