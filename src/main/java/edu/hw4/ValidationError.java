package edu.hw4;

import java.lang.reflect.Field;

public class ValidationError {

    private final static String FIELD_IS_ZERO = "- is zero\n";
    private final static String FIELD_IS_NULL = "- is null\n";
    private final static String FIELD_IS_NEGATE = "- is negate\n";

    private String errorMessage = null;

    public String getMessage() {
        return errorMessage;
    }

    public ValidationError(TaskClasses.Animal animal, String fieldName)
            throws IllegalAccessException, NoSuchFieldException {

        Field field = animal.getClass().getDeclaredField(fieldName);

        field.setAccessible(true);

        switch (fieldName) {

            case "name":
                checkName(animal, field);
                break;

            case "type":
            case "sex":
                checkTypeAndSex(animal, field);
                break;

            case "age":
            case "height":
            case "weight":
                checkAgeHeightWeight(animal, field);
                break;
            default:
                break;
        }
    }

    private void checkName(TaskClasses.Animal animal, Field field) throws IllegalAccessException {
        if (field.get(animal) == null) {
            errorMessage = field.getName() + FIELD_IS_NULL;
        } else if (field.get(animal).equals(" ")) {
            errorMessage = field.getName() + ": is empty\n";
        } else {
            throw new IllegalAccessException();
        }
    }

    private void checkTypeAndSex(TaskClasses.Animal animal, Field field) throws IllegalAccessException {
        if (field.get(animal) == null) {
            errorMessage = field.getName() + FIELD_IS_NULL;
        } else {
            throw new IllegalAccessException();
        }
    }

    private void checkAgeHeightWeight(TaskClasses.Animal animal, Field field) throws IllegalAccessException {
        if ((Integer) field.get(animal) == 0) {
            errorMessage = field.getName() + FIELD_IS_ZERO;
        } else if ((Integer) field.get(animal) < 0) {
            errorMessage = field.getName() + FIELD_IS_NEGATE;
        } else {
            throw new IllegalAccessException();
        }
    }

}
