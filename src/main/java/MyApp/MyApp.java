package MyApp;

import java.util.Arrays;

public class MyApp {



    public void main(String[] args) {
    }

    public int[] arrayAfterZero(int[] arr){
        int [] result= new int[]{arr.length};
        int write=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==0){
                write=1;
                result= Arrays.copyOfRange(arr,i+1,arr.length);
            }
        }
        if (write==0){
            throw  new RuntimeException("No zero founds");
        }
        return result;
    }

    public boolean sixNines(int[] arr){

        int sixies=0;
        int nines=0;

        for (int j : arr) {

            if (j == 66) {
                sixies = 1;
            }
            else if(j==99) {
                nines = 1;
            }
            else
                return false;
        }

        return sixies == 1 && nines == 1;
    }


}
