package Leetcode.hard;

import java.util.HashMap;
import java.util.Scanner;
class TrieNode{
    int data;
    HashMap<Integer,TrieNode> hm;
    int count;


}

public class Main {
    static int[] arra = {0,0};

    public static boolean isKthBitSet(int n, int k)
    {
        if ((n & (1 << (k))) > 0)
            return true;
        else
            return false;
    }

    public static void insertIntoTrie(TrieNode head, int number, int bitPosition){
        if(bitPosition < 0){
            return;
        }
        boolean isSet = isKthBitSet(number,bitPosition);
        if(isSet){//Find 1 is HasMap
            TrieNode ts = head.hm.get(1);
            if(ts == null){
                TrieNode ee = new TrieNode();
                ee.data = 1;
                ee.count = 1;
                HashMap<Integer,TrieNode> wo = new HashMap<>();
                ee.hm = wo;
                head.hm.put(1,ee);
                insertIntoTrie(ee,number,(bitPosition-1));
            }else{
                ts.count += 1;
                insertIntoTrie(ts,number,(bitPosition - 1));
            }
        }else{//Find 0 in Hash Map
            TrieNode ts = head.hm.get(0);
            if(ts == null){
                TrieNode ee = new TrieNode();
                ee.data = 0;
                ee.count = 1;
                HashMap<Integer,TrieNode> wo = new HashMap<>();
                ee.hm = wo;
                head.hm.put(0,ee);
                insertIntoTrie(ee,number,(bitPosition-1));
            }else{
                ts.count += 1;
                insertIntoTrie(ts,number,(bitPosition - 1));
            }
        }
    }

    public static void countPairs(TrieNode head, int index,int number,int upperBound,int woo){
        if(index == 0){
            arra[woo] += head.count;
            return;
        }
       System.out.println("**********************************************************************");
        boolean isNumb = isKthBitSet(number, index-1);
        boolean isUpper = isKthBitSet(upperBound, index-1);
       System.out.println("index = " + index );
        System.out.println("number = " + number );
        System.out.println("UpperBound = " + upperBound);
        if(isUpper){//Curr Bit of Bound Is set

            if(isNumb){//Numb Curr Bit is set
                System.out.println("Both are set");
                TrieNode ts = head.hm.get(1);
                if(ts == null){
                    arra[woo] += 0;
                }else{
                    System.out.println("addiding to ans " + ts.count);
                    arra[woo] += ts.count;

                }

                ts = head.hm.get(0);
                if(ts != null){
                    countPairs(ts,(index-1),number,upperBound,woo);
                }else{
                    return;
                }
            }else{//Numb Curr Bit is not set

                System.out.println("Upper Bound set WHereas currBit is not set ");

                TrieNode ts = head.hm.get(0);
                if(ts == null){
                    arra[woo] += 0;
                }else{
                    System.out.println("Adding " + ts.count);
                    arra[woo] += ts.count;

                }

                ts = head.hm.get(1);
                if(ts != null){
                    countPairs(ts,(index-1),number,upperBound,woo);
                }else{
                    return;
                }

            }


        }else{//Curr Bit of Bound is not set
            if(isNumb){//Numb Curr Bit is set
               System.out.println("Upper Not Set Curr Bit is set");
                TrieNode ts = head.hm.get(1);
                if(ts != null){
                    countPairs(ts,(index-1),number,upperBound,woo);
                }
                else{
                    return;
                }
            }else{//Numb Curr Bit is not set
                System.out.println("Both Not set ");
                TrieNode ts = head.hm.get(0);
                if(ts != null){
                    countPairs(ts,(index-1),number,upperBound,woo);
                }
                else{
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
	// write your code here
        int nn;
        System.out.println("Enter The Number of Nums ");
        Scanner scan = new Scanner(System.in);
        nn = scan.nextInt();
        int[] nums = new int[nn];
        System.out.println("Enter ALl The Nums ");
        for(int i=0;i<nn;i++){
            nums[i] = scan.nextInt();
        }
        int low,high;
        low = scan.nextInt();
        high = scan.nextInt();
        ///////////////////////////////////////////////////////
        TrieNode qw = new TrieNode();
        HashMap<Integer,TrieNode> hq = new HashMap<>();
        qw.hm = hq;
        for(int i=0;i<nums.length;i++){
            insertIntoTrie(qw,nums[i],5);
        }
        for(int i=0;i<nums.length;i++){
            System.out.println("Value of i = " + i);
            System.out.println("prevVal = " + arra[0]);
            countPairs(qw,6,nums[i],high,0);
            System.out.println("currVal = " + arra[0]);

        }
        System.out.println("**********************************************************************");
        for(int i=0;i<nums.length;i++){
            System.out.println("Value of i = " + i);
            System.out.println("prevVal = " + arra[1]);
            countPairs(qw,6,nums[i],(low-1),1);
            System.out.println("curral = " + arra[1]);
        }
        System.out.println(arra[0]);
        System.out.println("**********************************************************************");
        System.out.println(arra[1]);
        arra[0] -= nums.length;
        arra[1] -= nums.length;

        System.out.println((Math.abs(arra[0] - arra[1]))/2);



    }
}
