package com.matse.cheese.bean;

import java.util.List;

public class Cube extends Cuboid{
	
	public int dimension;
	
	/**
	 * Standard constructor which creates a cube of length 1 in all dimensions 
	 */
	public Cube() {
		this(1);
	}
	
	
	
	public Cube(int dimension) {
		cube = super(dimension, dimension, dimension);
		
	}
	
	public List<Segment> getFreeNeighbours(Segment segment) {
		return null;
	}
	
	public boolean isBoundarySegment(Segment segment) {
			return false;
	}

}
