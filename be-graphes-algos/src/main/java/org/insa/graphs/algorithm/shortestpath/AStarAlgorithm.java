package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;

public class AStarAlgorithm extends DijkstraAlgorithm {

	
    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    
    
    @Override
    LabelStar[] initLabel(ShortestPathData data){
    	 LabelStar[] tabLabel = new LabelStar[data.getGraph().size()];
    	 int idx = 0;
         for (Node node : data.getGraph().getNodes()) {
         	// origin cost 0
         	LabelStar label = null;
         	if (node == data.getOrigin()) {
         		label = new LabelStar(node,false,0,null,0);
         	}else {
         		label = new LabelStar(node,false,Double.MAX_VALUE,null,Double.MAX_VALUE);
         	}
         	tabLabel[idx]  = label;
         	idx++;
         }
         return tabLabel;
    }
    
    /** Override this
    public void init(ShortestPathData data) {
    	// Initialize label for each Node
        ttabLabel = new ArrayList<LabelStar>();
        this.heap = new BinaryHeap<Label>();
        for (Node node : data.getGraph().getNodes()) {
        	// origin cost 0
        	LabelStar label = null;
        	if (node == data.getOrigin()) {
        		label = new LabelStar(node,false,0,null,0);
        		// Insert origin in heap
        		this.heap.insert(label);
        	}else {
        		label = new LabelStar(node,false,Double.MAX_VALUE,null,Double.MAX_VALUE);
        	}
        	this.tabLabel.add(label);
        }
    }*/

}
