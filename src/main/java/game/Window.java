package game;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.*;

import javax.swing.JFrame;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window extends JFrame implements Runnable {
    private final int width, height;
    private final String title;
    public static Window window = null;
    private long glfwWindow;

    public Window() {
        this.width = 800;
        this.height = 640;
        this.title = "Geometry -> Dash";
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

        init();
        loop();

        // Free memory
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // Free error callbacks
        glfwTerminate();
        glfwSetErrorCallback(null).free();
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
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if ( glfwWindow == NULL ) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Mouse Listener
        glfwSetCursorPosCallback(glfwWindow, MouseListener::cursorPosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::scrollCallback);

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);

        /*
        // Get the thread stack and push a new frame
		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(glfwWindow, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				glfwWindow,
				(vidMode.width() - pWidth.get(0)) / 2,
				(vidMode.height() - pHeight.get(0)) / 2
			);
		} // the stack frame is popped automatically
        **/

        // Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(glfwWindow);
    }

    public void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(glfwWindow)) {
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            update();

            glfwSwapBuffers(glfwWindow); // swap the color buffers
        }
    }

    public void update() {
        // test Key listeners
        if (KeyListener.isKeyPressed(GLFW_KEY_SPACE)) {
            System.out.println("Space key was pressed!");
        }
    }
}
