import game.Window;

public class Main {
    public static void main(String[] args) {
        Window window = Window.get();
        window.run();

        Thread mainThread = new Thread(window);
        mainThread.start();
    }
}
