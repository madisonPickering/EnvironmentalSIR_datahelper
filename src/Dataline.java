
public class Dataline
{
	private int numLines;
	private int numSus;
	private int numInf;
	private int numRec;
	private int numEnv;
	private int numAg;
	private int numAgInf;
	private double avgNumAgInf;
	private int numAgTransit;
	private int numAgEnv;
	private double avgNumAgEnv;
	private int numAgRecRmv;
	private double avgNumAgRecRmv;
	private int numAgEnvRmv;
	private double avgNumAgEnvRmv;
	
	public Dataline(int numSus, int numInf, int numRec, int numEnv, int numAg,
			int numAgInf, double avgNumAgInf, int numAgTransit, int numAgEnv,
			double avgNumAgEnv, int numAgRecRmv, double avgNumAgRecRmv,
			int numAgEnvRmv, double avgNumAgEnvRmv)
	{
		numLines = 0;
		this.numSus = numSus;
		this.numInf = numInf;
		this.numRec = numRec;
		this.numEnv = numEnv;
		this.numAg = numAg;
		this.numAgInf = numAgInf;
		this.avgNumAgInf = avgNumAgInf;
		this.numAgTransit = numAgTransit;
		this.numAgEnv = numAgEnv;
		this.avgNumAgEnv = avgNumAgEnv;
		this.numAgRecRmv = numAgRecRmv;
		this.avgNumAgRecRmv = avgNumAgRecRmv;
		this.numAgEnvRmv = numAgEnvRmv;
		this.avgNumAgEnvRmv = avgNumAgEnvRmv;
	}
	
	//"getters"
	public int getNumLines() { return numLines; }
	public int getNumSus() { return numSus; }
	public int getNumInf() { return numInf; }
	public int getNumRec() { return numRec; }
	public int getNumEnv() { return numEnv; }
	public int getNumAg() { return numAg; }
	public int getNumAgInf() { return numAgInf; }
	public double getAvgNumAgInf() { return avgNumAgInf; }
	public int getNumAgTransit() { return numAgTransit; }
	public int getNumAgEnv() { return numAgEnv; }
	public double getAvgNumAgEnv() { return avgNumAgEnv; }
	public int getNumAgRecRmv() { return numAgRecRmv; }
	public double getAvgNumAgRecRmv() { return avgNumAgRecRmv; }
	public int getNumAgEnvRmv() { return numAgEnvRmv; }
	public double getAvgNumAgEnvRmv() { return avgNumAgEnvRmv; }
	
	//"setters"
	public void setNumSus(int newNumSus) { numSus = newNumSus; }
	public void setNumInf(int newNumInf) { numInf = newNumInf; }
	public void setNumRec(int newNumRec) { numRec = newNumRec; }
	public void setNumEnv(int newNumEnv) { numEnv = newNumEnv; }
	public void setNumAg(int newNumAg) { numAg = newNumAg; }
	public void setNumAgInf(int newNumAgInf) { numAgInf = newNumAgInf; }
	public void setAvgNumAgInf(double newAvgNumAgInf) { avgNumAgInf = newAvgNumAgInf; }
	public void setNumAgTransit(int newNumAgTransit) { numAgTransit = newNumAgTransit; }
	public void setNumAgEnv(int newNumAgEnv) { numAgEnv = newNumAgEnv; }
	public void setAvgNumAgEnv(double newAvgNumAgEnv) { avgNumAgEnv = newAvgNumAgEnv; }
	public void setNumAgRecRmv(int newNumAgRecRmv) { numAgRecRmv = newNumAgRecRmv; }
	public void setAvgNumAgRecRmv(double newAvgNumAgRecRmv) { avgNumAgRecRmv = newAvgNumAgRecRmv; }
	public void setNumAgEnvRmv(int newNumAgEnvRmv) { numAgEnvRmv = newNumAgEnvRmv; }
	public void setAvgNumAgEnvRmv(double newAvgNumAgEnvRmv) { avgNumAgEnvRmv = newAvgNumAgEnvRmv; }
	
	//other
	public void incrementNumLines()
	{
		numLines++;
	}
}
