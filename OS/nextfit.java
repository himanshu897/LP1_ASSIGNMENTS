import java.util.Scanner;
public class NextFit{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
//      taking input for all partitions.
        System.out.println("Enter number of partitions");
//        storing number of partitions.
        int partitionCount = sc.nextInt();
//        creating a array to store all the partitions.
        int partitionList[] = new int[partitionCount];
//        using for loop to store each individual partition.
        for(int i =0;i<partitionCount;i++){
            System.out.println("Enter "+(i+1)+"th partition");
            partitionList[i] = sc.nextInt();
        }
//      taking input for all processes.
        System.out.println("Enter number of processes");
//        storing number of processes.
        int processCount = sc.nextInt();
//        creating a array to store all the process.
        int processList[] = new int[processCount];
//        using for loop to store each individual process.
        for(int i =0;i<processCount;i++){
            System.out.println("Enter "+(i+1)+"th partition");
            processList[i] = sc.nextInt();
        }

        //  to keep record of filled partitions.
        int filled[] = new int[partitionCount];
//            to store the next fit index for ith process.
        int nextFitIndex=0;
//        to traverse and choose each process.
        for(int i =0;i<processCount;i++){
//            flag to store if we find next fit.
            int flag = 0;
//            to set the second loop pointer at the last partition that was allocated.
            int j = nextFitIndex;
//            to traverse and check if the partition and process are compatible according to next fit condition.
            do{
                if(partitionList[j]>=processList[i]&&filled[j]!=1){
//                    to store the partition index in nextFitIndex variable.
                    nextFitIndex=j;
//                    mark filled.
                    filled[j]=1;
//                    to indicate that we found next fit.
                    flag = 1;
                    break;
                }
                j++;
//                  to point j back to the start of the partition list after it goes out of bound.
                if(j==partitionCount)j=0;
//              loop till the pointer doesn't come back to the starting position.
            }while(j!=nextFitIndex);

//            if we dont find Next fit.
            if(flag==0){
                System.out.println("Next fit not found");
            }
//           else we found Next fit.
            else{
                System.out.println("Next Fit for process "+(i+1)+" is "+partitionList[nextFitIndex]+" and Hole of "+(partitionList[nextFitIndex]-processList[i])+" is created.");
            }
        }

    }
}
