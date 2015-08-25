package com.matse.cheese.solver;

import java.util.List;

import com.matse.cheese.bean.Segment;

@SuppressWarnings("unused")
public abstract class AbstractSolver {

	private List<Segment> path;
	
	/**
	 * Returns the check of the sufficient condition which is
	 * a mandatory segment of air on the lower side (z-axis) of the cube
	 * provided that there is a valid start segment
	 * @return TRUE if there is a segment of air on the lower side of a cube, FALSE otherwise
	 */
	abstract public boolean isSolvable();
	
	/**
	 * Solves the problem of deciding if there is a path of air segments through a given cube.
	 */
	abstract public boolean solve();
	
	/**
	 * Solves the problem of deciding if there a path of air segments through a given cube
	 */
	abstract public List<Segment> searchPath();
	
	abstract public int getPathLength();
	
	abstract public List<Segment> searchPath(int amountOfWater);
	
	abstract public boolean solve(int amountOfWater);
	
	abstract public 
	
	
}
