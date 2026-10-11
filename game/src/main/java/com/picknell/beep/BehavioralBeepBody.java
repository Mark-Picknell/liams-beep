package com.picknell.beep;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 * Deliberately simplified behavioral body for BEEP.
 *
 * <p>This is not the detailed six-leg mechanism. It gives controllers a stable
 * drive/turn body while the mechanical twin is reconstructed independently.</p>
 */
public final class BehavioralBeepBody {

    private static final float RADIUS = SimulationUnits.cm(BeepMeasurements.MAX_FOOTPAD_SPAN_CM * 0.5f);
    private static final float TOTAL_HEIGHT = SimulationUnits.cm(BeepMeasurements.HEIGHT_CM);
    private static final float CYLINDER_HEIGHT = Math.max(0.01f, TOTAL_HEIGHT - (2.0f * RADIUS));
    private static final float STEP_HEIGHT = SimulationUnits.cm(BeepMeasurements.APPROX_STEP_HEIGHT_CM);

    // Temporary calibration values until measured from physical BEEP.
    private static final float DRIVE_SPEED = SimulationUnits.cm(10.0f);
    private static final float TURN_RATE = FastMath.HALF_PI;

    private final Node node = new Node("BEEP");
    private final CharacterControl character;

    private float throttle;
    private float steering;
    private float heading;

    public BehavioralBeepBody(AssetManager assetManager, PhysicsSpace physicsSpace) {
        CapsuleCollisionShape shape = new CapsuleCollisionShape(RADIUS, CYLINDER_HEIGHT);
        character = new CharacterControl(shape, STEP_HEIGHT);
        node.addControl(character);

        float halfWidth = SimulationUnits.cm(BeepMeasurements.WIDTH_CM) * 0.5f;
        float halfHeight = TOTAL_HEIGHT * 0.5f;
        float halfDepth = SimulationUnits.cm(BeepMeasurements.DEPTH_CM) * 0.5f;
        Geometry geometry = new Geometry("BEEP.Geometry", new Box(halfWidth, halfHeight, halfDepth));

        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", new ColorRGBA(1.0f, 0.8f, 0.0f, 1.0f));
        geometry.setMaterial(material);
        node.attachChild(geometry);

        physicsSpace.add(character);
    }

    public Node node() {
        return node;
    }

    public void warp(Vector3f position) {
        character.warp(position);
    }

    public void drive(float amount) {
        throttle = clamp(amount);
    }

    public void turn(float amount) {
        steering = clamp(amount);
    }

    public void stop() {
        throttle = 0.0f;
        steering = 0.0f;
        character.setWalkDirection(Vector3f.ZERO);
    }

    /**
     * Applies one frame of simplified drive/turn behavior.
     *
     * <p>CharacterControl consumes a per-update walk displacement, so the
     * calibrated speed is multiplied by tpf here.</p>
     */
    public void update(float tpf) {
        heading += steering * TURN_RATE * tpf;

        Quaternion rotation = new Quaternion().fromAngleAxis(heading, Vector3f.UNIT_Y);
        node.setLocalRotation(rotation);

        Vector3f forward = new Vector3f(FastMath.sin(heading), 0.0f, FastMath.cos(heading));
        character.setWalkDirection(forward.mult(DRIVE_SPEED * throttle * tpf));
    }

    private static float clamp(float value) {
        return FastMath.clamp(value, -1.0f, 1.0f);
    }
}
