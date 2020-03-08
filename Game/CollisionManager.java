import java.util.*;

public class CollisionManager
{
    private Game scene;
    private ArrayList<CollisionBox> boxes = new ArrayList<CollisionBox>();
    public CollisionManager(Game scene)
    {
        this.scene = scene;
    }
}