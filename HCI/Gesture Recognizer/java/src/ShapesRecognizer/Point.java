package ShapesRecognizer;

/**
 * Created by kareem on 7/6/17.
 */
public class Point {
    double X;
    double Y;

    public Point(double x, double y) {
        X = x;
        Y = y;
    }

   public double distance(Point other) {
        return dist(X, Y, other.X, other.Y);
    }
    public double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1- x2, 2) + Math.pow(y1 - y2, 2));
    }
}