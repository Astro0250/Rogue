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
        int height = 200, width = bufferedImage.getWidth();
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
                if (newLine == width) {     //Does /n whenever necessary           	
                	newLine = 0;
                	System.out.println();
                }            
                switch (rgb) {
                	case "0 204 0":System.out.print("101 ");break;
                	case "64 64 64":System.out.print("102 ");break;
                	case "123 123 123":System.out.print("103 ");break;
                	case "0 0 204":System.out.print("104 ");break;
                	case "255 255 0":System.out.print("105 ");break;
                	case "230 41 230":System.out.print("106 ");break;
                	case "120 0 120":System.out.print("107 ");break;
                	case "102 51 0":System.out.print("108 ");break;
                	case "25 59 25":System.out.print("109 ");break;
                	case "48 107 48":System.out.print("110 ");break;
                	case "40 20 0":System.out.print("111 ");break;
                	case "255 255 255":System.out.print("100 ");break;
                }
                
//                if (red == 0 && green == 204 && blue == 0) { System.out.print("101 "); } // GRASS
//                else if (red == 64 && green == 64 && blue == 64) { System.out.print("102 "); } // PATH1
//                else if (red == 123 && green == 123 && blue == 123) { System.out.print("103 "); } // PATH2
//                else if (red == 0 && green == 0 && blue == 204) { System.out.print("104 "); } // WATER
//                else if (red == 255 && green == 255 && blue == 0) { System.out.print("105 "); } // SAND
//                else if (red == 230 && green == 41 && blue == 230) { System.out.print("106 "); } // BRIDGELR
//                else if (red == 120 && green == 0 && blue == 120) { System.out.print("107 "); } // BRIDGEUD
//                else if (red == 102 && green == 51 && blue == 0) { System.out.print("108 "); } // TREE1
//                else if (red == 25 && green == 59 && blue == 25) { System.out.print("109 "); } // TREE2
//                else if (red == 48 && green == 107 && blue == 48) { System.out.print("110 "); } // TREE3
//                else if (red == 40 && green == 20 && blue == 0) { System.out.print("111 "); } // FAKETREE
//                else if (red == 255 && green == 255 && blue == 255) { System.out.print("100 "); } // VOID
//                else {System.out.print("Not Colored In");}
//                newLine++;
//                if (newLine == width) {     //Does /n whenever necessary           	
//                	newLine = 0;
//                	System.out.println();
//                }              
           }  
        }
    }
}