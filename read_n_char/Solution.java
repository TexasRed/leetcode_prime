package read_n_char;

/*
157. Read N Characters Given Read4 My Submissions Question
Total Accepted: 8368 Total Submissions: 28664 Difficulty: Easy
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function will only be called once for each test case.
 */

/* The read4 API is defined in the parent class Reader4.
int read4(char[] buf); */

public class Solution extends Reader4 {
	/**
	 * @param buf Destination buffer
	 * @param n   Maximum number of characters to read
	 * @return    The number of characters read
	 */
	public int read(char[] buf, int n) {
		char[] buffer = new char[4];
		int readBytes = 0;
		boolean eof = false;
		while (!eof && readBytes < n) {
			int sz = read4(buffer);
			if (sz < 4) eof = true;
			int bytes = Math.min(n - readBytes, sz); 
			System.arraycopy(buffer, 0, buf, readBytes, bytes);
			readBytes += bytes;
		}
		return readBytes;
	}
}

/* The read4 API is defined in the parent class Reader4.
int read4(char[] buf); */

public class Solution extends Reader4 {
	/**
	 * @param buf Destination buffer
	 * @param n   Maximum number of characters to read
	 * @return    The number of characters read
	 */
	public int read(char[] buf, int n) {
		int len = 0; 
		int count = 4;
		while (len < n && count == 4) {
			char[] temp = new char[4];
			count = read4(temp);
			int bytesToFill = Math.min(n - len, count);
			System.arraycopy(temp, 0, buf, len, bytesToFill);
			len += bytesToFill;
		}
		return len;
	}
}
