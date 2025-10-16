package engineTester;

import renderEngine.DisplayManager;

public class MainGameLoop {
    public static void main(String[] args) {
        DisplayManager.createDisplay();

        while (!DisplayManager.isCloseRequested()) {
            DisplayManager.updateDisplay();
        }

        DisplayManager.closeDisplay();
    }
}
