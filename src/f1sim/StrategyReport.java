package f1sim;

public class StrategyReport {

    // Formats and prints the 2D telemetry array into a clean table
    public static void printTelemetryTable(String strategyName, double[][] telemetry) {
        System.out.println("==================================================");
        System.out.println("STRATEGY: " + strategyName);
        System.out.println("==================================================");
        System.out.println("Lap\tLap Time (s)\tTotal Race Time (s)");
        System.out.println("--------------------------------------------------");
        
        // Loop through the 2D array rows
        for (int i = 0; i < telemetry.length; i++) {
            System.out.printf("%d\t%.3f\t\t%.3f\n", 
                (int) telemetry[i][0], telemetry[i][1], telemetry[i][2]);
        }
        
        // Extract the final cumulative time from the last row
        double finalTime = telemetry[telemetry.length - 1][2];
        System.out.println("--------------------------------------------------");
        System.out.printf("FINAL RACE TIME: %.3f seconds\n", finalTime);
        System.out.println("==================================================\n");
    }
    
    // Compares the final times of two strategies and calculates the time delta
    public static void compareStrategies(String name1, double time1, String name2, double time2) {
        System.out.println("################ STRATEGY COMPARISON ################");
        System.out.printf("%s Total Time: %.3f s\n", name1, time1);
        System.out.printf("%s Total Time: %.3f s\n", name2, time2);
        
        if (time1 < time2) {
            System.out.printf("WINNER: %s is faster by %.3f seconds.\n", name1, (time2 - time1));
        } else if (time2 < time1) {
            System.out.printf("WINNER: %s is faster by %.3f seconds.\n", name2, (time1 - time2));
        } else {
            System.out.println("Both strategies result in the exact same time.");
        }
        System.out.println("#####################################################");
    }
}