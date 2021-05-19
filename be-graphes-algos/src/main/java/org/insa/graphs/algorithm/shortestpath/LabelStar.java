package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class LabelStar extends Label{
	
	private double estimatedCost;
	
	public LabelStar(Node node,boolean marque,double cout,Arc lastArc, boolean shortestMode, Node destination ) {
		super(node,marque,cout,lastArc);
		double cost = node.getPoint().distanceTo(destination.getPoint());
		if (shortestMode) {
			this.estimatedCost = cost;
		}else {
			// Fastest Mode
			// 100 m.s-1 choice of speed, birds flight fast dayum
			this.estimatedCost = cost/50;
		}
	}
	
	public double getTotalCost() {
		return this.estimatedCost+this.cout;
	}
	
	public int compareTo(LabelStar o) {
		
		int res = Double.compare(this.getTotalCost(), o.getTotalCost());
		// If equal
		if(res == 0) {
			// smallest estimatedCost is smaller
			return Double.compare(this.estimatedCost, o.estimatedCost);
		}
		
		return res;
		
	}
	
}