package algo.stringmatching.kmp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: wangran
 * @Description: kmp string matching algorithm
 * 重点在于数组nexts的计算，此处运用动态规划的思路
 * @Date: 2021/7/27 下午9:29
 */
public class KnuthMorrisPratt {

    @Test
    public void testKMPMatch() {
        String strn = "abbcabcabc";
        String strm = "cabcabc";
//        String strn = "abcdefg";
//        String strm = "de";
        int kmp = kmp(strn, strm);
    }

    private int kmp(String strn, String strm) {
        char[] mchars = strm.toCharArray();
        int mlength = strm.length();
        char[] nchars = strn.toCharArray();
        int nlength = strn.length();
        //通过'好前缀'下标得到'最大匹配前缀字符串'下标
        int[] nexts = new int[mlength - 1];
        generateNext(mchars, mlength, nexts);

        int j = 0;
//        for (int i = 0; i < nlength;) {
//
//            while (j < mlength) {
//
//                if (nchars[i] == mchars[j]) {
//                    j++;
//                    i++;
//                }
//                else if (j > 0) {
//                    j = nexts[j - 1] + 1;
//                }else {
//                    i++;
//                }
//            }
//
//            if (j == mlength) {
//                return i - mlength;
//            }
//        }

        for (int i = 0; i < nlength; i++) {

            while (j > 0 && nchars[i] != mchars[j]) {
                j = nexts[j - 1] + 1;
            }

            if (nchars[i] == mchars[j]) {
                j++;
            }

            if (j == mlength) {
                return i - mlength +1;
            }
        }
        return -1;
    }

    private void generateNext(char[] mchars, int mlength, int[] nexts) {
        Arrays.fill(nexts,-1);
        //k实际上是next[i-1]
        int k = -1;
        for (int i = 1; i < mlength-1; i++) {
            while (k != -1 && mchars[k+1] != mchars[i]){
                k = nexts[k];
            }

            if(mchars[i] == mchars[k+1]){
                nexts[i] = ++k;
            }

        }
    }
    @Test
    public void testNexts(){
        String str = "ababacd";
        int length = str.length();
        int[] ints = new int[length - 1];
        generateNext(str.toCharArray(),length,ints);
    }


}
