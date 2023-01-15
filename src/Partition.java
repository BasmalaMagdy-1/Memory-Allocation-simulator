import java.util.Vector;

public class Partition {

	public String Name;
	public int Size;
	 String occupation;
	    boolean status;
	
	public Partition(String n,int s)
	{
		Name = n;
		Size = s;
		this.occupation = "External fragment";
        this.status = false;
		
	}
	
	public String get_name()
	{
		return Name;
	}
	
	public int get_size()
	{
		return Size;
	}
	

}
