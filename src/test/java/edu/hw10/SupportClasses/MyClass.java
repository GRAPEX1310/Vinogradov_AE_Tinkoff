package edu.hw10.SupportClasses;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import edu.hw10.Task1.Annotations.NotNull;
import edu.hw10.Task2.Cache;

public class MyClass implements MyInterface {

    public int x;

    @NotNull
    public String string;
    public Boolean correct;

    public MyClass(@Max(100) int x, @NotNull String string, @NotNull Boolean correct) {
        this.x = x;
        this.string = string;
        this.correct = correct;
    }

    public static MyClass create(@Min(0) int x, String string, @NotNull Boolean correct) {
        return new MyClass(x, string, correct);
    }

    public int getX() {
        return x;
    }

    @Override
    public String getString() {
        return string;
    }

    public Boolean getCorrect() {
        return correct;
    }
}
