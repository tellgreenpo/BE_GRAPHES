package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractInputData.Mode;
import org.insa.graphs.model.Node;

public class AStarAlgorithm extends DijkstraAlgorithm {
	
	
    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    
    
    @Override
    LabelStar[] initLabel(ShortestPathData data){
    	 LabelStar[] tabLabel = new LabelStar[data.getGraph().size()];
         for (Node node : data.getGraph().getNodes()) {
         	// origin cost 0
         	LabelStar label = null;
			boolean shortestMode = data.getMode().equals(Mode.LENGTH);
         	if (node == data.getOrigin()) {
         		label = new LabelStar(node,false,0,null,shortestMode,data.getDestination());
         	}else {
         		label = new LabelStar(node,false,Double.MAX_VALUE,null,shortestMode,data.getDestination());
         	}
         	tabLabel[node.getId()]  = label;
         }
         tabLabel[data.getOrigin().getId()].setCost(0);
         return tabLabel;
    }
}
