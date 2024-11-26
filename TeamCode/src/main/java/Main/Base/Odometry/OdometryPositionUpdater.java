package Main.Base.Odometry;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import java.io.File;

import Main.Base.RobotUtilities.Gyro;

public class OdometryPositionUpdater implements Runnable{

    //Odometry wheels
    private DcMotor left, right, mid;
    private Gyro gyro;

    final double TICKS_PER_REV = 8192;
    final double WHEEL_DIAMETER = 38/25.4;

    final double TICKS_PER_INCH = TICKS_PER_REV / (Math.PI * WHEEL_DIAMETER);

    //Thead run condition
    private boolean isRunning = true;

    //Position variables used for storage and calculations
    private double rightPosition = 0, leftPosition = 0, midPosition = 0,  changeInRobotOrientation = 0;
    private volatile double robotXPosition = 0, robotYPosition = 0, robotOrientationRadians = 0;
    private double previousRightPosition = 0, previousLeftPosition = 0, previousMidPosition = 0, previousOrientationRadians = 0;

    //Algorithm constants
    private double robotEncoderWheelDistance;
    private double horizontalEncoderTickPerDegreeOffset;

    //Sleep time interval (milliseconds) for the position update thread
    private int sleepTime;

    //Files to access the algorithm constants
    private File wheelBaseSeparationFile = AppUtil.getInstance().getSettingsFile("wheelBaseSeparation.txt");
    private File horizontalTickOffsetFile = AppUtil.getInstance().getSettingsFile("horizontalTickOffset.txt");

    //Change Multipliers to reverse Encoders
    private int leftPositionMultiplier = -1;
    private int rightPositionMultiplier = 1;
    private int midPositionMultiplier = 1;

    /**
     * Constructor for GlobalCoordinatePosition Thread
     * @param verticalEncoderLeft left odometry encoder, facing the vertical direction
     * @param verticalEncoderRight right odometry encoder, facing the vertical direction
     * @param horizontalEncoder horizontal odometry encoder, perpendicular to the other two odometry encoder wheels
     * @param threadSleepDelay delay in milliseconds for the GlobalPositionUpdate thread (50-75 milliseconds is suggested)
     */
    public OdometryPositionUpdater(DcMotor verticalEncoderLeft, DcMotor verticalEncoderRight, DcMotor horizontalEncoder, Gyro robotGyro, int threadSleepDelay){
        this.left = verticalEncoderLeft;
        this.right = verticalEncoderRight;
        this.mid = horizontalEncoder;
        this.gyro = robotGyro;
        sleepTime = threadSleepDelay;

        robotEncoderWheelDistance = Double.parseDouble(ReadWriteFile.readFile(wheelBaseSeparationFile).trim()) * TICKS_PER_INCH;
        this.horizontalEncoderTickPerDegreeOffset = Double.parseDouble(ReadWriteFile.readFile(horizontalTickOffsetFile).trim());

    }

    public OdometryPositionUpdater(DcMotor verticalEncoderLeft, DcMotor verticalEncoderRight, DcMotor horizontalEncoder,  int threadSleepDelay){
        this.left = verticalEncoderLeft;
        this.right = verticalEncoderRight;
        this.mid = horizontalEncoder;
        
        sleepTime = threadSleepDelay;

        robotEncoderWheelDistance = Double.parseDouble(ReadWriteFile.readFile(wheelBaseSeparationFile).trim()) * TICKS_PER_INCH;
        this.horizontalEncoderTickPerDegreeOffset = Double.parseDouble(ReadWriteFile.readFile(horizontalTickOffsetFile).trim());

    }

    /**
     * Updates the global (x, y, theta) coordinate position of the robot using the odometry encoders
     */
    private void globalCoordinatePositionUpdate(){
        //Get Current Positions
        leftPosition = (left.getCurrentPosition() * leftPositionMultiplier);
        rightPosition = (right.getCurrentPosition() * rightPositionMultiplier);

        double leftChange = leftPosition - previousLeftPosition;
        double rightChange = rightPosition - previousRightPosition;

        //Calculate Angle
//        changeInRobotOrientation = (leftChange - rightChange) / (robotEncoderWheelDistance);
//        robotOrientationRadians = ((robotOrientationRadians + changeInRobotOrientation));

        robotOrientationRadians = gyro.getHeadingRadians();
        changeInRobotOrientation = robotOrientationRadians - previousOrientationRadians;

        //Get the components of the motion
        midPosition = (mid.getCurrentPosition()* midPositionMultiplier);
        double rawHorizontalChange = midPosition - previousMidPosition;
        double horizontalChange = rawHorizontalChange - (changeInRobotOrientation*horizontalEncoderTickPerDegreeOffset);

        double p = ((rightChange + leftChange) / 2);
        double n = horizontalChange;

        //Calculate and update the position values
        robotXPosition = robotXPosition + (p* Math.sin(robotOrientationRadians) + n* Math.cos(robotOrientationRadians));
        robotYPosition = robotYPosition + (p* Math.cos(robotOrientationRadians) - n* Math.sin(robotOrientationRadians));

        previousLeftPosition = leftPosition;
        previousRightPosition = rightPosition;
        previousOrientationRadians = robotOrientationRadians;
        previousMidPosition = midPosition;
    }


    /**
     * Returns the robot's global x coordinate
     * @return global x coordinate
     */
    public double returnXCoordinate(){ return robotXPosition; }


    /**
     * Returns the robot's global y coordinate
     * @return global y coordinate
     */
    public double returnYCoordinate(){ return robotYPosition; }


    /**
     * Returns the robot's global orientation
     * @return global orientation, in degrees
     */
    public double returnOrientation(){ return Math.toDegrees(robotOrientationRadians) % 360; }


    /**
     * Stops the position update thread
     */
    public void stop(){ isRunning = false; }


    /**
     * Runs the thread
     */
    @Override
    public void run() {
        while(isRunning) {
            telemetry.addData("LLLLLL: ", isRunning);
            telemetry.update();
            globalCoordinatePositionUpdate();
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
