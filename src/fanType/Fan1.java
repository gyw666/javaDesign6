package fanType;

public class Fan1 implements Comparable<Fan1>{
    public static final int SLOW = 1;
    public static final int MEDIUM = 2;
    public static final int FAST = 3;

    private int speed;
    private boolean on;
    private String color;
    private double radius;


    public Fan1() {
        speed = SLOW;
        on = false;
        color = "blue";
    }


    public Fan1(int speed, boolean on, String color, double radius) {
        this.speed = speed;
        this.on = on;
        this.color = color;
        this.radius = radius;
    }

    /**
     * 获取
     * @return speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * 设置
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * 获取
     * @return on
     */
    public boolean isOn() {
        return on;
    }

    /**
     * 设置
     * @param on
     */
    public void setOn(boolean on) {
        this.on = on;
    }

    /**
     * 获取
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * 设置
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * 获取
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * 设置
     * @param radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String toString() {
        return "fan1{ speed = " + speed + ", on = " + on + ", color = " + color + ", radius = " + radius + "}";
    }

    @Override
    public int compareTo(Fan1 o) {
        return Double.compare(this.radius, o.radius);
    }
}