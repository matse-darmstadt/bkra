package com.matse.cheese.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.matse.cheese.bean.Cube;
import com.matse.cheese.bean.Segment;
import com.matse.cheese.bean.SegmentType;
import com.matse.cheese.solver.RecursiveSolver;

public class Test_Cube {

	private boolean compareList(List<Segment> erwarteterWeg, List<Segment> weg) {
		if (erwarteterWeg.size() != weg.size())
			return false;

		for (int i = 0; i != erwarteterWeg.size(); i++) {
			if (!weg.get(i).getType().equals(erwarteterWeg.get(i).getType())) {
				return false;
			}
		}
		return true;
	}

	@Test
	public void testStartsegment() {
		Segment[][][] segments = new Segment[3][3][3];
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				for (int z = 0; z < 3; z++) {
					segments[x][y][z] = new Segment(SegmentType.CHEESE, false, x, y, z);
				}
		Cube cube = new Cube(segments);
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				for (int z = 0; z < 3; z++) {
					// Test if the first segment is valid and is on top
					assertEquals(SegmentType.CHEESE, segments[x][y][z].getType());
					assertFalse("Käsesegment wurde als Startsegment gefunden",
							cube.isValidStartSegment(segments[x][y][z]));

					segments[x][y][z].setType(SegmentType.AIR);
					assertEquals("Käsesegment gefunden", SegmentType.AIR, segments[x][y][z].getType());
					assertEquals("Kein Startsegment gefunden", z == 0, cube.isValidStartSegment(segments[x][y][z]));

					assertEquals("Kein Endsegment gefunden", z == 2,
							cube.isValidBottomBoundarySegment(segments[x][y][z]));

					segments[x][y][z].setType(SegmentType.CHEESE);
					segments[0][0][2].setType(SegmentType.AIR);
					assertEquals("Kein Endsegment gefunden", z == 2,
							cube.isValidBottomBoundarySegment(segments[x][y][2]));
				}
		assertEquals("Mehr als 3 Nachbar gefunden", 3, cube.getNeighbours(segments[0][0][0]).size());
		assertEquals("Valide Nachbar gefunden", 0, cube.getValidNeighbours(segments[0][0][2]).size());

		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				for (int z = 0; z < 3; z++) {
					segments[x][y][z] = new Segment(SegmentType.CHEESE, false, x, y, z);
				}
		for (int z = 0; z < 3; z++)
			segments[1][1][z].setType(SegmentType.AIR);

		Cube kaese = new Cube(segments);

		RecursiveSolver solver = new RecursiveSolver(kaese);

		assertTrue("Kein Weg gefunden", solver.solve());
		assertEquals("Die Länge stimmt nicht", 3, solver.getPathLength());

		List<Segment> erwarteterWeg = Arrays.asList(segments[1][1][0], segments[1][1][1], segments[1][1][2]);
		List<Segment> weg = solver.searchPath(-1);
		assertEquals("Die Länge stimmt nicht überein", erwarteterWeg.size(), weg.size());
		assertTrue("Falscher Weg", compareList(erwarteterWeg, weg));
		
		assertTrue("Kein Strat- oder Endsegment gefunden", solver.isSolvable());
	}

}
