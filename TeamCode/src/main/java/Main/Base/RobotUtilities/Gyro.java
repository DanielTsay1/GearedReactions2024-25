package Main.Base.RobotUtilities;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static java.lang.Math.PI;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class Gyro {

    private BNO055IMU imu;
    private BNO055IMU.Parameters imuParameters;
    private Orientation angles;

    public Gyro(BNO055IMU IMU) {
        imu = IMU;

        BNO055IMU.Parameters imuParameters = new  BNO055IMU.Parameters();

        // Use degrees as angle unit.
        imuParameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(imuParameters);
    }

    public double getHeading(){
        double heading;
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        heading = angles.firstAngle;
        return -heading;
    }

    public double getHeadingRadians(){
        double heading;
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
        heading = angles.firstAngle;
        return -heading;
    }

    public double angleWrapDegrees(double angle) {
        while (angle > 360) {
            angle -= 360;
        } while(angle < 0){
            angle += 360;
        }

        return angle;
    }


    public double angleWrapRadians(double angle) {
        while (angle > 2 * PI ) {
            angle -= 2 * PI;
        } while(angle < 0){
            angle += 2 * PI;
        }

        return angle;
    }

    public void displayHeading() {
        telemetry.addData("Heading: ", getHeading());
    }

}
