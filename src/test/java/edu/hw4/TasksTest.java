package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TasksTest {

    private static final List<TaskClasses.Animal> ANIMAL_LIST = new ArrayList<>(List.of(
            new TaskClasses.Animal("cat_1", TaskClasses.Animal.Type.CAT, TaskClasses.Animal.Sex.M, 4, 200, 15, true),
            new TaskClasses.Animal("cat_2", TaskClasses.Animal.Type.CAT, TaskClasses.Animal.Sex.F, 7, 15, 5, false),
            new TaskClasses.Animal("cat_3", TaskClasses.Animal.Type.CAT, TaskClasses.Animal.Sex.F, 10, 25, 9, true),
            new TaskClasses.Animal("dog_1", TaskClasses.Animal.Type.DOG, TaskClasses.Animal.Sex.M, 3, 333, 54, true),
            new TaskClasses.Animal("dog_2", TaskClasses.Animal.Type.DOG, TaskClasses.Animal.Sex.F, 2, 100, 200, false),
            new TaskClasses.Animal("bird_1", TaskClasses.Animal.Type.BIRD, TaskClasses.Animal.Sex.M, 1, 5, 7, false),
            new TaskClasses.Animal("bird_2", TaskClasses.Animal.Type.BIRD, TaskClasses.Animal.Sex.F, 8, 20, 3, true),
            new TaskClasses.Animal("looo_ooongName_fish_1", TaskClasses.Animal.Type.FISH, TaskClasses.Animal.Sex.M, 5, 50, 4, true),
            new TaskClasses.Animal("fish_2", TaskClasses.Animal.Type.FISH, TaskClasses.Animal.Sex.F, 1, 115, 115, true),
            new TaskClasses.Animal("Porsche spider 918", TaskClasses.Animal.Type.SPIDER, TaskClasses.Animal.Sex.F, 6, 110, 1, true)
    ));

    //Task1Test
    @Test
    @DisplayName("TASK1_TEST: Checking animalHeightSort function")
    void testAnimalHeightSort() {
        Tasks.animalHeightSort(ANIMAL_LIST);
        assertThat(ANIMAL_LIST.toArray(new TaskClasses.Animal[0]))
                .extracting(TaskClasses.Animal::height).contains(333, 200, 115, 110, 100, 50, 25, 20, 15, 5);
    }

    //Task2Test
    @Test
    @DisplayName("TASK2_TEST: Checking animalWeightSort function")
    void testAnimalWeightSort() {

        //k < array.size()
        assertThat(Tasks.animalWeightSort(ANIMAL_LIST, 3).toArray(new TaskClasses.Animal[0]))
                .extracting(TaskClasses.Animal::weight).contains(200, 115, 54);


        //k > array.size()
        assertThat(Tasks.animalWeightSort(ANIMAL_LIST, 10).toArray(new TaskClasses.Animal[0]))
                .extracting(TaskClasses.Animal::weight).contains(200, 115, 54, 15, 9, 7, 5, 4, 3, 1);
    }

    //Task3Test
    @Test
    @DisplayName("TASK3_TEST: Checking animalTypesCounter function")
    void testAnimalTypesCounter() {

        //empty array
        assertThat(Tasks.animalTypesCounter(new ArrayList<>()).size()).isEqualTo(0);

        //Not empty array
        var resultMap = Tasks.animalTypesCounter(ANIMAL_LIST);

        assertThat(resultMap.size()).isEqualTo(5);
        assertThat(resultMap.get(TaskClasses.Animal.Type.CAT)).isEqualTo(3);
        assertThat(resultMap.get(TaskClasses.Animal.Type.DOG)).isEqualTo(2);
        assertThat(resultMap.get(TaskClasses.Animal.Type.BIRD)).isEqualTo(2);
        assertThat(resultMap.get(TaskClasses.Animal.Type.FISH)).isEqualTo(2);
        assertThat(resultMap.get(TaskClasses.Animal.Type.SPIDER)).isEqualTo(1);
    }

    //Task4Test
    @Test
    @DisplayName("TASK4_TEST: Checking findAnimalWithLongestName function")
    void testFindAnimalWithLongestName() {
        assertThat(Tasks.findAnimalWithLongestName(ANIMAL_LIST).name()).isEqualTo("looo_ooongName_fish_1");
    }

    //Task5Test
    @Test
    @DisplayName("TASK5_TEST: Checking counterOfAnimalSex function")
    void testCounterOfAnimalSex() {
        assertThat(Tasks.counterOfAnimalSex(ANIMAL_LIST)).isEqualTo(TaskClasses.Animal.Sex.F);
    }

    //Task6Test
    @Test
    @DisplayName("TASK6_TEST: Checking findHeaviestAnimalPerType function")
    void testFindHeaviestAnimalPerType() {
        //empty array
        assertThat(Tasks.findHeaviestAnimalPerType(new ArrayList<>()).size()).isEqualTo(0);

        //Not empty array
        var resultMap = Tasks.findHeaviestAnimalPerType(ANIMAL_LIST);

        assertThat(resultMap.size()).isEqualTo(5);

        assertThat(resultMap.get(TaskClasses.Animal.Type.CAT).name()).isEqualTo("cat_1");
        assertThat(resultMap.get(TaskClasses.Animal.Type.DOG).name()).isEqualTo("dog_2");
        assertThat(resultMap.get(TaskClasses.Animal.Type.BIRD).name()).isEqualTo("bird_1");
        assertThat(resultMap.get(TaskClasses.Animal.Type.FISH).name()).isEqualTo("fish_2");
        assertThat(resultMap.get(TaskClasses.Animal.Type.SPIDER).name()).isEqualTo("Porsche spider 918");


    }

    //Task7Test
    @Test
    @DisplayName("TASK7_TEST: Checking findKOldestAnimal function")
    void testFindKOldestAnimal() {

        //k < array.size()
        assertThat(Tasks.findKOldestAnimal(ANIMAL_LIST, 3).name()).isEqualTo("cat_2");

        //k > array.size()
        assertThat(Tasks.findKOldestAnimal(ANIMAL_LIST, 10).name()).isEqualTo("fish_2");
    }

    //Task8Test
    @Test
    @DisplayName("TASK8_TEST: Checking findHeaviestAnimalWithOptionalHeight function")
    void testFindHeaviestAnimalWithOptionalHeight() {
        assertThat(Tasks.findHeaviestAnimalAmongLessSomeHeight(ANIMAL_LIST, 100).get().name())
                .isEqualTo("cat_3");
    }


    //Task9Test
    @Test
    @DisplayName("TASK9_TEST: Checking animalsPawsCounter function")
    void testAnimalsPawsCounter() {

        //empty array
        assertThat(Tasks.animalsPawsCounter(new ArrayList<>())).isEqualTo(0);

        //Not empty array
        assertThat(Tasks.animalsPawsCounter(ANIMAL_LIST)).isEqualTo(32);
    }

    //Task10Test
    @Test
    @DisplayName("TASK10_TEST: Checking findAnimalsWithDiffAmountOfPawsAndAge function")
    void testFindAnimalsWithDiffAmountOfPawsAndAge() {
        assertThat(Tasks.findAnimalsWithDiffAmountOfPawsAndAge(ANIMAL_LIST).toArray().length).isEqualTo(9);
    }

    //Task11Test
    @Test
    @DisplayName("TASK11_TEST: Checking findBitingAnimals function")
    void testFindBitingAnimals() {

        List<TaskClasses.Animal> result = Tasks.findBitingAnimals(ANIMAL_LIST);
        assertThat(Tasks.findBitingAnimals(ANIMAL_LIST).toArray().length).isEqualTo(4);
    }

    //Task12Test
    @Test
    @DisplayName("TASK12_TEST: Checking countAnimalsWhichWeightHigherHeight function")
    void testCountAnimalsWhichWeightHigherHeight() {
        assertThat(Tasks.countAnimalsWhichWeightHigherHeight(ANIMAL_LIST)).isEqualTo(2);
    }

    //Task13Test
    @Test
    @DisplayName("TASK13_TEST: Checking findAnimalsWithNamesLonger2Words function")
    void testFindAnimalsWithNamesLonger2Words() {
        assertThat(Tasks.findAnimalsWithNamesLonger2Words(ANIMAL_LIST)
                .toArray(new TaskClasses.Animal[0])).extracting(TaskClasses.Animal::name).contains("Porsche spider 918");
    }

    //Task14Test
    @Test
    @DisplayName("TASK14_TEST: Checking isInListDogLongerK function")
    void testIsInListDogLongerK() {
        assertThat(Tasks.isInListDogLongerK(ANIMAL_LIST, 100)).isEqualTo(true);
    }

    //Task15Test
    @Test
    @DisplayName("TASK15_TEST: Checking sumOfWeightWithBorders function")
    void testSumOfWeightWithBorders() {
        assertThat(Tasks.sumOfWeightWithAgeBorders(ANIMAL_LIST, 3, 8)).isEqualTo(82);
    }

    //Task16Test
    @Test
    @DisplayName("TASK16_TEST: Checking strangeAnimalsSort function")
    void testStrangeAnimalsSort() {
        ArrayList<String> nameList = new ArrayList<>();

        for (TaskClasses.Animal animal : Tasks.sortByTypeThenSexThenName(ANIMAL_LIST)) {
            nameList.add(animal.name());
        }

        assertThat(nameList.toArray(new String[0])).containsExactly("bird_2", "bird_1",
                "cat_2", "cat_3", "cat_1", "dog_2", "dog_1", "fish_2", "looo_ooongName_fish_1", "Porsche spider 918");
    }

    //Task17Test
    @Test
    @DisplayName("TASK17_TEST: Checking doSpidersBiteMoreThaDogs function")
    void testDoSpidersBiteMoreThaDogs() {
        assertThat(Tasks.doSpidersBiteMoreThaDogs(ANIMAL_LIST)).isEqualTo(true);
    }

    //Task18Test
    @Test
    @DisplayName("TASK18_TEST: Checking findHeaviestFish function")
    void testFindHeaviestFish() {
        List<TaskClasses.Animal> animalList_1 = new ArrayList<>(List.of(
                new TaskClasses.Animal("fish_1", TaskClasses.Animal.Type.FISH, TaskClasses.Animal.Sex.M, 100, 100, 350, true),
                new TaskClasses.Animal("fish_2", TaskClasses.Animal.Type.FISH, TaskClasses.Animal.Sex.F, 1, 50, 15, false)
        ));

        List<TaskClasses.Animal> animalList_2 = new ArrayList<>(List.of(
                new TaskClasses.Animal("fish_3", TaskClasses.Animal.Type.FISH, TaskClasses.Animal.Sex.M, 1, 100, 25, true),
                new TaskClasses.Animal("fish_4", TaskClasses.Animal.Type.FISH, TaskClasses.Animal.Sex.F, 100, 200, 500, false)
        ));

        assertThat(Tasks.findHeaviestFish(animalList_1, animalList_2).name()).isEqualTo("fish_4");
    }

    //Task19-20Test
    @Test
    @DisplayName("TASK19-20_TEST: Checking findErrorsWithAnimals function")
    void testFindErrorsWithAnimals() throws IllegalAccessException {
        List<TaskClasses.Animal> animalList = new ArrayList<>(List.of(
                new TaskClasses.Animal("a1", null, TaskClasses.Animal.Sex.M, 100, 100, 350, true),
                new TaskClasses.Animal("a2", TaskClasses.Animal.Type.DOG, null, 1, 50, 15, false),
                new TaskClasses.Animal("a3", TaskClasses.Animal.Type.CAT, TaskClasses.Animal.Sex.F, -1, 50, 15, false),
                new TaskClasses.Animal("a4", TaskClasses.Animal.Type.SPIDER, TaskClasses.Animal.Sex.F, 1, 50, -1, false)
        ));

        Map<String,String> resultMap = Tasks.findErrorsWithAnimalsWithStrings(animalList);


        assertThat(resultMap.get("a1")).isEqualTo("type- is null\n");
        assertThat(resultMap.get("a2")).isEqualTo("sex- is null\n");
        assertThat(resultMap.get("a3")).isEqualTo("age- is negate\n");
        assertThat(resultMap.get("a4")).isEqualTo("weight- is negate\n");
    }
}