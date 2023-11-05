package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utils.CachingServo;

public class Depositor {
     Telemetry telemetry;
     HardwareMap hardwareMap;


     // LDS = Left Depositor Servo //
     private ServoImplEx LDS;
     // RDS = Right Depositor Servo //
    private ServoImplEx RDS;

    private double ldsMAX = 2250;
    private double ldsMIN = 0;

    private double rdsMAX = 2250;
    private double rdsMIN = 0;

    public Depositor(HardwareMap hardwareMap,Telemetry telemetry){
        this.telemetry=telemetry;
        this.hardwareMap=hardwareMap;

        LDS = new CachingServo(hardwareMap.get(ServoImplEx.class,"LDS"));
        RDS = new CachingServo(hardwareMap.get(ServoImplEx.class,"RDS"));

        LDS.setPwmRange(new PwmControl.PwmRange(ldsMAX, ldsMIN));
        RDS.setPwmRange(new PwmControl.PwmRange(ldsMAX, ldsMIN));



    }

    public enum DepositorState{
        RESTING, SCORING
    }

    DepositorState depositorState = DepositorState.RESTING;
    public void depositorState(){
        switch (depositorState){
            case RESTING: {
                depositorResting();
                break;
            }
            case SCORING:{
                depositorScoring();
                break;
            }
        }
    }


    private void depositorResting(){
        LDS.setPosition(0.01);
        RDS.setPosition(0.01);
    }
    private void depositorScoring(){
        LDS.setPosition(0.99);
        RDS.setPosition(0.99);
    }


}

