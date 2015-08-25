package com.matse.cheese.bean;

public class Cuboid implements CuboidInterface{
	
	public Segment[][][] cube;
	public boolean[][][] visited;
	public int depth, width, height; // x, y, z
	
	public Cuboid() {
		this(1, 1, 1);
	}
	public Cuboid(int depth, int width, int height) {
		cube = new Segment[width][depth][height];
		initCuboid();
	}
	
	public void initCuboid() {
		
		for (int i = 0 ; i < width ; i++) {
			for (int j = 0 ; j < depth ; j++) {
				for (int k = 0 ; k < height ; k++) {
					cube[i][j][k] = new Segment(true, false, i, j, k);
				}
			}
		}
	}
}
