
package org.usfirst.frc.team5115.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
	
/**
 *
 */
public class BallFondler extends Subsystem {
	Victor shoot;
	Relay lift;
	DigitalInput isBall;
	
	public BallFondler()	{
		shoot = new Victor(1);
		lift = new Relay(0);
		isBall = new DigitalInput(7);
	}
	
	public void fondle() {
		shoot.set(.5);
	}
	
	public void stopFondling()	{
		shoot.set(-1);
	}
	
	public void squeeze() {
		shoot.set(0);
	}
	
	public void lower() {
		lift.set(Relay.Value.kForward);
	}
	
	public void raise() {
		lift.set(Relay.Value.kReverse);
	}
	
	public void holdHeight() {
		lift.set(Relay.Value.kOff);
	}
	
	public boolean isFondling() {
		return !isBall.get();
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {}
}

