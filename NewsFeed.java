import java.util.ArrayList;
import java.util.List;

public class NewsFeed extends UserSubject {
    private static NewsFeed instance;

    public static NewsFeed getInstance() {
        if(instance == null){
            instance = new NewsFeed();
        }
        return instance;
    }

    private NewsFeed(){}

    private List<String> senders = new ArrayList<String>();
    private List<String> messages = new ArrayList<String>();
    private String lastUser;

    public List<String> getSenders() {
        return senders;
    }

    public void setSenders(List<String> senders) {
        this.senders = senders;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void getMessages(List<String> messages) {
        this.messages = messages;
    }

    public void postMessage(User u, String message) {
        senders.add(u.getName());
        messages.add(message);
        updateFollowers();
        List<User> followers = u.getFollowers();
        System.out.println(u.getName());
        for (int i = 0; i <followers.size(); i++) {
            System.out.println(", " + followers.get(i).getName());  
        }
        System.out.println("News feed has been updated.");
        lastUser = u.getName();
    }

    public String getUpdateId() {
        return lastUser;
    }

    public List<String> printNewsFeed(User u, List<String> followings){
        List<String> userList = new ArrayList<String>();
        userList.add(u.getName() + "'s News Feed:");
        for( int i = 0; i < senders.size(); i++){
            if(u.getName() == senders.get(i) || followings.contains(senders.get(i))){
                userList.add(senders.get(i) + ": " + messages.get(i));
            }
        }
        return userList;
    }


    // Calulations
    public int getCountMessages(){
        return messages.size();
    }

    public float getPercentangePositiveMessages(){
        float positiveMessages = 0;
        // float percentangePositiveMessage = 0;
        
        for(String s: messages){
            if( s.toLowerCase().contains("good") ||
                s.toLowerCase().contains("happy") ||
                s.toLowerCase().contains("great") ||
                s.toLowerCase().contains("excellent")
            ) {
                positiveMessages++;
            }
        }
        //percentangePositiveMessage = positiveMessages/3;
        // (float)getCountMessages();
        
        return positiveMessages/(float)getCountMessages(); 
    }

    public int acceptMessage(Visitor visitor){
        return visitor.visitMessage(this);
    }

    public float acceptPositiveMessage(Visitor visitor){
        return visitor.visitPositiveMessage(this);
    }
    
}
