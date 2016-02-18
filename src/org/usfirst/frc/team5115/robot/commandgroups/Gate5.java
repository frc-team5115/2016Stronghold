package org.usfirst.frc.team5115.robot.commandgroups;

import org.usfirst.frc.team5115.robot.commands.StopFondling;
import org.usfirst.frc.team5115.robot.commands.StraightLine;
import org.usfirst.frc.team5115.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Gate5 extends CommandGroup {
    
    public  Gate5() {
        addSequential(new StraightLine(12.5));
        addSequential(new Turn(60));
        addSequential(new StopFondling());
    }
}
