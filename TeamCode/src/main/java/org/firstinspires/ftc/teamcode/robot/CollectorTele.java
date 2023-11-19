package org.firstinspires.ftc.teamcode.robot;

import static org.firstinspires.ftc.teamcode.robot.CollectorTele.CollectorState.IN;
import static org.firstinspires.ftc.teamcode.robot.CollectorTele.CollectorState.OFF;
import static org.firstinspires.ftc.teamcode.robot.CollectorTele.CollectorState.OUT;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.CachingMotor;
import org.firstinspires.ftc.teamcode.utils.CachingServo;

import java.security.SecureRandom;

public class CollectorTele {
    private final DcMotorEx CollectorMotor;
    public CollectorState CollectorState = CollectorState.OFF;
    private ServoImplEx Drawbridge;
    public DrawbridgeState drawbridgeState = DrawbridgeState.ONE;
    private static final double DRAWBRIDGE_STATE_MAX = 1500;
    private static final double DRAWBRIDGE_STATE_MIN = 500;
    private HardwareMap hardwareMap;
    private final Telemetry telemetry;

    public CollectorTele(HardwareMap hardwareMap,Telemetry telemetry){
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;

        CollectorMotor = new CachingMotor(hardwareMap.get(DcMotorEx.class, "Collector"));
        Drawbridge = new CachingServo(hardwareMap.get(ServoImplEx.class, "Drawbridge"));

        Drawbridge.setPwmRange(new PwmControl.PwmRange(DRAWBRIDGE_STATE_MAX,DRAWBRIDGE_STATE_MIN));
    }

    public enum DrawbridgeState {
        ONE, TWO, THREE, FOUR
    }

    public void setDrawbridgeState() {
        switch (setDrawbridgeState) {
            case ONE: {
                DrawbridgeOne();
                break;
            }
            case TWO: {
                DrawbridgeTwo();
                break;
            }
            case THREE: {
                DrawbridgeThree();
                break;
            }
            case FOUR: {
                DrawbridgeFour();
                break;

            }
        }
    }
    private void DrawbridgeOne() {
        Drawbridge.setPosition(0.01);
    }
    private void DrawbridgeTwo() {
        Drawbridge.setPosition(0.33);
    }
    private void DrawbridgeThree() {
        Drawbridge.setPosition(0.66);
    }
    private void DrawbridgeFour() {
        Drawbridge.setPosition(0.99);
    }

    public enum CollectorState {
        OFF, IN, OUT
    }


    public void setCollectorState() {
        telemetry.addData("collectorState", collectorState);
        switch (collectorState) {
            case OFF: {
                collectorOff();
                break;
            }
            case IN: {
                collectorIn();
                break;
            }
            case OUT: {
                collectorOut();
                break;
            }
        }
    }

    public void setCollectorOff(){
        collectorState = CollectorState.OFF;
    }

    public void setCollectorIn(){
        collectorState = CollectorState.IN;
    }

    public void setCollectorOut(){
        collectorState = CollectorState.OUT;
    }

    private void collectorOff() {CollectorMotor.setPower(0);}
    private void collectorIn(){
        CollectorMotor.setPower(0.5);
    }
    private void collectorOut() { CollectorMotor.setPower(-0.5);
    }

}
