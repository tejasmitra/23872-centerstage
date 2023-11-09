package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BrainSTEMRobot {
    HardwareMap hardwareMap;
    Telemetry telemetry;
//    public Collector collector;
    public Transfer transfer;
    public Depositor depositor;
    public Lift lift;

    public BrainSTEMRobot (HardwareMap hardwareMap, Telemetry telemetry){

        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;


//        collector = new Collector(hardwareMap, telemetry);
        transfer = new Transfer(hardwareMap, telemetry);
        depositor = new Depositor(hardwareMap, telemetry);
        lift = new Lift(hardwareMap, telemetry);
    }

    public void update(){
//        collector.collectorState();
        transfer.transferState();
        depositor.depositorState();
        lift.liftState();
    }



}

