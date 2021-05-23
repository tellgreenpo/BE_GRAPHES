package org.insa.graphs.algorithm.shortestpath;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.BinaryPathReader;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.io.PathReader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.insa.graphs.algorithm.ArcInspectorFactory.*;

// use abstract class instead of interface
// ==> share code among several closely related classes (ShortestPathAlgorithms)

// Create parameterized Tests with different paths for the maps for more concise code
// 

public abstract class ShortestPathTest{
	 
	
	// Feasible indicators
	protected boolean isFeasible;
	
	
	// Path solution
	protected static Path pathToulouseAuch,pathInsaJeanJau,pathSommet2Sommet;
	
	
	protected static ShortestPathData sameOriginDestinationMpData,toulouseAuchMpData, noPathToulouseData,
							sameOriginDestinationToulouseData,insaJeanJauToulouseData,
							sameOriginDestinationCarreData,sommet2SommetCarreData;

	// Method to implement for each algorithm
	public abstract  ShortestPathAlgorithm createShortestPathAlgorithm(ShortestPathData data);
	

	@BeforeClass
	public static void Initialise() throws IOException{
		// Paths used to load data
		final String basePath = "D:\\BEGraphes\\Maps";
		final String mpMapPath = basePath+".\\midi-pyrenees.mapgr";
		final String toulouseMapPath = basePath+".\\toulouse.mapgr";
		final String carreMapPath = "D:\\BEGraphes\\Maps\\carre.mapgr";
		
	// Initialize data for tests and Path solution	
	// Initialization variables for tests on Midi-pyrenees map
		// Create graph reader for France
		GraphReader reader = new BinaryGraphReader(new DataInputStream( new BufferedInputStream(new FileInputStream(mpMapPath))));
		Graph graph = reader.read();
		sameOriginDestinationMpData = new ShortestPathData(graph,graph.get(0),graph.get(0),ArcInspectorFactory.getAllFilters().get(0));
		toulouseAuchMpData = new ShortestPathData(graph,graph.get(244640),graph.get(82946),ArcInspectorFactory.getAllFilters().get(0));
	
		
		PathReader pathReader = new BinaryPathReader(new DataInputStream( new BufferedInputStream(new FileInputStream(basePath+"..\\Path\\toulouseAuchLength.path"))));
		pathToulouseAuch = pathReader.readPath(graph);
		
		
	// Initialization variables for tests on Toulouse map
		// graph Reader for Toulouse
		reader = new BinaryGraphReader(new DataInputStream(new FileInputStream(toulouseMapPath)));
		graph = reader.read();
		sameOriginDestinationToulouseData = new ShortestPathData(graph,graph.get(0),graph.get(0),ArcInspectorFactory.getAllFilters().get(0));
		insaJeanJauToulouseData = new ShortestPathData(graph,graph.get(19150),graph.get(1411),ArcInspectorFactory.getAllFilters().get(0));
		noPathToulouseData = new ShortestPathData(graph,graph.get(5918),graph.get(36861),ArcInspectorFactory.getAllFilters().get(0));
		
		
		pathReader = new BinaryPathReader(new DataInputStream( new BufferedInputStream(new FileInputStream(basePath+"..\\Path\\insaJeanJauLength.path"))));
		pathInsaJeanJau = pathReader.readPath(graph);

		
	// Initialization variables for tests on square map
		// graph Reader for square
		reader = new BinaryGraphReader(new DataInputStream( new BufferedInputStream(new FileInputStream(carreMapPath))));
		graph = reader.read();
		sameOriginDestinationCarreData = new ShortestPathData(graph,graph.get(0),graph.get(0),ArcInspectorFactory.getAllFilters().get(0));
		sommet2SommetCarreData = new ShortestPathData(graph,graph.get(9),graph.get(15),ArcInspectorFactory.getAllFilters().get(0));
		
		
		pathReader = new BinaryPathReader(new DataInputStream( new BufferedInputStream(new FileInputStream(basePath+"..\\Path\\sommet2Sommet.path"))));
		pathSommet2Sommet = pathReader.readPath(graph);
		
	}
	
	@Test
	public void noPath(){
		Assert.assertFalse("found feasible path to an unreachable destination in Toulouse map",
				createShortestPathAlgorithm(noPathToulouseData).doRun().isFeasible());
	}
	
	@Test
	public void sameOriginSameDestinationPath() {
		Assert.assertFalse("Found Path in null path, supposed to be infeasible",createShortestPathAlgorithm(sameOriginDestinationMpData).doRun().isFeasible());
		Assert.assertFalse("Found Path in null path, supposed to be infeasible",createShortestPathAlgorithm(sameOriginDestinationToulouseData).doRun().isFeasible());
		Assert.assertFalse("Found Path in null path, supposed to be infeasible",createShortestPathAlgorithm(sameOriginDestinationCarreData).doRun().isFeasible());
	}
	
	@Test
	public void optimalPath() {
		Assert.assertEquals(pathToulouseAuch,createShortestPathAlgorithm(toulouseAuchMpData).doRun().getPath());
		Assert.assertEquals(pathInsaJeanJau,createShortestPathAlgorithm(insaJeanJauToulouseData).doRun().getPath());
		Assert.assertEquals(pathSommet2Sommet,createShortestPathAlgorithm(sommet2SommetCarreData).doRun().getPath());
	}
	
	
}