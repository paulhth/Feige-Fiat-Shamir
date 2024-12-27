package src.model;

import java.math.BigInteger;

public class Proof {
    private final BigInteger x; // Prover's commitment
    private final BigInteger e; // Verifier's challenge
    private final BigInteger y; // Prover's response

    public Proof(BigInteger x, BigInteger e, BigInteger y) {
        this.x = x;
        this.e = e;
        this.y = y;
    }

    public BigInteger getX() {
        return x;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getY() {
        return y;
    }
}
