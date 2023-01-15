import java.util.Vector;
import java.util.Scanner;

class Best_Fit {
    public void bestFit(Vector<Process> v2, Vector<Partition> v1) {
        int remSize = 0;
        Scanner ans = new Scanner(System.in);
        Vector<Partition> comp = new Vector<>();
        Vector<Process> comproc = new Vector<>();

        // Iterate through each process and find the best-fitting partition
        for (Process process : v2) {
            int minSizeDiff = Integer.MAX_VALUE;
            Partition bestFit = null;

            for (Partition partition : v1) {
                int sizeDiff = partition.get_size() - process.get_size();
                if (sizeDiff >= 0 && sizeDiff < minSizeDiff) {
                    minSizeDiff = sizeDiff;
                    bestFit = partition;
                    remSize += bestFit.get_size() - process.get_size();
                }
            }
            comp.add(bestFit);
            comproc.add(process);
            // Allocate the process to the best-fitting partition
            if (bestFit != null) {

                System.out.println("Allocated process " + process.get_name() + " to partition " + bestFit.get_name());
            } else {
                System.out
                        .println("Could not allocate process " + process.get_name() + " - no suitable partition found");
            }

        }
        boolean found = false;
        for (Partition p : v1) {
            found = false;
            for (int i = 0; i < comp.size(); i++) {
                if (p.get_name() == comp.get(i).get_name()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println(p.get_name() + "=> External Fragment");
            }
        }
        System.out.println("Do you want to compact? 1.yes 2.no");
        int option = ans.nextInt();
        if (option == 2) {
            return;
        } else {
            Partition colc = new Partition("Collector Partion", remSize);
            comp.add(colc);
            for (int i = 0; i < comproc.size(); i++) {
                System.out.println(
                        comp.get(i).get_name() + "(" + comp.get(i).get_size() + "K)" + comproc.get(i).get_name());
            }
            System.out.println(comp.get(comp.size() - 1).get_name() + "(" + comp.get(comp.size() - 1).get_size()
                    + "K) => External Fragment");

        }

    }
}