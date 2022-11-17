import java.util.Scanner;

public class pt2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x, y;
        System.out.println("no of processes: ");
        x = sc.nextInt();
        int processSize[] = new int[x];
        System.out.println("no of blocks: ");
        y = sc.nextInt();
        int blocksize[] = new int[y];
        System.out.println("\nEnter processes : ");
        for (int i = 0; i < x; i++) {
            System.out.println("\tP" + (i + 1) + ":  ");
            processSize[i] = sc.nextInt();
        }
        System.out.println("\nEnter blocks : ");
        for (int i = 0; i < y; i++) {
            System.out.println("\tB" + (i + 1) + ":  ");
            blocksize[i] = sc.nextInt();
        }
        int m = blocksize.length;
        int n = processSize.length;
        bestFit(blocksize, m, processSize, n);
    }


    static void bestFit(int blockSize[], int m, int processSize[],
                        int n) {
        // Stores block id of the block allocated to a
        // process
        int allocation[] = new int[n];

        // Initially no block is assigned to any process
        for (int i = 0; i < allocation.length; i++)
            allocation[i] = -1;

        // pick each process and find suitable blocks
        // according to its size ad assign to it
        for (int i = 0; i < n; i++) {
            // Find the best fit block for current process
            int bestIdx = -1;
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (bestIdx == -1)
                        bestIdx = j;
                    else if (blockSize[bestIdx] > blockSize[j])
                        bestIdx = j;
                }
            }

            // If we could find a block for current process
            if (bestIdx != -1) {
                // allocate block j to p[i] process
                allocation[i] = bestIdx;

                // Reduce available memory in this block.
                blockSize[bestIdx] -= processSize[i];
            }
        }

        System.out.println("\nProcess No.\tProcess Size\tBlock no.");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1)
                System.out.print(allocation[i] + 1);
            else
                System.out.print("Not Allocated");
            System.out.println();
        }
    }
}
