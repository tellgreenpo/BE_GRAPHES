package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.List;

import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }
    /**
    private int search(Node x,ArrayList<Label> list) throws ElementNotFoundException {
    	int i=0;
    	boolean found = false;
    	while(i<list.size() && !found) {
    		found = list.get(i).getNode() == x.getId() ? true : false;
    		i++;
    	}
    	if(!found) {
    		throw new ElementNotFoundException(x);
    	}
    	return i;
    }
    */

    @Override
    protected ShortestPathSolution doRun() {
    	
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        // Label list
        ArrayList<Label> tabLabel = new ArrayList<Label>();
        // BinaryHeap
        BinaryHeap<Label> heap = new BinaryHeap<Label>();
        boolean allMarked = false;
        Label currLabel;
        
        
        // Initialize label for each Node
        for (Node node : data.getGraph().getNodes()) {
        	// origin cost 0
        	Label label = null;
        	if (node == data.getOrigin()) {
        		label = new Label(node,false,0,0);
        		// Insert origin in heap
        		heap.insert(label);
        	}else {
        		label = new Label(node,false,Integer.MAX_VALUE,0);
        	}
        	tabLabel.add(label);
        }
        
        // While non marked Nodes exist
        while (!allMarked) {
        	//x = ExtractMin(Heap)
        	currLabel = heap.deleteMin();
        	//Mark(x) = True
        	currLabel.setMarque(true);
        	// For y successors of x
        	List<Arc> successors =  currLabel.getNode().getSuccessors();
        	for (Arc arc : successors) {
        		// if not Mark(y) then
        		// Find the Label corresponding to the destination of the Arc
        		
        			// if(cost(y) > cost(x)+W(x,y)) then
        				// Cost(y) = Cost(x)+W(x,y)
        				// if Exist(y,Tas) then
        					// update(y,Tas)
        				// else
        					// Insert(y,Tas)
        	}
        }
        return solution;
    }

}
