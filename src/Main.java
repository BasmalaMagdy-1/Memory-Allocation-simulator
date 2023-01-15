
import java.util.Scanner;
import java.util.Vector;


public class Main {

	
	///Worst Policy 
	public static Vector<Partition> partitionList =new Vector<>();
    public static Vector<Process> processList = new Vector<>();
    public static Vector<Process> processListNew = new Vector<>();

    public static Partition getLargestPartition(Vector<Partition> partitionList)
    {
        int maxSize = 0;
        int idx = 0;
        for(int i=0; i<partitionList.size(); i++)
        {
            if(partitionList.get(i).Size > maxSize && !partitionList.get(i).status)
            {
                maxSize = partitionList.get(i).Size;
                idx = i;
            }
        }
        return partitionList.get(idx);
    }
    public static Vector<Partition> co(Vector<Partition> partitionList)
    {
        String test = "External fragment" ;
        int newSize = 0;
        ////delete external and stor size of new partition
        for(int i=0; i<partitionList.size(); i++)
        {
            String e = partitionList.get(i).occupation ;
            if(e.equals(test))
            {
                newSize += partitionList.get(i).Size;
                partitionList.remove(i);
                i--;

            }
        }
        ///// make new partition with new size
        Partition extra = new Partition("part" , newSize);
        partitionList.add(extra) ;
        ////renameing partitions
        for(int i=0; i<partitionList.size(); i++)
        {
                String s=Integer.toString(i) ;
                partitionList.get(i).Name="part"+s;
        }
        return partitionList;
    }
    
    
    
    
    
    
    
	public static void main(String[] args) {
		Scanner objj = new Scanner(System.in);
		
		System.out.println("Select Policy: ");
		
		int policy= objj.nextInt();
		
		if(policy==1)           /////First Fit policy
		{
			
			Vector<Partition>v1= new Vector(); 
			Vector<Process>v2 = new Vector();
			Scanner input = new Scanner(System.in);
			Scanner input0 = new Scanner(System.in);
			
			
			System.out.println("Enter number of partitions: ");
			
			int partition_num= input.nextInt();
			
			for(int i=0 ;i<partition_num;i++)
			{
				System.out.println("Enter Partition ["+(i+1)+"] Size");
				String name = "Partition "+(i+1);
				int size =input0.nextInt();
				Partition p=new Partition(name, size);
				v1.add(p);
				
			}
			
			Scanner input2 = new Scanner(System.in);
			
			System.out.println("Enter number of processes:");
			
			int process_num= input.nextInt();
			
			for(int i=0 ;i<process_num;i++)
			{
				System.out.println("Enter Proccess ["+(i+1)+"] Size");
				String name = "Process"+(i+1);
				int size =input0.nextInt();
				Process p =new Process(name, size);
				v2.add(p);
				
			}
			
			First_Fit f = new First_Fit();
			f.firstfit(v1,v2,partition_num);
			
		
			
		}
		else if(policy==2)   /////Best-Fit policy
		{
			
			Vector<Partition> v1 = new Vector();
			Vector<Process> v2 = new Vector();
			Scanner input = new Scanner(System.in);
			Scanner input0 = new Scanner(System.in);

			System.out.println("Enter number of partitions: ");

			int partition_num = input.nextInt();

			for (int i = 0; i < partition_num; i++) {
				System.out.println("Enter Partition [" + (i + 1) + "] Size");
				String name = "Partition " + (i + 1);
				int size = input0.nextInt();
				Partition p = new Partition(name, size);
				v1.add(p);

			}

			Scanner input2 = new Scanner(System.in);

			System.out.println("Enter number of processes:");

			int process_num = input.nextInt();

			for (int i = 0; i < process_num; i++) {
				System.out.println("Enter Proccess [" + (i + 1) + "] Size");
				String name = "Process" + (i + 1);
				int size = input0.nextInt();
				Process p = new Process(name, size);
				v2.add(p);

			}

			Best_Fit b = new Best_Fit();
			b.bestFit(v2, v1);
		}
		else   //////Worst-Fit policy
		{

			
			 Process p1 = new Process("p1", 15);
		        Process p2 = new Process("p2", 90);
		        Process p3 = new Process("p3", 30);
		        Process p4 = new Process("p4", 100);
		        
		        Partition part0 = new Partition("part0", 90);
		        Partition part1 = new Partition("part1", 20);
		        Partition part2 = new Partition("part2", 5);
		        Partition part3 = new Partition("part3", 30);
		        Partition part4 = new Partition("part4", 120);
		        Partition part5 = new Partition("part5", 80);

		        processList.add(p1);
		        processList.add(p2);
		        processList.add(p3);
		        processList.add(p4);

		        partitionList.add(part0);
		        partitionList.add(part1);
		        partitionList.add(part2);
		        partitionList.add(part3);
		        partitionList.add(part4);
		        partitionList.add(part5);

		        int size = 6;

		        
		            
		        for(int i=0; i<processList.size(); i++)
		        	
		        {
		            Partition largest = getLargestPartition(partitionList);
		            if(processList.get(i).Size > largest.Size){
		                System.out.println("Process " + processList.get(i).Name + " can not be allocated");
		                processListNew.add(processList.get(i)) ;
		            }
		            else if(processList.get(i).Size == largest.Size)
		            {
		                largest.status = true;
		                largest.occupation = processList.get(i).Name;
		            }
		            else
		            {
		                int newSize = largest.Size - processList.get(i).Size;
		                Partition newPart = new Partition("part" + size, newSize);
		                size++;
		                largest.Size = processList.get(i).Size;
		                largest.occupation = processList.get(i).Name;
		                largest.status = true;
		                partitionList.add(newPart);
		            }
		        }

		        for(int i=0; i<partitionList.size(); i++) {
		            System.out.print(partitionList.get(i).Name + " ");
		            System.out.print(partitionList.get(i).Size + " ");
		            System.out.print(partitionList.get(i).occupation + " ");
		            System.out.println();
		        }

		        Scanner sc= new Scanner(System.in); //System.in is a standard input stream.
		        System.out.print(" do com enter 1 or 2 for not  ");
		        int  str= sc.nextInt(); //reads string.
		        if(str==1){
		            Vector<Partition> partitionListAfterCo = new Vector<>();
		            partitionListAfterCo = co(partitionList) ;
		            for(int i=0; i<partitionListAfterCo.size(); i++)
		            {
		                System.out.print(partitionListAfterCo.get(i).Name + " ");
		                System.out.print(partitionListAfterCo.get(i).Size + " ");
		                System.out.print(partitionListAfterCo.get(i).occupation + " ");
		                System.out.println();
		            }
		            System.out.println("//////////////////////////after compaction////////////");
		            for(int i=0; i<processListNew.size(); i++)
		            {
		                Partition largest = getLargestPartition(partitionListAfterCo);
		                if(processListNew.get(i).Size > largest.Size)
		                    System.out.println("Process " + processListNew.get(i).Name + " can not be allocated");
		                else if(processListNew.get(i).Size == largest.Size)
		                {
		                    largest.status = true;
		                    largest.occupation = processListNew.get(i).Name;
		                }
		                else
		                {
		                    int newSize = largest.Size - processListNew.get(i).Size;
		                    Partition newPart = new Partition("part" + size, newSize);
		                    size++;
		                    largest.Size = processListNew.get(i).Size;
		                    largest.occupation = processListNew.get(i).Name;
		                    largest.status = true;
		                    partitionListAfterCo.add(newPart);
		                }
		            }
		            for(int i=0; i<partitionListAfterCo.size(); i++)
		            {
		                System.out.print(partitionListAfterCo.get(i).Name + " ");
		                System.out.print(partitionListAfterCo.get(i).Size + " ");
		                System.out.print(partitionListAfterCo.get(i).occupation + " ");
		                System.out.println();
		            }
		        }
		        
			
		}
		
		
		

	}

}
