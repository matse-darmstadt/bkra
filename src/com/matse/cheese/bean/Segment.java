package com.matse.cheese.bean;


@SuppressWarnings("unused")
public class Segment implements SegmentInterface{

	/**
	 * Type of this segment. TRUE if it is cheese, FALSE otherwise
	 * @TODO In future there should be different types (SegmentType)
	 * to 
	 */
	private SegmentType type; // false = air; true = cheese;
	/**
	 * Visited state set by the AbstractSolver
	 */
	private boolean visited;
	/**
	 * Coordinates of a segment in a cuboid
	 */
	private final int x, y, z;
	
	public Segment() {
		this(true);
	}
	
	public Segment(boolean type) {
		this(type, false);
	}
	
	public Segment(boolean type, boolean visited) {
		this(type, visited, 0, 0, 0);
	}
	
	public Segment(SegmentType type, boolean visited, int x, int y, int z) {
		this.type = type;
		this.visited = visited;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	
	
}
