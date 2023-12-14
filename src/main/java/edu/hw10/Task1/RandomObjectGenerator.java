package edu.hw10.Task1;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import edu.hw10.Task1.Annotations.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomObjectGenerator {

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 100;

    private static final String INTEGER_CLASSNAME = "java.lang.Integer";
    private static final String LONG_CLASSNAME = "java.lang.Long";
    private static final String CHARACTER_CLASSNAME = "java.lang.Character";
    private static final String FLOAT_CLASSNAME = "java.lang.Float";
    private static final String DOUBLE_CLASSNAME = "java.lang.Double";
    private static final String BOOLEAN_CLASSNAME = "java.lang.Boolean";
    private static final String STRING_CLASSNAME = "java.lang.String";

    private static final List<Class<?>> BASIC_DATA_TYPES = new ArrayList<>(List.of(
            int.class, Integer.class, char.class, Character.class, long.class, Long.class, float.class,
            Float.class, double.class, Double.class, String.class, boolean.class, Boolean.class
    ));

    public RandomObjectGenerator() {
    }

    public Object nextObject(Class<?> currentClass) {
        try {
            Random random = new Random();

            if (isBasicType(currentClass)) {
                switch (currentClass.getName()) {
                    case INTEGER_CLASSNAME, LONG_CLASSNAME, CHARACTER_CLASSNAME,
                            FLOAT_CLASSNAME, DOUBLE_CLASSNAME, BOOLEAN_CLASSNAME, STRING_CLASSNAME -> {
                        return getRandomValue(currentClass, random.nextBoolean(), MIN_VALUE, MAX_VALUE);
                    }

                    default -> {
                        return getRandomValue(currentClass, false, MIN_VALUE, MAX_VALUE);
                    }
                }
            }

            Constructor<?>[] constructors = currentClass.getConstructors();
            Constructor<?> constructor = constructors[random.nextInt(0, constructors.length)];
            Parameter[] parameterTypes = constructor.getParameters();

            return constructor.newInstance(generateRandomParameters(parameterTypes, currentClass).toArray());
        } catch (Exception e) {
            return null;
        }
    }

    public Object nextObject(Class<?> currentClass, String methodName) {
        try {
            Method fabricMethod = currentClass.getMethod(methodName,
                    new Class<?>[] {int.class, String.class, Boolean.class});
            Parameter[] parametersTypes = fabricMethod.getParameters();

            return fabricMethod.invoke(null, generateRandomParameters(parametersTypes, currentClass).toArray());
        } catch (Exception e) {
            return null;
        }
    }

    private List<Object> generateRandomParameters(Parameter[] parametersTypes, Class<?> currentClass)
            throws NoSuchFieldException {
        List<Object> parameters = new ArrayList<>();

        for (var parameterType : parametersTypes) {
            Annotation[] annotations = currentClass.getDeclaredField(parameterType.getName()).getAnnotations();

            boolean isNull = false;
            int start = MIN_VALUE;
            int end = MAX_VALUE;

            for (var annotation : annotations) {
                if (annotation.annotationType().equals(NotNull.class)) {
                    isNull = true;
                } else if (annotation.annotationType().equals(Min.class)) {
                    Min minAnnotation = (Min) annotation;
                    start = (int) minAnnotation.value();
                } else if (annotation.annotationType().equals(Max.class)) {
                    Max minAnnotation = (Max) annotation;
                    end = (int) minAnnotation.value();
                }
            }

            parameters.add(getRandomValue((Class<?>) parameterType.getParameterizedType(), isNull, start, end));
        }

        return parameters;
    }

    @SuppressWarnings("CyclomaticComplexity")
    private Object getRandomValue(Class<?> type, boolean isNull, int start, int end) {
        Object result;
        Random random = new Random();

        switch (type.getName()) {
            case "int", "long" -> {
                result = random.nextInt(start, end);
            }

            case INTEGER_CLASSNAME, LONG_CLASSNAME -> {
                if (isNull && random.nextBoolean()) {
                    result = null;
                } else {
                    result = random.nextInt(start, end);
                }
            }

            case "char" -> {
                result = (char) random.nextInt(start, end);
            }

            case CHARACTER_CLASSNAME -> {
                if (isNull && random.nextBoolean()) {
                    result = null;
                } else {
                    result = (char) random.nextInt(start, end);
                }
            }

            case "float" -> {
                result = random.nextFloat(start, end);
            }

            case FLOAT_CLASSNAME -> {
                if (isNull && random.nextBoolean()) {
                    result = null;
                } else {
                    result = random.nextFloat(start, end);
                }
            }

            case "double" -> {
                result = random.nextDouble(start, end);
            }

            case DOUBLE_CLASSNAME -> {
                if (isNull && random.nextBoolean()) {
                    result = null;
                } else {
                    result = random.nextDouble(start, end);
                }
            }

            case "boolean" -> {
                result = random.nextBoolean();
            }

            case BOOLEAN_CLASSNAME -> {
                if (isNull && random.nextBoolean()) {
                    result = null;
                } else {
                    result = random.nextBoolean();
                }
            }

            case STRING_CLASSNAME -> {
                if (isNull && random.nextBoolean()) {
                    result = null;
                } else {
                    int len = random.nextInt(0, end);

                    StringBuilder resultString = new StringBuilder();

                    for (int i = 0; i < len; i++) {
                        resultString.append(getRandomValue(char.class, false,
                                Character.MIN_VALUE, Character.MAX_VALUE));
                    }
                    result = resultString.toString();
                }
            }

            default -> {
                result = null;
            }
        }

        return result;
    }

    private boolean isBasicType(Class<?> object) {
        return BASIC_DATA_TYPES.contains(object);
    }
}
