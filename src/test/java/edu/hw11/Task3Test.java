package edu.hw11;

import edu.hw11.Task3.FibCalculator;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
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

        FibCalculator fibCalculator = new FibCalculator();
        Object object = new ByteBuddy()
                .subclass(Object.class)
                .name("FibCLass")
                .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC)
                .withParameter(int.class, "number")
                .intercept(MethodDelegation.to(fibCalculator))
                .modifiers(Opcodes.ACC_PUBLIC)
                .make()
                .load(Task3Test.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                .getLoaded()
                .newInstance();

        assertThat(object.getClass().getMethod("fib", int.class).invoke(object, 7)).isNotEqualTo(13L);
    }
}
