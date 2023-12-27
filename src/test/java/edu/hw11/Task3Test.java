package edu.hw11;

import edu.hw11.Task3.FibAppender;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.Implementation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    @DisplayName("Code generator test")
    void testCodeGenerator()
            throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        FibAppender fibAppender = new FibAppender();
        Object object =
                new ByteBuddy()
                        .subclass(Object.class)
                        .name("FibClass")
                        .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
                        .withParameter(int.class, "n")
                        .intercept(new Implementation.Simple(fibAppender))
                        .modifiers(Opcodes.ACC_PUBLIC)
                        .make()
                        .load(Task3Test.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                        .getLoaded()
                        .newInstance();

        assertThat(object.getClass().getMethod("fib", int.class).invoke(object, 7)).isEqualTo(13L);
    }
}