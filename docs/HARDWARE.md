# BEEP Hardware Reference

This document records measurements and observations from Liam's physical BEEP robots.
Confirmed measurements are kept separate from hypotheses so the simulator and future
controller replacement do not accidentally turn an inference into a hardware contract.

## Physical dimensions

- Overall height: approximately 15.24 cm
- Overall width: approximately 12.7 cm
- Overall depth: approximately 12.7 cm
- Maximum distance between opposite foot pads: approximately 14 cm
- Minimum distance between opposite foot pads: approximately 8 cm
- Useful first-pass step-height target for the simplified simulator: approximately 0.5 cm

The Java source of truth for measured dimensions is
`game/src/main/java/com/picknell/beep/BeepMeasurements.java`.

## Locomotion

BEEP has two DC motors, not six independent leg actuators.

The six legs are mechanically coupled. One motor participates in the walking mechanism
and the other in the turning mechanism. The detailed linkage is still being reverse
engineered and should not be replaced in documentation by six independent virtual motors.

Two simulation representations are intentionally useful:

1. a simplified behavioral body for navigation and controller work;
2. a detailed mechanical model of the coupled six-leg linkage.

The simplified body should not prevent later mechanical validation.

## Original optical sensors

The original PCB integrates its own optical components rather than using external
off-the-shelf obstacle modules.

Measured spacing across the optical components:

- black-to-black: approximately 4 cm
- clear-to-clear: approximately 2 cm
- adjacent black-to-clear: approximately 1 cm

Visible PCB designators suggest:

- Q2 / Q3: likely IR receivers / phototransistors
- D3 / D4: likely IR emitter LEDs

That interpretation is plausible from the designators and physical appearance, but is
not yet treated as electrically confirmed. Signal conditioning, analog-vs-threshold
behavior, and emitter modulation still need to be measured.

## Other original PCB observations

Board marking seen on the front:

- 21-893
- 180601
- Ver2.0

Observed components/connectors:

- M1 and M2: two-pin motor connectors
- SW1: tactile switch
- D1 and D2: visible eye LEDs
- BZ1: sounder / likely piezo buzzer
- P1: three-pin connector; exact pinout still to be traced
- U1: main controller candidate; exact part not identified
- U2: driver-stage candidate; exact function not yet confirmed

The original battery holder uses four AAA cells, nominally about 6 V. Exact loaded
voltages, motor running current, and motor stall current should be measured before
selecting a replacement H-bridge or connecting a Raspberry Pi Pico.

## BEEP I and BEEP II

**BEEP I** is the original robot Liam and Mark built together. Mark customized it in an
iridescent green-blue finish with black secondary pieces, metallic chrome accents, and
a durable clear coat. Liam ran it frequently for roughly a year.

Several small C-shaped leg-joint/piston connectors eventually broke. Temporary
replacements made from twisted 12-gauge wire eyelets kept the robot operating, but by
the third replacement the mechanism had enough asymmetry that BEEP required frequent
course correction while following a straight-moving hand.

BEEP I was still electrically operational when it was disassembled for investigation.

**BEEP II** is the newer working robot and current stock-behavior reference.

Both robots are useful: BEEP II provides a cleaner behavioral baseline, while BEEP I
provides a historical, repair, geometry, and failure-mode reference.

## Measurements still worth capturing

- straight-line speed
- gait period / steps per second
- turn rate and turn radius
- sensor trigger distance versus target color and angle
- eye LED and buzzer behavior
- motor voltage/PWM in Explore and Follow modes
- motor running and stall current
- exact C-joint dimensions and pivot centers
- detailed linkage pivot-to-pivot measurements
- P1 pinout
- U1 and U2 markings/functions
- whether D3/D4 are continuously driven or modulated
