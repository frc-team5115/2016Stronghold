package org.usfirst.frc.team5115.robot;

import org.usfirst.frc.team5115.robot.commands.GimbalReset;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	 Joystick joy1;	// both joysticks
	 Joystick joy2;
	 Joystick joy3;
	 Button gimbalReset;
	    
    public OI() {
    	//mapping
    	joy1 = new Joystick(0);		// The first task the OI performs is birthing its symbiotic partner, the vaguely phallic joystick object.
    	joy2 = new Joystick(1);
    	joy3 = new Joystick(2);
    	
    	gimbalReset = new JoystickButton(joy2, 2);
    	
    	gimbalReset.whenPressed(new GimbalReset());
    	// After the buttons have fully matured, they are paired with a newly formed Command object, which will be their life companion.
    	
    }
    
    // Below the constructor lie the getter functions, whose only purpose is to pass on a largely unaltered value.
    // Their life is simple, albeit tedious.
   
    public double getThrottle()	{
    	return (1 - joy1.getThrottle()) / -2;
    }
    
    public double getX() {			//reads x value on controller
    	double x = joy3.getX();
    	return Math.pow(x, 2) * Math.signum(x);
    }
    
    public double getY() {			//reads y value on controller
    	double y = joy3.getY();
    	return Math.pow(y, 2) * Math.signum(y);
    }
    
    public double getLeft() {		// reads y for left controller
    	double left = joy1.getY();
    	return Math.pow(left, 2) * Math.signum(left);
    }
    
    public double getRight() {		// reads y for right controller
    	double right = joy2.getY();
    	return Math.pow(right, 2) * Math.signum(right);
    }
    
    public double cameraX() {
		if (joy2.getPOV() == -1) { return 0; }
		return Math.sin(Math.toRadians(joy2.getPOV()));
	}
	public double cameraY() {
		if (joy2.getPOV() == -1) { return 0; }
		return -1 * Math.cos(Math.toRadians(joy2.getPOV()));
	}
}
