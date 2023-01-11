package Text_interface;


public class Threat_Work {
    public static class Speakelephant extends Thread {
        public void run() {
            for (int i = 1; i <= 20; i++) {
                System.out.print("大象" + i + " ");
            }
            System.out.print("");
        }
    }

    public static class SpeakCar extends Thread {
        public void run() {
            for (int i = 1; i <= 20; i++) {
                System.out.print("轿车" + i + " ");
            }
        }
    }

    public static void main(String []args) {
        Speakelephant speakelephant = new Speakelephant();
        SpeakCar speakcar = new SpeakCar();
        speakelephant.start();
        speakcar.start();
    }
}