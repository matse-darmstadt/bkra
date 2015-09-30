package com.matse.cheese.bean;

import java.util.List;

/**
 * This interface provides general methods to a body of {@link Segment}s.<br>
 * As of now a body is assumed to consist of cube-like elements for easier representation.
 * 
 * @FurtherDevelopment In further development a segment may vary in form or rather shape 
 * and there may be some physics applied to the body. 
 * @author bkra
 * @version 1.0
 *
 */
public interface CubeInterface {
	
	/**
	 * Returns a list of all neighbours to a given segment.
	 * This method is just for testing.
	 * @param segment
	 * @deprecated use {@link #getValidNeighbours(Segment)} instead.
	 */
	public List<Segment> getNeighbours(Segment segment);

	/**
	 * Returns a list of all valid neighbours.
	 * A valid neighbour must consist free space and may not yet be visited
	 * so it can be used in the next iteration in a path solving algorithm.
	 * @param segment The segment to which all valid neighbours are selected
	 * @return A list of segments that can be iterated over.
	 */
	public List<Segment> getValidNeighbours(Segment segment);
	
	/**
	 * Checks whether the given segment is valid on the boundary of a cube.
	 * Valid in this context at this stage of development means that 
	 * it must consist of air.
	 * This method will be used if the exit may not only be on the bottom but rather 
	 * on all sides of a cube
	 * @param segment
	 * @return <b>true</b> if the segment is on the boundary and is valid, <b>false</b> otherwise
	 * @FurtherDevelopment Since this method will check if 
	 */
	public boolean isValidBoundarySegment(Segment segment);
	
	/**
	 * Checks whether the given segment is valid on the bottom of the cube.
	 * Valid in this context at this stage of development means that 
	 * it must consist of air.
	 * This represents the necessary condition for a path algorithm
	 * to have an 
	 * @param segment
	 * @return <b>true</b> if the segment is on the bottom and is valid, <b>false</b> otherwise
	 */
	public boolean isValidBottomBoundarySegment(Segment segment);
	
	/**
	 * Checks whether the given segment may be used as a start segment
	 * for the path algorithm.
	 * As of now the segment must consist of air and must be on the top of a cube.
	 * 
	 * @param segment
	 * @return <b>true</b> if the segment is on the top and is valid, <b>false</b> otherwise
	 * 
	 * @FurtherDevelopment In the further development the conditions on a segment to be a valid
	 * start segment will be enhanced.
	 */
	public boolean isValidStartSegment(Segment segment);
	
	
}
