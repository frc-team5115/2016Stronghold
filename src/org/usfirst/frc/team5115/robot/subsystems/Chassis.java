package org.usfirst.frc.team5115.robot.subsystems;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team5115.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Chassis extends Subsystem {
	
	public final static int DIR_ARM = 1;
	public final static int DIR_BALL = -1;
	
	public boolean inuse;	//lets outside commands know what is happening
	public int direction;
	
	// key for both maps will be the position of the motor (ex 'frontleft')
    Map<String, CANTalon> motors;	// mapping text (string) to motors
    Map<String, Double> speeds;		// mapping text (string) to the speed of each motor
    
    AHRS navx;
    
    public Chassis() {
    //	imu = new AHRS(SerialPort.Port.kMXP);
    	
    	// intialize the motors with the correct CAN ids
    	motors = new HashMap<String, CANTalon>();
    	motors.put("left", new CANTalon(1));
    	motors.put("right", new CANTalon(2));
    	motors.put("left2", new CANTalon(3));
    	motors.put("right2", new CANTalon(4));
    	
    	motors.get("left").changeControlMode(TalonControlMode.Speed);
    	motors.get("right").changeControlMode(TalonControlMode.Speed);
    	motors.get("left").setPID(0.25, 0.002, 125);
    	motors.get("right").setPID(0.25, 0.003, 50);
    	
    	motors.get("left2").changeControlMode(TalonControlMode.Follower);
    	motors.get("right2").changeControlMode(TalonControlMode.Follower);
    	motors.get("left2").set(motors.get("left").getDeviceID());
    	motors.get("right2").set(motors.get("right").getDeviceID());
    	
    	motors.get("right").reverseOutput(true);
    	motors.get("right").reverseSensor(true);
    	
    	// initialize an empty map for speeds
    	speeds = new HashMap<String, Double>();
    	
    	navx = new AHRS(SerialPort.Port.kMXP);
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
   
    public void tankDrive(double right, double left, double throttle) {
    	speeds.put("left", left);
    	speeds.put("right", right);
    	normSpeeds();
    	
//    	if (Robot.arm.getLeftPot() > Arm.MAGIC_NUMBER)
//    		throttle /= 2;
    	
    	for (String key : speeds.keySet())
    		motors.get(key).set(speeds.get(key) * direction * throttle * 1000);
    	
    	SmartDashboard.putNumber("Encoder speed", motors.get("left").getSpeed());
    }
    
    // NAVX WRAPPERS
    public double getYaw() {
    	return navx.getRoll();
    }
    
    //navx is mounted sideways
    public double getPitch() {
    	return navx.getPitch();
    }
    
    public double getRoll() {
    	return navx.getYaw();
    }
    
    public void resetImu() {
		navx.reset();
	}
    
    // ENCODER WRAPPERS
    public double distanceTraveled() {
    	double leftDist = motors.get("left").getPosition();
    	double rightDist = motors.get("right").getPosition();
    	double avgDist = (leftDist + rightDist) / 2;
    	return avgDist / 1440 * 8 * Math.PI / 12;
    }
    
    public void resetEncoders() {
    	motors.get("left").setPosition(1);
    	motors.get("right").setPosition(1);
    }
    
 
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

