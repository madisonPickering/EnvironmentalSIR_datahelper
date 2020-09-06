import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
	
	public static ArrayList<Dataline> runningTot;
	
	public static void main(String[] args)
	{
		//initialize the running total
		runningTot = new ArrayList<>();
		
		//process each file in the input
		File root = new File("input");
		File[] files = root.listFiles();
		for (int i = 0; i < files.length; i++)
			processFile(files[i]);
		
		//log the info read in into an output file
		logOutput();

	}
	
	/**Log the info read into runningTot into an output file,
	 * logging the average value (arithmetic mean) of each value of runningTot
	 */
	public static void logOutput()
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
			//"throw away" the first line of the file since it contains metadata
			Scanner tokenScanner = new Scanner(lineScanner.nextLine());
			tokenScanner.useDelimiter(", "); //since we are parsing .csv
			int lineNum = 0; //use to keep track of which line we are parsing
			
			//iterate through the file, adding each line to the running total
			while (lineScanner.hasNext())
			{
				tokenScanner = new Scanner(lineScanner.nextLine());
				lineNum++;
				
				//grab each value and store it in a Dataline if needed
				while (tokenScanner.hasNext())
				{
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
					if (lineNum > runningTot.size())
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
