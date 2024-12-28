import src.service.*;
import src.model.*;
import src.util.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        TTP ttp = new TTP();
        Keys keys = ttp.generateKeys(512);

        // instantiate prover and verifier using the same keys provided by the TTP
        Prover prover = new Prover(keys);
        Verifier verifier = new Verifier(keys);

        BigInteger x = prover.generateCommitment();
        System.out.println("Prover sends x: " + x);

        BigInteger e = verifier.generateChallenge();
        System.out.println("Verifier sends challenge e: " + e);

        BigInteger y = prover.computeResponse(e);
        System.out.println("Prover sends y: " + y);

        boolean result = verifier.verifyProof(x, y);
        System.out.println("Verification result: " + (result ? "Convinced" : "Not convinced"));
    }
}


/*
 * https://en.wikipedia.org/wiki/Feige%E2%80%93Fiat%E2%80%93Shamir_identification_scheme 
 * https://en.wikipedia.org/wiki/Zero-knowledge_proof
 */
