package Main.Base;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;



import Main.Base.Hardware;

import Main.Base.Robot;


import Main.Base.RobotUtilities.Gyro;

import Main.Base.RobotUtilities.Odometry;

import Main.Base.RobotUtilities.Viper;
import Main.Base.RobotUtilities.WheelBase;
import Main.Base.RobotUtilities.Viper2;

public abstract class AutoRobot2 extends Hardware {


//    public Camera camera;
//    public BlueCamera cameraBlue;
//    public OpenCvWebcam webcam;
    //public Camera webcam;
    public Gyro gyro;
    public WheelBase wheelBase;
    public Odometry odometry;
    public Viper viper;
//    public Intake intake;
//    public LinearLR linearLR;
//    public Telemetry telemetry;
    public Viper2 viper2;


    public Thread positionThread;

    protected Thread autoThread = new Thread(new Runnable() {
        @Override
        public void run() {
            runAutonomous();
        }
    });


    @Override
    public void init() {


//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        OpenCvInternalCamera phoneCamera = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
//
//        camera = new Camera(phoneCamera);
//
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        WebcamName webcamName = hardwareMap.get(WebcamName.class, "cam"); // put your camera's name here
//        webcam = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
//        camera = new Camera(webcam);
//
//        CameraName camName = hardwareMap.get(WebcamName.class, "cam");
//        cameraBlue = new BlueCamera(camName);

        imu = hardwareMap.get(BNO055IMU.class, imuName);

        gyro = new Gyro(imu);


        leftFront = hardwareMap.dcMotor.get(leftFrontName);
        leftBack = hardwareMap.dcMotor.get(leftBackName);
        rightBack = hardwareMap.dcMotor.get(rightBackName);
        rightFront = hardwareMap.dcMotor.get(rightFrontName);



        wheelBase = new WheelBase(leftFront, leftBack, rightFront, rightBack);

        left = hardwareMap.dcMotor.get(leftName);
        right = hardwareMap.dcMotor.get(rightName);
        mid = hardwareMap.dcMotor.get(midName);

        viperMotor2 = hardwareMap.dcMotor.get(viperName2);
        viperMotor = hardwareMap.dcMotor.get(viperName);

        arm = hardwareMap.servo.get(armName);
        tray = hardwareMap.servo.get(trayName);
//        intakeMotor = hardwareMap.dcMotor.get(intakeName);
        clawLeft= hardwareMap.servo.get(clawLeftName);
        clawRight= hardwareMap.servo.get(clawRightName);
//
        odometry = new Odometry(left, right, mid,  50);
        positionThread = new Thread(odometry);
        positionThread.start();


        //camera.startStreaming();
//        cameraBlue.start();
//        telemetry.clearAll();
        viper = new Viper(viperMotor);
        viper2 = new Viper2(viperMotor2, arm, tray, clawLeft, clawRight);
//
//        viper2 = new Viper(viperMotor, tray, clawLeft, clawRight);

//        intake = new Intake(intakeMotor);
//
        viperMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viperMotor.setTargetPosition(0);
        viperMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        viperMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viperMotor2.setTargetPosition(0);
        viperMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//        droneServo.setPosition(0.5);
//        hookLeft.setPosition(0.2);
//        hookRight.setPosition(0.8);

    }

/*
    @Override
    public void init_loop() {
        telemetry.addData("Blue Team Element: ", cameraBlue.detect());
        telemetry.addData("Blue X: ", cameraBlue.x());
//        telemetry.addData("camera",camera.getAvg1());
//        telemetry.addData("location",camera.detectSignal());
        telemetry.update();
    }*/


    @Override
    public void start() {
        autoThread.start();
    }


    @Override
    public void loop() {

    }


    @Override
    public void stop() {
        try {
            autoThread.join();
            autoThread.interrupt();
        } catch (Exception e) {
            telemetry.addData("ENCOUNTERED AN EXCEPTION", e);
        }

    }


    protected void pause(long millis) {
        long maxTime;
        maxTime = System.currentTimeMillis() + millis;
        while (System.currentTimeMillis() < maxTime && !autoThread.isInterrupted()) {
        }
    }


    public abstract void runAutonomous();

}
