import org.jsfml.graphics.Texture;
import org.jsfml.system.*;


/**
 * Player class.
 * Stores sprite and state of the player.
 */
public class Player extends Entity
{
    private float speed = 0f;
    private float maxSpeed = 0.3f;
    private float accel = 0.1f;
    private Vector2f movVector = new Vector2f(0.1f, 0.1f);

    private boolean wasForward, wasBackward, wasLeft, wasRight;
    private InputHandler i;
    private int health = 100;
    private Vector2f forward = new Vector2f(0,-speed);
    private Vector2f backwards = new Vector2f(0,speed);
    private Vector2f left = new Vector2f(-speed,0);
    private Vector2f right = new Vector2f(speed,0);
    private Texture frontStand;
    private Animation frontWalk;

    CollisionBox b;
    /**
     */
    public Player(String filepath, Vector2f pos, Game scene)
    {
        
        // Loads the sprite and initialises the entity.
        super(TextureManager.getInstance().get(filepath), scene);

        frontWalk = new Animation(TextureManager.getInstance().get("assets/player/front_animation.png"), 5, 0.04f, new Vector2i(23, 45), 9);

        frontStand = TextureManager.getInstance().get("assets/player/Harvey.png");
        setOrigin(getLocalBounds().width/2, getLocalBounds().height/2);
        scale(1.6f, 1.6f);
        setPosition(pos.x, pos.y);
        i = getScene().getIHandler();
        wasForward = false;
        wasBackward = false;
        wasLeft = false;
        wasRight = false;
        b = new CollisionBox(new Vector2f(64,65),new Vector2f(64,64));
    }
    public void update()
    {
        //movVector = new Vector2f(0,0);
        movement();
        updateVectors();
        changeAnimation();
        setPosition(Vector2f.add(getPosition(), movVector));
        if(b.collided(this))
        {
            System.out.println("Collided");
            setPosition(Vector2f.add(getPosition(), Vector2f.neg(Vector2f.mul(movVector, 2f))));
            movVector = new Vector2f(0.01f, 0.01f);
        }
        getScene().draw(this);
    }
    private void movement()
    {
        
        if(isForward() || isBackward() || isRight() || isLeft())
        {
            //System.out.println("Bleb");
            //speed+=accel;
            speed = (float)Helper.clamp((double)speed + accel, 0, maxSpeed);
            wasForward = false;
            wasBackward = false;
            wasLeft = false;
            wasRight = false;
            if(isForward())
            {
                movVector = Vector2f.add(movVector, forward);
                wasForward = true;
            }
            if(isBackward())
            {
                movVector = Vector2f.add(movVector, backwards);
                wasBackward = true;
            } 
            if(isRight()) 
            {
                movVector = Vector2f.add(movVector, right);
                wasRight = true;
            }
            if(isLeft())
            {
                movVector = Vector2f.add(movVector, left);
                wasLeft = true;
            }
        }
        else
        {
            speed = (float)Helper.clamp((double)speed - accel, 0, maxSpeed);
            if(wasForward)
                movVector = Vector2f.add(movVector, forward);
            if(wasBackward)
                movVector = Vector2f.add(movVector, backwards);
            if(wasRight)
                movVector = Vector2f.add(movVector, right);
            if(wasLeft)
                movVector = Vector2f.add(movVector, left);
        }
        if(movVector.y != 0 && movVector.x != 0)
        {
            movVector = new Vector2f((float)(movVector.x*0.9), (float)(movVector.y*0.9));
        }
        
        speed = (float)Helper.clamp((double)speed, 0, maxSpeed);
        //System.out.println(speed);
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
        if(speed != 0)
        {
            frontWalk.startAnimation();
            changeTexture(frontWalk.getNextTexture());
        }
        else
        {
            frontWalk.stopAnimation();
            changeTexture(frontStand);
        }
        
    }
    public Vector2f getPos() { return this.getPosition(); }
}