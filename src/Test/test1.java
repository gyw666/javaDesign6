package Test;
import fanType.Fan1;

public class test1 {
    public static void main(String[] args) {
        Fan1 fan1 = new Fan1();
        fan1.setSpeed(Fan1.FAST);
        fan1.setRadius(10);
        fan1.setColor("yellow");
        fan1.setOn(true);
        System.out.println(fan1.toString());
    }
}
