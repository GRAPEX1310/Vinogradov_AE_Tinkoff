package edu.hw3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task8 {
    private Task8() {
    }

    public static class BackwardIterator<T> {
        private final List<T> internalCollection;
        private int currentIndex;

        public BackwardIterator(Collection<T> receivedCollection) {
            internalCollection = new ArrayList<>(receivedCollection);
            currentIndex = receivedCollection.size() - 1;
        }

        public boolean hasNext() {
            return currentIndex >= 0;
        }

        public T next() {
            return internalCollection.get(currentIndex--);
        }
    }
}
