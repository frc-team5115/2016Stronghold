package org.usfirst.frc.team5115.robot.triggers;

import org.usfirst.frc.team5115.robot.OI;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class PortcullisTrigger extends Trigger {
    
    public boolean get() {
        return OI.getHat() == 0;
    }
}
