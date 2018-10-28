package main;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RandomWalk extends Application{
	private Canvas can;
	private GraphicsContext gc;
	
	private Random r = new Random();
	
	private Walker walker;
	private short farbe = 0;
	private final byte schrittweite = 2;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void init() throws Exception {
		Timeline tl_draw = new Timeline(new KeyFrame(Duration.millis(16.67), e -> {
			draw();
		}));
		tl_draw.setCycleCount(Timeline.INDEFINITE);
		tl_draw.play();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Pane root = new Pane();
		Scene scene = new Scene(root, 700, 400);
		
		can = new Canvas(scene.getWidth(), scene.getHeight());
		gc = can.getGraphicsContext2D();
		
		root.getChildren().add(can);
//		root.setStyle("-fx-background-color: #000000");
		
		scene.widthProperty().addListener((obsv, oldVal, newVal) -> {
		   can.setWidth(newVal.doubleValue());
		});
		
		scene.heightProperty().addListener((obsv, oldVal, newVal) -> {
			can.setHeight(newVal.doubleValue());
		});
		
		stage.setScene(scene);
		stage.show();
		
		walker = new Walker(gc, can.getWidth() / 2, can.getHeight() / 2);
	}
	
	private void draw() {
		farbe++;
		if (farbe > 255) {
			farbe = 0;
		}
		gc.setFill(Color.hsb(farbe, 1, 1));
		
		walker.update(r.nextInt(schrittweite * 2 + 1) - schrittweite, r.nextInt(schrittweite * 2 + 1) - schrittweite);
		walker.show();
	}
}