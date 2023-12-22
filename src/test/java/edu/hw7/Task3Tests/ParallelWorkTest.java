package edu.hw7.Task3Tests;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDatabase;
import edu.hw7.Task3.ReadWriteLockDatabaseRealization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParallelWorkTest {

    @Test
    @DisplayName("Parallel work test")
    void testParallelWork() {
        PersonDatabase personDatabase = new ReadWriteLockDatabaseRealization();
        Person expectedResult = new Person(8, "wojak", "Moscow", "880055535358");

        CountDownLatch countDownLatch = new CountDownLatch(10);
        AtomicReference<Person> result = new AtomicReference<>(
                new Person(-1, "-1", "-1", "-1"));

        Thread loader = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                personDatabase.add(new Person(i, "wojak", "Moscow", "88005553535" + i));
                countDownLatch.countDown();
            }
        });

        Thread searcher = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
                if (countDownLatch.getCount() < 10) {
                    result.set(personDatabase.findByPhone("880055535358"));
                    if (result.get() != null) {
                        assertThat(result.get().phoneNumber()).isEqualTo("880055535358");
                        break;
                    }
                }
            }
        });

        loader.start();
        searcher.start();
        try {
            loader.join();
            searcher.join();
        } catch (InterruptedException ignored) {
        }

        assertThat(result.get()).isEqualTo(expectedResult);
    }
}
