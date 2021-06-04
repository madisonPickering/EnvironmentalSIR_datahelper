/**Project calculates the average value per line
 * The output of EnvironmentalSIR (over many runs) is used as the input
 * to this program; it calculates average values over many lines.
 * 
 * @author Madison Pickering
 * (Copyright 2020 Madison Pickering)
 * 
 * This file is part of EnvironmentalSIR_datahelper.

    EnvironmentalSIR_datahelper is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    EnvironmentalSIR_datahelper is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with EnvironmentalSIR_datahelper.  If not, see <https://www.gnu.org/licenses/>.
 * 
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
	
	public static ArrayList<Dataline> runningTot;
	
	private static int averageEpidemicSize;
	
	public static void main(String[] args)
	{
		//initialize the running total
		runningTot = new ArrayList<>();
		
		//initialize the average epidemic size
		averageEpidemicSize = 0;
		
		//process each file in the input
		File root = new File("input");
		File[] files = root.listFiles();
		for (int i = 0; i < files.length; i++)
		{
			double currentFile = i;
			System.out.println("Processing file " + (i + 1) + " " + files[i].getName() + " of " + files.length 
				+ ". " + (currentFile / files.length) * 100 + " percent completed.");
			processFile(files[i]);
		}
		
		//log the info read in into an output file
		logOutput(files.length);
		
		System.out.println("All " + files.length + " files processed. Exiting...");

	}
	
	/**Log the info read into runningTot into an output file,
	 * logging the average value (arithmetic mean) of each value of runningTot
	 * @param numInputFiles the number of input files, used to calculate the
	 * average epidemic size
	 */
	public static void logOutput(int numInputFiles)
	{
		
		try
		{
			File output = new File("output/compiledStatistics.csv");
			output.createNewFile();
			FileWriter innerWriter = new FileWriter(output);
			BufferedWriter writer = new BufferedWriter(innerWriter);
			
			for (int i = 0; i < runningTot.size(); i++)
			{
				Dataline line = runningTot.get(i);
				double numFiles = line.getNumLines();
				//divide each data point by the number of files (take arith mean)
				double lineNumSus = (0.0 + line.getNumSus()) / numFiles;
				double lineNumInf = (0.0 + line.getNumInf()) / numFiles;
				double lineNumRec = (0.0 + line.getNumRec()) / numFiles;
				double lineNumEnv = (0.0 + line.getNumEnv()) / numFiles;
				double lineNumAg = (0.0 + line.getNumAg()) / numFiles;
				double lineNumAgInf = (0.0 + line.getNumAgInf()) / numFiles;
				double lineAvgNumAgInf = (0.0 + line.getAvgNumAgInf()) / numFiles;
				double lineNumAgTransit = (0.0 + line.getNumAgTransit()) / numFiles;
				double lineNumAgEnv = (0.0 + line.getNumAgEnv()) / numFiles;
				double lineAvgNumAgEnv = (0.0 + line.getAvgNumAgEnv()) / numFiles;
				double lineNumAgRecRmv = (0.0 + line.getNumAgRecRmv()) / numFiles;
				double lineAvgNumAgRecRmv = (0.0 + line.getAvgNumAgRecRmv()) / numFiles;
				double lineNumAgEnvRmv = (0.0 + line.getNumAgEnvRmv()) / numFiles;
				double lineAvgNumAgEnvRmv = (0.0 + line.getAvgNumAgEnvRmv()) / numFiles;
			
				String avgdLine = "" + i + ", " + lineNumSus + ", " + lineNumInf + ", "
						+ lineNumRec + ", " + lineNumEnv + ", " + lineNumAg + ", "
						+ lineNumAgInf + ", " + lineAvgNumAgInf + ", " + lineNumAgTransit
						+ ", " + lineNumAgEnv + ", " + lineAvgNumAgEnv + ", " + lineNumAgRecRmv
						+ ", " + lineAvgNumAgRecRmv + ", " + lineNumAgEnvRmv + ", " + lineAvgNumAgEnvRmv
						+ "\n";
				writer.write(avgdLine);
			}
			
			//finish by writing the average epidemic size out
			double avgEpi = averageEpidemicSize;
			double numInput = numInputFiles;
			avgEpi = (avgEpi / numInput);
			writer.write("\nAverage epidemic size: " + avgEpi + " individuals");
			
			writer.close();
		}	
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/** Reads each data line of the file, accumulating each data point
	 * into the correct line of runningTot
	 * @param file the file to read data from
	 */
	public static void processFile(File file)
	{
		try
		{
			
			Scanner lineScanner = new Scanner(file);
			//"throw away" the first two lines of the file since it contains metadata
			lineScanner.nextLine();
			Scanner tokenScanner = new Scanner(lineScanner.nextLine());
			int lineNum = -1; //use to keep track of how many times a line has been seen
							//and which line this is. Set to -1 so that it points to index 0 first iter
			
			//iterate through the file, adding each line to the running total
			while (lineScanner.hasNext())
			{
				tokenScanner = new Scanner(lineScanner.nextLine());
				tokenScanner.useDelimiter(", "); //since we are parsing .csv
				lineNum++;
				
				//grab each value and store it in a Dataline if needed
				while (tokenScanner.hasNext())
				{
					tokenScanner.next(); //throw out the line number in the file
					int numSus = tokenScanner.nextInt();
					int numInf = tokenScanner.nextInt();
					int numRec = tokenScanner.nextInt();
					int numEnv = tokenScanner.nextInt();
					int numAg = tokenScanner.nextInt();
					int numAgInf = tokenScanner.nextInt();
					double avgNumAgInf = tokenScanner.nextDouble();
					int numAgTransit = tokenScanner.nextInt();
					int numAgEnv = tokenScanner.nextInt();
					double avgNumAgEnv = tokenScanner.nextDouble();
					int numAgRecRmv = tokenScanner.nextInt();
					double avgNumAgRecRmv = tokenScanner.nextDouble();
					int numAgEnvRmv = tokenScanner.nextInt();
					double avgNumAgEnvRmv = tokenScanner.nextDouble();
				
					//if this line number hasn't been processed before, make it
					if (lineNum >= runningTot.size())
					{
						Dataline newline = new Dataline(numSus, numInf, numRec, numEnv,
								numAg, numAgInf, avgNumAgInf, numAgTransit, numAgEnv,
								avgNumAgEnv, numAgRecRmv, avgNumAgRecRmv, numAgEnvRmv,
								avgNumAgEnvRmv);
						runningTot.add(newline);
					}
					//otherwise, update the running total for that line
					else
					{
						Dataline line = runningTot.get(lineNum);
						line.incrementNumLines();
						line.setNumSus( line.getNumSus() + numSus );
						line.setNumInf( line.getNumInf() + numInf );
						line.setNumRec( line.getNumRec() + numRec );
						line.setNumEnv( line.getNumEnv() + numEnv );
						line.setNumAg( line.getNumAg() + numAg);
						line.setNumAgInf( line.getNumAgInf() + numAgInf);
						line.setAvgNumAgInf( line.getAvgNumAgInf() + avgNumAgInf);
						line.setNumAgTransit( line.getNumAgTransit() + numAgTransit);
						line.setNumAgEnv( line.getNumAgEnv() + numAgEnv);
						line.setAvgNumAgEnv( line.getAvgNumAgEnv() + avgNumAgEnv);
						line.setNumAgRecRmv( line.getNumAgRecRmv() + numAgRecRmv);
						line.setAvgNumAgRecRmv( line.getAvgNumAgRecRmv() + avgNumAgRecRmv);
						line.setNumAgEnvRmv( line.getNumAgEnvRmv() + numAgEnvRmv);
						line.setAvgNumAgEnvRmv( line.getAvgNumAgEnvRmv() + avgNumAgEnvRmv);
					}
					
					//if this is the last line in the file, update average epidemic size
					if (!lineScanner.hasNext())
						averageEpidemicSize += numRec;
				}
			}
			
			tokenScanner.close();
			lineScanner.close();
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("Exception thrown when processing file " + file.getName());
			e.printStackTrace();
		}
		
	}

}
