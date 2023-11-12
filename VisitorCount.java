public class VisitorCount implements Visitor {

    @Override
    public int visitUser(ListView views){
        return views.getUserCount();
    }

    @Override
    public int visitGroup(ListView views) {
        return views.getGroupCount();
    }

    @Override
    public int visitMessage(NewsFeed news) {
        return news.getCountMessages();
    }

    @Override
    public float visitPositiveMessage(NewsFeed news) {
       return news.getPercentangePositiveMessages();
    }
    
}
