package frc.team3926.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import static frc.team3926.robot.Robot.*;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

    private WPI_TalonSRX FR;
    private WPI_TalonSRX BR;
    private WPI_TalonSRX FL;
    private WPI_TalonSRX BL;

    private SpeedControllerGroup m_right;
    private SpeedControllerGroup m_left;
    private DifferentialDrive m_myRobot;

    private double ESC;
    private double leftStickYaxis;
    private double rightStickYaxis;

    private double leftSpeed;
    private double rightSpeed;

    public void initDefaultCommand() {

        FR = new WPI_TalonSRX(RobotMap.FRONT_RIGHT);
        BR = new WPI_TalonSRX(RobotMap.BACK_RIGHT);
        FL = new WPI_TalonSRX(RobotMap.FRONT_LEFT);
        BL = new WPI_TalonSRX(RobotMap.BACK_LEFT);

        m_right = new SpeedControllerGroup(FR, BR);
        m_left = new SpeedControllerGroup(FL, BL);
        m_myRobot = new DifferentialDrive(m_left, m_right);

        ESC = RobotMap.EXPONENTIAL_SPEED_CONSTANT;//must be kept between 0 and 1
        leftStickYaxis = Robot.oi.leftStick.getY();
        rightStickYaxis = Robot.oi.rightStick.getY();
    }

    //takes in data from the position of the joysticks to determine speeds for drive system. As a joystick is pushed
    //forward, the speed goes up exponentially
    public void teleopDrive() {

        leftSpeed = ESC * (Math.pow(leftStickYaxis, 3))
                    + (1 - ESC) * leftStickYaxis;
        rightSpeed = ESC * (Math.pow(rightStickYaxis, 3))
                     + (1 - ESC) * rightStickYaxis;

        ESC = RobotMap.EXPONENTIAL_SPEED_CONSTANT; // constant used to determine the exponential curve for the speed. must be kept between 0 & 1

        leftStickYaxis = Robot.oi.leftStick.getY();
        rightStickYaxis = Robot.oi.rightStick.getY();

        leftSpeed = ESC * (Math.pow(leftStickYaxis, 3))
                    + (1 - ESC) * leftStickYaxis;
        rightSpeed = ESC * (Math.pow(rightStickYaxis, 3))
                     + (1 - ESC) * rightStickYaxis;

        m_myRobot.tankDrive(-leftSpeed, -rightSpeed);

       //m_myRobot.tankDrive(-Robot.oi.leftStick.getY(), -Robot.oi.rightStick.getY());

    }


    public void autonomous() {

        if (desiredSwitchOnRight) {
            if (centerPosition) {

                m_myRobot.tankDrive(.5, .5);
                Timer.delay(3);
                m_myRobot.tankDrive(-.5, .5);
                Timer.delay(.5);
                m_myRobot.tankDrive(.5, .5);
                Timer.delay(2);
                m_myRobot.tankDrive(.5, -.5);
                Timer.delay(.5);
                m_myRobot.tankDrive(.5, .5);
                Timer.delay(1);

            }
            else if (rightPosition) {

                m_myRobot.tankDrive(.5, .5);
                Timer.delay(6);
            }
            else if (leftPosition) {

                m_myRobot.tankDrive(.5, .5);
                Timer.delay(6);
            }
        } else {
            if (centerPosition) {

            }
            else if (rightPosition) {


            }
            else if (leftPosition) {


            }
        }

       m_myRobot.tankDrive(0, 0);
    }

    public double setSpeed(double leftSpeed, double rightSpeed) {

        m_myRobot.tankDrive(leftSpeed, rightSpeed);
        return 0;
    }

    public void hitSomething() {

        m_myRobot.tankDrive(0, 0);
        m_myRobot.tankDrive(-.5, -.5);
        Timer.delay(1);
        m_myRobot.tankDrive(0, 0);
        Timer.delay(3);
    }
}

