package org.firstinspires.ftc.teamcode.robot;

import static org.firstinspires.ftc.teamcode.robot.Collector.CollectorState.IN;
import static org.firstinspires.ftc.teamcode.robot.Collector.CollectorState.OFF;
import static org.firstinspires.ftc.teamcode.robot.Collector.CollectorState.OUT;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.CachingMotor;

public class Collector {
    private Telemetry telemetry;
    private DcMotorEx collectorMotor;
    private HardwareMap hardwareMap;
    public Collector(HardwareMap hardwareMap,Telemetry telemetry){
        this.telemetry=telemetry;
        this.hardwareMap=hardwareMap;

        collectorMotor = new CachingMotor(hardwareMap.get(DcMotorEx.class, "collector"));
    }

    public enum CollectorState {
        OFF, IN, OUT
    }
    Collector collector;



    CollectorState collectorState = OFF;

    private void collectorstate() {
        switch (collector) {
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

    public void collectorOff(){
        collectorMotor.setPower(0);
    }
    public void collectorIn(){
        collectorMotor.setPower(0.5);
    }
    public void collectorOut(){
        collectorMotor.setPower(-0.5);
    }

}
