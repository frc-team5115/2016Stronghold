
package org.usfirst.frc.team5115.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5115.robot.commands.ArcadeDrive;
import org.usfirst.frc.team5115.robot.commands.ArmDrive;
import org.usfirst.frc.team5115.robot.commands.Fondle;
import org.usfirst.frc.team5115.robot.commands.GimbalControl;
import org.usfirst.frc.team5115.robot.commands.TankDrive;
import org.usfirst.frc.team5115.robot.subsystems.Arm;
import org.usfirst.frc.team5115.robot.subsystems.BallFondler;
import org.usfirst.frc.team5115.robot.subsystems.Chassis;
import org.usfirst.frc.team5115.robot.subsystems.Gimbal;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static Preferences prefs;			//subsystems used
	public static OI oi;
	public static Chassis chassis;
	public static Arm arm;
	public static BallFondler ballfondler;
	public static Gimbal gimbal;
	
	public static TankDrive td;
	public static ArcadeDrive arcaded;
	public static ArmDrive ad;
	public static GimbalControl gc;
	public static boolean fieldOriented = false;	//whether it is driving in field oriented mode

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
	// Here we see the robotInit() method in its natural habitat. It is only called when the robot first starts, but it is one of the most important methods to the 5115 ecosystem.
	// All of the alpha-objects, who will determine the eventual nature of the ecosystem, are born here.
    public void robotInit() {
    	prefs = Preferences.getInstance();
		oi = new OI();					//making objects
		chassis = new Chassis();
		arm = new Arm();
		ballfondler = new BallFondler();
		gimbal = new Gimbal();
		
		td = new TankDrive();
		arcaded = new ArcadeDrive();
		ad = new ArmDrive();
		gc = new GimbalControl();
		
		//SmartDashboard.putData("Turn Command", ac);
    }
	
    // A pathetic, useless method.
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	// Nothing to see here.
    public void autonomousInit() {}

    // A simple method, but one that the entire autonomous system relies on to survive. It helps regulate the cyclical nature of the Commands, which are of the genus Finite-State-Machine.
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }
    
    // For the entirety of its short life, teleopInit() is moving. It sets in motion the essencial processes that allow the drivers to drive.
    public void teleopInit() {
    	chassis.direction = Chassis.DIR_ARM;
    	
    	chassis.inuse = false;
    	arm.inuse = false;
    	td.start();
    	ad.start();
    	gc.start();
    	//chassis.imuStart();		//starts imu
    }

    // Move along, folks.
    public void disabledInit() {}

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	SmartDashboard.putNumber("Left Potentiometer", arm.getLeftPot());
    	SmartDashboard.putNumber("Right Potentiometer", arm.getRightPot());
    	SmartDashboard.putString("Direction", chassis.direction == 1 ? "ARM" : "BALL");
        Scheduler.getInstance().run();		//runs scheduler
        
        Timer.delay(0.02);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
