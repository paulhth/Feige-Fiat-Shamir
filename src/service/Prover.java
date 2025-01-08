package src.service;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Prover {
    private BigInteger s; // Prover's secret key
    private BigInteger r; // Random commitment
    private BigInteger pbk; // Public key
    private BigInteger y; // Prover's response
    private BigInteger v; // Verifier's public key
    private BigInteger x; // Prover's commitment
    private int sign; // sign

    public Prover(){
        
    }
    
    public void setPublicKey(BigInteger pbk) {
        this.pbk = pbk;
    }

    public BigInteger getY() {
        return y;
    }

    public void generateY(BigInteger e, boolean legitimate) {
        if (legitimate) {
            // y = r * sign * s^e mod n
            this.y = r.multiply(getSign()).multiply(s.modPow(e, pbk)).mod(pbk);
        } else {
            // For invalid proof
            this.y = r.multiply(BigInteger.ONE).mod(pbk);
        }
        System.out.println("Prover: Y = " + this.y);
    }

    public void assignS(BigInteger s) {
        this.s = s;
    }

    public void generateR() {
        System.out.println("Prover: Generating R");
        SecureRandom random = new SecureRandom();
        this.r = new BigInteger(512, random); // r = random number
    }

    public void generateSign() {
        SecureRandom random = new SecureRandom();
        this.sign = random.nextInt(2) == 0 ? -1 : 1; // sign = {-1, 1}
    }

    public void generateX(BigInteger r) {
        // x = r^2 * sign mod n
        this.x = r.modPow(BigInteger.TWO, pbk).multiply(this.getSign()).mod(pbk);
        System.out.println("Prover: X = " + this.x);
    }

    public BigInteger getSign() {
        return this.sign == 1 ? BigInteger.ONE : BigInteger.valueOf(-1);
    }

    public BigInteger getR() {
        return this.r;
    }

    public BigInteger getX() {
        return this.x;
    }
}
