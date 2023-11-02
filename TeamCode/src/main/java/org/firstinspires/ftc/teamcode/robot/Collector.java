package org.firstinspires.ftc.teamcode.robot;

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

    CollectorState collectorState = CollectorState.OFF;

}
