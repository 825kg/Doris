

public class StoredAsset<T>
{
    private String path;
    private T asset;

    public StoredAsset(String path, T asset)
    {
        this.path = path;
        this.asset = asset;
    }

    public String getPath() { return this.path; }
    public T getAsset() { return this.asset; }
}