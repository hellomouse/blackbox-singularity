package blackbox.game.util;

public final class Random {
    public static int randInt(int a, int b) {
        return b + (int)(Math.random() * (b-a));
    }

    public static String choice(String[] arr) {
        return arr[randInt(0, arr.length)];
    }
}
