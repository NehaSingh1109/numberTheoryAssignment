package org.numbertheory.ass1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment1 {
	
	public double getTF_IDF(String WordToSearch, ArrayList<String> FileList, ArrayList<ArrayList<String>> FilesList) {
		double TF = getTF(WordToSearch, FileList);
		double IDF = getIDF(WordToSearch, FilesList);
		return TF * IDF;
	}
	
	public double getTF(String WordToSearch, ArrayList<String> FileList) {
	    double sum = 0;
	    for (String Word : FileList)
	    {
	       if (WordToSearch.equalsIgnoreCase(Word))
	       {
		   		sum++;
	       }
	    }
	    return sum / FileList.size();
	}
	
	public double getIDF(String WordToSearch, ArrayList<ArrayList<String>> FilesList)
	{
		double n = 0;
		for (ArrayList<String> FileList : FilesList)
		{
	        for (String Word : FileList)
	        {
	            if (WordToSearch.equalsIgnoreCase(Word)) 
	            {
	                n++;
	                break;
	            }
	        }
	    }

	    return n!=0.0? Math.log(FilesList.size() / n):1;
	}

	public static void main(String[] args) throws IOException {
		
	    ArrayList<String> FileList1 = new ArrayList<String>();
	    ArrayList<String> FileList2 = new ArrayList<String>();
	    ArrayList<String> FileList3 = new ArrayList<String>();
		try {
			File file = new File(System.getProperty("user.dir")+"\\src\\org\\numbertheory\\ass1\\Assignment Files\\1.txt");
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String st; 
		    while ((st = br.readLine()) != null) 
			{
				  for(String word: st.split("\\?|\\)|\\(|\\:|\\;|\\.|\\,|\\'|\"|\\s+"))
				  {
					  FileList1.add(word);
				  }
			}
			  
			  file = new File(System.getProperty("user.dir")+"\\src\\org\\numbertheory\\ass1\\Assignment Files\\2.txt");
			  br = new BufferedReader(new FileReader(file));
			  
			  while ((st = br.readLine()) != null) 
			  {
				  for(String word: st.split("\\?|\\)|\\(|\\:|\\;|\\.|\\,|\\'|\"|\\s+"))
				  {
					  FileList2.add(word);
				  }
			  }
			  
			  file = new File(System.getProperty("user.dir")+"\\src\\org\\numbertheory\\ass1\\Assignment Files\\3.txt");
			  br = new BufferedReader(new FileReader(file));
			  
			  while ((st = br.readLine()) != null) 
			  {
				  for(String word: st.split("\\?|\\)|\\(|\\:|\\;|\\.|\\,|\\'|\"|\\s+"))
				  {
					  FileList3.add(word);
				  }
			  }
		    
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		finally {
			//System.out.println(FileList1);
			  //System.out.println(FileList2);
			  //System.out.println(FileList3);	
		}
		
		ArrayList<String> mergedWords = new ArrayList<String>();
	    mergedWords.addAll(FileList1);
	    mergedWords.addAll(FileList2);
	    mergedWords.addAll(FileList3);
	    ArrayList<ArrayList<String>> FilesList = new ArrayList<ArrayList<String>>();
	    FilesList.add(FileList1);
	    FilesList.add(FileList2);
	    FilesList.add(FileList3);
	    Assignment1 assignment = new Assignment1();
	    for(String Word : mergedWords.stream().distinct().toArray(String[]::new))
	    {
	    	double tfidf = assignment.getTF_IDF(Word,FileList1, FilesList);
	        System.out.println("TF-IDF of "+Word+" in File1 = " + tfidf);
	        tfidf = assignment.getTF_IDF(Word,FileList2, FilesList);
	        System.out.println("TF-IDF of "+Word+" in File2 = " + tfidf);
	        tfidf = assignment.getTF_IDF(Word,FileList3, FilesList);
	        System.out.println("TF-IDF of "+Word+" in File3 = " + tfidf);
	    }
	    
	}

}
