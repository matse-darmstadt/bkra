package com.matse.cheese.bean;


@SuppressWarnings("unused")
public class Segment implements SegmentInterface{

	/**
	 * Type of this segment. TRUE if it is cheese, FALSE otherwise
	 * @TODO In future there should be different types (SegmentType)
	 * to 
	 */
	private SegmentType type;
	/**
	 * Visited state set by the AbstractSolver
	 */
	private boolean visited;
	/**
	 * Coordinates of a segment in a cube
	 */
	private final int x, y, z;
	
	public Segment() {
		this(SegmentType.CHEESE);
	}
	
	public Segment(SegmentType type) {
		this(type, false);
	}
	
	public Segment(SegmentType type, boolean visited) {
		this(type, visited, 0, 0, 0);
	}
	
	public Segment(SegmentType type, boolean visited, int x, int y, int z) {
		this.type = type;
		this.visited = visited;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public SegmentType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setType(boolean type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isVisited() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setVisited(boolean visited) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCoordinates(int x, int y, int z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setZ(int z) {
		// TODO Auto-generated method stub
		
	}

	
	
}
