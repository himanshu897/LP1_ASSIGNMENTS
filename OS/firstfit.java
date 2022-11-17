import java.util.Scanner;

public class pt2 {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int x,y;
        System.out.println("no of processes: ");
        x= sc.nextInt();
        int processSize[]=new int[x];
        System.out.println("no of blocks: ");
        y= sc.nextInt();
        int blocksize[]=new int[y];
        System.out.println("\nEnter processes : ");
        for (int i = 0;i<x;i++)
        {
            System.out.println("\tP"+(i+1)+":  ");
            processSize[i]=sc.nextInt();
        }
        System.out.println("\nEnter blocks : ");
        for (int i = 0;i<y;i++)
        {
            System.out.println("\tB"+(i+1)+":  ");
            blocksize[i]=sc.nextInt();
        }
        int m = blocksize.length;
        int n = processSize.length;
        implimentFirstFit(blocksize, m, processSize, n);
    }


    static void implimentFirstFit(int blockSize[], int blocks, int processSize[], int processes) {
        // This will store the block id of the allocated block to a process
        int allocate[] = new int[processes];
        int occupied[] = new int [blocks];

        // initially assigning -1 to all allocation indexes
        // means nothing is allocated currently
        for (int i = 0; i < allocate.length; i++)
            allocate[i] = -1;

        for(int i = 0; i < blocks; i++){
            occupied[i] = 0;
        }

        // take each process one by one and find
        // first block that can accomodate it
        for (int i = 0; i < processes; i++)
        {
            for (int j = 0; j < blocks; j++)
            {
                if (!(occupied[j] > 0) && blockSize[j] >= processSize[i])
                {
                    // allocate block j to p[i] process
                    allocate[i] = j;
                    occupied[j] = 1;

                    break;
                }
            }
        }

        System.out.println("\nProcess No.\tProcess Size\tBlock no.\n");
        for (int i = 0; i < processes; i++)
        {
            System.out.print(i + 1 + "\t\t\t" + processSize[i] + "\t\t\t");
            if (allocate[i] != -1)
                System.out.println(allocate[i] + 1);
            else
                System.out.println("Not Allocated");
        }
    }
}
