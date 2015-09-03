package com.matse.cheese.solver;

import java.util.List;

import com.matse.cheese.bean.Segment;

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

	@Override
	public boolean isSolvable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean solve() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Segment> searchPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPathLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Segment> searchPath(int amountOfWater) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean solve(int amountOfWater) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
