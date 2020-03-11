package hu.headortail.conroller;

import hu.headortail.domain.service.DataFileHandler;
import hu.headortail.domain.service.Simulate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HeadOrTailService {

    private final Simulate simulate;
    private final DataFileHandler dataFileHandler;

    public HeadOrTailService(Simulate simulate, DataFileHandler dataFileHandler) {
        this.simulate = simulate;
        this.dataFileHandler = dataFileHandler;
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
        String match = result.equalsIgnoreCase(bet) ? "Ön eltalálta" :  "Ön nem találta el";
        return String.format("A tipp %s volt, a dobás eredménye %s volt%n%s!", bet, result, match);
    }

    /**
     * 3. feladat
     */
    public String getCount() {
        return String.format("A kísérlet %d dobásból állt.", dataFileHandler.getCount());
    }

    /**
     * 4. feladat
     */
    public String getHeadPercent() {
        return String.format("A kísérlet során a fej relatív gyakoriságe %5.2f%% volt.", dataFileHandler.getHeadPercent());
    }

    /**
     * 5. feladat
     */
    public String getDoubleHeadCount() {
        return String.format("A kísérlet során %d alkalommal dobtak pontosan két fejet egymás után.", dataFileHandler.getDoubleHeadCount());
    }

    /**
     * 6. feladat
     */
    public String getLongestHeadSequenceDetails() {
        return String.format("A leghosszabb tisztafej sorozat %d tagból áll, kezdete a(z) %d. dobás.", getLongestHeadCount(), getLongestHeadPosition());
    }

    private int getLongestHeadCount() {
        return dataFileHandler.getLongestHeadCount();
    }

    private int getLongestHeadPosition() {
        return dataFileHandler.getLongestHeadPosition();
    }

    /**
     * 7. feladat
     */

    public List<String> simulation() {
        List<String> simulations = getSimulations();
        long headCount = simulations.stream().filter(i -> "FFFF".equals(i)).count();
        long tailCount = simulations.stream().filter(i -> "FFFI".equals(i)).count();
        String firstItem = String.format("FFFF: %d, FFFI: %d", headCount, tailCount);
        String secondItem = simulations.stream().collect(Collectors.joining(" "));
        return List.of(firstItem, secondItem);
    }

    private List<String> getSimulations() {
        return IntStream.range(0, 1000)
                .mapToObj(i -> simulate.getSequence())
                .collect(Collectors.toList());
    }
}
