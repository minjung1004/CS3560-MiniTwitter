package users;

import java.util.*;
// import java.util.ArrayList;
// import java.util.List;

//  1) an unique ID; 
//  2) a list of user IDs that are following this user (followers); 
//  3) a list of user IDs being followed by this user (followings); 
//  4) a news feed list containing a list of Twitter messages.

public class User {

    // Intance fields
    private int userId;
    private String userName;
    private String tweetMessage;
    private Set<Integer> followers = new HashSet<Integer>();
    private Set<Integer> followings = new HashSet<Integer>();
   
    // Accessor methods
    public void setUserId(int id){
        userId = id;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserName(String name){
        userName = name;
    }

    public String getUserName(){
        return userName;
    }

    public void setTweetMessage(String tweet){
        tweetMessage = tweet;
    }

    public String getTweetMessage(){
        return tweetMessage;
    }

    public void addFollowers(int userID){
        followers.add(userID);
    }

    public Set<Integer> getFollowers(){
        return followers;
    }

    public void addFollowings(int userID){
        followings.add(userID);
    }

    public Set<Integer> getFollowings(){
        return followings;
    }
}
