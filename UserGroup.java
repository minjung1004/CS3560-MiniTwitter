import java.util.*;

public class UserGroup implements UserComposite{

    private String groupName;
    private List<UserComposite> userList = new ArrayList<>();
    private static int CountGroup = 0;

    @Override
    public void clickOn() {
        System.out.println("Group is being clicked");
    }

    @Override
    public String getName() {
        return groupName;
    }

    public void setName(String name){
        this.groupName = name;
        if ( !groupName.equals("Current Following: ")){
            System.out.println(groupName + " has been created ");
        }
    }

    @Override
    public void addUser(UserComposite mem){
        userList.add(mem);
    }

    @Override
    public List<String> getUserList() {
        CountGroup++;
        String res;
        List<String> result = new ArrayList<String>();
        for(UserComposite m: userList){
            res="";
            for(int i = 0; i < CountGroup; i++){
                res += "      ";
            }
            res += "- " + m.getName();
            result.add(res);
            if (m.isGroup() && !(m.isEmpty())){
                result.addAll(m.getUserList());
            }
        }
        CountGroup--;
        return result;
    }

    public void setUserList(List<UserComposite> userList) {
        this.userList = userList;
    }

    @Override
    public boolean isGroup() {
       return true ;
    }


    @Override
    public boolean isEmpty() {
        if (userList.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public UserComposite findGroup(String group) {
        String g;
        UserComposite result = null;
        for (UserComposite m: userList){
            g = m.getName().replace(" ", "");
            g = g.replace("-", "");
            g = g.replace("<", "");
            g = g.replace(">", "");
            if (g.equals(group) && m.isGroup()){
                System.out.println("teha");
                return m;
            }
            if (m.isGroup()){
                result = m.findGroup(group);
            }
        }
        return result;
    }

    @Override
    public UserComposite findUser(String user) {
        String g;
        UserComposite result = null;
        for (UserComposite m : userList){
            g = m.getName().replace(" ", "");
            g = g.replace("-", "");
            g = g.replace("<", "");
            g = g.replace(">", "");
            System.out.println("Looking for group: " + user + " found: " + g);
            if(g.equals(user) && !(m.isGroup())){
                System.out.println("tehha");
                return m;
            }
            if(m.isGroup()){
                result = m.findUser(user);
            }
        }
        return result;
    }
    // private List<UserComposite> groupList = new ArrayList<UserComposite>();
    // private String groupName;
    // private static int count = 0;

   
    // // Instance fields
    // private String groupId;
    // private ArrayList<UserComposite> groupUsers;

    // public UserGroup(String id){
    //     groupId =id;
    //     groupUsers= new ArrayList<UserComposite>();
    // }

    // public UserGroup() {
    // }

    // // Accessor Methods
    // @Override
    // public void setId(String id) {
    //     groupId = id;
    // }

    // @Override
    // public String getId() {
    //     return groupId;
    // }

    // // Add user to a group
    // public void addUserToGroup(User userId){
    //     groupUsers.add(userId);
    // }

    // // Returns users in a group
    // public ArrayList<UserComposite> getUserGroup(){
    //     return groupUsers;
    // }

    // @Override
    // public String toString(){
    //     return groupId;
    // }

}
