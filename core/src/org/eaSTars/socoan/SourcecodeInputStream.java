package org.eaSTars.socoan;

import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

public class SourcecodeInputStream extends InputStream {

	private Stack<Integer> unreadbuffer = new Stack<Integer>();
	
	private InputStream in;
	
	public SourcecodeInputStream(InputStream in) {
		this.in = in;
	}
	
	@Override
	public int read() throws IOException {
		int result = -1;
		if (!unreadbuffer.isEmpty()) {
			result = unreadbuffer.pop();
		} else {
			result = in.read();
		}
		return result;
	}

	public void unread(int value) {
		unreadbuffer.push(value & 0xff);
	}
	
	public void unread(byte[] data) {
		for (int i = data.length - 1; i >= 0; --i) {
			unread(data[i]);
		}
	}
	
	@Override
	public void close() throws IOException {
		in.close();
	}
	
	@Override
	public int available() throws IOException {
		return in.available() + unreadbuffer.size();
	}
}
