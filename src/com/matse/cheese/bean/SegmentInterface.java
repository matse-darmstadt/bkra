package com.matse.cheese.bean;

public interface SegmentInterface {

	/**
	 * Returns the type of the segment
	 */
	public SegmentType getType();
	
	/**
	 * Sets the type of the segment
	 * @param type of the segment
	 */
	public void setType(SegmentType type);
	
	/**
	 * Gets the visited state
	 * @return TRUE if segment was already visited, FALSE otherwise
	 */
	public boolean isVisited();
	
	/**
	 * Set the visited state
	 * @param visited TRUE if visited by the solver, FALSE otherwise
	 */
	public void setVisited(boolean visited);
	
	/**
	 * Sets the coordinates of a segment
	 * @param x The x coordinate aka width
	 * @param y The y coordinate aka depth
	 * @param z The z coordinate aka height
	 */
	public void setCoordinates(int x, int y, int z);
	
	/**
	 * Returns the coordinate in the width dimension
	 */
	public int getX();
	
	/**
	 * Returns the coordinate in the depth dimension
	 */
	public int getY();
	
	/**
	 * Returns the coordinate in the height dimension
	 */
	public int getZ();
	
	/**
	 * Sets the coordinate in the width dimension
	 */
	public void setX(int x);
	
	/**
	 * Sets the coordinate in the depth dimension
	 */
	public void setY(int y);
	
	/**
	 * Sets the coordinate in the height dimension
	 */
	public void setZ(int z);
	
}
