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

   public double distance (Point d){
       return Math.sqrt(((d.x -x)*(d.x -x))+((d.y -y)*(d.y -y)));
   }

}
