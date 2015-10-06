package com.matse.cheese.solver;

import java.util.ArrayList;
import java.util.List;

import com.matse.cheese.bean.Cube;
import com.matse.cheese.bean.Segment;
import com.matse.cheese.bean.SegmentType;

/**
 * This class solves the water flow problem in a three dimensional cube by using a recursive algorithm. 
 * 
 * The idea of the algorithm is as follows: <br />
 * <code>
 * Start with a given valid segment. <br />
 * LOOP:
 * Get all neighbours which consist of air.
 * 
 * To solve the same problem with a given amount of water there is used an additional
 * array <code>filled</code> which saves among other things the amount of water used.
 * 
 * @author bkra
 *
 */
public class RecursiveSolver extends AbstractSolver {

	private boolean pathFound = false;
	
	public RecursiveSolver(Cube cube) {
		super(cube);
	}

	@Override
	public boolean solve() {
		List<Segment> startList = this.cube.getValidStartSegments();
		pathFound = false;
		this.runningTime = System.currentTimeMillis();
		
		for (Segment segment : startList) {
			if (!pathFound)
				pathFound = solve(segment);
		}
		
		this.runningTime = (System.currentTimeMillis() - this.runningTime)*1000;
		
		return !this.path.isEmpty();
	}
	
	public boolean solve(Segment start) {
		
		List<Segment> currList = new ArrayList<Segment>();
		long solvingTime = System.currentTimeMillis();
		
		if (cube.isValidStartSegment(start)) {
			for (int i = 0 ; i < this.repetition ; i++) {
				solveIt(start, currList);				
			}
			this.path = currList;
		}
		
		solvingTime = System.currentTimeMillis() - solvingTime;
		if (pathFound)
			this.timeToSolve.add(new Double(solvingTime));
		return pathFound;
	}
	
	protected boolean solveIt(Segment segment, List<Segment> currentPath) {
		
		currentPath.add(segment);
		segment.setVisited(true);
		
		if (cube.isValidBottomBoundarySegment(segment)) {
			pathFound = true;
			return pathFound;
		}
		
		List<Segment> neighbours = cube.getValidNeighbours(segment);
			
		for (Segment neighbour : neighbours) {
			if (!pathFound) {
				solveIt(neighbour, currentPath);
			}
		}
		
		return pathFound;
	}

//	@Override
//	public boolean solve(int amountOfWater, boolean allPaths) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	@Override
	public List<Segment> solveAndReturnPath() {
		solve();
		return this.path;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("----------------------------------\n")
			.append("The following cube has been tested:\n")
			.append(this.cube.toString() + "\n")
			.append("\n")
			.append("Could it be solved: " + this.pathFound + "\n");
		
		buffer.append("The path is as follows: \n");
		buffer.append("START --> ");
		int counter = 0;
		for (Segment segment : this.path) {
			counter++;
			buffer.append("(");
			buffer.append(segment.getX() + ", ");
			buffer.append(segment.getY() + ", ");
			buffer.append(segment.getZ() + ") --> ");
			if (counter % 6 == 0)
				buffer.append("\n\t\t");
		}
		buffer.append("END\n");
		buffer.append("The length of the path is: " + this.getPathLength() + "\n");
		buffer.append("and was found in " + this.runningTime + " microseconds\n");
		buffer.append("\n");
		buffer.append("Some more statistics (ms): \n");
		
		counter = 1;
		for (Double time : this.timeToSolve) {
			buffer.append("Laufzeit für Durchgang " + counter + ": " + time.doubleValue() + "\n");
			counter++;
		}
		buffer.append("\n");
		for (String text : statistics.keySet()) {
			buffer.append(text + ": " + statistics.get(text) + "\n");
		}
		
		return buffer.toString();
	}
	
	public static void main(String args[]) {
		
		int cubeSize = 3;
		
		Cube testCube = new Cube(cubeSize);
		RecursiveSolver solver = new RecursiveSolver(testCube);
		
		solver.repetition = 100;
		solver.statistics.put("Repetition", new Double(solver.repetition));
		
		
		boolean solved = solver.solve();

		
		solver.calculateStatistics();
		
		List<Segment> path = solver.getPath();
		int pathLength = solver.getPathLength();
		
		System.out.println(solver.toString());
		
	}

	
}
