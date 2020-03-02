import java.util.*;
import org.jsfml.graphics.*;
/**
 * Used to load fonts once.
 */
public class FontManager
{
    private static FontManager INSTANCE;

    private LinkedList<StoredAsset<Font>> textures = new LinkedList<StoredAsset<Font>>();

    public Font get(String filepath)
    {
        // Returns the stored texture.
        for (StoredAsset<Font> t : textures)
        {
            if (t.getPath().equals(filepath)) return t.getAsset();
        }
        // Else, we load it.
        Font texture = Helper.fileToFont(filepath);
        textures.add(new StoredAsset<Font>(filepath, texture));
        return texture;
    }

    public static FontManager getInstance()
    {
        if(INSTANCE == null) INSTANCE = new FontManager();
        return INSTANCE;
    }
}