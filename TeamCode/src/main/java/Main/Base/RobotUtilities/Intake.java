package Main.Base.RobotUtilities;
import com.qualcomm.robotcore.hardware.DcMotor;



public class Intake {

    DcMotor intakeMotor;


    public Intake(DcMotor intakeMotor) {
        this.intakeMotor = intakeMotor;
    }
    public void intakeOn(double num){
        intakeMotor.setPower(num);
    }

    public void stop() {
        intakeMotor.setPower(0.0);
    }

}
