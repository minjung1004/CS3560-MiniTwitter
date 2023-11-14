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
}
