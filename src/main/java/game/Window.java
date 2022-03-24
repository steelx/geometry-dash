package game;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import util.Time;

import javax.swing.JFrame;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window extends JFrame implements Runnable {
    public MouseListener mouseListener;
    private boolean isRunning = true;
    private final int width, height;
    private final String title;
    public static Window window = null;

    public Window() {
        this.width = 800;
        this.height = 640;
        this.title = "Geometry -> Dash";
//        this.setSize(800, 640);
//        this.setTitle("Geometry -> Dash");
//        this.setResizable(false);
//        this.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setLocationRelativeTo(null);// centered window
//        this.mouseListener = new MouseListener();
    }

    public static Window get() {
        if (Window.window == null) {
            window = new Window();
        }

        return Window.window;
    }

    @Override
    public void run() {
        System.out.println("LWJGL version " + Version.getVersion());
        
        double lastFrameTime = 0.0;
        try {
            while (isRunning) {
                double time = Time.getTime();
                double deltaTime = time - lastFrameTime;
                lastFrameTime = time;
                update(deltaTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
        // error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // init GLFW
        if(!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW!");
        }

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        long glfwWindow = glfwCreateWindow(this.width, this.height, "Hello World!", NULL, NULL);
        if ( glfwWindow == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(glfwWindow, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });
    }

    public void loop() {

    }

    public void update(double dt) {
        System.out.println(mouseListener.mousePressed);
        if (mouseListener.mousePressed) {
            System.out.println("mouse pressed");
        }
    }
}
