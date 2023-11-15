package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Lift {

    private DcMotorEx liftMotor;
    private HardwareMap hardwareMap;
    private Telemetry telemetry;


    public LiftState liftState;

    
    public Lift(HardwareMap hardwareMap, Telemetry telemetry){
        this.liftMotor = liftMotor;
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;


        liftMotor = new org.firstinspires.ftc.teamcode.util.CachingMotor(hardwareMap.get(DcMotorEx.class, "Lift"));
    }


    public enum LiftState {
      one, two, three, four, five, six, seven, eight
    }
       public void liftState() {
        switch (liftState) {
            case one: {
                setLiftDown();
                break;
            }
            case UP: {
                setLiftUp();
                break;
            }
        }
    }

    public void setLiftDown(){
        liftState = Lift.LiftState.DOWN;
    }

    public void setLiftUp(){
        liftState = Lift.LiftState.UP;
    }


}
