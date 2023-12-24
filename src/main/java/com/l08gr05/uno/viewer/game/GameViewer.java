package com.l08gr05.uno.viewer.game;

import com.l08gr05.uno.GUI;
import com.l08gr05.uno.Game.Game;
import com.l08gr05.uno.decks_cards.Card;
import com.l08gr05.uno.viewer.Viewer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class GameViewer extends Viewer<Game> {

    private BufferedImage drawText;
    private BufferedImage colorText;
    public GameViewer(Game game) throws IOException {
        super(game);
        loadImages();
    }

    private void loadImages() throws IOException {
        drawText = ImageIO.read(getClass().getResource("/UI/DrawCardsText.png"));
        drawText = scaleImage(drawText, Card.getWidth() * 8);
        colorText = ImageIO.read(getClass().getResource("/UI/Color.png"));
        colorText = scaleImage(colorText, (int)(Card.getWidth() * 1.4));
    }
    private BufferedImage scaleImage(BufferedImage src, int w) {
        int h = w * src.getHeight() / src.getWidth();
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
    @Override
    public void drawElements(GUI gui) throws IOException {
        drawPlayer(gui);
        drawCPU(gui);
        drawTop(gui);
        if(getModel().get_colorChooser() && getModel().get_playerTurn()){
            drawColorChooser(gui);
        }
        drawUI(gui);
    }

    private void drawPlayer(GUI gui) throws IOException {
        List<Card> deckList = getModel().get_playerDeck().get_deckList();
        int x = gui.get_terminalWidth() / 12;
        int y = gui.get_terminalHeight() * 4/5;
        int xInc = gui.get_terminalWidth() * 10 / 12 / deckList.size();
        for(Card card : deckList){
            if(card.get_isSelected() && getModel().get_playerTurn() && !getModel().get_colorChooser()){
                gui.drawHighlight(x,y,Card.getWidth(),Card.getHeight());
            }
            gui.drawImage(x,y,card.get_image());
            x += xInc;
        }
    }
    private void drawCPU(GUI gui) throws IOException {
        int size = getModel().get_cpuDeck().size();
        int x = gui.get_terminalWidth() / 12;
        int y = gui.get_terminalHeight()/8;
        int xInc = gui.get_terminalWidth() * 10 / 12 / size;
        for(int i = 1; i <= size;i++){
            gui.drawImage(x,y,Card.getBackImage());
            x += xInc;
        }
    }
    private void drawColorChooser(GUI gui){
        int increment = (gui.get_terminalWidth() - gui.get_terminalWidth() / 5) / 4;
        gui.drawSquare(gui.get_terminalWidth()/5 - gui.get_cardHeight() / 2, gui.get_terminalHeight()*3/5, gui.get_cardHeight(), "red");
        gui.drawSquare(gui.get_terminalWidth()/5 - gui.get_cardHeight() / 2 +increment, gui.get_terminalHeight()*3/5, gui.get_cardHeight(), "green");
        gui.drawSquare(gui.get_terminalWidth()/5 - gui.get_cardHeight() / 2 + 2 * increment, gui.get_terminalHeight()*3/5, gui.get_cardHeight(), "blue");
        gui.drawSquare(gui.get_terminalWidth()/5 - gui.get_cardHeight() / 2 + 3 * increment, gui.get_terminalHeight()*3/5, gui.get_cardHeight(), "yellow");
        switch( getModel().get_indexColorChooser()){
            case 0:
                gui.drawHighlight(gui.get_terminalWidth()/5 - gui.get_cardHeight() / 2, gui.get_terminalHeight()*3/5, gui.get_cardHeight(),gui.get_cardHeight());
                break;
            case 1:
                gui.drawHighlight(gui.get_terminalWidth()/5 - gui.get_cardHeight() / 2 +increment, gui.get_terminalHeight()*3/5, gui.get_cardHeight(),gui.get_cardHeight());
                break;
            case 2:
                gui.drawHighlight(gui.get_terminalWidth()/5 - gui.get_cardHeight() / 2 + 2 * increment, gui.get_terminalHeight()*3/5, gui.get_cardHeight(),gui.get_cardHeight());
                break;
            case 3:
                gui.drawHighlight(gui.get_terminalWidth()/5 - gui.get_cardHeight() / 2 + 3 * increment, gui.get_terminalHeight()*3/5, gui.get_cardHeight(),gui.get_cardHeight());
        }
    }
    private void drawTop(GUI gui) throws IOException {
        BufferedImage img = getModel().get_playedDeck().getTop().get_image();
        gui.drawImage(gui.get_terminalWidth()/2 - Card.getWidth()/2, gui.get_terminalHeight()/2 - Card.getHeight()/2,img);
    }

    private void drawUI(GUI gui) throws IOException {
        gui.drawImage( gui.get_terminalWidth()/40,(int)(gui.get_terminalHeight() / 2.75),colorText);
        gui.drawSquare(gui.get_terminalWidth()/40,(int)(gui.get_terminalHeight() / 2 - Card.getHeight() * 0.75),(int)(Card.getHeight()*1.5), getModel().get_color());
        if(getModel().get_playerDraw()){
            gui.drawImage(gui.get_terminalWidth() / 2 - drawText.getWidth() / 2,gui.get_terminalHeight() * 13 / 20, drawText);
        }
    }

}
