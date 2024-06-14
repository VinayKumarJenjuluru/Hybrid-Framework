package Methods.NABERS_Common_Methods;

import java.lang.reflect.Array;

public class demo {
    public static void main(String[] args)
    {
        String v="N@bers@123456uatuat";

        String orgValue="";
        String cryptedValue="";
        String againValue="";
        char arr[]=v.toCharArray();
        for (int i=0;i< arr.length;i++)
        {
            orgValue=orgValue+arr[i];
        }
        System.out.println(orgValue);

        System.out.println("\n");

        for (int i=0;i< arr.length;i++)
        {
            for(int j=0;j<i+1;j++)
            {
                if(i%2==0)//vinay
                {
                    arr[i]--;//ukbkdj
                }
                else {
                    arr[i]++;
                    arr[i]++;
                }
            }

            cryptedValue=cryptedValue+arr[i];
        }
        System.out.println(cryptedValue);

        System.out.println("\n");

        for (int i=0;i< arr.length;i++)
        {
            for(int j=0;j<i+1;j++)
            {
                if(i%2==0)
                {
                    arr[i]++;
                }
                else {
                    arr[i]--;
                    arr[i]--;
                }
            }
            againValue=againValue+arr[i];
        }
        System.out.println(againValue);
    }
}
