package urlInformation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoverMovement {

	private static Integer maxX, maxY, minX = 0, minY = 0, inputX,inputY,currentX, currentY;
	private static Character orientation, direction;
	private static final String inputString = "5 5\n" +
											  "1 2 N\n" +
											  "LMLMLMLMM\n" +
											  "3 3 E\n" +
											  "MMRMMRMRRM";
	private static final int inputLineSize = 2;
	
	
	public static void main(String [] args)
	{
		//List inputList = readFromFile();
		readInputAndStart(inputString);
	}
	

	public static void readInputAndStart(String inputString)
	{
		ArrayList<String> inputList = new ArrayList(Arrays.asList(inputString.split("\n")));		
		
		int i = 1;
		String location = null, movement = null; 
		List<String> oneMovement = new ArrayList<String>();
		while(oneMovement.size() <= inputLineSize && i<inputList.size())
		{
			oneMovement.add(inputList.get(i).toString());
			i++;
			
			if(oneMovement.size() == inputLineSize)
			{
				execute(inputList.get(0).toString(), oneMovement.get(0).toString(), oneMovement.get(1).toString());
				oneMovement = new ArrayList<String>();
			}
		}		
	}


	public static void execute(String maxValues, String location,String movement)
	{
		String [] maxValueArray = splitString(maxValues);
		String [] locationValueArray = splitString(location);
		assignMaxValues(maxValueArray);
		assignLocationValues(locationValueArray);

		currentX = inputX;
		currentY = inputY;


		for(int i=0;i<movement.length();i++)
		{
			Character c = movement.charAt(i);
			if(c == 'L' || c == 'R')
			{	
				updateOrientation(c);
			}
			else
			{
				updateLocation();
			}
		}

		System.out.println(currentX + " " + currentY + " "+ orientation);
	}
	
	public static void updateOrientation(Character direction)
	{
		Character ori = orientation;
		
		if(direction == 'R')
		{
			orientation = rightOrientation(ori);
		}
		else
		{
			orientation = leftOrientation(ori);
		}
	}
	
	public static Character rightOrientation(Character ori)
	{
		Map<Character,Character> map = new HashMap<Character,Character>();
		
		map.put('N', 'E');
		map.put('E', 'S');
		map.put('S', 'W');
		map.put('W', 'N');
		 
		return map.get(ori);
	}
	
	public static Character leftOrientation(Character ori)
	{
		Map<Character,Character> map = new HashMap<Character,Character>();
		
		map.put('N', 'W');
		map.put('E', 'N');
		map.put('S', 'E');
		map.put('W', 'S');
		 
		return map.get(ori);
	}

	public static void updateLocation()
	{
		if(orientation == 'N')
		{
			if(currentY != maxY)
			{
				currentY = currentY+1;
			}
		}
		if(orientation == 'E')
		{
			if(currentX != maxX)
			{	
				currentX = currentX+1;
			}
		}
		if(orientation == 'S')
		{
			if(currentY != minY)
			{
				currentY = currentY-1;
			}
		}
		if(orientation == 'W')
		{
			if(currentX != minX)
			{
				currentX = currentX-1;
			}
		}
	}

	public static String[] splitString(String str)
	{
		if(null!=str)
		{	
			return str.split(" ");
		}
		return null;	
	}

	public static void assignMaxValues(String[] maxValList)
	{
		if(null!=maxValList)
		{
			maxX = Integer.valueOf(maxValList[0]);
			maxY = Integer.valueOf(maxValList[1]);
		}
	}

	public static void assignLocationValues(String[] locationValList)
	{
		if(null!=locationValList)
		{
			inputX = Integer.valueOf(locationValList[0]);
			inputY = Integer.valueOf(locationValList[1]);
			orientation = locationValList[2].charAt(0);
		}
	}	
}
