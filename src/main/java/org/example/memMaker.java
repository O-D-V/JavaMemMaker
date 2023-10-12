package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class memMaker {
    private String[] args;
    private ArrayList<String> commandsDescriptionsList;
    private Map<String, Float> textPosition;

    public memMaker(String[] args) {
        textPosition = new HashMap<String, Float>();
        textPosition.put("bottom", 0.8f);
        textPosition.put("center", 0.5f);
        textPosition.put("top", 0.2f);
        commandsDescriptionsList = new ArrayList<>();
        commandsDescriptionsList.add("mem - create a meme with your picture and text. args:(imagePath, text, textPosition)");
        this.args = args;
    }

    public void argsHandling() {
        for(int i = 0; i < args.length; i++){
            System.out.println(args[i]);
        }
        if (args.length > 0) {
            if (args[0].equals("help")) {
                printHelp(commandsDescriptionsList);
            }
            else if(args[0].equals("mem")){
                createMeme();
            }
        }
    }

    public void createMeme() {
        BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);;
        try {
            image = ImageIO.read(new File(args[1]));//args[1])
        }catch (IOException e){
            System.out.println("Image open error");
        }
        Graphics g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        if (args.length > 3)
            g.drawString(args[2], image.getWidth()/3, (int) (image.getHeight() * textPosition.get(args[3])));
        else
            g.drawString(args[2], image.getWidth()/3, image.getHeight()/10 * 8);
        g.dispose();
        try {
            ImageIO.write(image, "png", new File("result.png"));//заменить pathname на args[1] чтобы заменять картинку, а не создавать новую
        }catch (IOException e){
            System.out.println("Image save error");
        }
    }

    public void printHelp(ArrayList<String> commandsDescriptionsList) {
        for (String command : commandsDescriptionsList) {
            System.out.println(command);
        }
    }
}
