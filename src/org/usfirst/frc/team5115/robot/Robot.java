
package org.usfirst.frc.team5115.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5115.robot.commandgroups.Auto;
import org.usfirst.frc.team5115.robot.commandgroups.CDF;
import org.usfirst.frc.team5115.robot.commands.Aim;
import org.usfirst.frc.team5115.robot.commands.ArcadeDrive;
import org.usfirst.frc.team5115.robot.commands.ArmDrive;
import org.usfirst.frc.team5115.robot.commands.Fondle;
import org.usfirst.frc.team5115.robot.commands.FondlerHeightManager;
import org.usfirst.frc.team5115.robot.commands.StraightLineGyro;
import org.usfirst.frc.team5115.robot.commands.Turn;
import org.usfirst.frc.team5115.robot.commands.TurnGyro;
import org.usfirst.frc.team5115.robot.subsystems.Arm;
import org.usfirst.frc.team5115.robot.subsystems.BallFondler;
import org.usfirst.frc.team5115.robot.subsystems.Chassis;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static Preferences prefs;
	public static SendableChooser defenseChooser;
	public static SendableChooser gateChooser;
	
	//subsystems used
	public static OI oi;
	public static Chassis chassis;
	public static Arm arm;
	public static BallFondler ballfondler;
	
	public static ArcadeDrive arcaded;
	public static ArmDrive ad;
	public static FondlerHeightManager fhm;
	public static Auto auto;
	public static Fondle f;
	
	public static Turn turn;
	
	public static NetworkTable nt;
	
	public static double angleOffset = 0;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
	// Here we see the robotInit() method in its natural habitat. It is only called when the robot first starts, but it is one of the most important methods to the 5115 ecosystem.
	// All of the alpha-objects, who will determine the eventual nature of the ecosystem, are born here.
    public void robotInit() {
    	prefs = Preferences.getInstance();
    	
    	defenseChooser = new SendableChooser();
    	gateChooser = new SendableChooser();
    	defenseChooser.addDefault("Low bar", 0);
    	defenseChooser.addObject("Cheval de Frise", 1);
    	defenseChooser.addObject("Portcullis", 2);
    	defenseChooser.addObject("Terrain", 3);
    	gateChooser.addDefault("Gate 1", 1);
    	gateChooser.addObject("Gate 2", 2);
    	gateChooser.addObject("Gate 3", 3);
    	gateChooser.addObject("Gate 4", 4);
    	gateChooser.addObject("Gate 5", 5);
    	SmartDashboard.putData("Defense", defenseChooser);
    	SmartDashboard.putData("Gate Chooser", gateChooser);
    	
		chassis = new Chassis();
		arm = new Arm();
		ballfondler = new BallFondler();
		
		arcaded = new ArcadeDrive();
		ad = new ArmDrive();
		fhm = new FondlerHeightManager();
		f = new Fondle();
		
		CDF cdf = new CDF();
		SmartDashboard.putData("CDF", cdf);

    	nt = NetworkTable.getTable("pi");
    	nt.putNumber("riostatus", 0);
    	nt.putNumber("angletogoal", 0);
    
		oi = new OI();

    	chassis.direction = Chassis.DIR_ARM;
    }
	
    // A pathetic, useless method.
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	// Nothing to see here.
    public void autonomousInit() {
    	nt.putNumber("riostatus", 1);
    	int defense = (int) defenseChooser.getSelected();
    	int gate = (int) gateChooser.getSelected();
    	auto = new Auto(defense, gate);
    	auto.start();
    	f.start();
    }
    
    // A simple method, but one that the entire autonomous system relies on to survive. It helps regulate the cyclical nature of the Commands, which are of the genus Finite-State-Machine.
    public void autonomousPeriodic() {
    	angleOffset = nt.getNumber("angletogoal", 0);
    	
        Scheduler.getInstance().run();
    }
    
    // For the entirety of its short life, teleopInit() is moving. It sets in motion the essencial processes that allow the drivers to drive.
    public void teleopInit() {
    	chassis.inuse = false;
    	arm.inuse = false;
    	
    	arcaded.start();
    	ad.start();
    	fhm.start();
    	if (!f.isRunning())
    		f.start();
    }

    // Move along, folks.
    public void disabledInit() {}

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	SmartDashboard.putNumber("Yaw", chassis.getYaw());
//    	SmartDashboard.putNumber("Left Encoder", chassis.leftDist());
//    	SmartDashboard.putNumber("Right Encoder", chassis.rightDist());
    	SmartDashboard.putString("Direction", chassis.direction == Chassis.DIR_ARM ? "ARM" : "BALL");
    	
    	angleOffset = nt.getNumber("angletogoal", 0);
    	System.out.println(angleOffset);
    	
        Scheduler.getInstance().run();		//runs scheduler
        
        Timer.delay(0.02);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public static void panic() {
    	Scheduler.getInstance().removeAll();
    	
    	chassis.inuse = false;
    	arm.inuse = false;
    	
    	arcaded.start();
    	ad.start();
    	fhm.start();
    }
}
