package users;

import java.util.*;

public class UserGroup {
    // Instance fields
    private int groupId;
    private Set<Integer> groupUsers = new HashSet<Integer>();

    // Accessor Methods
    public void setGroupId(int id){
        groupId = id;
    }

    public int getGroupId(){
        return groupId;
    }

    public void addGroupUser(int userId){
        groupUsers.add(userId);
    }

    public Set<Integer> getGroupUsers(){
        return groupUsers;
    }

}
