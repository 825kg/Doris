import org.jsfml.graphics.*;
import org.jsfml.system.*;

public class Animation
{
    private Image spriteSheetIm;
    private float secondsPerText = 1.0f;
    private Clock timer;
    private boolean animationStart = false;
    private int widthPerTexture, heightPerTexture;
    private int totalLength, totalHeight;
    private int currX, currY;
    private int currentFrame =0;
    private int frames;
    private int numOfSprites;
    private org.jsfml.graphics.Texture currentAnim;
    public Animation(org.jsfml.graphics.Texture spriteSheet, int numOfSprites, float secondsPerText, Vector2i dimension, int frames)
    {
        spriteSheetIm = spriteSheet.copyToImage();
        currentAnim = spriteSheet;
        this.secondsPerText = secondsPerText;
        this.numOfSprites = numOfSprites;
        this.frames = frames;
        totalLength = spriteSheet.getSize().x;
        totalHeight = spriteSheet.getSize().y;
        widthPerTexture = dimension.x;
        heightPerTexture = dimension.y;
        
        currX = 0;
        currY = 0;
    }
    public void startAnimation()
    {
        
        if(!animationStart)
        {
            animationStart = true;
            timer = new Clock();
        }
    }
    public void stopAnimation()
    {
        animationStart = false;
    }
    public org.jsfml.graphics.Texture getNextTexture()
    {
        if(animationStart)
        {
            if(timer.getElapsedTime().asSeconds() >= secondsPerText)
            {
                
                timer = new Clock();
                if(currX >= (numOfSprites * widthPerTexture) - widthPerTexture)
                {
                    currX = 0;
                    if((currY + heightPerTexture) >= totalHeight)
                        currY = 0;
                    else
                        currY += heightPerTexture;
                }
                else
                    currX += widthPerTexture;
                
                
                if(currentFrame >= frames)
                {
                    currY = 0;
                    currX = 0;
                    currentFrame = 0;
                }
                currentFrame++;
            }
        }
        try{
            currentAnim.loadFromImage(spriteSheetIm, new IntRect(currX, currY, widthPerTexture, heightPerTexture));
            //currentAnim.loadFromImage(spriteSheetIm, new IntRect(32, 0, 32, 45));

        }
        catch(TextureCreationException ex) 
        {
            System.err.println("Animation not found:  ");
            ex.printStackTrace();
        }
        
        return currentAnim;
    }
    public void setSecondsPerText(float time) { secondsPerText = time; }
}