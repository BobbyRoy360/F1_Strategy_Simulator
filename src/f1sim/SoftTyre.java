package f1sim;

public class SoftTyre extends TyreCompound {
    public SoftTyre() {
        // High grip (-1.5s), High degradation (+0.12s per lap), Short life (15 laps)
        super("Soft (C5)", -1.5, 0.12, 15);
    }
}