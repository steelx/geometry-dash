package util;

public class Time {
    public static double timeStarted = System.nanoTime();

    /**
     * getTime in nano seconds
     * **/
    public static float getTime() {
        return (float)((System.nanoTime() - timeStarted) * 1E-9);// E1-9 = 0.000000001
    }
}
