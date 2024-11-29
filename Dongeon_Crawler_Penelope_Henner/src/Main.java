import javax.swing.JFrame;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;


public class Main {

    JFrame displayZoneFrame;
    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;

    public static GameState gameState = GameState.TITLE_SCREEN;

    public Main() throws Exception {

        displayZoneFrame = new JFrame("Dungeon Crawler");
        displayZoneFrame.setSize(384, 600);
        displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       // Sprite test = new Sprite(ImageIO.read(new File("./img/tree.png")), 200, 300, 64, 64);
       // renderEngine.addToRenderList(test);

        DynamicSprite hero = new DynamicSprite(ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 200, 300, 48, 50);

        PlayGround playGround = new PlayGround("./data/level1.txt");

        gameEngine = new GameEngine(hero, playGround);

        renderEngine = new RenderEngine(hero, gameEngine);

        ArrayList<Displayable> spriteList = playGround.getSpriteList();
        ArrayList<Sprite> solidSpriteList = playGround.getSolidSpriteList();

        for (Displayable sprite : spriteList) {
            renderEngine.addToRenderList(sprite);
        }

        PhysicEngine physicEngine = new PhysicEngine();
        physicEngine.setEnvironment(solidSpriteList);


        renderEngine.addToRenderList(hero);
        physicEngine.addMovingSpriteList(hero);


        Timer renderTimer = new Timer(50, (time)-> renderEngine.update());
        Timer gameTimer = new Timer(50, (time)-> gameEngine.update());
        Timer physicTimer = new Timer(50, (time)-> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);
        displayZoneFrame.addKeyListener(gameEngine);
    }

    public static void main (String[] args) throws Exception {
        Main main = new Main();

    }

}