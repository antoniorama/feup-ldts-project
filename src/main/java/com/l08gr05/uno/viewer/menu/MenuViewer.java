package com.l08gr05.uno.viewer.menu;

import com.l08gr05.uno.GUI;
import com.l08gr05.uno.Game.Menu;
import com.l08gr05.uno.viewer.Viewer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenuViewer extends Viewer<Menu> {

    private BufferedImage backgroundImg;
    private BufferedImage jogar;
    private BufferedImage jogarHighlighed;
    private BufferedImage sair;
    private BufferedImage sairHighlighed;

    public MenuViewer(Menu menu) throws IOException {
        super(menu);
        loadImages();
    }

    private void loadImages() throws IOException {
        backgroundImg = ImageIO.read(getClass().getResource("/UI/BACKGROUND.png"));
        backgroundImg = scaleImage(backgroundImg, GUI.getTerminalWidth());
        jogar = ImageIO.read(getClass().getResource("/UI/JOGAR.png"));
        jogar = scaleImage(jogar,GUI.getTerminalWidth()/5);
        jogarHighlighed = ImageIO.read(getClass().getResource("/UI/JOGAR_HIGHLIGHTED.png"));
        jogarHighlighed = scaleImage(jogarHighlighed,GUI.getTerminalWidth()/5);
        sair = ImageIO.read(getClass().getResource("/UI/SAIR.png"));
        sair = scaleImage(sair,GUI.getTerminalWidth()/7);
        sairHighlighed = ImageIO.read(getClass().getResource("/UI/SAIR_HIGHLIGHTED.png"));
        sairHighlighed = scaleImage(sairHighlighed,GUI.getTerminalWidth()/7);
    };

    private BufferedImage scaleImage(BufferedImage src, int w) {
        int h = w*src.getHeight() / src.getWidth();
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
        gui.drawImage(0,0,backgroundImg);
        int xSair = gui.get_terminalWidth() / 2 - sairHighlighed.getWidth() / 2;
        int xJogar = gui.get_terminalWidth() / 2 - jogarHighlighed.getWidth() / 2;
        int yInc =  gui.get_terminalHeight() / 7;
        if(getModel().isSelected(0)){
            gui.drawImage(xJogar, gui.get_terminalHeight() / 2 - yInc,jogarHighlighed);
            gui.drawImage(xSair, gui.get_terminalHeight() / 2 + yInc,sair);
        }
        else{
            gui.drawImage(xJogar, gui.get_terminalHeight() / 2 - yInc,jogar);
            gui.drawImage(xSair, gui.get_terminalHeight() / 2 + yInc,sairHighlighed);
        }
//        gui.drawMenuBackGround();
//        for (int i = 0; i < getModel().getNumberEntries(); i++)
//            gui.drawMenuElement(- i * 100, getModel().isSelected(i) ? getModel().getEntry(i) + "_HIGHLIGHTED.png" : getModel().getEntry(i) + ".png");
    }

}
