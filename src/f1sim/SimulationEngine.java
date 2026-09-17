package f1sim;

import java.util.List;

public class SimulationEngine {
    private static final double FUEL_EFFECT_PER_LAP = -0.06; // Car gets ~0.06s faster per lap as fuel burns
    private static final double PIT_STOP_TIME_LOSS = 22.0;   // Time lost driving through the pit lane in seconds

    /**
     * Simulates a full race distance across multiple stints.
     * 
     * @param driver The driver object containing skill and team attributes
     * @param totalLaps Total number of laps in the Grand Prix
     * @param stints A list of TyreCompound objects representing each stint in order
     * @param pitLaps An array containing the lap numbers where pit stops occur
     * @return A 2D array [lap_number][telemetry_data]: 
     *         Column 0: Lap Number
     *         Column 1: Lap Time (seconds)
     *         Column 2: Cumulative Race Time (seconds)
     */
    public double[][] simulateRace(Driver driver, int totalLaps, List<TyreCompound> stints, int[] pitLaps) {
        // Row count = totalLaps, Columns = 3 (Lap #, Lap Time, Cumulative Time)
        double[][] telemetry = new double[totalLaps][3];

        double cumulativeTime = 0.0;
        int currentStintIndex = 0;
        int lapInCurrentStint = 1;
        TyreCompound currentTyre = stints.get(currentStintIndex);

        for (int lap = 1; lap <= totalLaps; lap++) {
            // Check if this lap requires a pit stop
            boolean isPitLap = false;
            if (currentStintIndex < pitLaps.length && lap == pitLaps[currentStintIndex]) {
                isPitLap = true;
            }

            // 1. Base team pace
            double lapTime = driver.getTeam().getBaseLapTime();

            // 2. Driver aggressiveness effect (faster base pace, but higher tire wear)
            lapTime -= (driver.getAggressiveness() * 0.4);

            // 3. Fuel burn-off effect (car becomes lighter each lap)
            lapTime += (lap * FUEL_EFFECT_PER_LAP);

            // 4. Tire compound base pace and wear modified by driver skill and car chassis
            double tyrePaceMod = currentTyre.calculatePaceModifier(lapInCurrentStint);
            double wearMultiplier = driver.getTeam().getChassisDegradationFactor() / driver.getTireSavingSkill();
            lapTime += (tyrePaceMod * wearMultiplier);

            // 5. Add pit lane time loss if entering the pits on this lap
            if (isPitLap) {
                lapTime += PIT_STOP_TIME_LOSS;
            }

            cumulativeTime += lapTime;

            // Store telemetry row
            telemetry[lap - 1][0] = lap;
            telemetry[lap - 1][1] = Math.round(lapTime * 1000.0) / 1000.0;
            telemetry[lap - 1][2] = Math.round(cumulativeTime * 1000.0) / 1000.0;

            // Transition to new stint if a pit stop occurred
            if (isPitLap && (currentStintIndex + 1) < stints.size()) {
                currentStintIndex++;
                currentTyre = stints.get(currentStintIndex);
                lapInCurrentStint = 1;
            } else {
                lapInCurrentStint++;
            }
        }

        return telemetry;
    }

    public double getPitStopTimeLoss() {
        return PIT_STOP_TIME_LOSS;
    }
}