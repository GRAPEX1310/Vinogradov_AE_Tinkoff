package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class Task1 {

    private Task1() {
    }

    public static String overrideToStringMethod() throws InstantiationException, IllegalAccessException {
        DynamicType.Unloaded unloadedType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.isToString())
                .intercept(FixedValue.value("Hello, ByteBuddy!"))
                .make();

        Class<?> dynamicType = unloadedType.load(unloadedType.getClass()
                        .getClassLoader())
                        .getLoaded();

        return dynamicType.newInstance().toString();
    }
}
