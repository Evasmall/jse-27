package ru.evasmall.tm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

public class FactorialHandler implements InvocationHandler {

    private final FactorialCalc factorialCalc;

    private final Map<Integer, BigInteger> cache;

    private static final int CACHESIZE = 10;

    public FactorialHandler (FactorialCalc factorialCalc) {
        this.factorialCalc = factorialCalc;
        this.cache = new LinkedHashMap<>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, BigInteger> eldest) {
                return size() > CACHESIZE;
            }
        };
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("getFactorial")) {
            Integer number = (Integer)args[0];
            if (cache.containsKey(number)) {
                System.out.print("Факториал числа из кэша " + number + "! = " );
                return cache.get(args[0]);
            }
            System.out.print("Факториал числа расчетный: " + number + "! = ");
            BigInteger valueFactorial = (BigInteger)method.invoke(factorialCalc, args);
            cache.put(number, valueFactorial);
            return valueFactorial;
        }
        return method.invoke(factorialCalc, args);
    }

}
