package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;

public class Label implements Comparable<Label>{
	
	// Sommet associe au label
	private final Node node;
	// True cout min definitivement connu par algorithme
	private boolean marque;
	// Valeur courante du plus court chemin
	private int cout;
	// Arc precedent sur le chemin plus court
	private int idLastArc;

	
	// Constructor
	public Label(Node node,boolean marque,int cout,int idLastArc) {
		this.node = node;
		this.marque = marque;
		this.cout = cout;
		this.idLastArc = idLastArc;
	}
	
	public int getCost() {
		return this.cout;
	}
	
	public Node getNode() {
		return this.node;
	}
	
	public boolean getMarque() {
		return this.marque;
	}
	
	public int getFather() {
		return this.idLastArc;
	}
	
	public void setCost(int cost) {
		this.cout = cost;
	}
	
	public void setMarque(boolean marque) {
		this.marque = marque;
	}
	
	public void setFather(int idLastArc) {
		this.idLastArc = idLastArc;
	}
	
	@Override
	public int compareTo(Label o) {
		return Integer.compare(cout, o.getCost());
	}
	
}