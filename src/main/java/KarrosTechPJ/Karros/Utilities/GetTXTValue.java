package KarrosTechPJ.Karros.Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GetTXTValue {
	
	public GetTXTValue() {
		
	}
	
	/*
	//This method is to read value from the TXT file which using delimiter "|", in this we are passing parameters as Row no and Col no
	public GetTXTValue(String filepath, int row, int column) throws IOException {
		
		File file = new File(filepath); 
		BufferedReader reader = new BufferedReader(new FileReader(file));
    
		String line = reader.readLine();    
		int line_no = 0;
		while (line != null)
		{
			if (line_no == row-1)
				break;
			line = reader.readLine();
			line_no++;
		}
		
		if (line != null)
		{	String[] _value = line.split("\\|");			
			if (column <= _value.length)
			   this.return_value = _value[column-1];
		}
		reader.close();
	}
	*/	
	
	//This method is to return value from the TXT file which using delimiter "|".
	public String getAValue(String filepath, int row, int column) throws IOException {
		
		String return_value = null;
		
		File file = new File(filepath); 
		BufferedReader reader = new BufferedReader(new FileReader(file));
    
		String line = reader.readLine();    
		int line_no = 0;
		while (line != null)
		{
			if (line_no == row-1)
				break;
			line = reader.readLine();
			line_no++;
		}
		
		if (line != null)
		{	String[] _value = line.split("\\|");			
			if (column <= _value.length)
			   return_value = _value[column-1];
		}
		reader.close();
		return return_value;
	}
	
	public int getTotalLine(String filepath) throws IOException {	
		
		File file = new File(filepath); 
		BufferedReader reader = new BufferedReader(new FileReader(file));
    
		String line = reader.readLine();    
		int line_no = 0;
		while (line != null)
		{
			line = reader.readLine();
			line_no = line_no + 1;
		}
		reader.close();
		return line_no;
	}
	
}
