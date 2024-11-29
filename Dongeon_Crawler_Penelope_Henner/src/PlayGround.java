import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class PlayGround {
    private ArrayList<Sprite> environment = new ArrayList<>();
    private int LabWidth;
    private int LabHeight;

    public PlayGround (String pathName){
        try{
            final Image imageTree = ImageIO.read(new File("./img/tree.png"));
            final Image imageGrass = ImageIO.read(new File("./img/grass.png"));
            final Image imageRock = ImageIO.read(new File("./img/rock.png"));
            final Image imageTrap = ImageIO.read(new File("./img/trap.png"));

            final int imageTreeWidth = imageTree.getWidth(null);
            final int imageTreeHeight = imageTree.getHeight(null);

            final int imageGrassWidth = imageGrass.getWidth(null);
            final int imageGrassHeight = imageGrass.getHeight(null);

            final int imageRockWidth = imageRock.getWidth(null);
            final int imageRockHeight = imageRock.getHeight(null);

            final int imageTrapWidth = imageTrap.getWidth(null);
            final int imageTrapHeight = imageTrap.getHeight(null);

            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
            String line=bufferedReader.readLine();
            int lineNumber = 0;
            while (line!= null){
                int columnNumber = 0;
                for (byte element : line.getBytes(StandardCharsets.UTF_8)){
                    switch (element){
                        case 'T' : environment.add(new SolidSprite(imageTree,
                                columnNumber*imageTreeWidth,lineNumber*imageTreeHeight, imageTreeWidth, imageTreeHeight));
                            break;
                        case ' ' : environment.add(new Sprite(imageGrass,
                                columnNumber*imageGrassWidth, lineNumber*imageGrassHeight, imageGrassWidth, imageGrassHeight));
                            break;
                        case 'R' : environment.add(new SolidSprite( imageRock,
                                columnNumber*imageRockWidth,lineNumber*imageRockHeight, imageRockWidth, imageRockHeight));
                            break;
                        case 'O' : environment.add(new SolidSprite(imageTrap, columnNumber*imageTrapWidth, lineNumber*imageTrapHeight, imageTrapWidth, imageTrapHeight));
                    }
                    columnNumber++;
                }
                lineNumber++;
                line=bufferedReader.readLine();
                LabWidth = Math.max(LabWidth, columnNumber * imageTreeWidth);
            }
            LabHeight = lineNumber * imageTreeHeight;

            //System.out.println("LabWidth: " + LabWidth);
            //System.out.println("LabHeight: " + LabHeight);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getLabWidth() {
        return LabWidth;
    }

    public int getLabHeight() {
        return LabHeight;
    }

    public ArrayList<Sprite> getSolidSpriteList(){
        ArrayList <Sprite> solidSpriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            if (sprite instanceof SolidSprite) solidSpriteArrayList.add(sprite);
        }
        return solidSpriteArrayList;
    }

    public ArrayList<Displayable> getSpriteList(){
        ArrayList <Displayable> displayableArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            displayableArrayList.add((Displayable) sprite);
        }
        return displayableArrayList;
    }
}

