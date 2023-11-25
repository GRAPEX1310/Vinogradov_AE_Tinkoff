package edu.hw7.Task3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.Nullable;

public class ReadWriteLockDatabaseRealization implements PersonDatabase {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private Map<Integer, Person> idCollection;
    private Map<String, Person> names;
    private Map<String, Person> phoneNumbers;
    private Map<String, Person> addresses;

    public ReadWriteLockDatabaseRealization() {
        this.idCollection = new HashMap<>();
        this.names = new HashMap<>();
        this.phoneNumbers = new HashMap<>();
        this.addresses = new HashMap<>();
    }


    @Override
    public void add(Person person) {
        lock.writeLock().lock();


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


        lock.writeLock().unlock();
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();


        Person person = idCollection.remove(id);
        names.remove(person.name());
        phoneNumbers.remove(person.phoneNumber());
        addresses.remove(person.address());


        lock.writeLock().unlock();
    }

    @Override
    public @Nullable Person findByName(String name) {
        lock.readLock().lock();
        try {
            Person person = names.get(name);
            if (person.address() != null && person.phoneNumber() != null) {
                return person;
            }

            return null;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByAddress(String address) {
        lock.readLock().lock();
        try {
            Person person = addresses.get(address);

            if (person.name() != null && person.phoneNumber() != null) {
                return person;
            }

            return null;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        lock.readLock().lock();
        try {
            Person person = phoneNumbers.get(phone);
            if (person.name() != null && person.address() != null) {
                return person;
            }

            return null;
        } finally {
            lock.readLock().unlock();
        }
    }

    public int size() {
        return this.idCollection.size();
    }
}
