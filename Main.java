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
        double medie = 0;
        int success = 0;
        int max = 0, maxoverall = 0;
        int iteration = 0;
        for(int t=0;t<100;t++){
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
            max++;
            if(max > maxoverall) {maxoverall = max; iteration = iterator;}
            if(!result) break;
        }
            
        max = 0;
        if(result){
            System.out.println("Verification result: Convinced --- " + numberOfIterations + " iterations");
            success ++ ;
        } else {
            System.out.println("Verification result: Not convinced --- " + iterator + " iterations");
        }
        }
        medie = (double)success/100;
        System.out.println("=================\n======STATS======\n=================\n" + //
                        "Success rate: " + medie + "\n" + "Iterations: " + 100 + "\nRounds per iteration: " + 1000 + "\nMax round reached while illegitimate: " + maxoverall + "\nChance of this happening: " + (double)maxoverall/1000 + "\n" + "Happened on iteration: " + iteration + "/1000");
    }
}


/*
 * https://en.wikipedia.org/wiki/Feige%E2%80%93Fiat%E2%80%93Shamir_identification_scheme 
 * https://en.wikipedia.org/wiki/Zero-knowledge_proof
 */
