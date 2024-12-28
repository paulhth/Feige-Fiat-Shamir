package src.util;

import java.math.BigInteger;

public class BigIntUtils {
    public static BigInteger modPow(BigInteger base, BigInteger exp, BigInteger mod) {
        return base.modPow(exp, mod);
    }
}
