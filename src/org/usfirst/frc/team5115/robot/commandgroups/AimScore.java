package org.usfirst.frc.team5115.robot.commandgroups;

import org.usfirst.frc.team5115.robot.commands.Aim;
import org.usfirst.frc.team5115.robot.commands.StartFondling;
import org.usfirst.frc.team5115.robot.commands.StopFondling;
import org.usfirst.frc.team5115.robot.commands.StraightLine;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AimScore extends CommandGroup {
    
    public  AimScore() {
        addSequential(new Aim());
        addSequential(new StraightLine(2, 0.2));
        addSequential(new StopFondling());
        addSequential(new WaitCommand(0.5));
        addSequential(new StartFondling());
        addSequential(new WaitCommand(3));
        addSequential(new StopFondling());
    }
}
