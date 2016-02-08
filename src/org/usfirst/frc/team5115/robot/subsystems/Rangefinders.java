package org.usfirst.frc.team5115.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Rangefinders extends Subsystem {
	
	public static final int FRONT = 0, LEFT = 1, RIGHT = 2;
    
	AnalogInput[] sensor;
	double[] lastvoltage = {0, 0, 0};
	
	public Rangefinders() {
		sensor = new AnalogInput[3];
		sensor[0] = new AnalogInput(1);
		sensor[1] = new AnalogInput(2);
		sensor[2] = new AnalogInput(3);
	}
	
	public double getVoltage(int i) {
		double voltage = sensor[i].getVoltage();
		
		if (lastvoltage[i] == 0)
			lastvoltage[i] = voltage;
		
		if (Math.abs(lastvoltage[i] - voltage) > 1 || voltage > 4 || voltage < 0.25)
			voltage = lastvoltage[i];
		
		lastvoltage[i] = voltage;
		
		return voltage;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

