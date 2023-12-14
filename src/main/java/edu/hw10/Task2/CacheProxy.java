package edu.hw10.Task2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {

    private Object object;
    private static Map<String, Object[]> cache;

    CacheProxy(Object newObject) {
        object = newObject;
        cache = new HashMap<>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            for (var key : cache.keySet()) {
                if (key.equals(Arrays.toString(args))) {
                    return cache.get(key)[0];
                }
            }

            var result = method.invoke(this.object, args);
            cache.putIfAbsent(Arrays.toString(args), new Object[] {result, method.getName()});
            if (method.getAnnotation(Cache.class).persist()) {
                File file = File.createTempFile("temp", ".txt", Path.of("src/main/resources").toFile());
                file.deleteOnExit();
                try (BufferedWriter bufferedWriter =
                             new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
                    for (var key : cache.keySet()) {
                        bufferedWriter.write(key + " - " + Arrays.toString(cache.get(key)) + "\n");
                    }
                }
            }

            return result;
        } else {
            return method.invoke(object, args);
        }
    }

    public static Object create(Object object, Class<?> name) {
        ClassLoader loader = name.getClassLoader();
        Class[] interfaces = name.getInterfaces();
        return Proxy.newProxyInstance(loader, interfaces, new edu.hw10.Task2.CacheProxy(object));
    }

    public static Map<String, Object[]> getCache() {
        return cache;
    }
}
