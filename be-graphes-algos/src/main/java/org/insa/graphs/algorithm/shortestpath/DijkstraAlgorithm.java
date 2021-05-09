package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.List;

import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.algorithm.utils.EmptyPriorityQueueException;
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
        		label = new Label(node,false,Double.MAX_VALUE,0);
        	}
        	tabLabel.add(label);
        }
        
        // While non marked Nodes exist
        // Nodes ID 0 to N-1 and label insert in ArrayList in the same order
        while (!tabLabel.get(data.getDestination().getId()).getMarque()) {
        	//x = ExtractMin(Heap)
        	try {
        		currLabel = heap.deleteMin();
        	}catch (EmptyPriorityQueueException e) {
        		break;
        	}
        	//Mark(x) = True
        	currLabel.setMarque(true);
        	// For y successors of x
        	for (Arc arc : currLabel.getNode().getSuccessors()) {
        		// retrieve the label
        		Label succesor = null;
        		int index = 0;
        		while (index < tabLabel.size() && succesor.getNodeId()==arc.getDestination().getId()) {
        			succesor = tabLabel.get(index); 
        			index++;
        		}
        		// if not Mark(y) then
        		if (!succesor.getMarque()) {
        			// if(cost(y) > cost(x)+W(x,y)) then
        			if(succesor.getCost() > (currLabel.getCost()+arc.getMinimumTravelTime())) {
        				// Cost(y) = Cost(x)+W(x,y)
        				succesor.setCost(currLabel.getCost()+arc.getMinimumTravelTime());
        				succesor.setFather(arc);
        				// if Exist(y,Tas) then
        				try {
        					// update element in heap
        					heap.remove(succesor);
        					heap.insert(succesor);
        				}
        				catch(ElementNotFoundException broder) {
        					// Element not in the heap
        					heap.insert(succesor);
        				}
        			}
        		}
        	}
        	
        }
        // Create solution from father of each Node
        // Reverse to get path
        return solution;
    }

}
