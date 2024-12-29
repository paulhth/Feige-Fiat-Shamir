package src.service;

import java.math.BigInteger;
import java.security.SecureRandom;

public class TTP { // Trusted Third Party

    private BigInteger pbk; // Public key

    public TTP(){   }

    public void run() {
        System.out.println("TTP: Running...");
    }

    public void generatePublicKey(int bitLength) {
        this.run();

        SecureRandom random = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(bitLength / 2, random); // the product has to have 512 bits so thats why we divide by 2
        BigInteger q = BigInteger.probablePrime(bitLength / 2, random);
        this.pbk = p.multiply(q); // n = p * q
        System.out.println("TTP: Public key generated: " + this.pbk);
    }

    public void sendPublicKey(Prover prover, Verifier verifier) {
        prover.setPublicKey(this.pbk);
        prover.generateSecret();
        verifier.setPublicKey(this.pbk);
        System.out.println("TTP: Public key sent to Prover and Verifier");
    }

}
