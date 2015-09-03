package com.matse.cheese.bean;

import java.util.ArrayList;
import java.util.List;

public abstract class Body {
	
	List<Segment> body;
	int dimensions;
	
	public Body() {
		
	}
	
	
	public Body(int dimensions) {
		
		this.dimensions = dimensions;
		body = new ArrayList<Segment>();
				
		createDimensions(this.body, dimensions);
		
	}
	
	public void createDimensions(List<Segment> body, int dimensions) {
		
		List<Segment> tmpList = new ArrayList<Segment>();
		
		for (int i = 0 ; i < dimensions ; i++) {
		}
	}

}
