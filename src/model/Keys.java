package src.model;

import java.math.BigInteger;

public class Keys {
    private final BigInteger n;  // Public modulus
    private final BigInteger s;  // Private secret (prover)
    private final BigInteger v;  // Public key (verifier uses this)

    public Keys(BigInteger n, BigInteger s, BigInteger v) {
        this.n = n;
        this.s = s;
        this.v = v;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getS() {
        return s;
    }

    public BigInteger getV() {
        return v;
    }
}
