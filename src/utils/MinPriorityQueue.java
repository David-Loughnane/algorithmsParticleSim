package utils;

import java.util.ArrayList;

public class MinPriorityQueue<T extends Comparable<T>> {
    
	private ArrayList<T> priorityQueue;
	
    /**
     * Creates an empty queue.
     */
    public MinPriorityQueue() {
        //we want an initial length of 1 since index 0 is always going to be empty
    	priorityQueue = new ArrayList<T>();
    	priorityQueue.add(null);
    	
    }

    /**
     * Returns the number of elements currently in the queue.
     */
    public int size() {
    	//returns current size subtracting the index 0
        return priorityQueue.size() - 1;
    }
    
    /**
     * Adds elem to the queue.
     */
    public void add(T elem) {

    	//add elem to the end
		priorityQueue.add(elem);
    	
    	//check for size, this may be the first element
    	if(priorityQueue.size() == 2){
    		return;
    	}
    	
    	//get parent index and compare
    	int elemIndex = priorityQueue.size()-1;
    	int parentIndex = elemIndex/2;
    	
    	//compare and swap loop to resort the array
    	while(elemIndex != 1 && elem.compareTo(priorityQueue.get(parentIndex)) < 0 ){
    		
    		T temp = priorityQueue.get(parentIndex);
    		priorityQueue.set(parentIndex, priorityQueue.get(elemIndex));
    		priorityQueue.set(elemIndex,temp);
    		elemIndex = parentIndex;
    		parentIndex = elemIndex/2;
    	}
    	
    }

    /**
     * Removes, and returns, the element at the front of the queue.
     */
    public T remove() {

    	if(isEmpty()){
    		return null;
    	}
    	
    	//hold the object at index 1
    	T returnObj = priorityQueue.get(1);  
    	
    	//put the object from the end of the list at the beginning
    	priorityQueue.set(1, priorityQueue.get(priorityQueue.size()-1));
    	priorityQueue.remove(priorityQueue.size()-1);
    	//resort the queue
    	int elemIndex = 1;
    	
    	while(true){
    		int b1 = 0, b2 = 0, newIndex;
    		//check for end	
    		if(elemIndex > priorityQueue.size()){
    			break;
    		}
    		//check both child branches, if either are lower store the index
    		boolean sorted = true;
    		if(priorityQueue.size() > elemIndex*2 && priorityQueue.get(elemIndex).compareTo(priorityQueue.get(elemIndex*2)) > 0){
    			b1 = elemIndex*2;
    			sorted = false;
    		}
    		if(priorityQueue.size() > (elemIndex*2)+1 && priorityQueue.get(elemIndex).compareTo(priorityQueue.get((elemIndex * 2) +1)) > 0){
    			b2 = (elemIndex*2) + 1;
    			sorted = false;
    		}
    		//if sorted remains true and we can break
    		if(sorted == true){
    			break;
    		}
    		//switch if either b1 or b2 is not 0
    		if(b1!=0 && b2!=0){
			
    			if(priorityQueue.get(b1).compareTo(priorityQueue.get(b2)) < 0){
    				newIndex = b1;
    			}
    			else{
    				newIndex = b2;
    			}
    		}
    		else if(b1!=0){
    			newIndex = b1;
    		}
    		else{
    			newIndex = b2;
    		}
			T temp = priorityQueue.get(elemIndex);
			priorityQueue.set(elemIndex, priorityQueue.get(newIndex));
			priorityQueue.set((newIndex), temp);
			elemIndex = (newIndex); 
			
			//printArray();
    		
    	}
    	return returnObj;
    }

    /**
     * Returns true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {

    	//check if size is 1
    	if(priorityQueue.size() == 1){
    		return true;
    	}
    	return false;
    }
    
    public void printArray(){
		System.out.println("Start Queue");
		for(int i = 1 ; i < priorityQueue.size(); i++){
			System.out.println(priorityQueue.get(i));	
		}
		System.out.println("End Queue");
    }
    
    
}
