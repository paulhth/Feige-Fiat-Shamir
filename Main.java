import src.service.*;

public class Main {
    public static void main(String[] args) {
        TTP ttp = new TTP();
        Prover prover = new Prover();

        // instantiate prover and verifier using the same keys provided by the TTP
        Verifier verifier = new Verifier();
        ttp.generatePublicKey(512);
        ttp.sendPublicKey(prover, verifier);
        prover.generateR();
        prover.generateV();

        verifier.generateE();
        System.out.println("Verifier sends challenge e: " + verifier.getE());

        prover.generateY(verifier.getE(), false);
        verifier.generateX(prover.getR());
        System.out.println("Prover sends X: " + verifier.getX());

        //System.out.println("V: " + prover.getV());
        boolean result = verifier.verifyProof(verifier.getX(), prover.getY(), prover);
        System.out.println("Verification result: " + (result ? "Convinced" : "Not convinced"));
    }
}


/*
 * https://en.wikipedia.org/wiki/Feige%E2%80%93Fiat%E2%80%93Shamir_identification_scheme 
 * https://en.wikipedia.org/wiki/Zero-knowledge_proof
 */
