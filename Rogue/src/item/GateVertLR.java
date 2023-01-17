package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class GateVertLR extends Item{

	public GateVertLR() {
		sizeY = 144;
		name = "GateVertLR";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/items/GateVertLR.png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/items/GateVertLRGap.png"));
					
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		collision = true;
		
		hitBox.x = 0;
		hitBox.width = 48;
		hitBox.height = 144;
	}
}
