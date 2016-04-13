package org.usfirst.frc.team5115.robot.commands;

import org.usfirst.frc.team5115.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FindBall extends Command {

	double angle;

	double accum = 0;

	double kp = 0.05;
	double ki = 0.0005;

	double speed = 0.3;

	public FindBall() {}

	protected void initialize() {
		Robot.chassis.inuse = true;
		Robot.ballfondler.inuse = true;
		accum = 0;

		Robot.f.start();
	}

	protected void execute() {
		angle = Robot.angleOffset;
		Robot.chassis.tankDrive(speed - (kp * angle + ki * accum), speed + (kp * angle + ki * accum), 1);
		accum += angle;
	}

	protected boolean isFinished() {
		return Robot.ballfondler.isFondling();
	}

	protected void end() {
		Robot.chassis.tankDrive(0, 0, 1);
		Robot.f.cancel();

		Robot.chassis.inuse = false;
		Robot.ballfondler.inuse = false;
	}

	protected void interrupted() {
		Robot.chassis.tankDrive(0, 0, 1);
		Robot.f.cancel();

		Robot.chassis.inuse = false;
		Robot.ballfondler.inuse = false;
	}
}
