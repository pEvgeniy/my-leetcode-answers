import java.util.*;

public class Solutions {

    public static void main(String[] args) {
        int[] mas = new int[] {8,10,14,0,13,10,9,9,11,11};
        System.out.println(maxArea(mas));


    }


    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    int[] result = {i, j};
                    return result;
                }
            }
        }
        return null;
    }

    public static int lengthOfLongestSubstring(String s) {
        String[] splitedStr = s.split("");
        List<String> curStr = new ArrayList<>();
        List<String> maxStr = new ArrayList<>();

        if (s.length() == 0) {
            return 0;
        }

        for (int i = 0; i < splitedStr.length; i++) {
            curStr.clear();
            for (int j = i; j < splitedStr.length; j++) {
                if (!curStr.contains(splitedStr[j])) {
                    curStr.add(splitedStr[j]);
                } else {
                    break;
                }
            }
            if (curStr.size() > maxStr.size()) {
                maxStr.clear();
                maxStr.addAll(curStr);
            }
        }

        return maxStr.size();
    }

    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
  }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        ListNode elements = new ListNode(-1);
        ListNode current = elements;

        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        return current;
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for(char ch: s.toCharArray())
        {
            switch (ch)
            {
                case '(':
                case '{':
                case '[':
                    stack.push(ch);
                    break;
                case ')':
                    if(stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                    break;
                case '}':
                    if(stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                    break;
                case ']':
                    if(stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();
    }

    public int removeDuplicates(int[] nums) {
        int insertIndex = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i - 1] != nums[i]) {
                nums[insertIndex] = nums[i];
                insertIndex++;
            }
        }
        return insertIndex;
    }

    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;

            if (left == right) {
                if (nums[right] < target) {
                    return right + 1;
                } else {
                    return right;
                }
            }

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int lengthOfLastWord(String s) {
        s = s.trim();
        int lastIndex = s.lastIndexOf(' ') + 1;
        return s.length() - lastIndex;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int firstIterator = 0;
        int secondIterator = 0;
        int operationsLength = nums1.length + nums2.length;
        int[] mergedMas = new int[operationsLength];
        int i = 0;

        while (firstIterator < nums1.length && secondIterator < nums2.length) {
            if (nums1[firstIterator] > nums2[secondIterator]) {
                mergedMas[i] = nums2[secondIterator];
                secondIterator += 1;
            } else {
                mergedMas[i] = nums1[firstIterator];
                firstIterator += 1;
            }
            i++;
        }

        if (firstIterator == nums1.length) {
            for (int j = secondIterator; j < nums2.length; j++) {
                mergedMas[i] = nums2[j];
                i++;
            }
        } else {
            for (int j = firstIterator; j < nums1.length; j++) {
                mergedMas[i] = nums1[j];
                i++;
            }
        }

        if (mergedMas.length % 2 != 0) {
            return mergedMas[mergedMas.length/2];
        } else {
            double result = (double) (mergedMas[(mergedMas.length-1)/2] + mergedMas[mergedMas.length/2]);
            return result/2;
        }
    }

    public static String longestPalindrome(String s) {
        int left;
        int right;
        int idx = 0;
        String res = s.substring(0, 1);
        while(idx < s.length()) {
            char c = s.charAt(idx);
            left = idx;
            right = idx;
            while(left >=0 && s.charAt(left) == c){
                left--;
            }
            while(right < s.length() && s.charAt(right) == c){
                right++;
            }
            int boundary_of_c = right;
            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
                left--;
                right++;
            }
            left = left + 1;
            if (right -  left > res.length()){
                res = s.substring(left, right);
            }
            idx = boundary_of_c;
        }
        return res;
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        StringBuilder answer = new StringBuilder();
        int n = s.length();
        int charsInSection = 2 * (numRows - 1);

        for (int currRow = 0; currRow < numRows; ++currRow) {
            int index = currRow;

            while (index < n) {
                answer.append(s.charAt(index));

                if (currRow != 0 && currRow != numRows - 1) {
                    int charsInBetween = charsInSection - 2 * currRow;
                    int secondIndex = index + charsInBetween;

                    if (secondIndex < n) {
                        answer.append(s.charAt(secondIndex));
                    }
                }
                index += charsInSection;
            }
        }

        return answer.toString();
    }

    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static int myAtoi(String s) {
        int index = 0;
        int total = 0;
        int sign = 1;

        // Check if empty string
        if (s.length() == 0)
            return 0;

        // remove white spaces from the string
        while (index < s.length() && s.charAt(index) == ' ')
            index++;

        if (index == s.length()) return 0;

        // get the sign
        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            sign = s.charAt(index) == '+' ? 1 : -1;
            index++;
        }

        // convert to the actual number and make sure it's not overflow
        while (index < s.length()) {
            int digit = s.charAt(index) - '0';
            if (digit < 0 || digit > 9) break;

            // check for overflow
            if (Integer.MAX_VALUE / 10 < total || Integer.MAX_VALUE / 10 == total && Integer.MAX_VALUE % 10 < digit)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            total = total * 10 + digit;
            index++; // don't forget to increment the counter
        }
        return total * sign;
    }

    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int currVolume = 0;
        int maxVolume = 0;

        while (left < right) {
            currVolume = (right - left) * Integer.min(height[left], height[right]);
            if (currVolume > maxVolume) {
                maxVolume = currVolume;
            }
            if (height[left] > height[right]) {
                right--;
            } else if (height[right] > height[left]) {
                left++;
            } else {
                left++;
                right--;
            }
        }
        return maxVolume;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length < 3) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
            if (nums[i] > 0) break;
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    list.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                    j++;
                    k--;
                } else if (sum > 0) {
                    k--;
                } else {
                    j++;
                }
                while (i < nums.length-2 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }
        return list;
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[nums.length - 1];
        boolean firstIter = true;
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return sum;
                }
                if (Math.abs(target - sum) < (Math.abs(target - closestSum))) {
                    closestSum = sum;
                }
                if (sum > target) {
                    k--;
                }
                if (sum < target) {
                    j++;
                }
            }
        }
        return closestSum;
    }
}
