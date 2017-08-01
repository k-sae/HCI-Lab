package ShapesRecognizer;

/**
 * Created by kareem on 7/6/17.
 */
public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

   public double distance(Point other) {
        return dist(x, y, other.x, other.y);
    }
    public double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1- x2, 2) + Math.pow(y1 - y2, 2));
    }
}