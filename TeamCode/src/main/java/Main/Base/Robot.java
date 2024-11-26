package Main.Base;

//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.dashboard.config.Config;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.HashMap;

import Main.Base.HelperClasses.Button;
import Main.Base.RobotUtilities.Gyro;
import Main.Base.RobotUtilities.Viper;
import Main.Base.RobotUtilities.Viper2;
import Main.Base.RobotUtilities.WheelBase;
import com.qualcomm.robotcore.hardware.Servo;

//@Config
public class Robot extends Hardware{

    public static Gamepad gp;
    public static Gamepad gp2;

//    public Camera camera;
    public Gyro gyro;
    public WheelBase wheelBase;

    public Viper viper;


    public Viper2 viper2;

//    public Intake intake;
//
//    public OpenCvWebcam webcam;
//
//    public BooleanUpdater boolUpdater;
//
//    public Suspension suspender;
//
//    public Drone drone;

    public HashMap<Button, Boolean> buttonChecker = new HashMap<Button, Boolean>();




    @Override
    public void init() {

        gp = gamepad1;
        gp2 = gamepad2;

        //int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        //OpenCvInternalCamera phoneCamera = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        WebcamName webcamName = hardwareMap.get(WebcamName.class, "cam"); // put your camera's name here
//        webcam = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
//        camera = new Camera(webcam);

        imu = hardwareMap.get(BNO055IMU.class, imuName);

        gyro = new Gyro(imu);


        leftFront = hardwareMap.dcMotor.get(leftFrontName);
        leftBack = hardwareMap.dcMotor.get(leftBackName);
        rightBack = hardwareMap.dcMotor.get(rightBackName);
        rightFront = hardwareMap.dcMotor.get(rightFrontName);

        left = hardwareMap.dcMotor.get(leftName);
        right = hardwareMap.dcMotor.get(rightName);
        mid = hardwareMap.dcMotor.get(midName);

        viperMotor = hardwareMap.dcMotor.get(viperName);

        viperMotor2 = hardwareMap.dcMotor.get(viperName2);


        tray = hardwareMap.servo.get(trayName);
        arm = hardwareMap.servo.get(armName);

        //intakeMotor = hardwareMap.dcMotor.get(intakeName);
        //voltageSensor = hardwareMap.voltageSensor.get("Motor Controller 1");

//        linearLeft = hardwareMap.dcMotor.get(leftLinName);
//        linearRight = hardwareMap.dcMotor.get(rightLinName);
//        hookLeft = hardwareMap.servo.get(hookLeftName);
//        hookRight = hardwareMap.servo.get(hookRightName);
//        droneServo = hardwareMap.servo.get(droneServoName);
        clawLeft= hardwareMap.servo.get(clawLeftName);
        clawRight= hardwareMap.servo.get(clawRightName);

        wheelBase = new WheelBase(leftFront, leftBack, rightFront, rightBack);
        viper = new Viper(viperMotor);
        viper2 = new Viper2(viperMotor2, arm, tray, clawLeft, clawRight);

        //intake = new Intake(intakeMotor);
//        suspender = new Suspension(linearLeft, linearRight,hookLeft,hookRight);
//        drone = new Drone(droneServo);
//
//        leftClawSensor = hardwareMap.get(DistanceSensor.class, "leftClaw");
//        rightClawSensor = hardwareMap.get(DistanceSensor.class, "rightClaw");
//        ledDriver = hardwareMap.get(RevBlinkinLedDriver.class, blinkenName);
//
//        tray.setPosition(0.14);
        clawRight.setPosition(0.075);
        clawLeft.setPosition(0.94);
        tray.setPosition(0.55);
//        droneServo.setPosition(0.3);
//        hookLeft.setPosition(0.1);
//        hookRight.setPosition(0.5);




        //leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //leftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //rightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

}
