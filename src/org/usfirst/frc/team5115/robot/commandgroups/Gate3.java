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
public class Gate3 extends CommandGroup {
    
    public  Gate3() {
    	addSequential(new PrintCommand("Gate 3"));
    	addSequential(new Turn(-23));
        addSequential(new StraightLine(11, 0.4));
        addSequential(new Turn(-115));
        addSequential(new SwitchDirection());
        addSequential(new StraightLine(2, 0.4));
        addSequential(new AimScore());
    }
}
