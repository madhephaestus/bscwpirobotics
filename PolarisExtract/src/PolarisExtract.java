import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PolarisExtract {
	
	////private////
	private ArrayList<PFrame> frames;
	private final File fFile;
	private static void log(Object obj)
	{
		System.out.println(String.valueOf(obj));
	}
	////private////
	
	
	////main////
	public static void main(String[] args) throws FileNotFoundException 
	{	
		PolarisExtract pe = new PolarisExtract("C:/temp/P0A-37111001.txt");
		pe.processLines();
		PFrame resultant = pe.getTotalAverage();
		resultant.format();
		log("RESULTANT-" + resultant);
		log("Done.");
	}
	////main////
	
	
	////work////
	public PolarisExtract(String fileName)
	{
		fFile = new File(fileName);
		frames = new ArrayList<PFrame>();
	}
	
	public PFrame getTotalAverage()
	{
		PFrame average = frames.get(0);
		
		for (int i = 1; i < frames.size()-1; i++)
		{
			average = PFrame.add(frames);
		}
		
		average = PFrame.divide(average, frames.size()); 
		
		return average;
	}
	
	public final void processLines() throws FileNotFoundException
	{
		Scanner scanner = new Scanner(new FileReader(fFile));
		try
		{
			scanner.nextLine();
			scanner.nextLine();
			scanner.nextLine();
			scanner.nextLine();
			
			while (scanner.hasNextLine())
			{
				processLine(scanner.nextLine());
			}
		}
		finally
		{
			scanner.close();
		}
	}
	
	public void processLine(String line)
	{
		Scanner scanner = new Scanner(line);
		scanner.useDelimiter(",");
		if (scanner.hasNext())
		{
			String frame = scanner.next();
			if (frame.charAt(0)==('C'|'F'|'['|' '))
			{
				return;
			}
			frame = frame.trim();
			String a = scanner.next();
			a=a.trim();
			String b = scanner.next();
			b=b.trim();
			String c = scanner.next();
			c=c.trim();
			String d = scanner.next();
			d=d.trim();
			String e = scanner.next();
			e=e.trim();
			String f = scanner.next();
			f=f.trim();
			String g = scanner.next();
			g=g.trim();
			String h = scanner.next();
			h=h.trim();
			String i = scanner.next();
			i=i.trim();
			
			if (isValid(a))
			{
				double[] dex = new double[9];
				dex[0]=parse(a);
				dex[1]=parse(b);
				dex[2]=parse(c);
				dex[3]=parse(d);
				dex[4]=parse(e);
				dex[5]=parse(f);
				dex[6]=parse(g);
				dex[7]=parse(h);
				dex[8]=parse(i);
				
				PFrame next = new PFrame(Integer.parseInt(frame), dex);
				
				if (checkSpot(next))
					frames.add(next);
				else
				{
					frames.add(PFrame.getAverageFrame(frames.get(frames.size()-1),next));
				}
			}
			
			log(frames.get(frames.size()-1));
			//log("#"+frame+"#"+a+"#"+b+"#"+c+"#"+d+"#"+e+"#"+f+"#"+g+"#"+h+"#"+i);
		}
		else
		{
			log("Empty or invalid line");
		}
	}
	
	public boolean checkSpot(PFrame f)
	{
		if (frames.isEmpty())
			return true;
		if (f.isDuplicate(frames.get(frames.size()-1)))
			return false;
		
		return true;
	}
	public boolean isValid(String s)
	{
		return !(s.equals("MISSING"));
	}
	public double parse(String s)
	{
		return Double.parseDouble(s);
	}
	////work////

}
