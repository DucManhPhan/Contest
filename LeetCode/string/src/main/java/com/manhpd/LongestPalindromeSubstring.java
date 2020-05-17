package com.manhpd;

/**
 * Given a string s, find the longest palindromic substring in s.
 * You may assume that the maximum length of s is 1000.
 *
 * Ex1: Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * Example 2: Input: "cbbd"
 * Output: "bb"
 *
 */
public class LongestPalindromeSubstring {

    private static int lo;

    private static int maxLen;

    public static void main(String[] args) {
        System.out.println(longestPalindrome("BABAD"));
        System.out.println(longestPalindrome("ACBABD"));
        System.out.println(longestPalindrome("CBBDB"));
    }

    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        for (int i = 0; i < len - 1; i++) {
            makePalindrome(s, i, i);
            makePalindrome(s, i, i + 1);
        }

        return s.substring(lo, lo + maxLen);
    }

    public static void makePalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }

        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }

}
