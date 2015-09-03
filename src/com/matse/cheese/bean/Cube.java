package com.matse.cheese.bean;

import java.util.List;

/**
 * This is a specific implementation of a standard three dimensional cube.
 * A cube consists of {@link Segment}s of different types. <br>
 * As of now there are only two types of segments and no physics are considered. In the future
 * there will be more types which behave in some physical parameters which are yet to be defined.
 * <br>
 * In the moment this cube holds different arrays in respect to path solving algorithms for e.g. the water flow problem.<br><br>
 * 
 * {@code Cube} - A three dimensional array of segments to represent the cube <br>
 * {@code visited} - A temporary boolean array to save the visited state. This might be put in the segments as well.
 * 
 * 
 * 
 * 
 * @author bkra
 *
 */
public class Cube extends Body implements BodyInterface{
	
	public Segment[][][] cube;
	public boolean[][][] visited;
	public int width, depth, height; // x, y, z
	
	public Cube() {
		this(1, 1, 1);
	}
	public Cube(int depth, int width, int height) {
		cube = new Segment[width][depth][height];
		initCuboid();
	}
	
	
	public void initCuboid() {
		
		
	}


	public Cube(int size) {
//		cube = super(dimension);
		
	}
	@Override
	public List<Segment> getNeighbours(Segment segment) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Segment> getValidNeighbours(Segment segment) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isValidBoundarySegment(Segment segment) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isValidBottomBoundarySegment(Segment segment) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isValidStartSegment(Segment segment) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
