package EXAMINATION;

public class Text_3 {
    static class Rectangle {
        void calculate_perimeter(double width, double height) {
            double perimeter =2*(width+height);
            System.out.print("周长为 "+perimeter+" ,");
        }

        void calculate_area(double width,double height){
            double area=width*height;
            System.out.print("面积为 "+area);
        }
        public  static void main(String []args){
            Rectangle rectangle = new Rectangle();
            rectangle.calculate_perimeter(3.0,4.0);
            rectangle.calculate_area(3.0,4.0);


        }
    }
}
