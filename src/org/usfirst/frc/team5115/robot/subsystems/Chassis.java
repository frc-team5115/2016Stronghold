package org.usfirst.frc.team5115.robot.subsystems;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team5115.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Chassis extends Subsystem {
	
	public boolean inuse;	//lets outside commands know what is happening
	
	// key for both maps will be the position of the motor (ex 'frontleft')
    Map<String, CANTalon> motors;	// mapping text (string) to motors
    Map<String, Double> speeds;		// mapping text (string) to the speed of each motor
    
    double xv = 0;			// accumulators for imu
    double yv = 0;
    double xd = 0;
    double yd = 0;
    
    public Chassis() {
    //	imu = new AHRS(SerialPort.Port.kMXP);
    	
    	// intialize the motors with the correct CAN ids
    	motors = new HashMap<String, CANTalon>();
    	motors.put("left", new CANTalon(1));
    	motors.put("right", new CANTalon(2));
    	motors.put("left2", new CANTalon(3));
    	motors.put("right2", new CANTalon(4));
//    	motors.get("left").changeControlMode(ControlMode.Speed);
//    	motors.get("right").changeControlMode(ControlMode.Speed);
//    	motors.get("left").setPID(0.25, 0.005, 100);
//    	motors.get("right").setPID(0.25, 0.005, 100);
    	
    	motors.get("left2").changeControlMode(ControlMode.Follower);
    	motors.get("right2").changeControlMode(ControlMode.Follower);
    	motors.get("left2").set(motors.get("left").getDeviceID());
    	motors.get("right2").set(motors.get("right").getDeviceID());
    	
    	// initialize an empty map for speeds
    	speeds = new HashMap<String, Double>();
    }
    
    // searches the speeds for the highest value greater than 1, then divides all the speeds by that value
    // ensures the highest speed is less than one, and keeps the ratio between each speed constant
    // (if no speeds are greater than 1, no change must be made)
    private void normSpeeds() {
    	double max = 1;		// the highest motor speed
    	for (double s : speeds.values())	// for every value in speed...
    		if (s > max)					// check whether it is over the max speed
    			max = s;					// and if so, set it as the max speed
    	for (double s: speeds.values())		// then, divide every speed by the max speed so as to stay below 1 and mantain ratio
    		s /= max;
    }
    
    // converts translation and rotation values into speeds for each motor and normalizes them
    // gets the throttle from smartdashboard
    // iterates through the maps keys, setting each motor to its corresponding speed
   
    public void tankDrive(double right, double left) {
    	speeds.put("left", left);
    	speeds.put("right", right);
    	normSpeeds();
    	
    	double throttle = Robot.oi.getThrottle();
    	
    	for (String key : speeds.keySet())
    		motors.get(key).set(speeds.get(key) * throttle);
    	
    	SmartDashboard.putNumber("Encoder speed", motors.get("left").getSpeed());
    }
    
 
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

