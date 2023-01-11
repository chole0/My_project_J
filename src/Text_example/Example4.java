package Text_example;

public class Example4 {
    abstract class MotorVehicles{
        abstract void brake();
    }

    interface MoneyFare{
        void charge();
    }

    interface ControlTemperature{
        void controlAirTemperature();
    }

    class Bus extends MotorVehicles implements MoneyFare{


        @Override
        void brake() {
            System.out.println("公共汽车使用进制砂车技术");
        }

        @Override
        public void charge() {
            System.out.println("公共汽车：一块钱");
        }
    }

    class Taxi extends MotorVehicles implements MoneyFare,ControlTemperature{

        @Override
        void brake() {
            System.out.println("出租车使用。。");

        }

        @Override
        public void charge() {
            System.out.println("99yuan ");

        }

        @Override
        public void controlAirTemperature() {
            System.out.println("桉树在那个kinf");

        }
    }

    public static void main(String []args){
        
    }
}
