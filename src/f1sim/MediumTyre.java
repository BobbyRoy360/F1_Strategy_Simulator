package f1sim;

public class MediumTyre extends TyreCompound {
    public MediumTyre() {
        // Balanced grip (-0.8s), Balanced degradation (+0.07s per lap), Medium life (25 laps)
        super("Medium (C3)", -0.8, 0.07, 25);
    }
}