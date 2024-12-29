package src.service;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Verifier {
    private BigInteger e; // Random challenge (e)
    private BigInteger pbk; // Public key
    private BigInteger x; // Prover's commitment

    public Verifier() {
    }

    public void generateE() {
        System.out.println("Generating random challenge (e)");
        SecureRandom random = new SecureRandom();
        this.e = BigInteger.valueOf(random.nextInt(2)); // e = {0, 1}
    }

    public boolean verifyProof(BigInteger x, BigInteger y, Prover prover) {
        BigInteger left = y.modPow(BigInteger.TWO, pbk); // y^2 mod n
        BigInteger right = x.multiply(prover.getV().modPow(this.e, pbk)).mod(pbk); // x * v^e mod n
        System.out.println(left + "\n" + right);
        return left.equals(right);
    }

    public void setPublicKey(BigInteger pbk) {
        this.pbk = pbk;
    }

    public BigInteger getE() {
        return this.e;
    }

    public void generateX(BigInteger r) {
        System.out.println("Verifier: Generating X");
        this.x = r.modPow(BigInteger.TWO, pbk); // x = r^2 mod n
    }

    public BigInteger getX() {
        return this.x;
    }
}
