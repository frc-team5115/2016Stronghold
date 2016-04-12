package org.usfirst.frc.team5115.robot.commandgroups;

import org.usfirst.frc.team5115.robot.commands.RecordYaw;
import org.usfirst.frc.team5115.robot.commands.StraightLine;
import org.usfirst.frc.team5115.robot.commands.StraightLineFast;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RockWall extends CommandGroup {
    
    public  RockWall() {
        addSequential(new StraightLineFast(8));
        
    }
}
