import java.util.Scanner;
import java.util.Vector;


class Pair {
	 
    // Pair attributes
	public String first;
	public String second;
    public int third;
    
 
    // Constructor to initialise pair
    public Pair(String first,  String second,int third)
    {
       
        this.first = first;
        this.second = second;
        this.third = third;
    }
}


public class First_Fit {
	
	public void firstfit(Vector<Partition>v1,Vector<Process>v2,int num)
	{
		
		Vector<Pair>ans = new Vector() ;
		Vector<Process>NotAllocated =  new Vector();
		
		 for(int i=0;i<v2.size();i++)
		 {
			 Boolean allocated= false;
			 for(int j=0;j<v1.size();j++)
			 {
				 
				 if(v2.get(i).Size <= v1.get(j).Size && v1.get(j).Size >0 )
				 {
					 Pair p=new Pair(v1.get(j).Name,v2.get(i).Name,v2.get(i).Size);
					 ans.add(p);
					 allocated=true;
					
					 //New partition is added
					 if(v2.get(i).Size < v1.get(j).Size){
						 num++;
						 String name ="Partition "+num;
						 int size = v1.get(j).Size- v2.get(i).Size;
						 Partition NewPartition = new Partition(name,size);
						 v1.add(NewPartition);
					 }
					 v1.get(j).Size=0;
					 break;
				 }
				 
			 }
			 if(!allocated)
			 NotAllocated.add(v2.get(i)); 
		 }
		 ////print all partitions
		 for(int k=0;k<v1.size();k++)
		 {
			 Boolean found=false;
			 int index = 0 ;
			 for(int i=0;i<ans.size();i++)
			 {
				 if(v1.get(k).Name==ans.get(i).first) {
					 found=true;
					 index=i;
				 }
					
			 }
			 if(found) {
					 System.out.println(v1.get(k).Name+" ("+ans.get(index).third+" KB) =>"+ans.get(index).second);	 
			 }else
				 if(v1.get(k).Size>0)
					 System.out.println(v1.get(k).Name+" ("+v1.get(k).Size+" KB) =>"+"External fragment");
		 }
		 
		 ///print not allocated processes
		 System.out.println();
		 for(int i=0;i<NotAllocated.size();i++)
		 {
			 System.out.println(NotAllocated.get(i).Name+" Can Not Be Allocated");
		 }
		 
		 Scanner objj2 = new Scanner(System.in);
			
			System.out.println("Do you want to compact? 1.yes 2.no");
			
			int option= objj2.nextInt();
			if(option==2) {
				return;
			}else
			{
				
				///print partitions after compaction
				Vector<Partition>compact = new Vector() ;
				int size=0;
				
				///compact all external fregments in one partition
				 for(int k=0;k<v1.size();k++)
				 {
					 Boolean found=false;
					 for(int i=0;i<ans.size();i++)
					 {
						 if(v1.get(k).Name==ans.get(i).first) {
							 found=true;
						 }
							
					 }
					 if(!found && v1.get(k).Size>0) {
						 size+= v1.get(k).Size;
						 v1.get(k).Size=0; //to skip these partitions 
					 }
					 
				 }
				 num++;
				 String name ="Partition "+num;
				 Partition NewPartition = new Partition(name,size);
				// compact.add(NewPartition);
				 v1.add(NewPartition);
				 
				 
				 ///allocation of not allocated processes
				 
				 for(int i=0;i<NotAllocated.size();i++)
				 {
						 for(int j=0;j<v1.size();j++)
						 {
							 
							 if(NotAllocated.get(i).Size <= v1.get(j).Size && v1.get(j).Size >0 )
							 {
								 Pair p=new Pair(v1.get(j).Name,NotAllocated.get(i).Name,NotAllocated.get(i).Size);
								 ans.add(p);
								 //New partition is added
								 if(v2.get(i).Size < v1.get(j).Size){
									 num++;
									 String name2 ="Partition "+num;
									 int size2 = v1.get(j).Size- NotAllocated.get(i).Size;
									 Partition NewPartition2 = new Partition(name2,size2);
									 v1.add(NewPartition2);
								 }
								 v1.get(j).Size=0;
								 break;
							 }
							 
						 }
					 
				 }
				 
				 //print all partitions after compaction
				 
				 for(int k=0;k<v1.size();k++)
				 {
					 Boolean found=false;
					 int index = 0 ;
					 for(int i=0;i<ans.size();i++)
					 {
						 if(v1.get(k).Name==ans.get(i).first) {
							 found=true;
							 index=i;
						 }
							
					 }
					 if(found) {
							 System.out.println(v1.get(k).Name+" ("+ans.get(index).third+" KB) =>"+ans.get(index).second);	 
					 }else
						 if(v1.get(k).Size>0)
							 System.out.println(v1.get(k).Name+" ("+v1.get(k).Size+" KB) =>"+"External fragment");
				 }
				 
				
				
			}
		
			
		 
	}
	
	

}
