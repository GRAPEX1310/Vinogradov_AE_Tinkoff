package edu.hw10.Task2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {

    private Object object;
    private static Map<String, Object> cache;
    private final File file;

    CacheProxy(Object newObject, File tempFilepath) {
        object = newObject;
        cache = new HashMap<>();
        try {
            file = File.createTempFile("temp", ".txt", tempFilepath);
            file.deleteOnExit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            for (var key : cache.keySet()) {
                if (key.equals(method.getName() + Arrays.toString(args))) {
                    return cache.get(key);
                }
            }

            var result = method.invoke(this.object, args);
            cache.putIfAbsent(method.getName() + Arrays.toString(args), result);
            if (method.getAnnotation(Cache.class).persist()) {
                try (BufferedWriter bufferedWriter =
                             new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
                    for (var key : cache.keySet()) {
                        bufferedWriter.write(key + " - " + cache.get(key).toString() + "\n");
                    }
                }
            }

            return result;
        } else {
            return method.invoke(object, args);
        }
    }

    public static Object create(Object object, Class<?> name, File filepath) {
        ClassLoader loader = name.getClassLoader();
        Class[] interfaces = name.getInterfaces();
        return Proxy.newProxyInstance(loader, interfaces,
                new CacheProxy(object, filepath));
    }

    public static Map<String, Object> getCache() {
        return cache;
    }
}
