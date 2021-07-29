package leetcode;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: wangran
 * @Description: 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
 * 输入：num = "1432219", k = 3
 * 输出："1219"
 * 解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
 *
 * 输入：num = "10200", k = 1
 * 输出："200"
 * 解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 *
 * 输入：num = "10", k = 2
 * 输出："0"
 * 解释：从原数字移除所有的数字，剩余为空就是 0 。
 *
 * 此类问题终点在于如何维护字符串的单调递增，是一个关于greedy algorithm和单调自增栈的应用(得到最小数时需要出栈倒序，此处使用双向链表)
 * @Date: 2021/7/29 下午10:44
 */
public class RemoveKDigits {

    @Test
    public void test(){
        String s = removeKdigits("10", 2);
        System.out.println(s);
    }

    public String removeKdigits(String num, int k) {
        Deque<Character> queue = new LinkedList<>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (!queue.isEmpty()&&queue.peekLast()>c&&k>0){
                k--;
                queue.pollLast();
            }
            queue.addLast(c);
        }
        //&&!queue.isEmpty() queue无元素值返回null
        while (k != 0 ){
            k--;
            queue.pollLast();
        }
        StringBuilder result = new StringBuilder();
//        int size = queue.size();
//        for (int j = 0; j < size ; j++) {
//            Character poll = queue.poll();
//            if(poll != '0'){
//                result.append(poll);
//            }else if(result.length()>0){
//                result.append(poll);
//            }
//        }
        boolean leadingZero = true;
        while (!queue.isEmpty()) {
            char digit = queue.pollFirst();
            if (leadingZero && digit == '0') {
                continue;
            }
            leadingZero = false;
            result.append(digit);
        }
        return result.length() == 0? "0" :result.toString();
    }


}

class Solution {
    /**
     * 初版，在数组各个位单调递增时最坏 o(k*num.size)
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        List<Integer> ints = Arrays.stream(num.split("")).map(Integer::new).collect(Collectors.toList());
        for (int i = 0; i < k; i++) {
            int size = ints.size();
            int j = 0;
            for (; j < size-1; j++) {
                if(ints.get(j)> ints.get(j+1)){
                    ints.remove(j);
                    break;
                }
            }
            if(j == size-1){
                ints.remove(size-1);
            }

        }
        if(ints.size() == 0){
            return "0";
        }else {
            String collect = ints.stream().map(String::valueOf).collect(Collectors.joining());
            for (int i = 0; i < ints.size(); i++) {
                if(ints.get(i) != 0){
                    return collect.substring(i);
                }
            }
            return "0";
        }
    }
}