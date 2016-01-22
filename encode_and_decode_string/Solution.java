package encode_and_decode_string;

/*
 *271. Encode and Decode Strings My Submissions Question
Total Accepted: 4264 Total Submissions: 15747 Difficulty: Medium
Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:
The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
 * 
 */

public class Codec {

	// Encodes a list of strings to a single string.
	public String encode(List<String> strs) {
		StringBuilder sb = new StringBuilder();
		for (String str : strs) {
			sb.append(str.length());
			sb.append('#');
			sb.append(str);
		}
		return sb.toString();
	}

	// Decodes a single string to a list of strings.
	public List<String> decode(String s) {
		List<String> result = new ArrayList<>();
		int len = 0; boolean inLength = true;
		int i = 0;
		for (i = 0; i < s.length();) {
			char c = s.charAt(i);
			if (inLength && c - '0' >= 0 && c - '9' <= 0) {
				len = len * 10 + c - '0';
				i++;
			} else if (inLength && c == '#'){
				i++;
				inLength = false;
			} else {
				result.add(s.substring(i, i + len));
				i = i + len;
				len = 0;
				inLength = true;
			}
		}
		if (!inLength) {
			result.add(s.substring(i, i + len));
		}
		return result;
	}
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));}
