package org.insa.graphs.algorithm.shortestpath;

public class AStarAlgorithmTest extends ShortestPathTest{
	
	@Override
	public ShortestPathAlgorithm createShortestPathAlgorithm(ShortestPathData data) {
		return new AStarAlgorithm(data);
	}
	
}