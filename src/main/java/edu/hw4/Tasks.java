package edu.hw4;

import java.util.ArrayList;
import java.util.Arrays;
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
        TaskClasses.Animal resultAnimal;
        List<TaskClasses.Animal> newAnimalList = new ArrayList<>(List.copyOf(givenList));

        newAnimalList.sort(Comparator.comparingInt(TaskClasses.Animal::age));
        newAnimalList = newAnimalList.reversed();

        if (k > newAnimalList.size()) {
            resultAnimal = newAnimalList.get(0);
        } else {
            resultAnimal = newAnimalList.get(k - 1);
        }

        return resultAnimal;
    }

    //Task8
    public static Optional<TaskClasses.Animal> findHeaviestAnimalWithOptionalHeight(
            List<TaskClasses.Animal> givenList, int optionalHeight) {

        List<TaskClasses.Animal> newAnimalList = new ArrayList<>(
                givenList.stream().filter(it -> it.height() < optionalHeight).toList());

        newAnimalList.sort(Comparator.comparingInt(TaskClasses.Animal::weight));

        if (newAnimalList.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(newAnimalList.get(newAnimalList.size() - 1));
    }

    //Task9
    public static Integer animalsPawsCounter(List<TaskClasses.Animal> givelList) {
        return Arrays.stream(givelList.toArray(new TaskClasses.Animal[0])).mapToInt(TaskClasses.Animal::paws).sum();
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
        return givenList.stream().filter(it -> it.weight() > it.height()).collect(Collectors.toList()).size();
    }

    //Task13
    public static List<TaskClasses.Animal> findAnimalsWithNamesLonger2Words(List<TaskClasses.Animal> givenList) {
        return givenList.stream().filter(it -> it.name().contains(" ")).collect(Collectors.toList());
    }

    //Task14
    public static Boolean isInListDogLongerK(List<TaskClasses.Animal> givenList, int k) {

        for (TaskClasses.Animal animal : givenList) {
            if (animal.type() == TaskClasses.Animal.Type.DOG && animal.height() > k) {
                return true;
            }
        }

        return false;
    }

    //Task15
    public static Integer sumOfWeightWithBorders(List<TaskClasses.Animal> givenList, int k, int l) {
        return Arrays.stream(givenList.toArray(new TaskClasses.Animal[0]))
                .filter(it -> it.age() >= k && it.age() <= l).mapToInt(TaskClasses.Animal::weight).sum();
    }

    //Task16
    public static List<TaskClasses.Animal> strangeAnimalsSort(List<TaskClasses.Animal> givenList) {
        List<TaskClasses.Animal> newAnimalsList = new ArrayList<>(List.copyOf(givenList));
        newAnimalsList.sort(new Comparator<TaskClasses.Animal>() {
            @Override
            public int compare(TaskClasses.Animal o1, TaskClasses.Animal o2) {
                if (!o1.type().equals(o2.type())) {
                    return o1.type().toString().compareTo(o2.type().toString());
                } else {
                    if (!o1.sex().equals(o2.sex())) {
                        return o1.sex().toString().compareTo(o2.sex().toString());
                    } else {
                        return o1.name().compareTo(o2.name());
                    }
                }
            }
        });

        return newAnimalsList;
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
    public static Map<String, Set<ValidationError>> findErrorsWithAnimals(List<TaskClasses.Animal> givenList)
            throws NoSuchFieldError, IllegalAccessException {

        ArrayList<String> fieldsNamesArray = new ArrayList<>(List.of("name", "type", "sex", "age", "height", "weight"));
        Map<String, Set<ValidationError>> errorsMap = new HashMap<>();

        for (TaskClasses.Animal animal : givenList) {
            Set<ValidationError> errorsSet = new HashSet<>();

            for (String field : fieldsNamesArray) {
                try {
                    errorsSet.add(new ValidationError(animal, field));
                } catch (IllegalAccessException | NoSuchFieldException operationException) {

                }
            }

            if (!errorsSet.isEmpty()) {
                errorsMap.put(animal.name(), errorsSet);
            }

        }
        return errorsMap;
    }

    //Task20
    public static Map<String, String> findErrorsWithAnimalsWithStrings(List<TaskClasses.Animal> givenList)
            throws NoSuchFieldError, IllegalAccessException {

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
