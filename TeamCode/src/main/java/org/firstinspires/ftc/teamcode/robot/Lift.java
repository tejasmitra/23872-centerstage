package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Lift {

    private DcMotorEx liftMotor;
    private final HardwareMap hardwareMap;
    private final Telemetry telemetry;


    public LiftState liftState = LiftState.ZERO;

    private final static double kP = 0.1;
    private final static double kI = 0.0;
    private final static double kD = 0.0;
    private final static double kS = 0.005;
    PIDController pidController = new PIDController(kP, kI, kD);
    private final static int levelZeroHeight = 0;
    private final static int levelOneHeight = 108;
    private final static int levelTwoHeight = 326;
    private final static int levelThreeHeight = 553;
    private final static int levelFourHeight = 788;
    private final static int levelFiveHeight = 1000;
    private int heightCounter = 0;
    private int liftHeight;
//    private final static int levelSixHeight = 600;
//    private final static int levelSevenHeight = 700;
//    private final static int levelEightHeight
//    ht = 800;

    public void setStateLevelZero() {
        liftState = LiftState.ZERO;
    }

    public void setStateLevelThree() {
        liftState = LiftState.THREE;
    }

    public Lift(HardwareMap hardwareMap, Telemetry telemetry) {
        this.liftMotor = liftMotor;
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;


        pidController.setInputBounds(0, 1000);
        pidController.setOutputBounds(0, 1);
        liftMotor = new org.firstinspires.ftc.teamcode.util.CachingMotor(hardwareMap.get(DcMotorEx.class, "Lift"));
        liftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    public enum LiftState {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT
    }


    public void liftState() {
        switch (liftState) {
            case ZERO: {
//                setLiftHeight(levelZeroHeight);
                telemetry.addData("lift enum", "Zero");
                break;
            }
            case ONE: {
//                setLiftHeight(levelOneHeight);
                telemetry.addData("lift enum", "One");
                break;
            }
            case TWO: {
//                setLiftHeight(levelTwoHeight);
                telemetry.addData("lift enum", "Two");
                break;
            }
            case THREE: {
//                setLiftHeight(levelThreeHeight);
                telemetry.addData("lift enum", "Three");
                break;
            }
            case FOUR: {
//                setLiftHeight(levelFourHeight);
                telemetry.addData("lift enum", "Four");
                break;
            }
            case FIVE: {
//                setLiftHeight(levelFiveHeight);
                telemetry.addData("lift enum", "Five");
                break;
            }
//            case SIX: {
//                setLiftHeight(levelSixHeight);
//                break;
//            }
//            case SEVEN: {
//                setLiftHeight(levelSevenHeight);
//                break;
//            }
//            case EIGHT: {
//                setLiftHeight(levelEightHeight);
//                break;
//            }
        }
    }

    public void setLiftHeight(int liftHeight) {
        pidController.setTarget(liftHeight);
        double error = liftHeight - liftMotor.getCurrentPosition();
        liftMotor.setPower(pidController.updateWithError(error) + kS);
//    public void setLiftUp() {
//        liftMotor.setPower(0.6);
//    }
    }

    public void updateLevelCounter() {
        heigh
    }
    public void levelCounter(){
        if (heightCounter == 0) {
            liftState = LiftState.ZERO;
        } else if(heightCounter == 108){
            //184
            liftState = LiftState.ONE;
        } else if(heightCounter == 326){
            //466
            liftState = LiftState.TWO;
        } else if(heightCounter == 553){
            //686
            liftState = LiftState.THREE;
        } else if(heightCounter == 788){
            //932
            liftState = LiftState.FOUR;
        } else if(heightCounter == 1000){
            liftState = LiftState.FIVE;
        }

    }


    public void setLiftDown () {
        liftMotor.setPower(-0.6);
    }

    public void setLiftOff () {
        liftMotor.setPower(0);
    }

    public void setRawPower ( double power){
        liftMotor.setPower(power);
    }

    public void setLiftZero () {
        liftState = LiftState.ZERO;
    }

    public void setLiftOne () {
        liftState = LiftState.ONE;
    }
    public int getPosition () {
        return liftMotor.getCurrentPosition();
    }

}
