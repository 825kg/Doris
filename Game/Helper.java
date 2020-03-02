import org.jsfml.system.*;
import java.lang.Math;
import java.nio.file.*;
import java.io.IOException;
import org.jsfml.graphics.*;


/**
 * Provides various helper functions to be used within the game.
 */
public class Helper
{
    /**
     * The default puzzle button size.
     */
    public static final Vector2f default_puzzle_button_size = new Vector2f(60,60);

    /**
     * Rotates the given vector by the specified degrees.
     * @param v The vector to rotate.
     * @param degree The degrees to rotate the vector by.
     * @return The newly rotated vector.
     */
    public static Vector2f rotateVector(Vector2f v, double degrees)
    {
        double radians = Math.toRadians(degrees);
        double x = v.x * Math.cos(radians) - v.y * Math.sin(radians);
        double y = v.x * Math.sin(radians) + v.y * Math.cos(radians);
        return new Vector2f((float)x, (float)y);
    }

    /**
     * Clamps the given value to the lower and upper bound.
     * @param val The value to clamp.
     * @param lower The lower bound to clamp to.
     * @param upper The upper bound to clamp to.
     */
    public static double clamp(double val, double lower, double upper)
    {
        if (val > upper) val = upper;
        else if (val < lower) val = lower;
        return val;
    }

    /**
     * This method calculates the unit vector for a vector.
     * @param a Vector need to calculate.
     * @return The unit vector.
     */
    public static Vector2f normalize(Vector2f a)
    {
        float var = (float)Math.sqrt(Math.pow(a.x,2)+Math.pow(a.y,2));
        return Vector2f.div(a,var);
    }

    // PLEASE COMMENT THIS!!!
    public static float Angle(Vector2f a, Vector2f b)
    {
        return (float)Math.atan2(a.y-b.y,a.x-b.x);
    }

    /**
     * Loads the specified file as a texture.
     * @param filepath The path of the file to load.
     * @return Returns the Texture object for the file.
     */
    public static org.jsfml.graphics.Texture fileToTexture(String filepath)
    {
        // Make image and texture.
        org.jsfml.graphics.Texture texture = new Texture();
        Image image = new Image();
        try
        {
            // Load image from file, and texture from image.
            image.loadFromFile(Paths.get(filepath));
            texture.loadFromImage(image);
        }
        catch(IOException|TextureCreationException ex) 
        {
            System.err.println("Something went wrong:");
            ex.printStackTrace();
        }
        return texture;
    }

    /**
     * Loads the specified file as a font.
     * @param filepath The path of the file to load.
     * @return Returns the Font object for the file.
     */
    public static Font fileToFont(String filepath)
    {
        Font f = new Font();
        try { f.loadFromFile(Paths.get(filepath)); }
        catch(IOException ex)
        {
            System.err.println("Something went wrong:");
            ex.printStackTrace();
        }
        return f;
    }

    /**
     * simple collision test wrote in the last 
     * @param a object a
     * @param b object b 
     * @return  if two object collide return true
     */
    public static boolean collisionTest(Entity a, Entity b)
    {
        if(a.getGlobalBounds().intersection(b.getGlobalBounds())!= null)
                return true;
        return false;
    }

    public static RectangleShape getCollisionBox(Entity a, Entity b, float dec1, float dec2)
    {
        FloatRect aGlobal = a.getGlobalBounds();
        FloatRect aLocal = a.getLocalBounds();
        // FloatRect bLocal = b.getLocalBounds();

        FloatRect gA = new FloatRect(aGlobal.left - aLocal.left, aGlobal.top - aLocal.top, aGlobal.width - aLocal.width, aGlobal.height - aLocal.height);
        // FloatRect gB = new FloatRect(bGlobal.left - bLocal.left, bGlobal.top - bLocal.top, bGlobal.width - bLocal.width, bGlobal.height - bLocal.height);
        FloatRect newBoundsA = new FloatRect(gA.left + (aLocal.left * dec1) - ((aLocal.width * dec1)/2) + (a.getGlobalBounds().width / 2), gA.top + (aLocal.top * dec2) - ((aLocal.height * dec2)/2) + (a.getGlobalBounds().height / 2), gA.width + (aLocal.width * dec1) - (a.getGlobalBounds().width / 2), gA.height + (aLocal.height * dec2) - (a.getGlobalBounds().height / 2));

        RectangleShape rs = new RectangleShape(new Vector2f(newBoundsA.width, newBoundsA.height));
        rs.setPosition(newBoundsA.left, newBoundsA.top);
        return rs;
    }

    public static boolean checkCollisionMul2(Entity a, Entity b, float dec1, float dec2)
    {
        // Both entities collion box are getting increased.

        FloatRect aGlobal = a.getGlobalBounds();
        FloatRect bGlobal = b.getGlobalBounds();
        FloatRect aLocal = a.getLocalBounds();
        // FloatRect bLocal = b.getLocalBounds();

        FloatRect gA = new FloatRect(aGlobal.left - aLocal.left, aGlobal.top - aLocal.top, aGlobal.width - aLocal.width, aGlobal.height - aLocal.height);
        // FloatRect gB = new FloatRect(bGlobal.left - bLocal.left, bGlobal.top - bLocal.top, bGlobal.width - bLocal.width, bGlobal.height - bLocal.height);
        FloatRect newBoundsA = new FloatRect(gA.left + (aLocal.left * dec1) - ((aLocal.width * dec1)/2) + (a.getGlobalBounds().width / 2), gA.top + (aLocal.top * dec2) - ((aLocal.height * dec2)/2) + (a.getGlobalBounds().height / 2), gA.width + (aLocal.width * dec1) - (a.getGlobalBounds().width / 2), gA.height + (aLocal.height * dec2) - (a.getGlobalBounds().height / 2));


        // FloatRect newBoundsB = new FloatRect(gB.left + (bLocal.left * dec), gB.top + (bLocal.top * dec), gB.width + (bLocal.width * dec), gB.height + (bLocal.height * dec));
        if(newBoundsA.intersection(bGlobal)!= null)
            return true;
        return false;
    }

    public static boolean checkCollisionMul(Entity a, Entity b, float dec)
    {
        return checkCollisionMul2(a, b, dec, dec);
    }

    public static boolean collisionTestSmaller(Entity a, Entity b)
    {
        return checkCollisionMul(a, b, 0.1f);
    }

}