package f1sim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimulatorApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimulationEngine engine = new SimulationEngine();

        System.out.println("==================================================");
        System.out.println("  FORMULA 1 RACE STRATEGY & TELEMETRY SIMULATOR   ");
        System.out.println("==================================================");

        // Predefined Constructors
        Constructor redBull = new Constructor("Red Bull Racing", 84.5, 0.95);
        Constructor ferrari = new Constructor("Scuderia Ferrari", 84.7, 1.02);
        Constructor mercedes = new Constructor("Mercedes-AMG PETRONAS", 84.8, 0.98);

        System.out.println("Select Constructor:");
        System.out.println("1. " + redBull.getTeamName());
        System.out.println("2. " + ferrari.getTeamName());
        System.out.println("3. " + mercedes.getTeamName());
        System.out.print("Enter choice (1-3): ");

        int teamChoice = 0;
        try {
            teamChoice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Defaulting to Red Bull Racing.");
            teamChoice = 1;
        }

        Constructor selectedTeam;
        switch (teamChoice) {
            case 2:
                selectedTeam = ferrari;
                break;
            case 3:
                selectedTeam = mercedes;
                break;
            default:
                selectedTeam = redBull;
                break;
        }

        System.out.print("\nEnter Driver Name: ");
        String driverName = scanner.nextLine().trim();
        if (driverName.isEmpty()) {
            driverName = "Driver 1";
        }

        double aggressiveness = 1.0;
        double tireSaving = 1.0;

        try {
            System.out.print("Enter Driver Aggressiveness rating (0.5 to 1.5, default 1.0): ");
            String aggInput = scanner.nextLine().trim();
            if (!aggInput.isEmpty()) {
                aggressiveness = Double.parseDouble(aggInput);
                if (aggressiveness < 0.5 || aggressiveness > 1.5) {
                    System.out.println("Value out of bounds. Setting aggressiveness to 1.0.");
                    aggressiveness = 1.0;
                }
            }

            System.out.print("Enter Driver Tire Preservation rating (0.5 to 1.5, default 1.0): ");
            String tireInput = scanner.nextLine().trim();
            if (!tireInput.isEmpty()) {
                tireSaving = Double.parseDouble(tireInput);
                if (tireSaving < 0.5 || tireSaving > 1.5) {
                    System.out.println("Value out of bounds. Setting tire preservation to 1.0.");
                    tireSaving = 1.0;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input. Applying default performance ratings.");
            aggressiveness = 1.0;
            tireSaving = 1.0;
        }

        Driver activeDriver = new Driver(driverName, selectedTeam, aggressiveness, tireSaving);

        int totalLaps = 30; // 30-lap sprint race simulation
        System.out.println("\nConfiguring 30-Lap Grand Prix Simulation for " + activeDriver.getName() 
                + " driving for " + activeDriver.getTeam().getTeamName() + "...\n");

        // Strategy A: 1-Stop Strategy (Soft -> Hard, Pit at Lap 10)
        List<TyreCompound> strategyA_Stints = new ArrayList<>();
        strategyA_Stints.add(new SoftTyre());
        strategyA_Stints.add(new HardTyre());
        int[] strategyA_Pits = {10};

        // Strategy B: 2-Stop Strategy (Soft -> Medium -> Soft, Pits at Lap 10 and 20)
        List<TyreCompound> strategyB_Stints = new ArrayList<>();
        strategyB_Stints.add(new SoftTyre());
        strategyB_Stints.add(new MediumTyre());
        strategyB_Stints.add(new SoftTyre());
        int[] strategyB_Pits = {10, 20};

        // Execute Simulations
        double[][] telemetryA = engine.simulateRace(activeDriver, totalLaps, strategyA_Stints, strategyA_Pits);
        double[][] telemetryB = engine.simulateRace(activeDriver, totalLaps, strategyB_Stints, strategyB_Pits);

        // Display Telemetry Tables
        StrategyReport.printTelemetryTable("Strategy A (1-Stop: Soft -> Hard)", telemetryA);
        StrategyReport.printTelemetryTable("Strategy B (2-Stop: Soft -> Medium -> Soft)", telemetryB);

        // Display Comparison Summary
        double finalTimeA = telemetryA[totalLaps - 1][2];
        double finalTimeB = telemetryB[totalLaps - 1][2];
        StrategyReport.compareStrategies("Strategy A (1-Stop)", finalTimeA, "Strategy B (2-Stop)", finalTimeB);

        scanner.close();
    }
}