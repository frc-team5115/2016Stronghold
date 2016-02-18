package org.usfirst.frc.team5115.robot.commandgroups;

import org.usfirst.frc.team5115.robot.commands.StraightLine;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TerrainDefense extends CommandGroup {
    
    public  TerrainDefense() {
        addSequential(new StraightLine(4));
    }
}
