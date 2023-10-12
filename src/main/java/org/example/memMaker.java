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
    private float textPosition;
    private int fontSize;

    public memMaker(String[] args) {
        commandsDescriptionsList = new ArrayList<>();
        commandsDescriptionsList.add("mem - create a meme with your picture and text. args:(imagePath, text[, textPosition, fontSize]).\n" +
                "Example: mem image.jpg memtext textposition:center fontsize:25 \n "+
                "Text position options:top, center, bottom ");
        this.args = args;
    }

    //main processing func
    public void argsHandling() {
        for(int i = 0; i < args.length; i++){
            System.out.println(args[i]);
        }
        if (args.length > 0) {
            if (args[0].equals("help")) {
                printHelp(commandsDescriptionsList);
            }
            else if(args[0].equals("mem")){
                argsConvent();
                createAMeme();
            }
        }
    }

    //arguments are converted to variables
    public void argsConvent(){
        textPosition = 0.8f;
        fontSize = 25;
        Map<String, Float> textPositions = new HashMap<String, Float>();
        textPositions.put("bottom", 0.8f);
        textPositions.put("center", 0.5f);
        textPositions.put("top", 0.2f);

        for (String arg:args){

            if(arg.toLowerCase().indexOf("textposition:") != -1){
                textPosition = textPositions.get(arg.substring(arg.indexOf(':') + 1));
            }
            if(arg.toLowerCase().indexOf("fontsize:") != -1){
                fontSize = Integer.parseInt(arg.substring(arg.indexOf(':') + 1));
            }
        }

    }

    //put text on image
    public void createAMeme() {
        BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        try {
            image = ImageIO.read(new File(args[1]));
        }catch (IOException e){
            System.out.println("Image open error");
            e.printStackTrace();
            return;
        }
        Graphics g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, fontSize));
        g.drawString(args[2], image.getWidth()/3, (int) (image.getHeight() * textPosition));
        g.dispose();
        try {
            ImageIO.write(image, "png", new File("result.png"));
        }catch (IOException e){
            System.out.println("Image save error");
        }
    }

    //print help to console
    public void printHelp(ArrayList<String> commandsDescriptionsList) {
        for (String command : commandsDescriptionsList) {
            System.out.println(command);
        }
    }
}
