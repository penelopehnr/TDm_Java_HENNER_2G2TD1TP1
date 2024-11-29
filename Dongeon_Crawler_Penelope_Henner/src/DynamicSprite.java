import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite{
    public boolean isWalking = true;
    public double speed = 5;
    public int spriteSheetNumberOfColumn = 10;
    public int timeBetweenFrame = 200;
    public Direction direction;

    private int health = 100;
    private long lastDamageTime = 0;
    private static final long IMMUNITY_PERIOD = 1000;


    public DynamicSprite(Image image, double x, double y, double width, double height){
        super(image, x, y, width, height);
        setDirection(Direction.SOUTH);
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }

    public int getHealth() {
        return health;
    }

    public void decreaseHealth(int amount) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastDamageTime >= IMMUNITY_PERIOD) {
            health = Math.max(0, health - amount);
            lastDamageTime = currentTime;
        }
    }

    @Override
    public void draw(Graphics g) {
        int index = (int) (System.currentTimeMillis() / timeBetweenFrame) % spriteSheetNumberOfColumn;

        int attitude = direction.getFrameLineNumber();

        int SourceXStart = (int) (index * width);
        int SourceYStart = (int) (attitude * height);
        int SourceXStop = (int) (SourceXStart + width);
        int SourceYStop = (int) (SourceYStart + height);

        g.drawImage(image, (int) x, (int) y, (int) (x + width), (int) (y + height), SourceXStart, SourceYStart, SourceXStop, SourceYStop, null);
    }

    private void move(){
        switch (direction){
            case NORTH -> {
                this.y-=speed;
            }
            case SOUTH -> {
                this.y+=speed;
            }
            case EAST -> {
                this.x+=speed;
            }
            case WEST -> {
                this.x-=speed;
            }
        }
    }

    private boolean isMovingPossible(ArrayList<Sprite> environment){

        double futureX = this.x;
        double futureY = this.y;

        switch(direction){
            case NORTH -> futureY -= speed;
            case SOUTH -> futureY += speed;
            case EAST -> futureX += speed;
            case WEST -> futureX -= speed;
        }

        Rectangle2D.Double hitBox = new Rectangle2D.Double(futureX,futureY,this.width,this.height);

        for (Sprite sprite: environment){
            Rectangle2D.Double spriteHitBox = null;
            if ((sprite instanceof SolidSprite) && (sprite != this)){
                spriteHitBox = new Rectangle2D.Double(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
            }

            if (spriteHitBox != null && hitBox.intersects(spriteHitBox)) {
                decreaseHealth(33);
                return false;
            }
        }
        return true;
    }

    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment)) {
            move();
        }
    }
}
