package tile;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

public class GetPixelColor {
	public static void clearFile(String fileName)

	{ 
	    try{
	    FileWriter fw = new FileWriter(fileName, false);
	    PrintWriter pw = new PrintWriter(fw, false);
	    pw.flush();
	    pw.close();
	    fw.close();
	    }catch(Exception exception){
	        System.out.println("Exception have been caught");
	    }

	}
	public static void main(String[] args) throws IOException{ 
		FileWriter fw = null;
	    BufferedWriter bw = null;
	    PrintWriter pw = null;	 
	    
	    try {
	    	clearFile("res/maps/finalMap.txt");
            fw = new FileWriter("res/maps/finalMap.txt", true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
 
            int a = 0;
            //This is the only important part ↓ 
            BufferedImage bufferedImage = ImageIO.read(new File("res/premap/Pre-World-Main2.png"));    //Put png here    
            int height = bufferedImage.getHeight(), width = bufferedImage.getWidth();
            int newLine = 0; 
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int RGBA = bufferedImage.getRGB(x, y);   
                    int red = (RGBA >> 16) & 255;
                    int green = (RGBA >> 8) & 255;
                    int blue = RGBA & 255;
                    
                    String rgb = red + " " + green + " " + blue;
                    if (a >= 1) {
                    newLine++;
                    }
                    a++;
                    if (newLine == width) { //Does /n whenever necessary           	
                    	newLine = 0;
                    	pw.println();
                    }
                    switch (rgb) {
                    	// Main Textures
                		case "255 255 255": pw.print("100 "); break;
                		case "0 0 0": pw.print("100 "); break;
                    	case "0 204 0": pw.print("101 "); break;
                    	case "64 64 64": pw.print("102 "); break;
                    	case "123 123 123": pw.print("103 "); break;
                    	case "0 0 204": pw.print("104 "); break;
                    	case "255 255 0": pw.print("105 "); break;
                    	case "230 41 230": pw.print("106 "); break;
                    	case "120 0 120": pw.print("107 "); break;
                    	case "102 51 0": pw.print("108 "); break;
                    	case "25 59 25": pw.print("109 "); break;
                    	case "48 107 48": pw.print("110 "); break;
                    	case "40 20 0": pw.print("111 "); break;
                    	// GS Transitions
                    	case "67 77 0": pw.print("115 "); break;
                    	case "105 121 0": pw.print("116 "); break;
                    	case "150 173 0": pw.print("114 "); break;
                    	case "188 217 0": pw.print("117 "); break;
                    	case "197 228 0": pw.print("118 "); break;
                    	case "221 255 0": pw.print("119 "); break;
                    	case "174 255 0": pw.print("112 "); break;
                    	case "91 133 0": pw.print("113 "); break;
                    	case "155 226 0": pw.print("121 "); break;
                    	case "72 105 0": pw.print("123 "); break;
                    	case "125 183 0": pw.print("120 "); break;
                    	case "143 209 0": pw.print("122 "); break;
                    	// GW Transitions
                    	case "0 90 73": pw.print("124 "); break;
                    	case "0 111 91": pw.print("125 "); break;
                    	case "0 133 109": pw.print("126 "); break;
                    	case "0 161 131": pw.print("127 "); break;
                    	case "0 193 157": pw.print("128 "); break;
                    	case "0 229 187": pw.print("129 "); break;
                    	case "0 255 208": pw.print("130 "); break;
                    	case "0 65 53": pw.print("131 "); break;
                    	case "0 70 106": pw.print("132 "); break;
                    	case "0 44 65": pw.print("133 "); break;
                    	case "0 97 145": pw.print("134 "); break;
                    	case "0 131 196": pw.print("135 "); break;
                    	// WOOD HOUS
                    	case "254 177 82": pw.print("136 "); break;
                    	case "228 158 71": pw.print("137 "); break;
                    	case "200 136 56": pw.print("138 "); break;
                    	case "62 41 16": pw.print("139 "); break;
                    	case "85 57 22": pw.print("140 "); break;
                    	case "189 126 48": pw.print("141 "); break;
                    	case "146 97 37": pw.print("142 "); break;
                    	case "171 113 41": pw.print("143 "); break;
                    	case "116 77 29": pw.print("144 "); break;
                    }
                }
            }
            pw.flush();
            

        } finally {
            try {
                pw.close();
                bw.close();
                fw.close();
            } catch (IOException io) {// can't do anything }
            	System.out.print("denied");
            }

        }
		
                
                
    }
}