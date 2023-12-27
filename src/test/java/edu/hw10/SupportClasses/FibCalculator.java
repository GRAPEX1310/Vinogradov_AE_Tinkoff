package edu.hw10.SupportClasses;

import edu.hw10.Task2.Cache;

public interface FibCalculator {
    @Cache(persist = true)
    public long fib(int number);
}
