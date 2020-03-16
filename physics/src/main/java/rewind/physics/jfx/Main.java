package rewind.physics.jfx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX Application to test the interaction and to also demonstrate the physics engine.
 */

public class Main extends Application {
    private Render demo = new Render();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(demo.createContent());
        stage.setTitle("Demo");
        stage.setResizable(false);
        stage.setFullScreen(false);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                demo.particleDraw();
                demo.update();
            }
        };

        timer.start();
        stage.setScene(scene);
        stage.show();
    }


}
