package Main.Base.Odometry.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous
public class firstOpMode extends OpMode {

    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backRight;
    private DcMotor backLeft;

    String fR = "fR";
    String fL = "fL";
    String bR = "bR";
    String bL = "bL";

    @Override
    public void init() {
        frontRight = hardwareMap.dcMotor.get(fR);
        frontLeft = hardwareMap.dcMotor.get(fL);
        backRight = hardwareMap.dcMotor.get(bR);
        backLeft = hardwareMap.dcMotor.get(bL);

    }

    public void start() {

//        try {
//            wait(3000);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        frontRight.setPower(0);
//        frontLeft.setPower(0);
//        backRight.setPower(0);
//        backLeft.setPower(0);
        frontRight.setPower(0.3);
        frontLeft.setPower(-0.3);
        backRight.setPower(0.3);
        backLeft.setPower(-0.3);

    }

    @Override
    public void loop() {

    }

    public void stop() {

    }
}
