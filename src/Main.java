import days.Day23;

public class Main {

    public static void main(String[] args) {
        Day23 temp = new Day23("219347865", 9);
        //Day23 temp = new Day23("389125467");
        temp.playGame(100, 1);
        Day23 temp2 = new Day23("219347865", 1000000);
        temp2.playGame(10000000, 2);
    }
}
