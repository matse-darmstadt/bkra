package com.matse.cheese.bean;

/**
 * This enumerates all possible types a segment may be made of.
 * 
 * @FurtherDevelopement Physics may be applied here by using a physics object
 * @author Seraph
 *
 */
public enum SegmentType {
	
	AIR(0),
	CHEESE(1);
	
	// possible physics
//	AIR(0, somePhysicsObject),
//	CHEESE(1, someOtherPhysics),
//	ROCK(2, rockyPhysics),
//	ENERGY(3, hardCorePhysics);
	
	
	private String id;
	private int type;
	
	
	public String getId() {
		return id;
	}
	
	private SegmentType(int type) {
		this.type = type;
	}

}
