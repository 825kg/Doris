import org.jsfml.graphics.*;
import org.jsfml.window.*;
import org.jsfml.system.*;

/**
 * Used to create and manage the game.
 */
public class Game extends RenderWindow
{
    public enum GameState
    {
        GAME
    }

    private Player player;
    private int width;
    private int height;
    private GameState state = GameState.GAME;
    private View temp;

    private InputHandler inputHandler;
    private BigMap map1;

    /**
     * Starts a new game instance.
     */
    public Game(int width, int height, String title)
    {
        // Stores the width of the window.
        this.width = width;
        // Stores the height of the window.
        this.height = height;
        // Creates the input handler.
        inputHandler = new InputHandler(this);
        map1 = new BigMap(this);
        player = new Player("assets/player/rat.png", new Vector2f(0,0), this);

        create(new VideoMode(width, height), title, 4);
        // Limit the framerate.
        setFramerateLimit(60);
        
        // Enters the window render loop.
        while (isOpen())
        {
            inputHandler.update();
            // Makes the window black because we are in space.
            clear(Color.BLACK);
            if (state == GameState.GAME)
            {
                // Set view for player, planets,background. Draw game element
                setView(new View(player.getPos(), new Vector2f(width * 0.5f, height * 0.5f)));
                map1.update();
                // Updates the background.
                player.update();
                //Do everything
                //set view for UI update. Draw UI
                setView(this.getDefaultView());
            
            }
            // Display what was drawn.
            display();
        }
    }

    /**
     * Updates the view to the given position.
     * @param pos The position to set the view to.
     */
    public void setView(Vector2f pos) { temp = new View(pos, new Vector2f(width, height)); setView(temp); }
    public InputHandler getIHandler() { return inputHandler; }
    public int getWidth(){ return width; }
    public int getHeight(){ return height; }
}