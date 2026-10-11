package com.picknell.beep;

/**
 * Physical measurements taken from Liam's BEEP.
 *
 * <p>Values in this class stay in centimeters on purpose. Simulation/world-unit
 * scaling is a separate concern and should be chosen by the body implementation,
 * rather than baked into the physical reference data.</p>
 */
public final class BeepMeasurements {

    public static final float HEIGHT_CM = 15.24f;
    public static final float WIDTH_CM = 12.70f;
    public static final float DEPTH_CM = 12.70f;

    public static final float MAX_FOOTPAD_SPAN_CM = 14.0f;
    public static final float MIN_FOOTPAD_SPAN_CM = 8.0f;

    /**
     * Approximate obstacle/step height observed as a useful first simulation target.
     */
    public static final float APPROX_STEP_HEIGHT_CM = 0.5f;

    /**
     * Center-to-center spacing between the two black IR receiver components.
     */
    public static final float IR_RECEIVER_SPACING_CM = 4.0f;

    /**
     * Center-to-center spacing between the two clear IR emitter components.
     */
    public static final float IR_EMITTER_SPACING_CM = 2.0f;

    /**
     * Measured spacing between adjacent black and clear optical components.
     */
    public static final float IR_ADJACENT_COMPONENT_SPACING_CM = 1.0f;

    private BeepMeasurements() {
        // Physical reference constants only.
    }
}
