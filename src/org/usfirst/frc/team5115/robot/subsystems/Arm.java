
package org.usfirst.frc.team5115.robot.subsystems;

import org.usfirst.frc.team5115.robot.Robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
	
	public static final double MAGIC_NUMBER = 42;
    
	public boolean inuse = false;
	public double mainPos = 50;
	
	DigitalInput topLimit;
	DigitalInput bottomLimit;
	
    AnalogInput leftpot;
    AnalogInput rightpot;
    Victor motor;
    
    public Arm() {
    	topLimit = new DigitalInput(0);
    	bottomLimit = new DigitalInput(1);
    	leftpot = new AnalogInput(0);
    	rightpot = new AnalogInput(1);
    	motor = new Victor(0);
    }
    
    public void move(int d) {
    	if (d == 1 && topLimit.get())
    		motor.set(0.5);
    	else if (d == -1 && !bottomLimit.get())
    		motor.set(-0.5);
    	else
    		motor.set(0);
    }
    
    public double getLeftPot()	{
    	return leftpot.getValue();
    }
    
    public double getRightPot()	{
    	return rightpot.getValue();
    }
    
    public double getAvgPot()	{
    	return (leftpot.getValue() + rightpot.getValue()) /2;
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

