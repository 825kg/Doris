import java.util.ArrayList;
import java.util.Random;

import org.jsfml.graphics.Sprite;

public class Map
{
    Game scene;
    ArrayList<Sprite> pallet;
    /*int[][] layout ={ 
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    };    
    */
    int[][] layout = new int[100][100];
    public Map(Game scene)
    {
        this.scene = scene;
        pallet = new ArrayList<Sprite>();
        pallet.add(new Sprite(TextureManager.getInstance().get("assets/tiles/Grass tile #1.png")));
        pallet.add(new Sprite(TextureManager.getInstance().get("assets/tiles/Grass tile #2.png")));
        pallet.add(new Sprite(TextureManager.getInstance().get("assets/tiles/Grass tile #3.png")));
        pallet.add(new Sprite(TextureManager.getInstance().get("assets/tiles/Water pool #1.png")));
        pallet.add(new Sprite(TextureManager.getInstance().get("assets/tiles/Grass tile #5.png")));
        randomize();
    }
    public void randomize()
    {
        Random rand = new Random();
        for(int row = 0; row < layout.length; row++)
        {
            for(int column = 0; column < layout[row].length; column++)
            {
                layout[row][column] = rand.nextInt(5);
            }
        }
    }
    public void update()
    {
        for(int row = 0; row < layout.length; row++)
        {
            for(int column = 0; column < layout[row].length; column++)
            {
                if(layout[row][column] != -1)
                {
                    pallet.get(layout[row][column]).setPosition(row*64, column*64);
                    scene.draw(pallet.get(layout[row][column]));
                }
            }
        }
    }
}