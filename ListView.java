import java.util.List;
import java.util.ArrayList;

public class ListView {
    private List<UserComposite> userList = new ArrayList<UserComposite>();
    private int userCount = 0;
    private int groupCount = 0;

    private static ListView instance;

    public static ListView getInstance() {
        if(instance == null){
            instance = new ListView();
        }
        return instance;
    }

    private ListView() {
        User root = new User();
        root.setName("Root");
        userList.add(root);
    }

    public UserComposite findGroup(String group){
        String g;
        UserComposite result = null;
        for (UserComposite m : userList){
            g = m.getName().replace(" ", "");
            g = g.replace("-", "");
            g = g.replace("<", "");
            g = g.replace(">", "");
            if ( g.equals(group) && m.isGroup()){
                return m;
            }

            if(m.isGroup()){
                result = m.findGroup(group);
            }
        }
        return result;
    }

    public UserComposite findUser(String user){
        String g;
        user = user.replace(" ", "");
        user = user.replace("-", "");
        user = user.replace("<", "");
        user = user.replace(">", "");
        UserComposite result = null;
        for( UserComposite m : userList){
            g = m.getName().replace(" ", "");
            g = g.replace("-", "");
            g = g.replace("<", "");
            g = g.replace(">", "");
            if(g.equals(user) && !(m.isGroup())){
                return m;
            }

            if (m.isGroup()){
                result = m.findUser(user);
            }
        }
        return result;
    }

    public List<String> getUserList() {
        List<String> result = new ArrayList<String>();
        for (UserComposite m: userList){
            if(m.getName() == "Root"){
                result.add(m.getName());
            }else{
                result.add("- " + m.getName());
            }

            if(m.isGroup() && !(m.isEmpty())){
                result.addAll(m.getUserList());
            }
        }
        return result;
    }

    public void setUserList(List<UserComposite> userList){
        this.userList = userList;
    }

    public void addUser(UserComposite uc) {
        userList.add(uc);
    }

    public int acceptMessage(Visitor visitor){
        return visitor.visitUser(this);
    }

    public int acceptGroup(Visitor visitor){
        return visitor.visitGroup(this);
    }

    public int getUserCount() {
        return userCount; 
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getGroupCount(){
        return groupCount;
    }

    public void setGroupCount(int groupCount) {
        this.groupCount = groupCount;
    }
    
}
