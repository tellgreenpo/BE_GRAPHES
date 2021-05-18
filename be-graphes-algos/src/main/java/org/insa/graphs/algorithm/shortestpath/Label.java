package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label implements Comparable<Label>{
	
	// Sommet associe au label
	protected final Node node;
	// True cout min definitivement connu par algorithme
	protected boolean marque;
	// Valeur courante du plus court chemin
	protected double cout;
	// Arc precedent sur le chemin plus court
	protected Arc lastArc;

	
	// Constructor
	public Label(Node node,boolean marque,double cout,Arc lastArc) {
		this.node = node;
		this.marque = marque;
		this.cout = cout;
		this.lastArc = lastArc;
	}
	
	public double getCost() {
		return this.cout;
	}
	
	public Node getNode() {
		return this.node;
	}
	
	public int getNodeId() {
		return this.node.getId();
	}
	
	public boolean getMarque() {
		return this.marque;
	}
	
	public Arc getFather() {
		return this.lastArc;
	}
	
	public void setCost(double cost) {
		this.cout = cost;
	}
	
	public void setMarque(boolean marque) {
		this.marque = marque;
	}
	
	public void setFather(Arc lastArc) {
		this.lastArc = lastArc;
	}
	
	public double getTotalCost() {
		return this.cout;
	}
	
	@Override
	public int compareTo(Label o) {
		return Double.compare(this.getTotalCost(), o.getTotalCost());
	}
	
}