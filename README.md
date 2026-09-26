# Liam's BEEP

Turning my son's Elenco Teach Tech Zivko robot kit, named **BEEP**, into a better robot friend.

Liam built BEEP from the kit and named him after the sound he makes when he detects an obstacle or searches for something to follow. This project is an experiment in giving that existing little robot a richer simulated life first, then carrying the same ideas back into the physical machine.

## Direction

The project is intentionally small and experimental:

- build a simple 3D simulation of BEEP with jMonkeyEngine and JBullet
- represent his real body around the two physical actuators: **drive** and **turn**
- model his two infrared obstacle/follow sensors
- preserve his expressive eyes and beeping as part of his identity
- explore richer "Beepise" output using timing, repetition, pitch, and volume where hardware allows
- eventually replace the original controller with a Raspberry Pi Pico
- keep behavior logic separable from the simulated or physical body so the same controller can inhabit either one

ByteBrain may eventually become one possible controller, but BEEP does not depend on it.

## Project layout

```text
app/       Android launcher
desktop/   desktop launcher
game/      shared BEEP simulation
assets/    shared jMonkeyEngine assets
```

The `game` module contains the platform-independent simulation. The Android and desktop modules are deliberately thin launchers around it.

## Current state

This is an early prototype. The repository currently contains the jMonkeyEngine/JBullet simulation scaffold, Android launcher, desktop launcher, shared assets directory, and Gradle build.

The next useful milestones are deliberately concrete: stabilize the articulated body, add the two simulated IR sensors, then reproduce BEEP's existing explore/follow behavior before adding learning.

## Running

Desktop:

```bash
./gradlew :desktop:run
```

Android debug APK:

```bash
./gradlew :app:assembleDebug
```

The desktop build requires JDK 17. Android builds also require an Android SDK compatible with the configured compile SDK.

## Why BEEP?

Because the goal is not to build a generic robotics framework.

The goal is to help Liam's little yellow robot become a better robot friend.
