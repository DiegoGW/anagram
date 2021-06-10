import java.util.HashMap;


public class Anagram {

    public static void main(String args[]){
            String s1 = "dlo";
            String s2 = "old";

            System.out.println("Is Anagram? " + s1 + " - " + s2);
            System.out.println(isRealAnagram(s1, s2));

        }


    /**
     * This function returns if 2 strings are anagrams
     * Every string is iterated once
     * Then supposing n character per string
     * Order is O(n) + O(n) = > O(n)
     */
     private static boolean isRealAnagram(String s1, String s2){

            //Consider not valid edge cases
            if ((s1 == null) || (s2 == null) || (s1 == "") || (s2 == "")) {
                return false;
            }

            //First check as a shortcut of the result
            if (s1.length() != s2.length()) {
                return false;
            }

            s1 = s1.toLowerCase();
            s2 = s2.toLowerCase();

            //populate s1 to a hashmap in order to count the occurrences of each char
            HashMap<Character, Integer> hashMap = new HashMap<>();
            Integer frequency;
            for (int i = 0; i < s1.length(); i++) {
                char letter = s1.charAt(i);

                if (!hashMap.containsKey(letter)) {
                    hashMap.put(letter, 1);
                } else {
                    frequency = hashMap.get(letter);
                    hashMap.put(letter, ++frequency);
                }
            }

            //compare each letter of second string against the hashmap
            //return false in case the letter doesn't exist or the frequency is 0
            //otherwise decrease the frequency by 1
            frequency = 0;
            for (int j = 0; j < s2.length(); j++) {
                char letter = s2.charAt(j);

                if (!hashMap.containsKey(letter)) {
                    return false;
                }

                frequency = hashMap.get(letter);
                if (frequency == 0) {
                    return false;
                } else {
                    hashMap.put(letter, --frequency);
                }
            }
            //if both strings contain same characters and occurrences
            return true;

    }

}
