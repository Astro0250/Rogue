package tile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
//Takes a png with very specific RGB colors and prints out the map accordingly
//Run separately from the actual program
//Console buffer size should be 500,000 or higher to work 
public class GetPixelColor {
	public static void main(String args[]) throws IOException{ 
        BufferedImage bufferedImage = ImageIO.read(new File("res/premap/map3.png"));    //Put png here    
        int height = bufferedImage.getHeight(), width = bufferedImage.getWidth();
        int newLine = 0; 
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int RGBA = bufferedImage.getRGB(x, y);   
                int red = (RGBA >> 16) & 255;
                int green = (RGBA >> 8) & 255;
                int blue = RGBA & 255;
                if (red == 0 && green == 204 && blue == 0) { System.out.print("101 "); }
                else if (red == 64 && green == 64 && blue == 64) { System.out.print("102 "); }
                else if (red == 255 && green == 255 && blue == 255) { System.out.print("103 "); }
                else if (red == 0 && green == 0 && blue == 204) { System.out.print("104 "); }
                else if (red == 255 && green == 255 && blue == 0) { System.out.print("105 "); }
                else if (red == 230 && green == 41 && blue == 230) { System.out.print("106 "); }
                else if (red == 0 && green == 0 && blue == 0) { System.out.print("107 "); }
                else if (red == 102 && green == 51 && blue == 0) { System.out.print("108 "); }
                else if (red == 25 && green == 59 && blue == 25) { System.out.print("109 "); }
                else {System.out.print("Not Colored In");}
                newLine++;                
                if (newLine == width) {     //Does /n whenever neccesary           	
                	newLine = 0;
                	System.out.println();
                }              
            }  
        }
    }
}