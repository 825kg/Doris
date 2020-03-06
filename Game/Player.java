import org.jsfml.graphics.Texture;
import org.jsfml.system.*;


/**
 * Player class.
 * Stores sprite and state of the player.
 */
public class Player extends Entity
{
    private float speed = 0;
    private float maxSpeed = 0.6f;
    private float accel = 0.1f;
    private Vector2f movVector = new Vector2f(0, 0);

    private InputHandler i;
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
        i = getScene().getIHandler();
        
    }
    public void update()
    {
        //movVector = new Vector2f(0,0);
        movement();
        updateVectors();
        changeAnimation();
        setPosition(Vector2f.add(getPosition(), movVector));
        getScene().draw(this);
    }
    private void movement()
    {
        
        if(isForward() || isBackward() || isRight() || isLeft())
        {
            //System.out.println("Bleb");
            //speed+=accel;
            speed = (float)Helper.clamp((double)speed + accel, 0, maxSpeed);
            if(isForward())
                movVector = Vector2f.add(movVector, forward);
            if(isBackward())
                movVector = Vector2f.add(movVector, backwards);
            if(isRight())
                movVector = Vector2f.add(movVector, right);
            if(isLeft())
                movVector = Vector2f.add(movVector, left);
        }
        else
        {
            speed = (float)Helper.clamp((double)speed - accel, 0, maxSpeed);
        }
        if(movVector.y != 0 && movVector.x != 0)
        {
            movVector = new Vector2f((float)(movVector.x*0.9), (float)(movVector.y*0.9));
        }
        
        speed = (float)Helper.clamp((double)speed, 0, maxSpeed);
        //if(speed ==0)
        //{
        //    movVector = new Vector2f(0,0);
        //}
        System.out.println(speed);
    }
    private boolean isForward() { return i.getKeyState(InputHandler.KeyType.W).isDown(); }
    private boolean isBackward() { return i.getKeyState(InputHandler.KeyType.S).isDown(); }
    private boolean isRight() { return i.getKeyState(InputHandler.KeyType.D).isDown(); }
    private boolean isLeft() { return i.getKeyState(InputHandler.KeyType.A).isDown(); }
    private void updateVectors()
    {
        forward = new Vector2f(0,-speed);
        backwards = new Vector2f(0,speed);
        left = new Vector2f(-speed,0);
        right = new Vector2f(speed,0);
    }
    private void changeAnimation()
    {
        //System.out.println(movVector.y);
        if(movVector.y != 0)
        {
            
            if(isForward())
            {
                backWalk.startAnimation();
                frontWalk.stopAnimation();
                changeTexture(backWalk.getNextTexture());
                isBack = true;
            }
            else if(isBackward())
            {
                frontWalk.startAnimation();
                backWalk.stopAnimation();
                changeTexture(frontWalk.getNextTexture());
                isBack = false;
            }
        }
        else
        {
            
            if(isRight() || isLeft())
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