package com.matse.cheese.solver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.matse.cheese.bean.Cube;
import com.matse.cheese.bean.Segment;
import com.matse.cheese.bean.SegmentType;

/**
 * This is the abstract class for solving the water flow problem (path finding).
 * It implements common methods and defines standard methods for all kinds of solvers.
 * Currently there are the following types of algorithms implemented: <br>
 * - A*
 * - Recursion
 *  
 * @FurtherDevelopment In the future there may be some physics applied to the search graph
 * which will produce completely different results on the same test objects
 * @author bkra
 *
 */

@SuppressWarnings("unused")
public abstract class AbstractSolver {

	
	/**
	 * Represents the shortest path of segments solved by the implemented algorithm.
	 */
	public List<Segment> path;
	
	/**
	 * Represents a collection of all paths through a cube found by the implemented algorithm. 
	 */
	public HashMap<Integer, List<Segment>> allPaths;
	
	/**
	 * Flag if all possible paths should be found.
	 */
	public boolean findAllPaths;
	
	/**
	 * Flag for using the yet to be implemented physics. May be moved to its own interface.
	 */
	public boolean usePhysics;
	
	public Cube cube;
	
	public List<Double> timeToSolve;
	
	public Long runningTime;
	
	public HashMap<String, Double> statistics;
	
	public AbstractSolver(Cube cube) {
		this.cube = cube;
		path = new ArrayList<Segment>();
		allPaths = new HashMap<Integer, List<Segment>>();
		timeToSolve = new ArrayList<Double>();
		statistics = new HashMap<String, Double>();
	}
	
	
	/**
	 * Returns the check of the necessary condition which is
	 * a mandatory segment of air on the lower side (z-axis) of the cube
	 * provided that there is a valid start segment
	 * @return <b>true</b> if there is a segment of air on the lower and the top side of a cube, 
	 * <b>false</b> otherwise
	 */
	public boolean isSolvable() {

		if (this.cube == null)
			return false;
		
		boolean hasValidStart = false;
		boolean hasValidEnd = false;
		
		for (int width = 0 ; width < this.cube.getWidth() ; width++) {
			for (int depth = 0 ; depth < this.cube.getDepth() ; depth++) {
				if (cube.getSegment(width, depth, 0).getType() == SegmentType.AIR)
					hasValidStart = true;
				if (cube.getSegment(width, depth, cube.getHeight()-1).getType() == SegmentType.AIR)
					hasValidEnd = true;
			}
		}
		
		return hasValidStart && hasValidEnd;
	};
	
	/**
	 * Solves the problem of deciding if there is a path of air segments for the water to run
	 * through a given cube.
	 * This is the main method for calculation.
	 */
	abstract public boolean solve();
	
	abstract public boolean solve(Segment start);
	
//	/**
//	 * Solves the problem of deciding if there is a path of air segments for a fixed amount of 
//	 * water to run through a given cube.
//	 * This is the main method for calculation if there is not an unlimited amount of water.<br>
//	 * 
//	 * @FurtherDevelopment In the future when there will be some physics applied to cubes or bodies
//	 * respectively the results of this method will vary to earlier ones of the same test object.
//	 * 
//	 * @param amountOfWater The maximum amount of water used to decide wether there is a path 
//	 * through the cube. One could also call this <code>length</code>.
//	 * 
//	 * @return <b>true</b> if there is a path through the cube with the given amount of water.
//	 */
//	abstract public boolean solve(int amountOfWater, boolean allPaths);
	
	
	abstract protected boolean solveIt(Segment segment, List<Segment> path);
	/**
	 * Returns the found path from a starting node to an exit 
	 * if searchPath() has been executed.
	 */
	public List<Segment> getPath() {
		return path;
	}
	
	/**
	 * Returns the length of the path found by the implemented path algorithm.
	 * @return The length of the path, -1 if no path is found
	 */
	public int getPathLength() {
		if (path == null)
			return Integer.MIN_VALUE;
		
		return path.size();
	};
	
	/**
	 * Returns <code>true</code> if all paths should be found, <code>false</false> otherwise
	 */
	public boolean isFindAllPaths() {
		return findAllPaths;
	}
	
	/**
	 * Sets the flag for finding all possible paths.
	 * @param flag <b>true</b> if all paths should be found by the algorithm,
	 * <b>false</b> if only the shortest shall be found.
	 * 
	 * @FurtherDevelopment In the future <i>shortest</i> may be with applied physics.
	 */
	public void setFindAllPaths(boolean flag) {
		findAllPaths = flag;
	}
	
	abstract public List<Segment> solveAndReturnPath();

	public void calculateStatistics() {

		Double stdDeviation = 0.0;
		Double average = 0.0;
		Double sum = 0.0;
		Double testSize = 0.0;
		for (Double time : timeToSolve) {
			sum += time;
			testSize++;
		}
		average = sum / testSize;
		
		Double varianz = 0.0;
		for (Double time : timeToSolve) {
			varianz += Math.pow(time-average, 2);
		}
		varianz = varianz / testSize;
		statistics.put("Varianz", varianz);
		
		stdDeviation = Math.sqrt(varianz);
		statistics.put("Standardabweichung", stdDeviation);
		
	}
	
		
	
}
