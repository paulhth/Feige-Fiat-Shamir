package src.service;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Prover {
    private BigInteger s; // Prover's secret key
    private BigInteger r; // Random commitment
    private BigInteger pbk; // Public key
    private BigInteger y; // Prover's response
    private BigInteger v; // Verifier's public key

    public Prover(){
        
    }

    public BigInteger computeResponse(BigInteger challenge) {
        return null; // y = r * s^e mod n
    }

    public void generateSecret(){
        //SecureRandom random = new SecureRandom();
        System.out.println("Prover: Generating secret");
        this.s = new BigInteger("69"); // generates s
    }

    public void setPublicKey(BigInteger pbk) {
        this.pbk = pbk;
    }

    public BigInteger getY() {
        return y;
    }

    public BigInteger getV() {
        return this.v;
    }

    public void generateY(BigInteger e, boolean LEGITIMATE) {
        System.out.println("Prover: Generating Y");
        //this.y = r.multiply(s.modPow(e, pbk)).mod(pbk); // y = r * s^e mod n -> THIS IS LEGITIMATE BECAUSE IT KNOWS THE SECRET
        //this.y = r.multiply(BigInteger.ONE).mod(pbk); // THIS IS ILLEGITIMATE BECAUSE IT DOESN'T KNOW THE SECRET, IT USES 1 AS A PLACEHOLDER FOR S (TRIES TO FABRICATE A SECRET)
        this.y = LEGITIMATE ? r.multiply(s.modPow(e, pbk)).mod(pbk) : r.multiply(BigInteger.ONE).mod(pbk);
    }

    public void generateV() {
        System.out.println("Prover: Generating V");
        this.v = s.modPow(BigInteger.TWO,pbk); // v = s^2 mod pbk
    }

    public void generateR() {
        System.out.println("Prover: Generating R");
        SecureRandom random = new SecureRandom();
        this.r = new BigInteger(512, random); // r = random number
    }

    public BigInteger getR() {
        return this.r;
    }
}
