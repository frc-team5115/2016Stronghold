package org.usfirst.frc.team5115.robot;

import org.usfirst.frc.team5115.robot.commandgroups.*;
import org.usfirst.frc.team5115.robot.commands.Aim;
import org.usfirst.frc.team5115.robot.commands.Fondle;
import org.usfirst.frc.team5115.robot.commands.Panic;
import org.usfirst.frc.team5115.robot.commands.StopFondling;
import org.usfirst.frc.team5115.robot.commands.SwitchDirection;
import org.usfirst.frc.team5115.robot.subsystems.Chassis;
import org.usfirst.frc.team5115.robot.triggers.CDFTrigger;
import org.usfirst.frc.team5115.robot.triggers.PortcullisTrigger;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	 static Joystick joy;
	 Button armup;
	 Button armdown;
	 Button StraightLineTest;
	
	 Button gate1;
	 Button gate2;
	 Button gate3;
	 Button gate4;
	 Button gate5;
	 
	 Button fondle;
	 Button stopFondling;
	 Button aim;
	 
	 Button switchDirection;
	 
	 Button panicButton;
	 
	 Button cdf;
	 Button port;
	    
    public OI() {
    	//mapping
    	joy = new Joystick(0);		// The first task the OI performs is birthing its symbiotic partner, the vaguely phallic joystick object.
    	
    	armup = new JoystickButton(joy, 6);
    	armdown = new JoystickButton(joy, 4);
    	StraightLineTest = new JoystickButton(joy, 10);

    	fondle = new JoystickButton(joy, 3);
    	stopFondling = new JoystickButton(joy, 5);
    	aim = new JoystickButton(joy, 1);
    	
    	switchDirection = new JoystickButton(joy, 2);
    	
    	panicButton = new JoystickButton(joy, 8);
    	
    	cdf = new JoystickButton(joy, 10);
    	port = new JoystickButton(joy, 9);

    	// After the buttons have fully matured, they are paired with a newly formed Command object, which will be their life companion.
    	switchDirection.whenPressed(new SwitchDirection());
    	
    	panicButton.whenPressed(new Panic());
    	
    	StraightLineTest.whenPressed(new Test());
    	
    	fondle.whenPressed(Robot.f);
    	stopFondling.cancelWhenPressed(Robot.f);
    	aim.whenPressed(new AimScore());
    	
    	cdf.whenActive(new CDF());
    	port.whenActive(new Portcullis());
    }
    
    // Below the constructor lie the getter functions, whose only purpose is to pass on a largely unaltered value.
    // Their life is simple, albeit tedious.
   
    public double getThrottle()	{
    	return (1 - joy.getThrottle()) / -2;
    }
    
    public double getX() {//reads x value on controller
		double x = -joy.getX();
    	if (Robot.chassis.direction == Chassis.DIR_ARM) {
    		x = joy.getX();
    	}
    	return Math.pow(x, 2) * Math.signum(x);
    }
    public double getY() {			//reads y value on controller
    	double y = joy.getY();
    	return Math.pow(y, 2) * Math.signum(y);
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
	
	public static double getHat() {
		return joy.getPOV();
	}
	
	public boolean aimPressed() {
		return aim.get();
	}
}
