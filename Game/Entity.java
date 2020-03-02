import org.jsfml.graphics.*;

/**
 * Represents an entity in the game scene.
 * Used to provide base methods for manipulating objects within the game scene.
 */
public class Entity extends Sprite
{
    /**
     * A copy of the scene so the entity can be drawn.
     */
    private Game scene;

    /**
     * The health of the entity.
     */
    private int health;

    /**
     * The damage the entity can deal.
     */
    private int damage;

    /**
     * Creates a new empty entity within the scene.
     */
    public Entity(org.jsfml.graphics.Texture texture, Game scene) { super(texture); this.scene = scene; }

    /**
     * Returns the RenderWindow that draws this entity.
     */
    public Game getScene() { return scene; }

    /**
     * Updates the entity (should be called every frame update).
     */
    public void update() { this.getScene().draw(this); }
    /**
     * Changes sprite texture
     * @param texture
     */
    public void changeTexture(org.jsfml.graphics.Texture texture) { setTexture(texture, true); }

    /**
     * Returns true if this entity intersects with another entity.
     * @param target The target entity to check for an intersection with.
     * @param mul The multiplier (allows for extension of the collsion box of the target entity).
     * @return True if the two entity intersect.
     */
    public boolean intersection(Entity target, float mul, float mul2)
    {
        return Helper.checkCollisionMul2(this, target, mul, mul2);
    }

    public void setHealth(int health) { this.health = health; }

    public void setDamage(int damage) { this.damage = damage; }

    public boolean dealDamage(Entity e) { return e.takeDamage(this.damage); }

    public boolean isDead() { return this.health <= 0; }

    public boolean takeDamage(int damage) { this.health -= damage; return this.isDead(); }
}