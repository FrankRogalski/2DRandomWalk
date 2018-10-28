package main;

import javafx.scene.canvas.GraphicsContext;

public class Walker {
	private double x, y;
	
	private GraphicsContext gc;
	
	public Walker(GraphicsContext gc, double x, double y) {
		this.x = x;
		this.y = y;
		this.gc = gc;
	}
	
	public void update(double offX, double offY) {
		x += offX;
		y += offY;
	}
	
	public void show() {
		gc.fillOval(x, y, 5, 5);
	}
}