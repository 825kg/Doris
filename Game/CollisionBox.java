import org.jsfml.graphics.*;
import org.jsfml.system.*;

public class CollisionBox extends RectangleShape
{
    public CollisionBox(Vector2f size, Vector2f position)
    {
        super(size);
        setPosition(position);
    }
    public boolean collided(Entity b)
    {
        if(getGlobalBounds().intersection(b.getGlobalBounds())!= null)
                return true;
        return false;
    }
}