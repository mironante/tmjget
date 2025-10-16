package renderEngine;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.opengl.GL.createCapabilities;

public class DisplayManager {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    private static final int FPS_CAP = 120;

    static long window;

    public static void createDisplay() {
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will be resizable
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        glfwWindowHint(GLFW_REFRESH_RATE, FPS_CAP);

        window = glfwCreateWindow(WIDTH, HEIGHT, "Game Engine", MemoryUtil.NULL, MemoryUtil.NULL);
        if (window == MemoryUtil.NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwMakeContextCurrent(window);

        createCapabilities();

        // Enable v-sync
        //glfwSwapInterval(1);

        IntBuffer width = BufferUtils.createIntBuffer(1),
                height = BufferUtils.createIntBuffer(1);
        glfwGetFramebufferSize(window, width, height);
        GL11.glViewport(0, 0, width.get(), height.get());

        glfwShowWindow(window);
    }

    public static void updateDisplay() {
        glfwPollEvents();
        glfwSwapBuffers(window);
    }

    public static void closeDisplay() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public static boolean isCloseRequested() {
        return glfwWindowShouldClose(window);
    }
}
