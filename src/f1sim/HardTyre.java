package f1sim;

public class HardTyre extends TyreCompound {
    public HardTyre() {
        // Low grip (0.0s), Low degradation (+0.03s per lap), Long life (40 laps)
        super("Hard (C1)", 0.0, 0.03, 40);
    }
}