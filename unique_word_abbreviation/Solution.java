package unique_word_abbreviation;

/*
 *288. Unique Word Abbreviation My Submissions Question
Total Accepted: 4915 Total Submissions: 31749 Difficulty: Easy
An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true
 */

public class ValidWordAbbr {
    Map<String, List<String>> hash;
    public ValidWordAbbr(String[] dictionary) {
        hash = new HashMap<>();
        for (String word : dictionary) {
            String abbrev = abbreviate(word);
            List<String> list = null;
            if (!hash.containsKey(abbrev)) {
                list = new ArrayList<>();
            } else {
                list = hash.get(abbrev);
            }
            list.add(word);
            hash.put(abbrev, list);
        }
    }

    public boolean isUnique(String word) {
        String abbrev = abbreviate(word);
        if (!hash.containsKey(abbrev)) {
            return true;
        } else {
           for (String candidate : hash.get(abbrev)) {
               if (!candidate.equals(word)) {
                   return false;
               }
           }
           return true;
        }
    }
    
    public String abbreviate(String word) {
        int n = word.length();
        if (n <= 2) return word;
        StringBuilder sb = new StringBuilder();
        sb.append(word.charAt(0));
        sb.append((char)('0' + n - 2));
        sb.append(word.charAt(n - 1));
        return sb.toString();
    }
}


// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");
