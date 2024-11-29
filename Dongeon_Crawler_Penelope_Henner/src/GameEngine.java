import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameEngine implements Engine, KeyListener {

    private final DynamicSprite hero;
    private final PlayGround playGround;
    private long gameOverStartTime = 0;
    private long gameStartTime;
    private long gameEndTime;
    private boolean gameEnded;

    public GameEngine(DynamicSprite hero, PlayGround playGround) {
        this.hero = hero;
        this.playGround = playGround;
        this.gameStartTime = System.currentTimeMillis();
        this.gameEnded = false;
    }

    public DynamicSprite getHero() {
        return hero;
    }

    @Override
    public void update() {
        if (Main.gameState == GameState.PLAYING) {

            if (hero.getX() < 0 || hero.getY() < 0 ||
                    hero.getX() + hero.getWidth() > playGround.getLabWidth() ||
                    hero.getY() + hero.getHeight() > playGround.getLabHeight()) {
                if (!gameEnded) {
                    Main.gameState = GameState.GAME_WIN;
                    gameEndTime = System.currentTimeMillis();
                    gameEnded = true;
                }
                return;
            }

            if (hero.getHealth() <= 0) {
                if (gameOverStartTime == 0) {
                    gameOverStartTime = System.currentTimeMillis();
                }
                if (System.currentTimeMillis() - gameOverStartTime >= 500) {
                    Main.gameState = GameState.GAME_OVER;
                }
            }
        }
    }

    public long getElapsedTime() {
        return (System.currentTimeMillis() - gameStartTime) / 1000;
    }

    public long getTotalTime() {
        return (gameEndTime - gameStartTime) / 1000;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (Main.gameState == GameState.TITLE_SCREEN) {

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                Main.gameState = GameState.PLAYING;
            }
        } else if (Main.gameState == GameState.PLAYING) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    hero.setDirection(Direction.NORTH);
                    break;
                case KeyEvent.VK_DOWN:
                    hero.setDirection(Direction.SOUTH);
                    break;
                case KeyEvent.VK_LEFT:
                    hero.setDirection(Direction.WEST);
                    break;
                case KeyEvent.VK_RIGHT:
                    hero.setDirection(Direction.EAST);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
