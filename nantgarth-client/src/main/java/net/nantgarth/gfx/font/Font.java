package net.nantgarth.gfx.font;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public enum Font {

	TITLE("title"),
	TEXT("text");
	
	private String name;
	
	private int[] widths = new int[95];
	private int size;
	
	private Font(String name) {
		this.name = name;
		
		int i = 0;
		try(BufferedReader br = new BufferedReader(new FileReader(new File("res/fonts/" + name + ".font")))) {
		    for(String line; (line = br.readLine()) != null && i < widths.length; ) {
		    	try {
		    		int num = Integer.parseInt(line);
		    		//System.out.println((char)(i + 32) + " | " + (i + 1));
		    		widths[i] = num;
		    	} catch(Exception e) {
		    		widths[i] = 0;
		    	}
		    	i++;
		    }
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public int getWidth(int i) {
		return widths[i];
	}
	
	public int getWidth(char c) {
		return widths[(int)(c) - 32];
	}
	
	public int getHeight() {
		return size;
	}
}
