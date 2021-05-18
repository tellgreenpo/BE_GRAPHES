package org.insa.graphs.algorithm.shortestpath;

import static org.junit.Assert.assertEquals;

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

// use abstract class instead of interface
// ==> share code among several closely related classes (ShortestPathAlgorithms)

// Create parameterized Tests with different paths for the maps for more concise code
// 

public abstract class ShortestPathTest{
	
	// data for maps test
	protected ShortestPathData dataToulouseLesBayles,dataInsaJeanJau,dataSommet2Sommet;
	
	// Path Solution
	protected Path pathToulouseLesBayles,pathInsaJeanJau,pathSommet2Sommet;
	
	protected ShortestPathData sameOriginDestinationFranceData,noPathFranceData,toulouseLesBaylesFranceData,
							sameOriginDestinationToulouseData,noPathToulouseData,insaJeanJauToulouseData,
							sameOriginDestinationCarreData,sommet2SommetCarreData;
	
	// Method to implement for each algorithm
	public abstract  ShortestPathAlgorithm createShortestPathAlgorithm(ShortestPathData data);
	
	//Initialize attributes before tests
	@Before
	public void init() throws IOException{
		
		// Thanks manu
		final String basePath = "D:\\BEGraphes\\Maps";
		final String franceMapPath = basePath+".\\france.mapgr";
		final String toulouseMapPath = basePath+".\\toulouse.mapgr";
		final String carreMapPath = basePath+".\\carre.mapgr";
		
		
		// Create graph reader for France
		GraphReader reader = new BinaryGraphReader(new DataInputStream(new FileInputStream(franceMapPath)));
		Graph graph = reader.read();
		// Create data for different conditions
		sameOriginDestinationFranceData = new ShortestPathData(graph,graph.get(0),graph.get(0),null);
		// Create Algo for each case
		noPathFranceData = new ShortestPathData(graph,null,null,null);
		// Create algo for each case
		toulouseLesBaylesFranceData = new ShortestPathData(graph,null,null,null);
		// Create algo for each case
		
		PathReader pathReader = new BinaryPathReader(new DataInputStream(new FileInputStream(basePath+"A REMPLIR")));
		pathToulouseLesBayles = pathReader.readPath(graph);
		
		// graph Reader for Toulouse
		reader = new BinaryGraphReader(new DataInputStream(new FileInputStream(toulouseMapPath)));
		graph = reader.read();
		// Create data for different conditions
		sameOriginDestinationToulouseData = new ShortestPathData(graph,graph.get(0),graph.get(0),null);
		// Create algo for each case
		noPathToulouseData = new ShortestPathData(graph,null,null,null);
		// Create algo for each case
		insaJeanJauToulouseData = new ShortestPathData(graph,null,null,null);
		// Create algo for each case
		
		pathReader = new BinaryPathReader(new DataInputStream(new FileInputStream(basePath+"A REMPLIR")));
		pathInsaJeanJau = pathReader.readPath(graph);
		
		// graph Reader for square
		reader = new BinaryGraphReader(new DataInputStream(new FileInputStream(carreMapPath)));
		graph = reader.read();
		sameOriginDestinationCarreData = new ShortestPathData(graph,graph.get(0),graph.get(0),null);
		// Create algo for each case
		sommet2SommetCarreData = new ShortestPathData(graph,null,null,null);
		// Create algo for each case
		
		pathReader = new BinaryPathReader(new DataInputStream(new FileInputStream(basePath+"A REMPLIR")));
		pathSommet2Sommet = pathReader.readPath(graph);
		// Create algo for each case
		
		
	}
	
	@Test
	public void testValidPath() {
		/**
		* Assume.assumeFalse();
		* assertTrue();
		*/
	}
	
	@Test
	public void testCostPath() {
		/**
		* Assume.assumeFalse();
		* assertEquals();
		* assertEquals();
		*/
	}
	
	@Test
	public void testCorrectSolution() {
		/**
		* if status is not infeasible
		* assertEquals(path,path);
		* 
		* assertEquals(Status,status);
		*/
	}
}