package algo.stringmatching.bfandrk;

import org.junit.Test;

/**
 * @Author: wangran
 * @Description: Rabin-Karp String matching algorithm
 * 可以视为对bf算法的优化 o(n) 匹配主串和模式串的hash值，其中hash算法的设计比较重要；此处hash算法有溢出风险(Long)
 * @Date: 2021/7/20 上午11:20
 */
public class RabinKarp {

    @Test
    public void rkMatchingTest(){
        String strn = "fgabcde";
        String strm = "de";

        int rk = rk(strn, strm);
        int rkRelease = rkRelease(strn, strm);
    }

    private int rk(String strn, String strm) {

        int[] hashN = hashStr(strn, strm.length());
        int[] hashM = hashStr(strm, strm.length());
        int hashMval = hashM[0];
        //此处比对'主串的子串hash值'和模式串hash值
        for (int i = 0; i < strn.length() - strm.length() + 1; i++) {
            if(hashN[i] == hashMval){
                return i;
            }
        }
        return -1;
    }

    /**
     * h[i-1] 对应 s[i-1]~s[i+mlength-2]的hash值
     * h[i]=(h[i-1]-(s[i-1]-'a')*26^m-1)*26 + (s[i+m-1]-a)*26^0
     * @param strn
     * @param mlength
     * @return
     */
    private int[] hashStr(String strn, int mlength) {
        char[] s = strn.toCharArray();
        int[] h = new int[strn.length() - mlength +1];

        //对h[0]累加求值
        for (int i = 0; i <mlength; i++) {
            h[0] += (s[i]-'a')*Math.pow(26,mlength-1-i);
        }
        for (int i = 1; i < h.length; i++) {
            h[i] = (int) ((h[i-1]-(s[i-1]-'a')*Math.pow(26,mlength-1))*26 + (s[i+mlength-1]-'a'));
        }
        return h;
    }

    /**
     * 计算下标i的hash值
     * @param strn
     * @param mlength
     * @param h
     * @param i
     * @return
     */
    private int hashi(String strn, int mlength,int[] h,int i){
        char[] s = strn.toCharArray();
        if(i == 0){
            for (int j = 0; j <mlength; j++) {
                h[0] += (s[j]-'a')*Math.pow(26,mlength-1-j);
            }
            return h[0];
        }
        h[i] = (int) ((h[i-1]-(s[i-1]-'a')*Math.pow(26,mlength-1))*26 + (s[i+mlength-1]-'a'));
        return h[i];
    }


    /**
     * 对比rk优化点在于使用时在计算当前的hash值，总体上减少了对hash值的计算次数(对于提前匹配到的场景)
     * @param strn
     * @param strm
     * @return
     */
    private int rkRelease(String strn, String strm) {
        //主串n长度为m(模式串长度)字串的数量
        int nson = strn.length() - strm.length() + 1;
        int[] h = new int[nson];
        int hashMval = hashi(strm,strm.length(),new int[1],0);
        //此处比对'主串的子串hash值'和模式串hash值
        for (int i = 0; i < nson; i++) {
            if(hashi(strn,strm.length(),h,i) == hashMval){
                return i;
            }
        }
        return -1;
    }
}
