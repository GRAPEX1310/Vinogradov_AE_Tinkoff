package edu.hw3;

import java.util.Collection;

public class Task8 {
    private Task8() {
    }

    static class BackwardIterator<T> {
        private Collection<T> collection;
        private int index;

        BackwardIterator(Collection<T> iternalCollection) {
            collection = iternalCollection;
            index = iternalCollection.size() - 1;
        }

        public boolean hasNext() {
            return index >= 0 && collection.toArray()[index] != null;
        }

        public T next() {
            return (T) collection.toArray()[index--];
        }
    }
}
