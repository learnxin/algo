package algo.stringmatching.bfandrk;

import org.junit.Test;

/**
 * @Author: wangran
 * @Description: bf string matching algorithm 暴力匹配算法 o(m*n)
 * @Date: 2021/7/19 下午5:02
 */
public class BruteForce {
    @Test
    public void testMatch(){
        String strn = "abcdefg";
        String strm = "de";

        int bf = bf(strn, strm);
    }

    /**
     *
     * @param strn 主串
     * @param strm 模式串
     * @return
     */
    public int bf(String strn,String strm){

        char[] nchars = strn.toCharArray();

        char[] mchars = strm.toCharArray();
        //从主串下标0开始匹配
        for (int i = 0; i < nchars.length; i++) {
            boolean flag = true;
            //匹配模式串长度的chars
            for (int j = 0; j <mchars.length; j++) {
                if(mchars[j] != nchars[i+j]){
                    flag = false;
                    break;
                }
            }
            if (flag){
                return i;
            }
        }
        return -1;
    }
}
