import java.util.*;
import org.jsfml.graphics.*;

/**
 * Used to load textures once.
 */
public class TextureManager
{
    private static TextureManager INSTANCE;

    private LinkedList<StoredAsset<Texture>> textures = new LinkedList<StoredAsset<Texture>>();

    public Texture get(String filepath)
    {
        // Returns the stored texture.
        for (StoredAsset<Texture> t : textures)
        {
            if (t.getPath().equals(filepath)) return t.getAsset();
        }
        // Else, we load it.
        Texture texture = Helper.fileToTexture(filepath);
        textures.add(new StoredAsset<Texture>(filepath, texture));
        return texture;
    }

    public static TextureManager getInstance()
    {
        if(INSTANCE == null) INSTANCE = new TextureManager();
        return INSTANCE;
    }
}