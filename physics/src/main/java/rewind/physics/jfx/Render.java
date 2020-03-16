package rewind.physics.jfx;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import rewind.physics.PVector;
import rewind.physics.Particles.Particle;
import rewind.physics.Particles.ParticleCreation;
import rewind.physics.Particles.ParticleSpawn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


class Render {
    private PVector spawn = new PVector(10, 0);
    private BorderPane root = new BorderPane();
    private Pane center = new Pane();
    private VBox controls = new VBox();
    private PVector gravity = new PVector(0, 0.1);
    private PVector wind = new PVector(0, 0);
    private List<Sprite> sprites = new ArrayList<>();
    private ParticleSpawn particleSpawn = new ParticleCreation();
    private List<Particle> particles = new ArrayList<>();
    private GraphicsContext gc;
    private int xPart = 1;
    private int yPart = 1;
    private String particleType;
    private String renderType;
    private double rotateAngle = 0;
    private Rectangle water;
    private boolean checkWater = false;
    private Canvas part;
    private Canvas gravCanvas = new Canvas(758, 600);

    /**
     * Spawn a sprite to apply gravity and wind to.
     */

    private void spawnSprite() {
        Sprite sprite = new Sprite(spawn, Color.color(Math.random(), Math.random(), Math.random()), (Math.random() * ((5.0 - 0.1) + 1)) + 0.1);
        sprites.add(sprite);
        sprite.applyForce(sprite.applyFriction(sprite.getVelocity(), 0.01));
        sprite.applyForce(gravity);
        sprite.applyForce(wind);
        sprite.draw(gravCanvas.getGraphicsContext2D());
    }

    private VBox UI() {
        controls.setPadding(new Insets(10));
        controls.setSpacing(8);
        Text title = new Text("Controls");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        controls.getChildren().add(title);
        Button spawnSprite = new Button("Sprite");
        Button addGrav = new Button("Gravity +");
        Button subGrav = new Button("Gravity -");
        Button resGrav = new Button("Reset Gravity");
        Button addWind = new Button("Add Wind");
        Button resetWind = new Button("Reset Wind");
        Button addWater = new Button("Water");
        Button particlesAll = new Button("Screen Particles");
        Button particlesLoc = new Button("Add Particles");
        Button resetParticles = new Button("Reset Particles");
        Button reset = new Button("Reset");
        ComboBox partType = new ComboBox();
        partType.getItems().addAll("Ovals", "Arcs", "Rectangles", "Rain", "Snow", "Meteors", "cherryBlossom");
        partType.getSelectionModel().selectLast();
        TextField xLoc = new TextField("50");
        TextField yLoc = new TextField("50");
        xLoc.setPrefWidth(20);
        yLoc.setPrefWidth(20);
        reset.setPrefSize(100, 50); // Set the size of the reset button.
        //Add all buttons here once you create one! Order in here is the order drawn.
        controls.getChildren().addAll(spawnSprite, addGrav, subGrav, resGrav, addWind, resetWind, addWater, particlesAll,
                partType, xLoc, yLoc, particlesLoc, resetParticles, reset);
        controls.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        spawnSprite.setOnAction((ActionEvent e) -> spawnSprite()); // Spawn the sprite at the spawn vector(Need to change)

        // Adding water. Add other things to the center pane like this!
        addWater.setOnAction(((ActionEvent e) -> {
            if (!checkWater) { // checks to see if the water is already spawned or not.
                water = new Rectangle(0, 400, 766, 250);
                water.setFill(Color.BLUE);
                center.getChildren().add(water);
                checkWater = true;
            } else {
                center.getChildren().remove(water);
                checkWater = false;
            }
        }));
        // Spawn particle all across the whole screen.
        particlesAll.setOnAction((ActionEvent e) -> {
            renderType = "allPart";
            particleType = (String) partType.getValue();
            center.getChildren().remove(part);
            center.getChildren().add(part);
        });
        // Spawn particle at given x,y.
        particlesLoc.setOnAction((ActionEvent e) -> {
            xPart = Integer.parseInt(xLoc.getText());
            yPart = Integer.parseInt(yLoc.getText());
            renderType = "singlePart";
            particleType = (String) partType.getValue();
            center.getChildren().remove(part);
            center.getChildren().add(part);
        });
        resetParticles.setOnAction(((ActionEvent e) -> center.getChildren().remove(part)));

        // Clear the whole area here!!
        reset.setOnAction((ActionEvent e) -> {
            sprites.clear();
            checkWater = false;
            wind = new PVector(0, 0);
            gravity = new PVector(0, 0.1);
            center.getChildren().removeAll();
            center.getChildren().remove(water);
            center.getChildren().remove(part);
            rotateAngle = 0;

        });
        // On action events to update things upon button press.
        addGrav.setOnAction((ActionEvent e) -> gravity = gravity.add(0, 1));
        subGrav.setOnAction((ActionEvent e) -> gravity = gravity.sub(0, 1));
        addWind.setOnAction((ActionEvent e) -> {
            rotateAngle -= 22.5;
            wind = wind.add(0.02, 0.00);
        });
        resetWind.setOnAction((ActionEvent e) -> {
            rotateAngle = 0;
            wind = new PVector(0, 0);
        });
        resGrav.setOnAction((ActionEvent e) -> gravity = new PVector(0, 0.1));

        return controls;
    }

    /**
     * Renders particles to canvas
     */
    void particleDraw() {
        gc.setGlobalAlpha(1.0);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.setFill(Color.TRANSPARENT);
        gc.clearRect(0, 0, 800, 600); // should be same size as canvas render area
        gc.fillRect(0, 0, 800, 600); // should be same size as canvas render area
        if (renderType == "allPart") { // fills entire canvas
            particles.addAll(particleSpawn.spawn(Math.random() * 750, Math.random() * 600, particleType));
        } else if (renderType == "singlePart") { // spawns as specific location
            particles.addAll(particleSpawn.spawn(xPart, yPart, particleType));
        }
        for (Iterator<Particle> it = particles.iterator(); it.hasNext(); ) {
            Particle p = it.next();
            p.update(particleType);
            if (!p.isAlive()) { // check if the particle has no life left
                it.remove();
                continue;
            }
            p.applyForce(wind);
            p.render(gc, particleType, rotateAngle);
        }
    }

    /**
     * Called once to create the UI layout.
     *
     * @return the root Pane.
     */
    Parent createContent() {
        root.setPrefSize(900, 600);
        center.setPrefSize(800, 600);
        part = new Canvas(758, 600);
        gc = part.getGraphicsContext2D();
        part.setTranslateX(0);
        part.setTranslateY(0);
        root.setCenter(part);
        root.setRight(UI());
        root.setCenter(center); // main center Pane to add to.
        root.getChildren().add(gravCanvas); // canvas for sprites or what ever else.

        return root;
    }

    /**
     * Main loop, All logic goes here.
     */
    void update() {
        gravCanvas.getGraphicsContext2D().clearRect(0, 0, gravCanvas.getWidth(), gravCanvas.getHeight()); // clear the canvas
        sprites.forEach(sprite -> { // sprite force updates
            sprite.applyForce(sprite.applyFriction(sprite.getVelocity(), 0.01));
            if (checkWater) {
                if (sprite.bound().intersects(new Rectangle2D(0, 400, 787, 250))) { // I don't like this!
                    sprite.applyForce(sprite.applyDrag(sprite.getVelocity(), 0.1));
                }
            }
            sprite.applyForce(new PVector(gravity.x, gravity.y * sprite.getMass()));
            sprite.applyForce(wind);
            sprite.update();
            sprite.checkEdges();
            sprite.draw(gravCanvas.getGraphicsContext2D());
        });
    }
}