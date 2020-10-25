import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class ReadFile {
	
	public static ArrayList<String> fileReader(String fileName, Map<String, Integer> fileWords, ArrayList<String> array)
	{
		File checker = new File(fileName);
        	//methods
          	wordCount(checker, fileWords);
           	array = sortWordFreq(fileWords);
		return array;
	}
	
	
	public static Map<String, Integer> wordCount(File fileName, Map<String, Integer> fileWords)
	    {
	    	Scanner file;//setting up text file to be scanned and as well as passing values
			try {
				file = new Scanner(fileName);
				file.useDelimiter("[^a-zA-Z]+");//limiting string to alphabets 
		    	while (file.hasNext())//while the text file contains a token it moves on until it reaches none
		    	{
		    		
		    		String tempPasser = file.next();//passing the scanner token that it encounter into the string
		    		
		    		String trackingWords = tempPasser.toLowerCase();//passing string once all of the string has been lower cased
		    		Integer countWords = fileWords.get(trackingWords);//key and integer is passed but integer is recorded
		    		if(countWords != null)//once integer is passed checks if its null
		    		{
		    			countWords++;//if not null integer goes up by 1 
		    			//this happens when key if a known key is passed and already has a value
		    		}
		    		else
		    		{
		    			//if key is null it gets 1 to represent its first iteration
		    			countWords = 1;
		    		}
		    		fileWords.put(trackingWords, countWords);//key and value gets recorded and loop goes again
		    	}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return fileWords;
	    }

	public static ArrayList<String> sortWordFreq(Map<String,Integer> fileWords)
	    {
	    	//this creates a set containing the same elements as the hashmap as well as passing the set into the list
	    	List<Entry<String, Integer>> passingList = new LinkedList<Entry<String, Integer>>(fileWords.entrySet());
			ArrayList<String> orderArray = new ArrayList<String>();
	    	//using collection sorts allows the sorting of the passingList and as well as using the comparator interface as a anonymous class
	    	//we can then compare two objects of the set and return the sorted elements into the passingList.
	    	Collections.sort(passingList, new Comparator<Entry<String, Integer>>()
	    			{

						@Override
						public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) 
						{
							// TODO Auto-generated method stub
							return o2.getValue().compareTo(o1.getValue());
						}
	    		
	    			});
	    	
	    	int tempIndex = 0;//temp value for limiting list
	    	
	    	//initially I was going to transfer the list back to the map but if we can just using the sorted list 
	    	//to show the ordered items then i think its fine
	    	for(Entry<String,Integer> e : passingList)//enchanced for loop
	    	{
	    		tempIndex++;
	    		if(tempIndex <= 20)//this will be printed until the temp variable reaches past 21
	    		{
	    			if(tempIndex == 1) {
		    			String getStringText =e.getKey() + " " + e.getValue();
		    			orderArray.add(getStringText);
	    			}
	    			else {
		    			String getStringText ="\n" + e.getKey() + " " + e.getValue();
		    			orderArray.add(getStringText);
	    			}
	    		}
	    		else
	    		{
	    			//System.out.println(orderArray);
	    			
	    			return orderArray;
	    		}
	    	}
			return orderArray;
	    	
	    }
}
