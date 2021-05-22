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
import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.insa.graphs.algorithm.AbstractSolution.Status;
import static org.insa.graphs.algorithm.ArcInspectorFactory.*;

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
	protected Path pathToulouseAuch,pathInsaJeanJau,pathSommet2Sommet,sameOriginDestinationCarrePath,
							sameOriginDestinationINSAPath,sameOriginDestinationToulousePath;
	
	
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
		pathReader = new BinaryPathReader(new DataInputStream(new FileInputStream(basePath+"A REMPLIR")));
		sameOriginDestinationToulousePath = pathReader.readPath(graph);
		
	// Initialization variables for tests on Toulouse map
		// graph Reader for Toulouse
		reader = new BinaryGraphReader(new DataInputStream(new FileInputStream(toulouseMapPath)));
		graph = reader.read();
		sameOriginDestinationToulouseData = new ShortestPathData(graph,graph.get(0),graph.get(0),null);
		noPathToulouseData = new ShortestPathData(graph,null,null,null);
		insaJeanJauToulouseData = new ShortestPathData(graph,null,null,null);
		
		pathReader = new BinaryPathReader(new DataInputStream(new FileInputStream(basePath+"A REMPLIR")));
		sameOriginDestinationINSAPath = pathReader.readPath(graph);
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
		pathReader = new BinaryPathReader(new DataInputStream(new FileInputStream(basePath+"A REMPLIR")));
		sameOriginDestinationCarrePath = pathReader.readPath(graph);
		
	}
	
	@Test
	public void noPath(){
		Assert.assertFalse("found feasible path to an unreachable destination in Midi-Pyrenees map",
				createShortestPathAlgorithm(noPathMpData).doRun().isFeasible());
		Assert.assertFalse("found feasible path to an unreachable destination in Toulouse map",
				createShortestPathAlgorithm(noPathToulouseData).doRun().isFeasible());
	}
	
	@Test
	public void sameOriginSameDestinationPath() {
		Assert.assertEquals(sameOriginDestinationCarrePath, createShortestPathAlgorithm(sameOriginDestinationCarreData).doRun().getPath());
		Assert.assertEquals(sameOriginDestinationINSAPath, createShortestPathAlgorithm(sameOriginDestinationToulouseData).doRun().getPath());
		Assert.assertEquals(sameOriginDestinationToulousePath, createShortestPathAlgorithm(sameOriginDestinationMpData).doRun().getPath());
	}
	
	@Test
	public void optimalPath() {
		Assert.assertEquals(pathToulouseAuch,createShortestPathAlgorithm(toulouseAuchMpData).doRun().getPath());
		Assert.assertEquals(pathInsaJeanJau,createShortestPathAlgorithm(insaJeanJauToulouseData).doRun().getPath());
		Assert.assertEquals(pathSommet2Sommet,createShortestPathAlgorithm(sommet2SommetCarreData).doRun().getPath());
	}
	
	
}