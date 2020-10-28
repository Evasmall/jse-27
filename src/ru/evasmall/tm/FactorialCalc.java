package ru.evasmall.tm;

import java.math.BigInteger;

public class FactorialCalc implements IFactorial {

    @Override
    public BigInteger getFactorial(Integer n) {
        BigInteger result = BigInteger.valueOf(1);
        for (long i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

}
