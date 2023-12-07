package edu.hw4;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    private Validator() {
    }

    private final static String FIELD_IS_ZERO = "- is zero\n";
    private final static String FIELD_IS_EMPTY = "- is empty\n";
    private final static String FIELD_IS_NULL = "- is null\n";
    private final static String FIELD_IS_NEGATE = "- is negate\n";

    private final static String TYPE = "type";
    private final static String SEX = "sex";
    private final static String AGE = "age";
    private final static String HEIGHT = "height";
    private final static String WEIGHT = "weight";

    public static List<ValidationError> validate(TaskClasses.Animal animal) {
        List<ValidationError> errorList = new ArrayList<>();

        ValidationError message = checkName(animal);
        if (message != null) {
            errorList.add(message);
        }

        message = checkTypeAndSex(animal, TYPE);
        if (message != null) {
            errorList.add(message);
        }

        message = checkTypeAndSex(animal, SEX);
        if (message != null) {
            errorList.add(message);
        }

        message = checkAgeHeightWeight(animal, AGE);
        if (message != null) {
            errorList.add(message);
        }

        message = checkAgeHeightWeight(animal, HEIGHT);
        if (message != null) {
            errorList.add(message);
        }

        message = checkAgeHeightWeight(animal, WEIGHT);
        if (message != null) {
            errorList.add(message);
        }

        return errorList;
    }

    private static ValidationError checkName(TaskClasses.Animal animal) {
        ValidationError errorMessage = new ValidationError();
        if (animal.name() == null) {
            errorMessage.setMessage("name" + FIELD_IS_NULL);
        } else if (animal.name().equals(" ")) {
            errorMessage.setMessage(animal.name() + FIELD_IS_EMPTY);
        } else {
            errorMessage = null;
        }

        return errorMessage;
    }

    private static ValidationError checkTypeAndSex(TaskClasses.Animal animal, String fieldType) {
        ValidationError errorMessage = new ValidationError();

        if (fieldType.equals(TYPE)) {
            if (animal.type() == null) {
                errorMessage.setMessage(fieldType + FIELD_IS_NULL);
            } else {
                errorMessage = null;
            }
        } else if (fieldType.equals(SEX)) {
            if (animal.sex() == null) {
                errorMessage.setMessage(fieldType + FIELD_IS_NULL);
            } else {
                errorMessage = null;
            }
        }

        return errorMessage;
    }

    private static ValidationError checkAgeHeightWeight(TaskClasses.Animal animal, String fieldType) {
        ValidationError errorMessage = new ValidationError();
        switch (fieldType) {
            case AGE -> {
                if (animal.age() == 0) {
                    errorMessage.setMessage(fieldType + FIELD_IS_ZERO);
                } else if (animal.age() < 0) {
                    errorMessage.setMessage(fieldType + FIELD_IS_NEGATE);
                } else {
                    errorMessage = null;
                }
            }
            case HEIGHT -> {
                if (animal.height() == 0) {
                    errorMessage.setMessage(fieldType + FIELD_IS_ZERO);
                } else if (animal.height() < 0) {
                    errorMessage.setMessage(fieldType + FIELD_IS_NEGATE);
                } else {
                    errorMessage = null;
                }
            }
            case WEIGHT -> {
                if (animal.weight() == 0) {
                    errorMessage.setMessage(fieldType + FIELD_IS_ZERO);
                } else if (animal.weight() < 0) {
                    errorMessage.setMessage(fieldType + FIELD_IS_NEGATE);
                } else {
                    errorMessage = null;
                }
            }
            default -> {
                errorMessage = null;
            }
        }

        return errorMessage;
    }
}
