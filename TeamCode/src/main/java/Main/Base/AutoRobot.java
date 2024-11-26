package Main.Base;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.openftc.easyopencv.OpenCvWebcam;

//import Main.Base.RobotUtilities.Camera;
import Main.Base.RobotUtilities.Gyro;
import Main.Base.RobotUtilities.Odometry;
//import Main.Base.RobotUtilities.RedCamera;
import Main.Base.RobotUtilities.Viper;
import Main.Base.RobotUtilities.Viper2;
import Main.Base.RobotUtilities.WheelBase;

public abstract class AutoRobot extends Hardware {



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
   // public Arm arm;
    public Thread positionThread;


    public int[] tags = new int[6];

    protected Thread autoThread = new Thread(new Runnable() {
        @Override
        public void run() {
            runAutonomous();
        }
    });


    @Override
    public void init() {

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


//    @Override
//    public void init_loop() {
//        tags = cameraRed.april();
//        telemetry.addData("Red Team Element: ", cameraRed.detect());
//        telemetry.addData("Red X: ", cameraRed.x());
//
//        for(int i = 0; i < 6; i++) {
//            int tagNum = i+1;
//            telemetry.addData("April Tags " + tagNum + ": ", tags[i]);
//        }
//        telemetry.addData("Red Align: ", cameraRed.checkRight(tags));
//        telemetry.update();
//    }


    @Override
    public void start() {
        autoThread.start();
    }


//    @Override
//    public void loop() {
//        tags = cameraRed.april();
//        for(int i = 0; i < 6; i++) {
//            int tagNum = i+1;
//            telemetry.addData("April Tags " + tagNum + ": ", tags[i]);
//        }
//        telemetry.addData("Red Align: ", cameraRed.checkRight(tags));
//        telemetry.update();
//    }


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
