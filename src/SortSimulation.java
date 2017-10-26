import java.io.IOException;

public class SortSimulation {
    public static void main(String[] args) throws IOException{
        int [][]arrayOfArrays = new int[3][1000000];
        for(int i=0;i<3;i++){
            for(int j = 0;j<1000000;j++){
                arrayOfArrays[i][j] = (int)(Math.random() * 1000000 - 500000);
            }
        }

        TestThread quick = new TestThread("Quick",arrayOfArrays[0]);
        TestThread bubble = new TestThread("Bubble",arrayOfArrays[1]);
        TestThread merge = new TestThread("Merge",arrayOfArrays[2]);

    }
}
