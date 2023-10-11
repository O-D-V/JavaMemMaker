package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class memMaker {
    private String[] args;
    private ArrayList<String> commandsDescriptionsList;

    public memMaker(String[] args) {
        commandsDescriptionsList = new ArrayList<>();
        commandsDescriptionsList.add("mem - create a meme with your picture and text args:(imagePath, text)");
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
        }catch (IllegalArgumentException e){
            System.out.println("Input is null");

        }
        //BufferedImage image = ImageIO.read(new File(args[1]));
        //BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.setFont(Font.getFont("Arial"));
        g.getFontMetrics()
        g.drawString(args[2], image.getWidth()/2, image.getHeight()/10 * 8);
        g.dispose();
        try {
            ImageIO.write(image, "png", new File("result.png"));
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
