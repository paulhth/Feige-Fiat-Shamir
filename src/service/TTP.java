package src.service;

import java.math.BigInteger;
import java.security.SecureRandom;

import src.model.Keys;

public class TTP { // Trusted Third Party
    public TTP(){   }

    public void run() {
        System.out.println("TTP is running...");
    }

    public Keys generateKeys(int bitLength) {
        this.run();

        SecureRandom random = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(bitLength / 2, random); // the product has to have 512 bits so thats why we divide by 2
        BigInteger q = BigInteger.probablePrime(bitLength / 2, random);
        BigInteger n = p.multiply(q); // n = p * q
        BigInteger s = new BigInteger(bitLength / 2, random); // s is a random number with 256 bits
        BigInteger v = s.modPow(BigInteger.TWO, n); // v = s^2 mod n
        return new Keys(n, s, v);
    }
}
