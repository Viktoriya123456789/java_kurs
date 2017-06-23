/**
 * Created by admin on 22.06.2017.
 */
public class Point {

    public double x;
    public double y;


    public Point (double a, double b){
        this.x = a;
        this.y = b;
    }

   public double distance (Point p){
       return Math.sqrt(((p.x -x)*(p.x -x))+((p.y -y)*(p.y -y)));
   }

}
