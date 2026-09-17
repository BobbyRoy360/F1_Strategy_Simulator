package f1sim;

public class Constructor {
    private String teamName;
    private double baseLapTime; // Baseline lap time in seconds (e.g., 85.0 for 1:25.000)
    private double chassisDegradationFactor; // How hard the car is on tires (lower is better)

    // Constructor method
    public Constructor(String teamName, double baseLapTime, double chassisDegradationFactor) {
        this.teamName = teamName;
        this.baseLapTime = baseLapTime;
        this.chassisDegradationFactor = chassisDegradationFactor;
    }

    // Getters
    public String getTeamName() { return teamName; }
    public double getBaseLapTime() { return baseLapTime; }
    public double getChassisDegradationFactor() { return chassisDegradationFactor; }
}