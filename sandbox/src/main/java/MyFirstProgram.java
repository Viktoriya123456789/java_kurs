public class MyFirstProgram {

    //public static void main(String[] args) {

    //    System.out.println("Hello, world!");
// }

    public static void main(String[] args) {
        Point p1 = new Point(5, 5);
        Point p2 = new Point(4, 2);

        System.out.println("Расстояние между двумя точками" + "=" +  distance(p1, p2));

    }



    public static double distance(Point p1, Point p2){
        return Math.sqrt(((p1.x -p1.y)*(p1.x -p1.y))+((p2.x -p2.y)*(p2.x -p2.y)));
}
}