package f1sim;

public abstract class TyreCompound {
    protected String name;
    protected double baseGripAdvantage; // Time subtracted from a lap (negative is faster)
    protected double degradationRate; // Time added per lap due to wear (positive is slower)
    protected int maxLifeSpan; // Maximum laps before the tire is completely dead

    public TyreCompound(String name, double baseGripAdvantage, double degradationRate, int maxLifeSpan) {
        this.name = name;
        this.baseGripAdvantage = baseGripAdvantage;
        this.degradationRate = degradationRate;
        this.maxLifeSpan = maxLifeSpan;
    }

    // Mathematical formula to calculate how much time this tire adds/subtracts on a specific stint lap
    public double calculatePaceModifier(int lapOfStint) {
        // Base grip minus the degradation over time
        return baseGripAdvantage + (degradationRate * lapOfStint);
    }

    public String getName() { return name; }
    public int getMaxLifeSpan() { return maxLifeSpan; }
}