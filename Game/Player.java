import org.jsfml.graphics.Texture;
import org.jsfml.system.*;


/**
 * Player class.
 * Stores sprite and state of the player.
 */
public class Player extends Entity
{
    private float speed = 3;
    private Vector2f movVector = new Vector2f(0, 0);

    private int health = 100;
    private Vector2f forward = new Vector2f(0,-speed);
    private Vector2f backwards = new Vector2f(0,speed);
    private Vector2f left = new Vector2f(-speed,0);
    private Vector2f right = new Vector2f(speed,0);
    private Texture backStand, frontStand;
    private Animation backWalk, frontWalk;
    private boolean isBack = false;
    /**
     */
    public Player(String filepath, Vector2f pos, Game scene)
    {
        
        // Loads the sprite and initialises the entity.
        super(TextureManager.getInstance().get(filepath), scene);

        frontWalk = new Animation(TextureManager.getInstance().get("assets/player/front_animation.png"), 5, 0.04f, new Vector2i(23, 45), 9);
        backWalk = new Animation(TextureManager.getInstance().get("assets/player/back_animation.png"), 5, 0.04f, new Vector2i(23, 45), 9);
        frontStand = TextureManager.getInstance().get("assets/player/Harvey.png");
        backStand = TextureManager.getInstance().get("assets/player/Harvey's back.png");
        setOrigin(getLocalBounds().width/2, getLocalBounds().height/2);
        scale(1.6f, 1.6f);
        setPosition(pos.x, pos.y);
        
    }
    public void update()
    {
        movVector = new Vector2f(0,0);
        movement();
        changeAnimation();
        setPosition(Vector2f.add(getPosition(), movVector));
        getScene().draw(this);
    }
    private void movement()
    {
        InputHandler i = getScene().getIHandler();
        if(i.getKeyState(InputHandler.KeyType.W).isDown())
            movVector = Vector2f.add(movVector, forward);
        if(i.getKeyState(InputHandler.KeyType.S).isDown())
            movVector = Vector2f.add(movVector, backwards);
        if(i.getKeyState(InputHandler.KeyType.A).isDown())
            movVector = Vector2f.add(movVector, left);
        if(i.getKeyState(InputHandler.KeyType.D).isDown())
            movVector = Vector2f.add(movVector, right);
        
        if(movVector.y != 0 && movVector.x != 0)
        {
            movVector = new Vector2f((float)(movVector.x*0.9), (float)(movVector.y*0.9));
        }
        
    }
    private void changeAnimation()
    {
        if(movVector.y != 0)
        {
            if(movVector.y < 0 )
            {
                backWalk.startAnimation();
                frontWalk.stopAnimation();
                changeTexture(backWalk.getNextTexture());
                isBack = true;
            }
            else if(movVector.y > 0.1)
            {
                frontWalk.startAnimation();
                backWalk.stopAnimation();
                changeTexture(frontWalk.getNextTexture());
                isBack = false;
            }
        }
        else
        {
            if(movVector.x < 0  || movVector.x > 0.1)
            {
                if(isBack)
                {
                    backWalk.startAnimation();
                    frontWalk.stopAnimation();
                    changeTexture(backWalk.getNextTexture());
                }
                else
                {
                    frontWalk.startAnimation();
                    backWalk.stopAnimation();
                    changeTexture(frontWalk.getNextTexture());
                }
            }
            else
            {
                if(isBack)
                    changeTexture(backStand);
                else
                    changeTexture(frontStand);
            }
        }
    }
    public Vector2f getPos() { return this.getPosition(); }
}