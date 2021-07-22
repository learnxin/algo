package algo.stringmatching.bm;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: wangran
 * @Description: Boyer-Moore string matching algorithm
 * bad character rule&good suffix shift
 * @Date: 2021/7/21 下午4:58
 */
public class BoyerMoore {


    @Test
    public void testBMMatch(){
//        String strn = "abbcabcabc";
//        String strm = "cabcabc";
        String strn = "abcdefg";
        String strm = "de";
        int bm = bm(strn, strm);
    }

    private int bm(String strn, String strm) {
        //此处认为模式串中只有长度为1字节的小写字母 2^8
        int[] bc = new int[256];
        char[] mchars = strm.toCharArray();
        int mlength = strm.length();
        char[] nchars = strn.toCharArray();
        int nlength = strn.length();
        generateBC(mchars,mlength,bc);

        boolean[] prefix = new boolean[strm.length()];
        int[] suffix = new int[strm.length()];
        generateGS(mchars,mlength,suffix,prefix);


        //主串n长度为m(模式串长度)字串的数量
        for (int i = 0; i < nlength-mlength+1;) {
            //从后往前匹配
            int j = mlength-1;
            for (; j > -1; j--) {
                char mchar = mchars[j];
                char nchar = nchars[i + j];
                if(mchar != nchar){
                    //bad character
                    //j-bc[nchar] 在 n = 'aaa' m = 'ba' 情况下为负数
//                    i = i + (j-bc[nchar]);
                    break;
                }
            }
            if(j<0){
                return i;
            }
            //bc
            int x=j-bc[nchars[i + j]];
            //若存在已匹配的后缀
            int y=0;
            if(j<mlength-1){
                //k = mlength-1-j
                y = moveByGS(suffix,prefix,mlength,mlength-1-j);
            }
            i += Math.max(x,y);
        }
        return -1;
    }

    private int moveByGS(int[] suffix, boolean[] prefix, int m, int k) {

        //k=m-s x= suffix[k]  y = s-x
        //k后缀是否在模式串中有匹配位置
        if(suffix[k] != -1){
            return m-k-suffix[k];
        }
        //是否存在后缀字串与前缀匹配的情况
        for (int i = k-1; i > 0; i--) {
            if(prefix[i]){
                //此时 s = m-i 而 x =0
                return m-i;
            }
        }
        //否则则整串后移
        return m;
    }

    /**
     * bad character rule
     * 此处为快速查找'bc在模式串中最后出现的下标'构建数组；
     * mc为模式串，m为模式串长度，bc为快速查询数组；
     * bc-(hash)->bc[] hash部分为ascii码值
     *
     * 若字串字符集较多则会导致此数组会很大，空间复杂度很高,内存紧张时可只使用gs原则
     * 但String strn = "abcdefg";
     *         String strm = "de";
     *         时匹配不上，因为不存在好后缀，每次后移m，错过了匹配的情况
     * 看来要手动查询坏字符最后出现的位置，或回归bf往后移一位
     */
    private void generateBC(char[] mc,int m,int[] bc){
        //init bc[]
        Arrays.fill(bc, -1);
        for (int i = 0; i < m; i++) {
            int ascii = mc[i];
            //后出现的char会覆盖之前的下标
            bc[ascii] = i;
        }
    }

    /**
     * good suffix shift
     * @param mc 模式串
     * @param m  模式串长度
     * @param suffix 前缀数组
     * @param prefix 是否为最大前缀
     * 若全为a,时间复杂度为o(m^2)
     */
    private void generateGS(char[] mc,int m,int[] suffix,boolean[] prefix){
        /*我们拿下标从 0 到 i 的子串（i 可以是 0 到 m-2）与整个模式串，求公共后缀子串。
        如果公共后缀子串的长度是 k，那我们就记录 suffix[k]=j（j 表示公共后缀子串的起始下标）。
        如果 j 等于 0，也就是说，公共后缀子串也是模式串的前缀子串，我们就记录 prefix[k]=true。
         */
        //init suffix
        Arrays.fill(suffix,-1);
        //模式串m最大下标为m-1
        for (int i = 0; i < m - 1; i++) {
            //0 到 i 的子串（i 可以是 0 到 m-2）与整个模式串，求公共后缀子串
            int j = i;
            for (; j > -1; j--) {
                //mc对应匹配下标由  m-1-y= i-j  => y= m-1-i+j
                if(mc[j] != mc[m-1-i+j]){
                    //k = i-j
//                    if(i!=j){
//                        int k = i-j;
//                        suffix[k] = j;
//                    }
                    break;
                }else {
                    suffix[i-j+1] = j;
                }

            }
            if(j==-1){
                prefix[i-j] = true;
            }
        }
    }

    @Test
    public void testGenerateGS(){
        String b = "cabcabc";
        int m = b.length();
        boolean[] prefix = new boolean[b.length()];
        int[] suffix = new int[b.length()];
        generateGS(b.toCharArray(),m,suffix,prefix);
    }

}
