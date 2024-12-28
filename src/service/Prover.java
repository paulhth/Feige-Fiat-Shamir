package src.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import src.model.Keys;

public class Prover {
    private final Keys keys;
    private BigInteger r; // Random commitment

    public Prover(Keys keys) {
        this.keys = keys;
    }

    public BigInteger generateCommitment() {
        SecureRandom random = new SecureRandom();
        this.r = new BigInteger(keys.getN().bitLength(), random).mod(keys.getN()); // generates r
        return r.modPow(BigInteger.TWO, keys.getN()); // and computes commitment x = r^2 mod n
    }

    public BigInteger computeResponse(BigInteger challenge) {
        return r.multiply(keys.getS().modPow(challenge, keys.getN())).mod(keys.getN()); // y = r * s^e mod n
    }
}
