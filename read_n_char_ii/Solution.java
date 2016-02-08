package read_n_char_ii;

/*
158. Read N Characters Given Read4 II - Call multiple times My Submissions Question
Total Accepted: 6716 Total Submissions: 29091 Difficulty: Hard
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.

 */

/* The read4 API is defined in the parent class Reader4.
int read4(char[] buf); */

public class Solution extends Reader4 {
	/**
	 * @param buf Destination buffer
	 * @param n   Maximum number of characters to read
	 * @return    The number of characters read
	 */
	char[] buffer = new char[4];
	int offset = 0;    // [0, 3]
	int buffsize = 0;  // [0, 4]
	boolean eof = false;

	public int read(char[] buf, int n) {
		int readBytes = 0; int length = 0;
		while (!eof && length < n || buffsize > 0 && length < n) {
			if (buffsize == 0) {
				buffsize = read4(buffer);
				if (buffsize < 4) eof = true;
			}
			readBytes = Math.min(n - length, buffsize);
			System.arraycopy(buffer, offset, buf, length, readBytes);
			buffsize = buffsize - readBytes;
			length += readBytes;
			offset = (offset + readBytes) % 4;
		}
		return length;
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
	private char [] buffer = new char[4];
	int offset = 0, bufsize = 0; 

	public int read(char[] buf, int n) {
		int readBytes = 0;
		boolean eof = false;
		while (!eof && readBytes < n) {
			int sz = (bufsize>0)? bufsize : read4(buffer);
			if (bufsize==0 && sz < 4) eof = true;
			int bytes = Math.min(n - readBytes, sz); 
			System.arraycopy(buffer /* src */, offset /* srcPos */, buf /* dest */, readBytes /* destPos */, bytes /* length */);
			offset = (offset + bytes) % 4;
			bufsize = sz - bytes;
			readBytes += bytes;
		}
		return readBytes;
	}
}