package edu.hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Tasks {
    private Tasks() {

    }

    //Task1
    public static void animalHeightSort(List<TaskClasses.Animal> givenList) {
        givenList.sort(Comparator.comparingInt(TaskClasses.Animal::height));
    }

    //Task2
    public static List<TaskClasses.Animal> animalWeightSort(List<TaskClasses.Animal> givenList, int k) {
        List<TaskClasses.Animal> newAnimalList = new ArrayList<>(List.copyOf(givenList));

        newAnimalList.sort(Comparator.comparingInt(TaskClasses.Animal::weight));

        return newAnimalList.reversed().stream().limit(k).collect(Collectors.toList());
    }

    //Task3
    public static Map<TaskClasses.Animal.Type, Integer> animalTypesCounter(List<TaskClasses.Animal> givenList) {
        Map<TaskClasses.Animal.Type, Integer> animalTypesMap = new HashMap<>();

        for (TaskClasses.Animal animal : givenList) {
            animalTypesMap.merge(animal.type(), 1, Integer::sum);
        }

        return animalTypesMap;
    }

    //Task4
    public static TaskClasses.Animal findAnimalWithLongestName(List<TaskClasses.Animal> givenList) {

        if (givenList.isEmpty()) {
            return null;
        }

        TaskClasses.Animal animalWithLongestName = givenList.get(0);

        for (TaskClasses.Animal animal : givenList) {
            if (animal.name().length() > animalWithLongestName.name().length()) {
                animalWithLongestName = animal;
            }
        }

        return animalWithLongestName;
    }

    //Task5
    public static TaskClasses.Animal.Sex counterOfAnimalSex(List<TaskClasses.Animal> givenList) {
        int maleCounter = 0;
        int femaleCounter = 0;

        for (TaskClasses.Animal animal : givenList) {
            if (animal.sex() == TaskClasses.Animal.Sex.M) {
                maleCounter++;
            } else {
                femaleCounter++;
            }
        }

        if (maleCounter > femaleCounter) {
            return TaskClasses.Animal.Sex.M;
        } else {
            return TaskClasses.Animal.Sex.F;
        }
    }

    //Task6
    public static Map<TaskClasses.Animal.Type, TaskClasses.Animal> findHeaviestAnimalPerType(
            List<TaskClasses.Animal> givenList) {
        Map<TaskClasses.Animal.Type, TaskClasses.Animal> resultMap = new HashMap<>();

        for (TaskClasses.Animal animal : givenList) {
            if (!resultMap.containsKey(animal.type()) || resultMap.get(animal.type()).weight() < animal.weight()) {
                resultMap.put(animal.type(), animal);
            }
        }

        return resultMap;
    }

    //Task7
    public static TaskClasses.Animal findKOldestAnimal(List<TaskClasses.Animal> givenList, int k) {

        if (givenList.isEmpty()) {
            return null;
        }

        return givenList.stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(TaskClasses.Animal::age)))
                .limit(k)
                .toList()
                .getLast();
    }

    //Task8
    public static Optional<TaskClasses.Animal> findHeaviestAnimalAmongLessSomeHeight(
            List<TaskClasses.Animal> givenList, int height) {

        return Optional.of(givenList.stream()
                .filter(element -> element.height() < height)
                .max(Comparator.comparing(TaskClasses.Animal::weight))
                .get());
    }

    //Task9
    public static Integer animalsPawsCounter(List<TaskClasses.Animal> givelList) {
        return givelList.stream().mapToInt(TaskClasses.Animal::paws).sum();
    }

    //Task10
    public static List<TaskClasses.Animal> findAnimalsWithDiffAmountOfPawsAndAge(List<TaskClasses.Animal> givenList) {
        return givenList.stream().filter(it -> it.paws() != it.age()).collect(Collectors.toList());
    }

    //Task11
    public static List<TaskClasses.Animal> findBitingAnimals(List<TaskClasses.Animal> givenList) {
        final int MINIMUM_HEIGHT = 100;

        return givenList.stream().filter(it -> it.bites() && it.height() > MINIMUM_HEIGHT).collect(Collectors.toList());
    }

    //Task12
    public static Integer countAnimalsWhichWeightHigherHeight(List<TaskClasses.Animal> givenList) {
        return givenList.stream().filter(it -> it.weight() > it.height()).toList().size();
    }

    //Task13
    public static List<TaskClasses.Animal> findAnimalsWithNamesLonger2Words(List<TaskClasses.Animal> givenList) {
        return givenList.stream()
                .filter(it -> {
                    return it.name().trim().split(" ").length > 2;
                })
                .toList();
    }

    //Task14
    public static Boolean isInListDogLongerK(List<TaskClasses.Animal> givenList, int k) {
        return !givenList.stream()
                .filter(it -> it.type().equals(TaskClasses.Animal.Type.DOG) && it.height() > k)
                .toList()
                .isEmpty();
    }

    //Task15
    public static Integer sumOfWeightWithAgeBorders(List<TaskClasses.Animal> givenList, int minAge, int maxAge) {
        return givenList.stream()
                .filter(it -> it.age() >= minAge && it.age() <= maxAge)
                .mapToInt(TaskClasses.Animal::weight)
                .sum();
    }

    //Task16
    public static List<TaskClasses.Animal> sortByTypeThenSexThenName(List<TaskClasses.Animal> givenList) {
        return givenList.stream().sorted(((o1, o2) -> {

            if (!o1.type().equals(o2.type())) {
                return o1.type().toString().compareTo(o2.type().toString());
            } else {
                if (!o1.sex().equals(o2.sex())) {
                    return o1.sex().toString().compareTo(o2.sex().toString());
                } else {
                    return o1.name().compareTo(o2.name());
                }
            }
        })).toList();
    }

    //Task17
    public static Boolean doSpidersBiteMoreThaDogs(List<TaskClasses.Animal> givenList) {
        Boolean result = false;
        double spidersCounter = 0;
        double dogsCounter = 0;
        double spidersBitesCounter = 0;
        double dogsBitesCounter = 0;

        for (TaskClasses.Animal animal : givenList) {
            if (animal.type().equals(TaskClasses.Animal.Type.SPIDER)) {
                spidersCounter++;
                if (animal.bites()) {
                    spidersBitesCounter++;
                }
            }

            if (animal.type().equals(TaskClasses.Animal.Type.DOG)) {
                dogsCounter++;
                if (animal.bites()) {
                    dogsBitesCounter++;
                }
            }
        }

        if (dogsBitesCounter != 0 && dogsCounter != 0 && spidersBitesCounter != 0 && spidersCounter != 0) {
            result = dogsBitesCounter / dogsCounter < spidersBitesCounter / spidersCounter;
        }

        return result;
    }

    //Task18
    @SafeVarargs
    public static TaskClasses.Animal findHeaviestFish(List<TaskClasses.Animal>... givenLists) {

        if (givenLists.length == 0) {
            return null;
        }

        TaskClasses.Animal result = givenLists[0].get(0);

        for (List<TaskClasses.Animal> animalsList : givenLists) {
            for (TaskClasses.Animal animal : animalsList) {
                if (animal.type().equals(TaskClasses.Animal.Type.FISH) && animal.weight() > result.weight()) {
                    result = animal;
                }
            }
        }

        return result;
    }

    //Task19
    public static Map<String, Set<ValidationError>> findErrorsWithAnimals(List<TaskClasses.Animal> givenList) {
        Map<String, Set<ValidationError>> errorsMap = new HashMap<>();

        for (TaskClasses.Animal animal : givenList) {

            List<ValidationError> errorList = Validator.validate(animal);

            Set<ValidationError> errorsSet = new HashSet<>(errorList);

            if (!errorsSet.isEmpty()) {
                errorsMap.put(animal.name(), errorsSet);
            }
        }

        return errorsMap;
    }

    //Task20
    public static Map<String, String> findErrorsWithAnimalsWithStrings(List<TaskClasses.Animal> givenList) {
        Map<String, Set<ValidationError>> errorsMap = findErrorsWithAnimals(givenList);
        Map<String, String> resultMap = new HashMap<>();

        for (var element : errorsMap.entrySet()) {
            StringBuilder errorString = new StringBuilder();

            for (ValidationError error : element.getValue()) {
                errorString.append(error.getMessage());
            }

            resultMap.put(element.getKey(), errorString.toString());
        }

        return resultMap;
    }
}
