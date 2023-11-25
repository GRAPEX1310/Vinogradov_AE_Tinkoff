package edu.hw7.Task3;

import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class PersonDatabaseRealization implements PersonDatabase {

    private Map<Integer, Person> idCollection;
    private Map<String, Person> names;
    private Map<String, Person> phoneNumbers;
    private Map<String, Person> addresses;

    public PersonDatabaseRealization() {
        this.idCollection = new HashMap<>();
        this.names = new HashMap<>();
        this.phoneNumbers = new HashMap<>();
        this.addresses = new HashMap<>();
    }

    @Override
    public synchronized void add(Person person) {
        if (person.name() != null) {
            names.put(person.name(), person);
        }

        if (person.phoneNumber() != null) {
            phoneNumbers.put(person.phoneNumber(), person);
        }
        if (person.address() != null) {
            addresses.put(person.address(), person);
        }

        idCollection.put(person.id(), person);
    }

    @Override
    public synchronized void delete(int id) {
        Person person = idCollection.remove(id);
        names.remove(person.name());
        phoneNumbers.remove(person.phoneNumber());
        addresses.remove(person.address());
    }

    @Override
    public synchronized @Nullable Person findByName(String name) {
        Person person = names.get(name);
        if (person.address() != null && person.phoneNumber() != null) {
            return person;
        }

        return null;
    }

    @Override
    public synchronized @Nullable Person findByAddress(String address) {
        Person person = addresses.get(address);

        if (person.name() != null && person.phoneNumber() != null) {
            return person;
        }

        return null;
    }

    @Override
    public synchronized @Nullable Person findByPhone(String phone) {
        Person person = phoneNumbers.get(phone);
        if (person.name() != null && person.address() != null) {
            return person;
        }

        return null;
    }

    public int size() {
        return this.idCollection.size();
    }
}
