package org.usfirst.frc.team5115.robot.commandgroups;

import org.usfirst.frc.team5115.robot.commands.Aim;
import org.usfirst.frc.team5115.robot.commands.StopFondling;
import org.usfirst.frc.team5115.robot.commands.StraightLine;
import org.usfirst.frc.team5115.robot.commands.SwitchDirection;
import org.usfirst.frc.team5115.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 *
 */
public class Gate4 extends CommandGroup {
    
    public  Gate4() {
    	addSequential(new PrintCommand("Gate 4"));
    	addSequential(new Turn(25));
        addSequential(new StraightLine(14, 0.4));
        addSequential(new Turn(-105));
        addSequential(new AimScore());
    }
}
