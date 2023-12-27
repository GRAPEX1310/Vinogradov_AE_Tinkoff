package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    @DisplayName("ByteBuddy override toString test")
    void testToStringOverride() throws InstantiationException, IllegalAccessException {

        DynamicType.Unloaded unloadedType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.isToString())
                .intercept(FixedValue.value("Hello, ByteBuddy!"))
                .make();

        Class<?> dynamicType = unloadedType.load(unloadedType.getClass()
                        .getClassLoader())
                .getLoaded();

        assertThat(dynamicType.newInstance().toString()).isEqualTo("Hello, ByteBuddy!");
    }
}
