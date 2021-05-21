package org.insa.graphs.algorithm.shortestpath;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.BinaryPathReader;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.io.PathReader;
import org.junit.BeforeClass;
import static org.insa.graphs.algorithm.AbstractSolution.Status;
import static org.insa.graphs.algorithm.ArcInspectorFactory.*;
import static org.insa.graphs.algorithm.ArcInspectorFactory.FilterType;

// use abstract class instead of interface
// ==> share code among several closely related classes (ShortestPathAlgorithms)

// Create parameterized Tests with different paths for the maps for more concise code
// 

public abstract class ShortestPathTest{
	 
	
	// Feasible indicators
	protected boolean isFeasible;
	
	// Algorithm to be test
	protected ShortestPathAlgorithm algorithmDataSet1,algorithmDataSet2,algorithmDataSet3;
	
	// Path solution
	protected Path pathToulouseAuch,pathInsaJeanJau,pathSommet2Sommet;
	
	
	protected ShortestPathData sameOriginDestinationMpData,noPathMpData,toulouseAuchMpData,
							sameOriginDestinationToulouseData,noPathToulouseData,insaJeanJauToulouseData,
							sameOriginDestinationCarreData,sommet2SommetCarreData;

	// Method to implement for each algorithm
	public abstract  ShortestPathAlgorithm createShortestPathAlgorithm(ShortestPathData data);
	

	@BeforeClass
	public void Initialise() throws IOException{
		// Paths used to load data
		final String basePath = "D:\\BEGraphes\\Maps";
		final String mpMapPath = basePath+".\\midi-pyrenees.mapgr";
		final String toulouseMapPath = basePath+".\\toulouse.mapgr";
		final String carreMapPath = basePath+".\\carre.mapgr";
		
	// Initialize data for tests and Path solution	
	// Initialization variables for tests on Midi-pyrenees map
		// Create graph reader for France
		GraphReader reader = new BinaryGraphReader(new DataInputStream(new FileInputStream(mpMapPath)));
		Graph graph = reader.read();
		sameOriginDestinationMpData = new ShortestPathData(graph,graph.get(0),graph.get(0),null);
		noPathMpData = new ShortestPathData(graph,null,null,null);
		toulouseAuchMpData = new ShortestPathData(graph,null,null,null);
	
		
		PathReader pathReader = new BinaryPathReader(new DataInputStream(new FileInputStream(basePath+"A REMPLIR")));
		pathToulouseAuch = pathReader.readPath(graph);
		
	// Initialization variables for tests on Toulouse map
		// graph Reader for Toulouse
		reader = new BinaryGraphReader(new DataInputStream(new FileInputStream(toulouseMapPath)));
		graph = reader.read();
		sameOriginDestinationToulouseData = new ShortestPathData(graph,graph.get(0),graph.get(0),null);
		noPathToulouseData = new ShortestPathData(graph,null,null,null);
		insaJeanJauToulouseData = new ShortestPathData(graph,null,null,null);
		
		
		pathReader = new BinaryPathReader(new DataInputStream(new FileInputStream(basePath+"A REMPLIR")));
		pathInsaJeanJau = pathReader.readPath(graph);
		
	// Initialization variables for tests on square map
		// graph Reader for square
		reader = new BinaryGraphReader(new DataInputStream(new FileInputStream(carreMapPath)));
		graph = reader.read();
		sameOriginDestinationCarreData = new ShortestPathData(graph,graph.get(0),graph.get(0),null);
		sommet2SommetCarreData = new ShortestPathData(graph,null,null,null);
		
		
		pathReader = new BinaryPathReader(new DataInputStream(new FileInputStream(basePath+"A REMPLIR")));
		pathSommet2Sommet = pathReader.readPath(graph);
		
		
		// Initialize solution
		pathToulouseAuch = createShortesPathAlgorithm(toulouseAuchMpData)
	}
	
	
	
}