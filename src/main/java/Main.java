package main.java;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            App app = new App();
            app.start();
            app.over();
        });
    }
}
