package java;
public class DataGenerator {

    public static void generateDummyData() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Dummy data " + i);
        }
    }

    public static void generateRandomNumbers() {
        for (int i = 0; i < 500; i++) {
            int num = (int)(Math.random() * 1000);
            System.out.println("Random number: " + num);
        }
    }
}