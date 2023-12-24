package com.l08gr05.uno.decks_cards;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class Card{

    private final String type;
    private final String color;
    private BufferedImage image;
    private Boolean isSelected;
    private static int cardHeight;
    private static int cardWidth;
    private static BufferedImage backImage;

    public Card(String type) throws IOException {
        this("dark",type);
    }
    public Card(String color, String type) throws IOException {
        this.color = color;
        this.type = type;
        String resName = "/Cards/" + type + color.charAt(0) + ".png";

        image = ImageIO.read(getClass().getResource(resName));
        image = scaleImage(image,cardWidth,cardHeight);

        isSelected = false;
   }

    private BufferedImage scaleImage(BufferedImage src, int w,int h) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        int ww = src.getWidth();
        int hh = src.getHeight();
        int[] ys = new int[h];
        for (int y = 0; y < h; y++)
            ys[y] = y * hh / h;
        for (int x = 0; x < w; x++) {
            int newX = x * ww / w;
            for (int y = 0; y < h; y++) {
                int col = src.getRGB(newX, ys[y]);
                img.setRGB(x, y, col);
            }
        }
        return img;
    }
    private static BufferedImage scaleStaticImage(BufferedImage src, int w,int h) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        int ww = src.getWidth();
        int hh = src.getHeight();
        int[] ys = new int[h];
        for (int y = 0; y < h; y++)
            ys[y] = y * hh / h;
        for (int x = 0; x < w; x++) {
            int newX = x * ww / w;
            for (int y = 0; y < h; y++) {
                int col = src.getRGB(newX, ys[y]);
                img.setRGB(x, y, col);
            }
        }
        return img;
    }
   public BufferedImage get_image(){
        return image;
   }
    public String get_type(){
        return this.type;
    }

    public String get_color(){
        return this.color;
    }

    public Boolean isNumber(){
        return Arrays.asList("00","01","02","03","04","05","06","07","08","09").contains(type);
    }
    public static void setWidthHeight(int w,int h) throws IOException {
        cardWidth = w;
        cardHeight = h;
        setBackCard();
    }
    public static int getWidth(){
        return cardWidth;
  }
    public static int getHeight(){
        return cardHeight;
    }
    private static void setBackCard() throws IOException {
        backImage = ImageIO.read(Card.class.getResource("/Cards/back.png"));
        backImage = scaleStaticImage(backImage,Card.getWidth(), Card.getHeight());
    }
    public static BufferedImage getBackImage(){
        return backImage;
    }

    public void setIsSelected(boolean selected){
        isSelected = selected;
    }

    public Boolean get_isSelected(){return isSelected;}
    public Boolean canCardBePlayedOver(Card card){
        return card.color == color ||card.color == "dark" || card.type == type;
    }
}
