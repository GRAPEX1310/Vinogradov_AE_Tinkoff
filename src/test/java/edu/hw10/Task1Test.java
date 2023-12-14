package edu.hw10;

import edu.hw10.SupportClasses.MyClass;
import edu.hw10.SupportClasses.MyRecord;
import edu.hw10.Task1.RandomObjectGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    private final RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();

    @Test
    @DisplayName("Check RandomObjectGenerator work with class")
    void testRandomObjectGeneratorWithClass() {
        for (int i = 0 ; i < 10; i++) {
            MyClass object1 = (MyClass) randomObjectGenerator.nextObject(MyClass.class);
            MyClass object2 = (MyClass) randomObjectGenerator.nextObject(MyClass.class, "create");

            assertThat(object1).isNotNull();
            assertThat(object2).isNotNull();
            assertThat(object1).isNotEqualTo(object2);
        }
    }

    @Test
    @DisplayName("Check RandomObjectGenerator work with record")
    void testRandomObjectGeneratorWorkWithRecord() {
        for (int i = 0 ; i < 10;i++) {
            MyRecord object1 = (MyRecord) randomObjectGenerator.nextObject(MyRecord.class);
            MyRecord object2 = (MyRecord) randomObjectGenerator.nextObject(MyRecord.class);

            assertThat(object1).isNotNull();
            assertThat(object2).isNotNull();
            assertThat(object1).isNotEqualTo(object2);
        }
    }
}
