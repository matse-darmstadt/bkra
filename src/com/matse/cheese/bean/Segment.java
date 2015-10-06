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
	private int x, y, z;
	
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
		return this.type;
	}

	@Override
	public void setType(SegmentType type) {
		this.type = type;
	}

	@Override
	public boolean isVisited() {
		return this.visited;
	}

	@Override
	public void setVisited(boolean visited) {
		this.visited = visited;		
	}

	@Override
	public void setCoordinates(int x, int y, int z) {
		setX(x);
		setY(y);
		setZ(z);
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public int getZ() {
		return this.z;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void setZ(int z) {
		this.z = z;
	}

	
	
}
