package org.usfirst.frc.team5115.robot;

import org.usfirst.frc.team5115.robot.commandgroups.*;
import org.usfirst.frc.team5115.robot.commands.Fondle;
import org.usfirst.frc.team5115.robot.commands.GimbalReset;
import org.usfirst.frc.team5115.robot.commands.StopFondling;
import org.usfirst.frc.team5115.robot.commands.SwitchDirection;

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
	 Button armup;
	 Button armdown;
	
	 Button gate1;
	 Button gate2;
	 Button gate3;
	 Button gate4;
	 Button gate5;

	 Button terrain;
	 Button portcullis;
	 Button cdf;
	 
	 Button fondle;
	 Button stopFondling;
	 
	 Button switchDirection;
	    
    public OI() {
    	//mapping
    	joy1 = new Joystick(0);		// The first task the OI performs is birthing its symbiotic partner, the vaguely phallic joystick object.
    	joy2 = new Joystick(1);
    	joy3 = new Joystick(2);
    	
    	gimbalReset = new JoystickButton(joy2, 2);
    	armup = new JoystickButton(joy2, 6);
    	armdown = new JoystickButton(joy2, 4);
    	
    	gate1 = new JoystickButton(joy1, 8);
    	gate2 = new JoystickButton(joy1, 7);
    	gate3 = new JoystickButton(joy2, 9);
    	gate4 = new JoystickButton(joy2, 11);
    	gate5 = new JoystickButton(joy2, 12);

    	terrain = new JoystickButton(joy1, 11);
    	portcullis = new JoystickButton(joy1, 7);
    	cdf = new JoystickButton(joy1, 9);
    	
    	fondle = new JoystickButton(joy2, 3);
    	stopFondling = new JoystickButton(joy2, 5);
    	
    	switchDirection = new JoystickButton(joy2, 2);

    	// After the buttons have fully matured, they are paired with a newly formed Command object, which will be their life companion.
    	gimbalReset.whenPressed(new GimbalReset());
    	
    	switchDirection.whenPressed(new SwitchDirection());
    	
//    	gate1.whenPressed(new Gate1());
//    	gate2.whenPressed(new Gate2());
//    	gate3.whenPressed(new Gate3());
//    	gate4.whenPressed(new Gate4());
//    	gate5.whenPressed(new Gate5());
//    	
//    	def1.whenPressed(new TerrainDefense());
    	
    	fondle.whileHeld(new Fondle());
    	stopFondling.whenPressed(new StopFondling());
    }
    
    // Below the constructor lie the getter functions, whose only purpose is to pass on a largely unaltered value.
    // Their life is simple, albeit tedious.
   
    public double getThrottle()	{
    	return (1 - joy1.getThrottle()) / -2;
    }
    
    public double getX() {			//reads x value on controller
    	double x = joy1.getX();
    	return Math.pow(x, 2) * Math.signum(x);
    }
    public double getY() {			//reads y value on controller
    	double y = -joy1.getY();
    	return Math.pow(y, 2) * Math.signum(y);
    }
    
    public double getLeft() {		// reads y for left controller
    	double left = -joy1.getY();
    	return Math.pow(left, 2) * Math.signum(left);
    }
    public double getRight() {		// reads y for right controller
    	double right = -joy2.getY();
    	return Math.pow(right, 2) * Math.signum(right);
    }
    
    public int getArmDir() {
    	if (armup.get() && !armdown.get()) return -1;
    	else if (!armup.get() && armdown.get()) return 1;
    	else return 0;
    }
    
    public boolean fondling() {
    	System.out.println(fondle.get());
    	return fondle.get();
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
