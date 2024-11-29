import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RenderEngine extends JPanel implements Engine{

    private List<Displayable> renderList;
    private DynamicSprite hero;
    private GameEngine gameEngine;

    public RenderEngine(DynamicSprite hero, GameEngine gameEngine) {

        renderList = new ArrayList<>();
        this.hero = hero;
        this.gameEngine = gameEngine;
    }

    public void setRenderList(List<Displayable> renderList) {
        this.renderList = renderList;
    }

    public void addToRenderList(Displayable displayable) {//not to have twice the same element
        renderList.add(displayable);
    }

    @Override
    public void update() {
        //System.out.println("RenderEngine is updating");
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (Main.gameState == GameState.TITLE_SCREEN) {

            g.setFont(new Font("Monospaced", Font.BOLD, 32));
            g.setColor(Color.BLACK);
            String title = "Dungeon Crawler";
            FontMetrics titleMetrics = g.getFontMetrics();
            int titleWidth = titleMetrics.stringWidth(title);
            int titleX = (getWidth() - titleWidth) / 2;
            int titleY = 100;
            g.drawString(title, titleX, titleY);

            g.setFont(new Font("DialogInput", Font.BOLD, 24));
            g.setColor(Color.RED);
            String text = "Press Enter to start";
            FontMetrics textMetrics = g.getFontMetrics();
            int textWidth = textMetrics.stringWidth(text);
            int textHeight = textMetrics.getHeight();
            int textX = (getWidth() - textWidth) / 2;
            int textY = 300;

            g.setColor(Color.YELLOW);
            g.fillRect(textX - 10, textY - textHeight + 5, textWidth + 20, textHeight);

            g.setColor(Color.RED);
            g.drawString(text, textX, textY);
        } else if (Main.gameState == GameState.PLAYING) {

            for (Displayable renderListElement : renderList) {
                renderListElement.draw(g);
            }

            if (hero != null) {
                int health = hero.getHealth();

                int barWidth = 200;
                int barHeight = 20;
                int barX = getWidth() - barWidth - 20;
                int barY = 20;

                Color barColor = health > 99 ? Color.GREEN : health > 66 ? Color.YELLOW : health > 33 ? Color.ORANGE : Color.RED;

                g.setColor(Color.GRAY);
                g.fillRect(barX, barY, barWidth, barHeight);

                g.setColor(barColor);
                g.fillRect(barX, barY, (health * barWidth) / 100, barHeight);

                g.setColor(Color.BLACK);
                g.drawRect(barX, barY, barWidth, barHeight);
            }

            if (gameEngine != null) {
                long elapsedTime = gameEngine.getElapsedTime();
                g.setFont(new Font("DialogInput", Font.BOLD, 20));
                g.setColor(Color.BLACK);
                String timeText = "Time: " + elapsedTime + "s";
                g.drawString(timeText, 20, 40);
            }

        } else if (Main.gameState == GameState.GAME_OVER) {

            g.setFont(new Font("Monospaced", Font.BOLD, 35));
            g.setColor(Color.RED);

            String gameOverText = "Game Over !";
            FontMetrics gameOverMetrics = g.getFontMetrics();
            int gameOverWidth = gameOverMetrics.stringWidth(gameOverText);
            int gameOverX = (getWidth() - gameOverWidth) / 2;
            int gameOverY = getHeight() / 2 - 20;
            g.drawString(gameOverText, gameOverX, gameOverY);


            String youLostText = "You lost";
            FontMetrics youLostMetrics = g.getFontMetrics();
            int youLostWidth = youLostMetrics.stringWidth(youLostText);
            int youLostX = (getWidth() - youLostWidth) / 2;
            int youLostY = gameOverY + 40;
            g.drawString(youLostText, youLostX, youLostY);
        }

        else if (Main.gameState == GameState.GAME_WIN) {
            g.setFont(new Font("Monospaced", Font.BOLD, 35));
            g.setColor(Color.GREEN);

            String gameWinText = "End Game !";
            FontMetrics gameWinMetrics = g.getFontMetrics();
            int gameWinWidth = gameWinMetrics.stringWidth(gameWinText);
            int gameWinX = (getWidth() - gameWinWidth) / 2;
            int gameWinY = getHeight() / 2 - 20;
            g.drawString(gameWinText, gameWinX, gameWinY);

            String youWinText = "You win";
            FontMetrics youWinMetrics = g.getFontMetrics();
            int youWinWidth = youWinMetrics.stringWidth(youWinText);
            int youWinX = (getWidth() - youWinWidth) / 2;
            int youWinY = gameWinY + 40;
            g.drawString(youWinText, youWinX, youWinY);

            g.setFont(new Font("DialogInput", Font.PLAIN, 20)); // Plus petit texte
            g.setColor(Color.BLACK);
            long totalTime = gameEngine.getTotalTime();
            String timeText = "Time: " + totalTime + "s";
            FontMetrics timeMetrics = g.getFontMetrics();
            int timeWidth = timeMetrics.stringWidth(timeText);
            int timeX = (getWidth() - timeWidth) / 2;
            int timeY = youWinY + 40;
            g.drawString(timeText, timeX, timeY);
        }
    }




}
