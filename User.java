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
}
