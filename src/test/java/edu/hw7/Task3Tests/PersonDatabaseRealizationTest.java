package edu.hw7.Task3Tests;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDatabase;
import edu.hw7.Task3.PersonDatabaseRealization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PersonDatabaseRealizationTest {

    private PersonDatabase personDatabase;

    @BeforeEach
    void refreshStorage() {
        personDatabase = new PersonDatabaseRealization();
    }

    @Test
    @DisplayName("Test add function")
    void testAdd() {
        Thread t1 = new Thread(() -> {
            personDatabase.add(new Person(11, "wojak", "Moscow", "88005553535"));
            personDatabase.add(new Person(12, "wojak", "Moscow", "88005553535"));
            personDatabase.add(new Person(13, "wojak", "Moscow", "88005553535"));
            personDatabase.add(new Person(14, "wojak", "Moscow", "88005553535"));
            personDatabase.add(new Person(15, "wojak", "Moscow", "88005553535"));
            personDatabase.add(new Person(16, "wojak", "Moscow", "88005553535"));
            personDatabase.add(new Person(17, "wojak", "Moscow", "88005553535"));
            personDatabase.add(new Person(18, "wojak", "Moscow", "88005553535"));
            personDatabase.add(new Person(19, "wojak", "Moscow", "88005553535"));
            personDatabase.add(new Person(20, "wojak", "Moscow", "88005553535"));
        });

        Thread t2 = new Thread(() -> {
            personDatabase.add(new Person(1, "wojak", "Moscow", "88005553535"));
            personDatabase.add(new Person(2, "wojak", "Moscow", "88005553535"));
            personDatabase.add(new Person(3, "wojak", "Moscow", "88005553535"));
            personDatabase.add(new Person(4, "wojak", "Moscow", "88005553535"));
            personDatabase.add(new Person(5, "wojak", "Moscow", "88005553535"));
            personDatabase.add(new Person(6, "wojak", "Moscow", "88005553535"));
            personDatabase.add(new Person(7, "wojak", "Moscow", "88005553535"));
            personDatabase.add(new Person(8, "wojak", "Moscow", "88005553535"));
            personDatabase.add(new Person(9, "wojak", "Moscow", "88005553535"));
            personDatabase.add(new Person(10, "wojak", "Moscow", "88005553535"));
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            fail("Fail! Exception catch`s");
        }

        PersonDatabaseRealization resultDB = (PersonDatabaseRealization) personDatabase;
        int result = resultDB.size();

        assertThat(result).isEqualTo(20);
    }

    @Test
    @DisplayName("Test delete function")
    void testDelete() {

        personDatabase.add(new Person(1, "wojak1", "Moscow1", "1234"));
        personDatabase.add(new Person(2, "wojak2", "Moscow2", "0987"));
        personDatabase.add(new Person(3, "wojak3", "Moscow3", "5673"));
        personDatabase.add(new Person(4, "wojak4", "Moscow4", "2345"));
        personDatabase.add(new Person(5, "wojak5", "Moscow5", "4576"));
        personDatabase.add(new Person(6, "wojak6", "Moscow6", "1243"));
        personDatabase.add(new Person(7, "wojak7", "Moscow7", "6534"));
        personDatabase.add(new Person(8, "wojak8", "Moscow8", "8092"));
        personDatabase.add(new Person(9, "wojak9", "Moscow9", "7891"));
        personDatabase.add(new Person(10, "wojak10", "Moscow10", "7612"));

        Thread t1 = new Thread(() -> {
            personDatabase.delete(6);
            personDatabase.delete(7);
            personDatabase.delete(8);
            personDatabase.delete(9);
            personDatabase.delete(10);
        });

        Thread t2 = new Thread(() -> {
            personDatabase.delete(1);
            personDatabase.delete(2);
            personDatabase.delete(3);
            personDatabase.delete(4);
            personDatabase.delete(5);
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            fail("Fail! Exception catch`s");
        }


        PersonDatabaseRealization resultDB = (PersonDatabaseRealization) personDatabase;
        int result = resultDB.size();

        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("Test findByName function")
    void testFindByName() {
        Person neededPerson = new Person(0, "wojak", "Moscow", "88005553535");
        personDatabase.add(neededPerson);
        personDatabase.add(new Person(0, "doomer", "Moscow", "88005553535"));
        personDatabase.add(new Person(0, null, "Moscow", "88005553535"));
        personDatabase.add(new Person(0, "zoomer", "Moscow", "88005553535"));

        assertThat(personDatabase.findByName("wojak")).isEqualTo(neededPerson);
    }

    @Test
    @DisplayName("Test findByAddress function")
    void testFindByAddress() {
        Person neededPerson = new Person(0, "wojak", "Kursk", "88005553535");
        personDatabase.add(neededPerson);
        personDatabase.add(new Person(0, "doomer", "Moscow", "88005553535"));
        personDatabase.add(new Person(0, "boomer", "Spb", "88005553535"));
        personDatabase.add(new Person(0, "zoomer", null, "88005553535"));

        assertThat(personDatabase.findByAddress("Kursk")).isEqualTo(neededPerson);
    }

    @Test
    @DisplayName("Test findByPhone function")
    void testFindByPhone() {

        Person neededPerson = new Person(0, "wojak", "Kursk", "777");
        personDatabase.add(neededPerson);
        personDatabase.add(new Person(0, "doomer", "Moscow", "88005553535"));
        personDatabase.add(new Person(0, "boomer", "Moscow", "666"));
        personDatabase.add(new Person(0, "zoomer", "Moscow", null));

        assertThat(personDatabase.findByPhone("777")).isEqualTo(neededPerson);
    }
}
