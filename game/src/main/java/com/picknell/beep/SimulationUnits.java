package com.picknell.beep;

/**
 * Conversion between measured physical centimeters and simulation world units.
 *
 * <p>The scale is deliberately centralized so experiments can change it without
 * rewriting physical measurements or body geometry.</p>
 */
public final class SimulationUnits {

    /**
     * Current experimental scale: 1 world unit = 10 cm.
     */
    public static final float WORLD_UNITS_PER_CM = 0.1f;

    private SimulationUnits() {
    }

    public static float cm(float centimeters) {
        return centimeters * WORLD_UNITS_PER_CM;
    }
}
