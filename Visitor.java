// Visitor Pattern

public interface Visitor {

    public int visitUser(ListView views);

    public int visitGroup(ListView views);

    public int visitMessage(NewsFeed news);
    
    public float visitPositiveMessage(NewsFeed news);
    
}