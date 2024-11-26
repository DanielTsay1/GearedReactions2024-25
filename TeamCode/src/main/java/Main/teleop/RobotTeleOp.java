package Main.teleop;
//THERE ARE ERRORS BUT IT STILL WORKS DON"T MESS WITH THEM LIKE I DID. YOU'LL REGRET IT.
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import Main.Base.Robot;


//@Config
@TeleOp(name = "TeleOp", group = "competition")
public class RobotTeleOp extends Robot {

    //why is reverse true?
    //the prevailing theory is sOMeOnE left it true and then manually edited the values negative in WheelBase LMAO
    boolean slomo = false, reverse = true;

    boolean FOD = true;


    double reset = 0.0;

    boolean lastgpDPad_Left = false;
    boolean lastgp2A = false;
    boolean lastgp2B = false;
    boolean lastgp2X = false;
    boolean lastgp2Y = false;
    boolean lastgpA = false;
    boolean lastgpB = false;
    boolean lastgpX = false;
    boolean lastgpY = false;

    boolean autoClawLeft = false;
    boolean autoClawRight = false;

    boolean endgame = false;
    final double ENDGAME = 60.0;
    ElapsedTime runtime = new ElapsedTime();

    double rightClawClose = 0.83;
    double leftClawClose = 0.3;

    double rightClawOpen = 0.75;
    double leftClawOpen = 0.5;


    @Override
    public void init() {
        super.init();

    }

    public void start() {

    }

    @Override
    public void loop() {

        if (FOD) wheelBase.fieldOrientatedDrive(gp, gyro, slomo, reverse, reset);
        else wheelBase.mecanumDrive(gp.left_stick_x, gp.left_stick_y, gp.right_stick_x, slomo);

        if (gp.ps) reset = gyro.getHeadingRadians();


        if (gp.start) FOD = !FOD;

        if (gp.right_trigger > 0.1) viper2.power(gp.right_trigger);
        else if (gp.left_trigger > 0.1) viper2.power(-gp.left_trigger);
        else viper2.powerOff();

        if (gp2.right_trigger > 0.1) viper.power(-gp2.right_trigger);
        else if (gp2.left_trigger > 0.1) viper.power(gp2.left_trigger);
        else viper.powerOff();

        boolean viperOn = false;


        if (gp.x && !lastgpX) slomo = !slomo;
        lastgpX = gp.x;

//        if(gp.y) intake.intakeOn(-1.0);
//        else if (gp.a) intake.intakeOn(1.0);
//        else intake.stop();
//
//        lastgpY = gp.y;
//
//        if (gp2.dpad_up) {
//            tray.setPosition(0.0);
//        }
//        if (gp2.dpad_down) {
//            tray.setPosition(0.13);
//        }
//        if (gp2.dpad_left || gp2.dpad_right) {
//            tray.setPosition(0.6);
//        }
////
//        if (gp.dpad_up) {
//            if (clawRight.getPosition() > 0.77) {
//                viper2.clawOpen();
//                clawRight.setPosition(rightClawOpen);
//                clawLeft.setPosition(leftClawOpen);
//                autoClawRight = false;
//
//                autoClawLeft = false;
//            } else {
////                viper.clawClose();
//                clawRight.setPosition(0.0);
//                clawLeft.setPosition(0.5);
//            }
//
//        }
////
//        if (gp.dpad_down) {
////            viper.clawClose();
//            clawRight.setPosition(rightClawClose);
//            clawLeft.setPosition(leftClawClose);
//        }
        if (gp.dpad_right) {
            clawRight.setPosition(0.15);
            clawLeft.setPosition(0.8);
        }

        if (gp.dpad_up) {
            viper2.clawClose();
        }
        if (gp.dpad_down) {
            viper2.clawOpen();
        }
        if (gp.x) {
            if (tray.getPosition() <= 0.7) {
                arm.setPosition(0.20);
            }
        }
        if (gp.a) {
            if (tray.getPosition() <= 0.7) {
                arm.setPosition(0.43);
            }
        }
        if (gp.y) {
            if (tray.getPosition() <= 0.7) {
                arm.setPosition(0.01);
            }
        }
//        if(gp.dpad_right){
//            viper2.extend(true, 1350);
////            wheelBase.goToPosition(odometry,gyro,telemetry,35,-6,128,0.5,3);
//
//            tray.setPosition(0.13);
//
//            viper2.clawOpen();
//
//            tray.setPosition(.35);
//
//            viper2.extend(true, 10);
//            tray.setPosition(.7);
//        }

        if (gp2.x) { //square
            viper2.clawClose();
        }
        if (gp2.a) { //x
            tray.setPosition(0.0);
        }
//            tray.setPosition(0.2);
//            tray.setPosition(0.35);

//        }
//        if(gp2.y){ //triangle
//            tray.setPosition(0.2);
//        }

            if (gp2.dpad_right) {
                tray.setPosition(0.96);

            }
            if (gp2.dpad_down) {
                tray.setPosition(0.35);
            }
            if (gp2.dpad_left) {
                tray.setPosition(0.2);

            }
            if (gp2.dpad_up) {
                tray.setPosition(0.7);
            }


//        if (gp.dpad_left){
//            clawRight.setPosition(0.89);
//            clawLeft.setPosition(0.43);
//        }
//        if(gp.dpad_right){
//            clawRight.setPosition(0.84);
//            clawLeft.setPosition(0.38);
//        }
////
//        if (gp2.left_stick_x < -0.2 && gp2.right_stick_x > 0.2){ //open
//            clawRight.setPosition(rightClawOpen);
//            clawLeft.setPosition(leftClawOpen);
//        }
//        if (gp2.left_stick_x > 0.2 && gp2.right_stick_x < -0.2){ //close
//            clawRight.setPosition(rightClawClose);
//            clawLeft.setPosition(leftClawClose);
//        }
//        if (gp2.left_stick_x < -0.2 && gp2.right_stick_x < -0.2){ //grab left
//            clawRight.setPosition(0.89);
//            clawLeft.setPosition(0.44);
//        }
//        if (gp2.left_stick_x > 0.2 && gp2.right_stick_x > 0.2){ //grab right
//            clawRight.setPosition(0.83);
//            clawLeft.setPosition(0.38);
//        }
//
//        gp.rumble(gp.left_trigger, gp.right_trigger, Gamepad.RUMBLE_DURATION_CONTINUOUS);
//        if (runtime.seconds() > ENDGAME && !endgame) {
//            gp.rumble(1000);
//            endgame = true;
//        }
//
//        if(gp.left_bumper) {
//            linearLeft.setPower(1.0);
//        }
//        else if(gp.right_bumper) {
//            linearLeft.setPower(-1.0);
//        } else {
//            linearLeft.setPower(0.0);
//        }
//
//        if (gp2.left_bumper) {
//            linearRight.setPower(1.0);
//        } else if (gp2.left_trigger > 0.1) {
//            linearRight.setPower(-1.0);
//        } else {
//            linearRight.setPower(0.0);
//        }
//
//        if (gp2.right_bumper) {
//            linearLeft.setPower(1.0);
//        } else if (gp2.right_trigger > 0.1) {
//            linearLeft.setPower(-1.0);
//        } else {
//            linearLeft.setPower(0.0);
//        }
//
//        if(gp2.a) {
//            hookLeft.setPosition(0.45);
//            hookRight.setPosition(0.2);
//        }
//
//        if(gp2.y) {
//                drone.fire(0.67);
//        }

//        if(leftClawSensor.getDistance(DistanceUnit.CM) <= 5.8 && !autoClawLeft) {
//            clawLeft.setPosition(leftClawClose);
//
//            autoClawLeft = true;
//
//        }
//
//        if(rightClawSensor.getDistance(DistanceUnit.CM) <= 6.8 && !autoClawRight) {
//            clawRight.setPosition(rightClawClose);
//
//            autoClawRight = true;
//        }

//        if(!autoClawLeft || !autoClawRight) {
//            ledDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.DARK_RED);
//        } else {
//            ledDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
//        }
//
//        if(gp2.b){
//            drone.fire(0.0);
//        }


//        telemetry.addData("viper2: ",viperMotor2.getCurrentPosition());
//        telemetry.addData("viper: ",viperMotor.getCurrentPosition());
//        telemetry.addData("tray: ",tray.getPosition());
////        telemetry.addData("arm: ",arm.getPosition());
//
////        telemetry.addData("righthook: ",hookRight.getPosition());
////        telemetry.addData("lefthook: ",hookLeft.getPosition());
////        telemetry.addData("right: ",rightClawSensor.getDistance(DistanceUnit.CM));
////        telemetry.addData("left: ",leftClawSensor.getDistance(DistanceUnit.CM));
//        telemetry.update();
//


        }


    }

