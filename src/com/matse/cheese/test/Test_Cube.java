package src.com.matse.cheese.test;
import src.com.matse.cheese.bean.Cube;
import src.com.matse.cheese.bean.Segment;
import src.com.matse.cheese.bean.SegmentType;;

public class Test_Cube {

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

	}

}
