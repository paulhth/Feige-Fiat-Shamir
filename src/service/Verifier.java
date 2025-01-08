package src.service;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Verifier {
    private BigInteger e; // Random challenge (e)
    private BigInteger pbk; // Public key
    private BigInteger v; // Prover's commitment

    public Verifier() {
    }

    public void generateE() {
        System.out.println("Verifier: Generating random challenge (e)");
        SecureRandom random = new SecureRandom();
        this.e = BigInteger.valueOf(random.nextInt(2)); // e = {0, 1}
    }

    public boolean verifyProof(BigInteger x, BigInteger y, Prover prover) {
        // left = y^2 mod n
        BigInteger left = y.modPow(BigInteger.TWO, pbk);
    
        // right = x * v^e * sign mod n
        BigInteger right = x.multiply(v.modPow(this.e, pbk)).multiply(prover.getSign()).mod(pbk);
    
        System.out.println("Verifier: Left = " + left);
        System.out.println("Verifier: Right = " + right);
    
        // Ensure x is non-zero
        if (x.signum() == 0) {
            System.out.println("x is zero, proof verification failed.");
            return false;
        }
        return left.equals(right);
    }

    public void assignV(BigInteger v) {
        this.v = v;
    }

    public void setPublicKey(BigInteger pbk) {
        this.pbk = pbk;
    }

    public BigInteger getE() {
        return this.e;
    }

}
