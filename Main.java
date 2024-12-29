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
        
        //result = verifier.verifyProof(verifier.getX(), prover.getY(), prover);
        //System.out.println("Verification result: " + (result ? "Convinced" : "Not convinced"));
        boolean result = false;
        int numberOfIterations = 1000,
            iterator = 0;
        for(; iterator<numberOfIterations; iterator++){
            System.out.println("=================ROUND: " + iterator);
            prover.generateR();
            verifier.generateE();
            prover.generateY(verifier.getE(), true); //MODIFY THIS TO FALSE TO MAKE IT ILLEGITIMATE
            verifier.generateX(prover.getR());
            result = verifier.verifyProof(verifier.getX(), prover.getY(), prover);
            System.out.println();
            if(!result) break;
        }

        if(result){
            System.out.println("Verification result: Convinced --- " + numberOfIterations + " iterations");
        } else {
            System.out.println("Verification result: Not convinced --- " + iterator + " iterations");
        }
    }
}


/*
 * https://en.wikipedia.org/wiki/Feige%E2%80%93Fiat%E2%80%93Shamir_identification_scheme 
 * https://en.wikipedia.org/wiki/Zero-knowledge_proof
 */
