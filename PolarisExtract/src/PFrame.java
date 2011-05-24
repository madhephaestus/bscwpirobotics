import java.util.ArrayList;


public class PFrame {
	
	private int ind;
	private double[] dv;
	
	public PFrame(int frameIndex, double[] values)
	{
		ind = frameIndex;
		dv = values;
	}
	
	public int getFrameIndex() {return ind;}
	public double[] getValues() {return dv;}
	public double getValueAt(int n) {return dv[n];}
	
	public String toString()
	{
		String s = "CHECK: " + ind;
		for (int i=0; i< dv.length;i++)
		{
			s = s + " " + dv[i];
		}
		return s;
	}
	
	public static PFrame getAverageFrame(PFrame a, PFrame b)
	{
		PFrame avg;
		
		int aFrame = (a.getFrameIndex() + b.getFrameIndex())/2;
		double[] aValues = new double[9];
		for (int i = 0; i < aValues.length; i++)
		{
			aValues[i] = (a.getValueAt(i)+b.getValueAt(i))/2.0;
		}
		
		avg = new PFrame(aFrame, aValues);
		return avg;
	}
	
	public static PFrame add(PFrame a, PFrame b)
	{
		double[] d = new double[9];
		
		for (int i = 0; i < 9; i++)
		{
			d[i] = a.getValueAt(i) + b.getValueAt(i);
		}
		
		return new PFrame(a.getFrameIndex() + b.getFrameIndex(), d);
	}
	
	public static PFrame add(ArrayList<PFrame> a)
	{
		PFrame result;
		
		result = a.get(0);
		for(int i = 1; i < a.size()-1; i++)
		{
			result = PFrame.add(result, a.get(i));
		}
		
		return result;
	}
	
	public static PFrame divide(PFrame a, int n)
	{
		double[] d = new double[9];
		
		for (int i = 0; i < 9; i++)
		{
			d[i] = a.getValueAt(i)/n;
		}
		
		return new PFrame(a.getFrameIndex()/n, d);
	}
	
	public void format()
	{
		dv[0] = (int)(dv[0]*10000)/10000.0;
		dv[1] = (int)(dv[1]*10000)/10000.0;
		dv[2] = (int)(dv[2]*10000)/10000.0;
		dv[3] = (int)(dv[3]*10000)/10000.0;
		dv[4] = (int)(dv[4]*100)/100.0;
		dv[5] = (int)(dv[5]*100)/100.0;
		dv[6] = (int)(dv[6]*100)/100.0;
		dv[7] = (int)(dv[7]*10000)/10000.0;
	}
	
//	public double rSigFig(double num, int n) {
//	    if(num == 0) {
//	        return 0;
//	    }
//
//	    final double d = Math.ceil(Math.log10(num < 0 ? -num: num));
//	    final int power = n - (int) d;
//
//	    final double magnitude = Math.pow(10, power);
//	    final long shifted = Math.round(num*magnitude);
//	    
//	    return shifted/magnitude;
//	}
	
	public boolean isDuplicate(PFrame p)
	{
		return (p.getFrameIndex() == this.getFrameIndex());
	}
	
}
