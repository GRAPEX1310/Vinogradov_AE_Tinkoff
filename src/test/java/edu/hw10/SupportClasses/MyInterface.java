package edu.hw10.SupportClasses;

import edu.hw10.Task2.Cache;

public interface MyInterface {

    @Cache(persist = true)
    String getString();
}
