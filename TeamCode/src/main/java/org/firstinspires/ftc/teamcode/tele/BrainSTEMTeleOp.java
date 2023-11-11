package org.firstinspires.ftc.teamcode.tele;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Twist2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.TankDrive;
import org.firstinspires.ftc.teamcode.robot.BrainSTEMRobot;

@TeleOp (name = "TeleOp", group = "Robot")
public class BrainSTEMTeleOp extends LinearOpMode {

    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    @Override
    public void runOpMode() {
//        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        BrainSTEMRobot robot = new BrainSTEMRobot(hardwareMap, telemetry);

        waitForStart();

        while (opModeIsActive()) {
            drive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x
                    ),
                    -gamepad1.right_stick_x
            ));

            drive.updatePoseEstimate();

            telemetry.addData("x", drive.pose.position.x);
            telemetry.addData("y", drive.pose.position.y);
            telemetry.addData("heading", drive.pose.heading);
            telemetry.update();

        if (gamepad1.a) {
            robot.collector.setCollectorIn();
        }
        if (gamepad1.b) {
            robot.collector.setCollectorOut();
        }
        if (!gamepad1.a && gamepad1.b) {
           robot.collector.setCollectorOff();
        }
        }
    }
}
