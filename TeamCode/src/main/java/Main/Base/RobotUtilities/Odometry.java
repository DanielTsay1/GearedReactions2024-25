package Main.Base.RobotUtilities;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import java.io.File;

public class Odometry implements Runnable {

    static final double TICKS_PER_REVOLUTION = 8192;
    static final double WHEEL_DIAMETER = 1.45;
    static final double TICKS_PER_INCH = (TICKS_PER_REVOLUTION / (WHEEL_DIAMETER * Math.PI));


    private DcMotor left, right, mid;

    private boolean isRunning = true;

    double rightEncoderPosition = 0, leftEncoderPosition = 0, midEncoderPosition = 0,  changeInRobotOrientation = 0;
    private volatile double robotXPosition = 0, robotYPosition = 0, robotOrientationRadians = 0;
    private double prevRightPosition = 0, prevLeftPosition = 0, prevMidPosition = 0;

    private double robotEncoderWheelDistance;
    private double horizontalEncoderTickPerDegreeOffset;

    private int sleepTime;

    private File wheelBaseSeparationFile = AppUtil.getInstance().getSettingsFile("wheelBaseSeparation.txt");
    private File horizontalTickOffsetFile = AppUtil.getInstance().getSettingsFile("horizontalTickOffset.txt");

    private int verticalLeftEncoderPositionMultiplier = 1;
    private int verticalRightEncoderPositionMultiplier = -1;
    private int normalEncoderPositionMultiplier = -1;



    public Odometry(DcMotor verticalEncoderLeft, DcMotor verticalEncoderRight, DcMotor horizontalEncoder, int threadSleepDelay){
        this.left = verticalEncoderLeft;
        this.right = verticalEncoderRight;
        this.mid = horizontalEncoder;
        sleepTime = threadSleepDelay;

        robotEncoderWheelDistance = Double.parseDouble(ReadWriteFile.readFile(wheelBaseSeparationFile).trim()) * TICKS_PER_INCH;
        this.horizontalEncoderTickPerDegreeOffset = Double.parseDouble(ReadWriteFile.readFile(horizontalTickOffsetFile).trim());

    }


    public Odometry(DcMotor verticalEncoderLeft, DcMotor verticalEncoderRight, DcMotor horizontalEncoder, double worldX, double worldY, int threadSleepDelay){
        this.left = verticalEncoderLeft;
        this.right = verticalEncoderRight;
        this.mid = horizontalEncoder;
        sleepTime = threadSleepDelay;

        this.robotXPosition = worldX;
        this.robotYPosition = worldY;

        robotEncoderWheelDistance = Double.parseDouble(ReadWriteFile.readFile(wheelBaseSeparationFile).trim()) * TICKS_PER_INCH;
        this.horizontalEncoderTickPerDegreeOffset = Double.parseDouble(ReadWriteFile.readFile(horizontalTickOffsetFile).trim());

    }


    private void updatePositions(){
        //Get Current Positions
        leftEncoderPosition = (left.getCurrentPosition() * verticalLeftEncoderPositionMultiplier);
        rightEncoderPosition = (right.getCurrentPosition() * verticalRightEncoderPositionMultiplier);

        double leftChange = leftEncoderPosition - prevLeftPosition;
        double rightChange = rightEncoderPosition - prevRightPosition;

        //Calculate Angle
        changeInRobotOrientation = (leftChange - rightChange) / (robotEncoderWheelDistance);
        robotOrientationRadians = ((robotOrientationRadians + changeInRobotOrientation));

        //Get the components of the motion
        midEncoderPosition = (mid.getCurrentPosition()*normalEncoderPositionMultiplier);
        double rawHorizontalChange = midEncoderPosition - prevMidPosition;
        double horizontalChange = rawHorizontalChange - (changeInRobotOrientation*horizontalEncoderTickPerDegreeOffset);

        double p = ((rightChange + leftChange) / 2);
        double n = horizontalChange;

        //Calculate and update the position values
        robotXPosition = robotXPosition + (p* Math.sin(robotOrientationRadians) + n* Math.cos(robotOrientationRadians));
        robotYPosition = robotYPosition + (p* Math.cos(robotOrientationRadians) - n* Math.sin(robotOrientationRadians));

        prevLeftPosition = leftEncoderPosition;
        prevRightPosition = rightEncoderPosition;
        prevMidPosition = midEncoderPosition;
    }


    private double calculateX(double desiredAngle, double speed) {
        return Math.sin(Math.toRadians(desiredAngle)) * speed;
    }

    /**
     * Calculate the power in the y direction
     * @param desiredAngle angle on the y axis
     * @param speed robot's speed
     * @return the y vector
     */
    private double calculateY(double desiredAngle, double speed) {
        return Math.cos(Math.toRadians(desiredAngle)) * speed;
    }

    public double returnXCoordinate(){ return robotXPosition; }


    public double returnYCoordinate(){ return robotYPosition; }


    public double returnXCoordinateInches(){ return robotXPosition / TICKS_PER_INCH;}


    public double returnYCoordinateInches(){ return robotYPosition / TICKS_PER_INCH;}


    public double returnOrientation(){ return Math.toDegrees(robotOrientationRadians) % 360; }

    public double returnOrientationRadians(){ return robotOrientationRadians; }

    public void stop(){ isRunning = false; }


    @Override
    public void run() {
        while(isRunning) {
            updatePositions();
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
