package com.picknell.beep;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;


/**
 * Shared beep simulation application.
 *
 * Platform-specific launchers live in the desktop and Android modules.
 */
public class Beep extends SimpleApplication {
    
    @Override
    public void simpleInitApp() {
        flyCam.setEnabled(false);
        
        // Clean this up 
        cam.setLocation(new Vector3f(0.0f, 15.0f, 45.0f));
        cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
        
        BulletAppState bulletAppState = new BulletAppState();
        //bulletAppState.setDebugEnabled(true);
        //bulletAppState.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
        //bulletAppState.setBroadphaseType(PhysicsSpace.BroadphaseType.AXIS_SWEEP_3_32);
        stateManager.attach(bulletAppState);
        
        //Delete stuff before JayMe sees it!
        
        PhysicsSpace physicsSpace = bulletAppState.getPhysicsSpace();
        
        Node floor = createFloor();
        floor.setLocalTranslation(0.0f, -5.0f, 0.0f);
        rootNode.attachChild(floor);
        physicsSpace.addAll(floor);
        
        Node beep = createZivko();
        beep.setLocalTranslation(0.0f, 100.0f, 0.0f);
        rootNode.attachChild(beep);
        physicsSpace.addAll(beep);
    }
    
    private Node createFloor() {
        String name= "Floor";
        Vector3f minimum = new Vector3f(-15.0f, -5.0f, -15.0f);
        Vector3f maximum = new Vector3f(15.0f, 5.0f, 15.0f);
        ColorRGBA color = new ColorRGBA(0.9f, 0.9f, 0.9f, 1.0f);
        return createPhysicsBox(name, minimum, maximum, color);
    }
    
    private Node createZivko() {
        String name= "Zivko";
        Vector3f minimum = new Vector3f(-0.20f, -0.15f, -0.10f);
        Vector3f maximum = new Vector3f(0.20f, 0.15f, 0.10f);
        ColorRGBA color = new ColorRGBA(1.0f, 0.8f, 0.0f, 1.0f);
        float mass = 1.0f;
        
        return createPhysicsBox(name, minimum, maximum, color, mass);
    }
    
    private Node createPhysicsBox(String name, Vector3f minimum, Vector3f maximum, ColorRGBA color) {
        return createPhysicsBox(name, minimum, maximum, color, 0);
    }
    
    private Node createPhysicsBox(String name, Vector3f minimum, Vector3f maximum, ColorRGBA color, float mass) {
        Vector3f halfExtents = new Vector3f(maximum).subtractLocal(minimum);
        
        Box box = new Box(halfExtents.negate(), halfExtents);
        Geometry geometry = new Geometry(name + ".Geometry", box);
        
        BoxCollisionShape shape = new BoxCollisionShape(halfExtents);
        RigidBodyControl control = new RigidBodyControl(shape, mass);
        control.setKinematic(mass == 0.0f);
        
        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", color);
        
        Node node = new Node(name);
        node.attachChild(geometry);
        node.addControl(control);
        node.setMaterial(material);
        return node;
    }
}
