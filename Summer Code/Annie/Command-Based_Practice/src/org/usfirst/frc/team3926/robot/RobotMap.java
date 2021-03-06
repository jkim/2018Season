package org.usfirst.frc.team3926.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    public final static int FRONT_RIGHT_MOTOR_ID      = 2; //inputs for Jill (might need to change)
    public final static int FRONT_LEFT_MOTOR_ID       = 14;
    public final static int BACK_RIGHT_MOTOR_ID       = 3;
    public final static int BACK_LEFT_MOTOR_ID        = 15;


    public final static boolean XBOX_DRIVE_CONTROLLER = false;
    public final static int RIGHT_JOYSTICK_ID         = 1;
    public final static int LEFT_JOYSTICK_ID          = 2;

    
    public final static int XBOX_CONTROLLER_ID        = 0;
    public final static int XBOX_LEFT_SPEED_AXIS      = 1; //left xbox joystick
    public final static int XBOX_RIGHT_SPEED_AXIS     = 5; //right xbox joystick


}
