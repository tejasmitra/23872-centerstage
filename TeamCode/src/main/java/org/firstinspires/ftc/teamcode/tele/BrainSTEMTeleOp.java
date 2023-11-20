package org.firstinspires.ftc.teamcode.tele;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Twist2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.TankDrive;
import org.firstinspires.ftc.teamcode.robot.BrainSTEMRobot;

import java.util.HashMap;
import java.util.Map;

@TeleOp (name = "TeleOp", group = "Robot")
public class BrainSTEMTeleOp extends LinearOpMode {
    Map<String, Boolean> toggleMap = new HashMap<String, Boolean>() {{
        put(GAMEPAD_1_A_STATE, false);
        put(GAMEPAD_1_A_IS_PRESSED, false);
    }};

    String GAMEPAD_1_A_STATE = "GAMEPAD_1_A_STATE";
    String GAMEPAD_1_A_IS_PRESSED = "GAMEPAD_1_A_IS_PRESSED";
    @Override
    public void runOpMode() {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        BrainSTEMRobot robot = new BrainSTEMRobot(hardwareMap, telemetry);
        double power = 0.0;
        StickyButton stickyButtonA = new StickyButton();
        StickyButton stickyButtonB = new StickyButton();


        waitForStart();

        while (opModeIsActive()) {
            setButtons();
            drive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x
                    ),
                    -gamepad1.right_stick_x * 0.75));

            drive.updatePoseEstimate();

//            telemetry.addData("x", drive.pose.position.x);
//            telemetry.addData("y", drive.pose.position.y);
//            telemetry.addData("heading", drive.pose.heading);
            telemetry.addData("Tele Collector State", "TEST");
            telemetry.update();

            if(toggleMap.get(GAMEPAD_1_A_STATE)){
                robot.lift.setLiftHeight(108);
            } else {
                robot.lift.setLiftHeight(0);
            }
            if (gamepad1.right_trigger > 0.5) {
                robot.collector.setCollectorIn();
                robot.transfer.setTransferIn();
            } else if (gamepad1.left_trigger > 0.5) {
                robot.collector.setCollectorOut();
                robot.transfer.setTransferOut();
            } else {
               robot.collector.setCollectorOff();
               robot.transfer.setTransferOff();
            }

            stickyButtonA.update(gamepad1.a);
            stickyButtonB.update(gamepad1.b);

            if (stickyButtonA.getState()) {
                power += 0.01;
//                robot.lift.levelCounter();
//                robot.lift.updateLevelCounter();
            } else if (stickyButtonB.getState()) {
                power -= 0.01;
            }
            robot.lift.setRawPower(power);
            telemetry.addData("power", power);
            telemetry.addData("lift encoder", robot.lift.getPosition());
            if (gamepad2.x) {
                robot.hanging.setHangingUnwind();
            } else if (gamepad2.y) {
                robot.hanging.setHangingWind();
            }

            if (gamepad2.left_bumper) {
                robot.hanging.setLockState();
            } else if (gamepad2.right_bumper) {
                robot.hanging.setUnlockState();
                }

            if (gamepad1.left_bumper) {
                robot.depositor.setHoldState();
            } else if (gamepad1.right_bumper) {
                robot.depositor.setDropState();
            }
            if (gamepad1.x) {
                robot.depositor.setRestingState();
            } else if (gamepad1.y) {
                robot.depositor.setScoringState();
            }

            robot.update();

        }
    }
    private boolean toggleButton(String buttonStateName, String buttonPressName, boolean buttonState) {
        boolean buttonPressed = toggleMap.get(buttonPressName);
        boolean toggle = toggleMap.get(buttonStateName);

        if (buttonState) {
            if (!buttonPressed) {
                toggleMap.put(buttonStateName, !toggle);
                toggleMap.put(buttonPressName, true);
            }
        } else {
            toggleMap.put(buttonPressName, false);
        }

        return toggleMap.get(buttonStateName);
    }

    private void setButtons() {
        toggleButton(GAMEPAD_1_A_STATE, GAMEPAD_1_A_IS_PRESSED, gamepad1.a);

    }
}

