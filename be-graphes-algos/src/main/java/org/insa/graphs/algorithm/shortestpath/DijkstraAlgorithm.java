package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.algorithm.utils.EmptyPriorityQueueException;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    };

    @Override
    protected ShortestPathSolution doRun() {
    	
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        // Label list
        ArrayList<Label> tabLabel = new ArrayList<Label>();
        // BinaryHeap
        BinaryHeap<Label> heap = new BinaryHeap<Label>();
        Label currLabel;
        
        
        // Initialize label for each Node
        for (Node node : data.getGraph().getNodes()) {
        	// origin cost 0
        	Label label = null;
        	if (node == data.getOrigin()) {
        		label = new Label(node,false,0,null);
        		// Insert origin in heap
        		heap.insert(label);
        	}else {
        		label = new Label(node,false,Double.MAX_VALUE,null);
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
        		// retrieve the label check line 47
        		Label succesor = tabLabel.get(arc.getDestination().getId());
        		/**
        		int index = 0;
        		while (index < tabLabel.size() && succesor.getNodeId()==arc.getDestination().getId()) {
        			succesor = tabLabel.get(index); 
        			index++;
        		}
        		*/
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
        // Unfeasible
        if(!tabLabel.get(tabLabel.size()-1).getMarque()) {
        	solution = new ShortestPathSolution(data,Status.INFEASIBLE);
        }else {
        // Create solution from father of each Node
        	ArrayList<Node> nodes = new ArrayList<Node>();
        	Node father = data.getDestination();
        	while (!father.equals(data.getOrigin())) {
        		nodes.add(father);
        		// On va chercher dans le graph le node qui correspond a l'origine de l'arc pere contenu dans le label correspondant au node actuel
        		father = data.getGraph().get(tabLabel.get(father.getId()).getFather().getOrigin().getId());
        	}
        	nodes.add(data.getOrigin());
        	// Reverse to create Path
        	Collections.reverse(nodes);
        	Path path = null;
        	if (data.getMode().equals(AbstractInputData.Mode.TIME)) {
        		path = Path.createFastestPathFromNodes(data.getGraph(), nodes);
        	}else {
        		path = Path.createShortestPathFromNodes(data.getGraph(), nodes);
        	}
        	solution = new ShortestPathSolution(data,Status.OPTIMAL,path);
        };
        return solution;
    }

}
