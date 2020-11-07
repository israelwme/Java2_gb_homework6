public class Main {
    public static void main(String[] args) {
        new Thread(() -> new GUI("localhost", 18443, 18444)).start();
        new Thread(() -> new GUI("localhost", 18444, 18443)).start();
    }
}