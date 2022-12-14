package simulation;

import java.sql.Array;
import java.util.ArrayList;
/**
 *	A sink
 *	@author Joel Karel
 *	@version %I%, %G%
 */
public class Sink implements PatientAcceptor
{
	/** All patients are kept */
	private ArrayList<Patient> patients;
	/** All properties of patients are kept */
	private ArrayList<Integer> numbers;
	private ArrayList<Double> times;
	private ArrayList<String> events;
	private ArrayList<String> stations;
	public ArrayList<Integer>types;
	public ArrayList<double[]>coord;
	/** Counter to number patients */
	private int number;
	/** Name of the sink */
	private String name;
	
	/**
	*	Constructor, creates objects
	*/
	public Sink(String n)
	{
		name = n;
		patients = new ArrayList<>();
		numbers = new ArrayList<>();
		times = new ArrayList<>();
		events = new ArrayList<>();
		stations = new ArrayList<>();
		types=new ArrayList<>();
		coord=new ArrayList<>();
		number = 0;
	}
	
        @Override
	public boolean giveProduct(Patient p)
	{
		number++;
		patients.add(p);
		// store stamps
		ArrayList<Double> t = p.getTimes();
		ArrayList<String> e = p.getEvents();
		ArrayList<String> s = p.getAmbulances();
		ArrayList<double[]>d=p.locations;
		for(int i=0;i<t.size();i++)
		{
			numbers.add(number);
			times.add(t.get(i));
			events.add(e.get(i));
			stations.add(s.get(i));
			types.add(p.type);
			coord.add(d.get(i));
		}
		return true;
	}

	public boolean crewChangeStamp(double time,String station, int type,double[] c){
		numbers.add(0);
		times.add(time);
		stations.add(station);
		events.add("Crew Change");
		types.add(type);
		coord.add(c);
		return true;
	}
	
	public int[] getNumbers()
	{
		numbers.trimToSize();
		int[] tmp = new int[numbers.size()];
		for (int i=0; i < numbers.size(); i++)
		{
			tmp[i] = (numbers.get(i)).intValue();
		}
		return tmp;
	}

	public double[] getTimes()
	{
		times.trimToSize();
		double[] tmp = new double[times.size()];
		for (int i=0; i < times.size(); i++)
		{
			tmp[i] = (times.get(i)).doubleValue();
		}
		return tmp;
	}

	public String[] getEvents()
	{
		String[] tmp = new String[events.size()];
		tmp = events.toArray(tmp);
		return tmp;
	}

	public String[] getStations()
	{
		String[] tmp = new String[stations.size()];
		tmp = stations.toArray(tmp);
		return tmp;
	}
}