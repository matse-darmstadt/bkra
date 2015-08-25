package com.matse.cheese.bean;

import java.util.List;

public interface CuboidInterface {
	
	/**
	 * Returns a list of all neighbours to a given segment.
	 */
	public List<Segment> getNeighbours(Segment segment);

	/**
	 * Returns a list of neighbours which consist of air and are therefore
	 * free to use in the next iteration of the path solving algorithm.
	 */
	public List<Segment> getFreeNeighbours(Segment segment);
}
