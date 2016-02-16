package utils;

public class Test_MinPriorityQueue {

	
	public static void main(String [] args){
    	
    	MinPriorityQueue<Integer> testQ = new MinPriorityQueue<Integer>();

    	System.out.println(testQ.isEmpty()); //true
    	System.out.println (testQ.size()); // 0
    	testQ.add(5); 
    	System.out.println (testQ.size()); // 1
    	System.out.println(testQ.isEmpty()); // false
    	testQ.add(9); 
    	testQ.add(10);
    	testQ.add(8);
    	testQ.add(7);
    	System.out.println (testQ.size()); // 5
    	System.out.println(testQ.isEmpty()); // false

    	for(int i = 0; i < 6; i++){
    		
    		System.out.println(testQ.remove()); // 5, 7, 8, 9, 10 , null
    	}

    	
    	for(int i = 1; i<10; i++){
    		testQ.add(i);
    	}
    	
		System.out.println("Removal"); //Removal

    	for(int i = 0; i < 12; i++){    		
    		System.out.println(testQ.remove()); // 1, 2, 3, 4, 5 ... 9, null...
        	//testQ.printArray();
    	}
    	
    	return;
    }
}
