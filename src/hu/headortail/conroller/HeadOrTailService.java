package hu.headortail.conroller;

import hu.headortail.domain.service.Simulate;

public class HeadOrTailService {

    private final Simulate simulate;

    public HeadOrTailService(Simulate simulate) {
        this.simulate = simulate;
    }

    /**
     * 1. feladat
     */

    public String getSimulationResult() {
        return simulate.getResult();
    }

    /**
     * 2. feladat
     */
    public String getBetResult(String bet) {
        String result = getSimulationResult();
        String match = result.equals(bet) ? "Ön eltalálta" :  "Ön nem találta el";
        return String.format("A tipp %s volt, a dobás eredménye %s volt%n%s!", bet, result, match);
    }
}
