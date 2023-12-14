package edu.hw10;

import edu.hw10.SupportClasses.MyClass;
import edu.hw10.SupportClasses.MyInterface;
import edu.hw10.Task2.CacheProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    @DisplayName("Check proxy cache work")
    void testProxyCache() {
            MyInterface object = new MyClass(13, "abc", true);
            var proxyObject = (MyInterface) CacheProxy.create(object, object.getClass());

            String result = proxyObject.getStr();
            var cache = CacheProxy.getCache();

            assertThat(result).isEqualTo("abc");
            assertThat(cache).isNotNull();
            assertThat(Arrays.stream(cache.get("null")).toList().get(0).toString()).isEqualTo("abc");
    }
}
