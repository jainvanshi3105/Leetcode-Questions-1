/**
 * Problem: https://leetcode.com/problems/decode-ways/
 *
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 * Given a string s containing only digits, return the number of ways to decode it.
 * The answer is guaranteed to fit in a 32-bit integer.
 * Example 1:
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * Example 3:
 * Input: s = "0"
 * Output: 0
 * Explanation: There is no character that is mapped to a number starting with 0.
 * The only valid mappings with 0 are 'J' -> "10" and 'T' -> "20", neither of which start with 0.
 * Hence, there are no valid ways to decode this since all digits need to be mapped.
 * Example 4:
 * Input: s = "1"
 * Output: 1
 * Constraints:
 * 1 <= s.length <= 100
 * s contains only digits and may contain leading zero(s).
 */
class Solution {
    /**
     * Dynamic Programming approach solution
     * @param s input string
     * @return number of ways to decode it
     */
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        // no matter it has digit or not, it has 1 way to decode
        dp[n] = 1;
        // as it need to consider 2 digits, so it is better to use bottom-up approach, scan from right to left
        for (int i = n - 1; i >= 0; i--) {
            // if it is 0, then it cannot be decoded
            if (s.charAt(i) == '0') {
                dp[i] = 0;
            } else {
                dp[i] = dp[i + 1];
                // if it is 1 or 2, then it can be decoded with 2 digits
                // if it is 2, then it can be decoded with 2 digits only if the next digit is less than 7
                if (i < n - 1 && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i + 1) < '7')) {
                    // add the number of ways to decode the next digit
                    dp[i] += dp[i + 2];
                }
            }
        }
        // return the result
        return dp[0];
    }
}