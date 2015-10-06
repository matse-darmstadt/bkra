package com.matse.cheese.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.geometry.Point3D;

/**
 * This is a specific implementation of a standard three dimensional cube.
 * A cube consists of {@link Segment}s of different types. <br>
 * As of now there are only two types of segments and no physics are considered. In the future
 * there will be more types which behave in some physical parameters which are yet to be defined.
 * <br>
 * In the moment this cube holds different arrays in respect to path solving algorithms for e.g. the water flow problem.<br><br>
 * 
 * {@code Cube} - A three dimensional array of segments to represent the cube <br>
 * {@code visited} - A temporary boolean array to save the visited state. This might be put in the segments as well.
 * 
 * 
 * 
 * 
 * @author bkra
 *
 */
public class Cube implements CubeInterface{
	
	public Segment[][][] cube;
	public boolean[][][] visited;
	public int dimensions;
	public int width, depth, height; // x, y, z
	
	public Cube() {
		this(1, 1, 1, true);
	}
	
	/**
	 * Main Constructor which also creates a random cube
	 * @param width The width aka x-coordinate
	 * @param depth The depth aka y-coordinate
	 * @param height The height aka z-coordinate
	 */
	public Cube(int width, int depth, int height, boolean createRandomCube) {
		cube = new Segment[width][depth][height];
		visited = new boolean[width][depth][height];
		this.width = width;
		this.depth = depth;
		this.height = height;
		if (createRandomCube)
			initRandomCuboid();
	}

	/**
	 * Constructor for creating a random cube with the same size in each dimension
	 * @param size
	 */
	public Cube(int size) {
		this(size, size, size, true);
	}

	/**
	 * Constructor for creating a cube with the same size in each dimension.
	 * It is possible to choose whether a random filling has to be created.
	 * @param size
	 */
	public Cube(int size, boolean createRandomCube) {
		this(size, size, size, createRandomCube);
	}
		
	/**
	 * Fills the cube randomly with AIR or CHEESE
	 */
	public void initRandomCuboid() {
		Random rand = new Random();
		for (int i = 0 ; i < this.width ; i++) {
			for (int j = 0 ; j < this.depth ; j++) {
				for (int k = 0 ; k < this.height ; k++) {
					if (rand.nextInt(2) == 0)
						cube[i][j][k] = new Segment(SegmentType.AIR, false, i, j, k);
					else
						cube[i][j][k] = new Segment(SegmentType.CHEESE, false, i, j, k);
				}
			}
		}
	}
	
	/**
	 * Constructor to use a given cube of Segments. 
	 * !IMPORTANT! Validation is not implemented yet.
	 * @param inputCube
	 */
	public Cube(Segment[][][] inputCube) {
			this.cube = inputCube;
			this.width = inputCube.length;
			this.depth = inputCube[0].length;
			this.height = inputCube[0][0].length;
			this.visited = new boolean[this.width][this.depth][this.height];
	}
	
	@Override
	public List<Segment> getNeighbours(Segment segment) {
		int position = 0;
		int factor = 0;
		int deltaX, deltaY, deltaZ;
		int x, y, z;
		List<Segment> neighbours = new ArrayList<Segment>();
		
		for ( int i = 0 ; i < 6 ; i++) {
			x = segment.getX();
			y = segment.getY();
			z = segment.getZ();
			
			position = i >> 1;
			factor = 1 - 2 * (i & 1);
			
			deltaZ = (position % 2) * factor;
			deltaY = (position / 2) * factor;
			deltaX = (~(deltaZ ^ deltaY)&1) * (-factor);
//			deltaX = (deltaY == deltaZ ? 1 : 0) * -factor;
			
			x = x + deltaX;
			y = y + deltaY;
			z = z + deltaZ;
			if (isInsideOfCube(new Point3D(x, y, z))) {
				neighbours.add(cube[x][y][z]);
			}			
		}

		return neighbours;
	}
	@Override
	public List<Segment> getValidNeighbours(Segment segment) {
		int position = 0;
		int factor = 0;
		int deltaX, deltaY, deltaZ;
		int x, y, z;
		List<Segment> neighbours = new ArrayList<Segment>();
		
		for ( int i = 0 ; i < 6 ; i++) {
			x = segment.getX();
			y = segment.getY();
			z = segment.getZ();
			
			position = i >> 1;
			factor = 1 - 2 * (i & 1);
			
			deltaZ = (position % 2) * factor;
			deltaY = (position / 2) * factor;
			deltaX = (~(deltaZ ^ deltaY)&1) * (-factor);
//			deltaX = (deltaY == deltaZ ? 1 : 0) * -factor;
			
			x = x + deltaX;
			y = y + deltaY;
			z = z + deltaZ;
			if (isInsideOfCube(new Point3D(x, y, z))) {
				if (cube[x][y][z].getType() == SegmentType.AIR && !cube[x][y][z].isVisited()) {
					neighbours.add(cube[x][y][z]);
				}
			}
		}
		return neighbours;
	}
	
	public List<Segment> getValidStartSegments() {
		
		List<Segment> startList = new ArrayList<Segment>();
		
		for (int x = 0 ; x < this.width ; x++) {
			for (int y = 0 ; y < this.depth ; y++) {
				Segment segment = getSegment(x, y, 0);
				if (isValidStartSegment(segment))
					startList.add(segment);
			}
		}
		return startList;
	}
	
	@Override
	public boolean isValidBoundarySegment(Segment segment) {
		boolean xBoundary = false;
		boolean yBoundary = false;
		boolean zBoundary = false;
		
		if (segment.getX() == 0 || segment.getX() == this.width-1)
			xBoundary = true;
		if (segment.getY() == 0 || segment.getY() == this.depth-1)
			yBoundary = true;
		if (segment.getZ() == 0 || segment.getZ() == this.height-1)
			zBoundary = true;
		
		if ((xBoundary || yBoundary || zBoundary) && segment.getType() == SegmentType.AIR)
			return true;
		
		return false;
	}
	
	@Override
	public boolean isValidBottomBoundarySegment(Segment segment) {
		boolean isValidBoundary = isValidBoundarySegment(segment);
		
		return (isValidBoundary && segment.getZ() == this.height-1);
	}
	
	@Override
	public boolean isValidStartSegment(Segment segment) {
		boolean isValidBoundary = isValidBoundarySegment(segment);
		
		return (isValidBoundary && segment.getZ() == 0);
	}
	
	@Override
	public boolean isInsideOfCube(Point3D coordinates) {
		if (coordinates.getX() < 0 || coordinates.getX() >= this.width
				|| coordinates.getY() < 0 || coordinates.getY() >= this.depth
				|| coordinates.getZ() < 0 || coordinates.getZ() >= this.height)
			return false;
		
		return true;
	}
	
	@Override
	public Segment[][][] getCube() {
		return this.cube;
	}

	@Override
	public void setCube(Segment[][][] inputCube) {
		this.cube = inputCube;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int z = 0 ; z < height ; z++) {
			for(int y = 0 ; y < depth ; y++) {
				for (int x = 0 ; x < width ; x++) {
					buffer.append("(" + x + ", " + y + ", " + z + "): " + cube[x][y][z].getType() + (cube[x][y][z].getType() == SegmentType.AIR ? "\t\t" : "\t"));
				}
				buffer.append("\n");
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}

	public static void main(String args[]) {
		Cube testCube = new Cube(3);
		Segment testSegment = testCube.cube[1][1][1];
		System.out.println(testCube.toString());
		
		List<Segment> neighbours = testCube.getNeighbours(testSegment);
		
		for (Segment seg : neighbours) {
			System.out.println("x: " + seg.getX() + "\t y: " + seg.getY() + "\t z: " + seg.getZ() + "\t Type: " + seg.getType());
		}
		
		
		
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getDepth() {
		return this.depth;
	}

	@Override
	public int getHeight() {
		return this.height;
	}
	
	@Override
	public Segment getSegment(int x, int y, int z) {
		
		if (isInsideOfCube(new Point3D(x, y, z))) {
			return this.cube[x][y][z];
		}
		
		return null;
	}
	
	@Override
	public boolean isVisited(Segment segment) {
		return this.visited[segment.getX()][segment.getY()][segment.getZ()];
	}

	

	

}
