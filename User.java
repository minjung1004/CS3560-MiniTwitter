import java.util.*;

// Composite Pattern

// import java.util.ArrayList;
// import java.util.List;

//  1) an unique ID; 
//  2) a list of user IDs that are following this user (followers); 
//  3) a list of user IDs being followed by this user (followings); 
//  4) a news feed list containing a list of Twitter messages.

public class User implements UserComposite, UserObservers{

    private String userName;
    private List<User> followings = new ArrayList<User>();
    private List<User> followers = new ArrayList<User>();

    @Override
    public void clickOn() {
        System.out.println("User is being Clicked");
    }

    public void setName(String name) {
        this.userName = name;
        if ( !userName.equals("Current Following: ")){
            System.out.println(userName + " has been created ");
        }
    }
    
    @Override
    public String getName() {
        return userName;
    }

    @Override
    public void update(UserSubject subject){
        if ( subject instanceof NewsFeed) {
            ((NewsFeed) subject).printNewsFeed(this, getFollowing());
        }
    }

    public List<String> getFollowing() {
        List<String> userList = new ArrayList<String>();
        if(followings.isEmpty()){
            User user = new User();
            user.setName("Current Following: ");
            followings.add(user);
        }
        for (User u : followings) {
            userList.add(u.getName());
        }
        return userList;
    }

    public void setFollowing(List<User> followings){
        this.followings = followings;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public void follow(User u){
        followers.add(u);
    }

    // Implement for UserGroups
    @Override
    public boolean isGroup(){
        return false;
    }

    @Override
    public List<String> getUserList() {
        return null;
    }

    @Override
    public boolean isEmpty(){
        return false;
    }

    @Override
    public void addUser(UserComposite uc){
        // return nothing
    }

    @Override
    public UserComposite findGroup(String gorup){
        return null;
    }

    @Override
    public UserComposite findUser(String user){
        return null;
    }



    // // Intance fields
    // private String userId; // unique Id
    // // private List<User> followings = new ArrayList<>();
    // // private List<User> followers = new ArrayList<>();
    // // private List<String> tweetMessage = new ArrayList<>();
    // // private List<String> newsFeed = new ArrayList<>(Arrays.asList());
    // // private JList tweetFeeds;

    // private ArrayList<User> followers = new ArrayList<User>();
    // private ArrayList<User> followings = new ArrayList<User>();
    // private List<String> tweetMessage= new ArrayList<String>();
    // private Message newsFeed = new Message();
    // private JList tweetFeed;


    // public User(String id){
    //     this.userId = id;
    // }

    // // Accessor methods
    // @Override
    // public void setId(String id) {
    //     userId = id;
    // }

    // @Override
    // public String getId() {
    //     return userId;
    // }

    // @Override
    // public String toString() {
    //     return userId;
    // }

    // // @Override
    // // public void accept(Visitor visitor){
    // //     visitor.visitUser(this);
    // // }

    // // @Override
    // // public void update(UserSubject subject, String tweetMessage) {
    // //     if ( subject instanceof User) {
    // //         this.new
    // //     }
    // // }

    // public void addFollowers(User userId){
    //     followers.add(userId);
    // }

    // public ArrayList<User> getFollowers(){
    //     return followers;
    // }

    // public void addFollowings(User userId){
    //     followings.add(userId);
    // }

    // public ArrayList<User> getFollowings(){
    //     return followings;
    // }

    // public void addTweetMessage(String msg){
    //     tweetMessage.add(msg);
    // }

    // public String getTweetMessage(int positive){
    //     return tweetMessage.get(positive);
    // }

    // @Override
    // public void update(UserSubject subject, String tweetMessage) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'update'");
    // }

 

}
