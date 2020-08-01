package tree;

/**
 * @author: tn
 * @Date: 2020/8/1 0001 13:58
 * @Description:
 */
public class Solution {
    public static void main(String[] args) {
        String hello = "hello";
        reverseString(hello.toCharArray());
    }

    public static void reverseString(char[] s) {
         for (int i = 0, j = s.length - 1; i < s.length/2; i++, j--) {
            char temp = s[j];
            s[j] = s[i];
            s[i] = temp;
        }
        System.out.println(String.valueOf(s));
    }

}
