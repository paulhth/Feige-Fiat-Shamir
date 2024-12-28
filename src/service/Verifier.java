package src.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import src.model.Keys;

public class Verifier {
    private final Keys keys;
    private BigInteger challenge; // Random challenge (e)

    public Verifier(Keys keys) {
        this.keys = keys;
    }

    public BigInteger generateChallenge() {
        SecureRandom random = new SecureRandom();
        this.challenge = BigInteger.valueOf(random.nextInt(2)); // e = {0, 1}
        return challenge;
    }

    public boolean verifyProof(BigInteger x, BigInteger y) {
        BigInteger left = y.modPow(BigInteger.TWO, keys.getN()); // y^2 mod n
        BigInteger right = x.multiply(keys.getV().modPow(challenge, keys.getN())).mod(keys.getN()); // x * v^e mod n
        return left.equals(right);
    }
}
