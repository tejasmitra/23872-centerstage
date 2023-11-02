package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Map;

public class Lift {
    private Telemetry telemetry;
    private DcMotorEx LiftMotor;



    public enum LiftState {
        DEFAULT, SCORING, MAX, HOLDING
    }

    public LiftState liftState = LiftState.DEFAULT;

}
