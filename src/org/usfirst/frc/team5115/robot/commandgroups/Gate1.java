package org.usfirst.frc.team5115.robot.commandgroups;

import org.usfirst.frc.team5115.robot.commands.StopFondling;
import org.usfirst.frc.team5115.robot.commands.StraightLine;
import org.usfirst.frc.team5115.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Gate1 extends CommandGroup {
    
    public  Gate1() {
        addSequential(new StraightLine(8));
        addSequential(new Turn(-60));
        addSequential(new StraightLine(9));
        addSequential(new StopFondling());
    }
}
