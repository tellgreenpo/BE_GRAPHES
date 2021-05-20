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
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;


import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.runners.parameterized.*;


// use abstract class instead of interface
// ==> share code among several closely related classes (ShortestPathAlgorithms)

// Create parameterized Tests with different paths for the maps for more concise code
// 

@RunWith(Parameterized.class)
public abstract class ShortestPathTest{
	 /**
	// data for maps test
	protected ShortestPathData dataToulouseAuch,dataInsaJeanJau,dataSommet2Sommet;
	*/
	// Path Solution
	protected Path foundPath,expectedPath;
	
	// Feasible indicators
	protected boolean isFeasible,isExpectedFeasible;
	
	// Algorithm to be test
	protected ShortestPathAlgorithm algorithmDataSet1,algorithmDataSet2,algorithmDataSet3;
	
	// Constructor
	public ShortestPathTest(Path foundPath,Path expectedPath,boolean isFeasible, boolean isExpectedFeasible) {
		this.foundPath = foundPath;
		this.expectedPath = expectedPath;
		this.isFeasible = isFeasible;
		this.isExpectedFeasible = isExpectedFeasible;
	}
	
	/**
	// To be tested
	protected ShortestPathData sameOriginDestinationMpData,noPathMpData,toulouseAuchMpData,
							sameOriginDestinationToulouseData,noPathToulouseData,insaJeanJauToulouseData,
							sameOriginDestinationCarreData,sommet2SommetCarreData;
	*/
	// Method to implement for each algorithm
	public abstract  ShortestPathAlgorithm createShortestPathAlgorithm(ShortestPathData data);
	
	@Before
	public void Initialize() {
		// Paths used to load data
		final String basePath = "D:\\BEGraphes\\Maps";
		final String mpMapPath = basePath+".\\midi-pyrenees.mapgr";
		final String toulouseMapPath = basePath+".\\toulouse.mapgr";
		final String carreMapPath = basePath+".\\carre.mapgr";
		
		// Create data from loaded maps
		//algorithmDataSet1 = createShortestPathAlgorithm(data);
		//algorithmDataSet2 = createShortestPathAlgorithm(data);
		//algorithmDataSet3 = createShortestPathAlgorithm(data);
	}
	
	/**
	public ShortestPathData[][] Initialise() throws IOException{
		// Paths used to load data
		final String basePath = "D:\\BEGraphes\\Maps";
		final String mpMapPath = basePath+".\\midi-pyrenees.mapgr";
		final String toulouseMapPath = basePath+".\\toulouse.mapgr";
		final String carreMapPath = basePath+".\\carre.mapgr";
		
		ShortestPathData sameOriginDestinationMpData,noPathMpData,toulouseAuchMpData,
		sameOriginDestinationToulouseData,noPathToulouseData,insaJeanJauToulouseData,
		sameOriginDestinationCarreData,sommet2SommetCarreData;
		
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
		//pathSommet2Sommet = pathReader.readPath(graph);
	// Solution initialization
		

		return new ShortestPathData[][] {{sameOriginDestinationMpData,sameOriginDestinationToulouseData,sameOriginDestinationCarreData},
										{noPathMpData,noPathToulouseData},
										{toulouseAuchMpData,insaJeanJauToulouseData,sommet2SommetCarreData}};
	}
	
	
	//https://www.guru99.com/junit-parameterized-test.html
	
	*/
}