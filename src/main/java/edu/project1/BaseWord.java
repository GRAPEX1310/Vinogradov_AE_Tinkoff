package edu.project1;

public abstract class BaseWord {

    private String word;

    private BaseWord() {
        word = "";
    }

    public BaseWord(String givenWord) {
        word = givenWord;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String newWord) {
        word = newWord;
    }
}
