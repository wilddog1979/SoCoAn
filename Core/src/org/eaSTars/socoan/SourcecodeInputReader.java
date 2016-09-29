package org.eaSTars.socoan;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Stack;

public class SourcecodeInputReader extends InputStreamReader {

private Stack<Character> unreadbuffer = new Stack<Character>();
	
	public SourcecodeInputReader(InputStream in) {
		super(in, Charset.forName("UTF-8"));
	}
	
	@Override
	public int read() throws IOException {
		int result = -1;
		if (!unreadbuffer.isEmpty()) {
			result = unreadbuffer.pop();
		} else {
			result = super.read();
		}
		return result;
	}
	
	@Override
	public int read(char[] cbuf) throws IOException {
		int result = 0;
		
		for (int i = 0; i < cbuf.length; ++i) {
			if (!unreadbuffer.isEmpty()) {
				cbuf[i] = unreadbuffer.pop();
				result++;
			} else {
				break;
			}
		}
		
		if (result != cbuf.length) {
			int result2 = super.read(cbuf, result, cbuf.length - result);
			if (result2 == -1) {
				result = -1;
			} else  {
				result += result2;
			}
		}
		
		return result;
	}
	
	@Override
	public int read(char[] cbuf, int offset, int length) throws IOException {
		int result = 0;
		
		for (int i = offset; i < cbuf.length; ++i) {
			if (!unreadbuffer.isEmpty()) {
				cbuf[i] = unreadbuffer.pop();
				result++;
			} else {
				break;
			}
		}
		
		if (result != cbuf.length) {
			int result2 = super.read(cbuf, offset+result, cbuf.length - result - offset);
			if (result2 == -1) {
				result = -1;
			} else  {
				result += result2;
			}
		}
		
		return result;
	}
	
	public void unread(char value) {
		unreadbuffer.push(value);
	}
	
	public void unread(String data) {
		for (int i = data.length() - 1; i >= 0; --i) {
			unread(data.charAt(i));
		}
	}
}
