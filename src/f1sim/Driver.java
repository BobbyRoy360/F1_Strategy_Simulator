package f1sim;

public class Driver {
    private String name;
    private Constructor team;
    private double aggressiveness; // Pace multiplier (higher means faster laps but worse wear)
    private double tireSavingSkill; // Skill multiplier (higher means less tire degradation)

    // Constructor method
    public Driver(String name, Constructor team, double aggressiveness, double tireSavingSkill) {
        this.name = name;
        this.team = team;
        this.aggressiveness = aggressiveness;
        this.tireSavingSkill = tireSavingSkill;
    }

    // Getters
    public String getName() { return name; }
    public Constructor getTeam() { return team; }
    public double getAggressiveness() { return aggressiveness; }
    public double getTireSavingSkill() { return tireSavingSkill; }
}