package org.usfirst.frc.team5115.robot.commandgroups;

import org.usfirst.frc.team5115.robot.commands.Aim;
import org.usfirst.frc.team5115.robot.commands.StopFondling;
import org.usfirst.frc.team5115.robot.commands.StraightLine;
import org.usfirst.frc.team5115.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 *
 */
public class Gate5 extends CommandGroup {
    
    public  Gate5() {
    	addSequential(new PrintCommand("Gate 5"));
        addSequential(new StraightLine(12.5, 0.4));
        addSequential(new Turn(120));
        addSequential(new AimScore());
    }
}
