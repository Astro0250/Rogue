package tile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
//Takes a png with very specific RGB colors and prints out the map accordingly
//In hindsight I probably could have made a new file and have had all the values here appended to it
//Console buffer size should be 500,000 or higher to work 
public class GetPixelColor {
	public static void main(String args[]) throws IOException{ 
		int a = 0;
        BufferedImage bufferedImage = ImageIO.read(new File("res/premap/Pre-World-Main.png"));    //Put png here    
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
                	System.out.println();
                }
                switch (rgb) {
                	// Main Textures
            		case "255 255 255": System.out.print("100 "); break;
            		case "0 0 0": System.out.print("100 "); break;
                	case "0 204 0": System.out.print("101 "); break;
                	case "64 64 64": System.out.print("102 "); break;
                	case "123 123 123": System.out.print("103 "); break;
                	case "0 0 204": System.out.print("104 "); break;
                	case "255 255 0": System.out.print("105 "); break;
                	case "230 41 230": System.out.print("106 "); break;
                	case "120 0 120": System.out.print("107 "); break;
                	case "102 51 0": System.out.print("108 "); break;
                	case "25 59 25": System.out.print("109 "); break;
                	case "48 107 48": System.out.print("110 "); break;
                	case "40 20 0": System.out.print("111 "); break;
                	// GS Transitions
                	case "67 77 0": System.out.print("115 "); break;
                	case "105 121 0": System.out.print("116 "); break;
                	case "150 173 0": System.out.print("114 "); break;
                	case "188 217 0": System.out.print("117 "); break;
                	case "197 228 0": System.out.print("118 "); break;
                	case "221 255 0": System.out.print("119 "); break;
                	case "174 255 0": System.out.print("112 "); break;
                	case "91 133 0": System.out.print("113 "); break;
                	case "155 226 0": System.out.print("121 "); break;
                	case "72 105 0": System.out.print("123 "); break;
                	case "125 183 0": System.out.print("120 "); break;
                	case "143 209 0": System.out.print("122 "); break;
                	// GW Transitions
                	case "0 90 73": System.out.print("124 "); break;
                	case "0 111 91": System.out.print("125 "); break;
                	case "0 133 109": System.out.print("126 "); break;
                	case "0 161 131": System.out.print("127 "); break;
                	case "0 193 157": System.out.print("128 "); break;
                	case "0 229 187": System.out.print("129 "); break;
                	case "0 255 208": System.out.print("130 "); break;
                	case "0 65 53": System.out.print("131 "); break;
                	case "0 70 106": System.out.print("132 "); break;
                	case "0 44 65": System.out.print("133 "); break;
                	case "0 97 145": System.out.print("134 "); break;
                	case "0 131 196": System.out.print("135 "); break;
                	
                }
           }  
        }
    }
}