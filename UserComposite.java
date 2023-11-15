// Composite Pattern to accept vistor from visitor pattern

import java.util.List;

public interface UserComposite {
    
    public void clickOn();
    public String getName();

    // Groups
    public boolean isGroup();
    public List<String> getUserList();
    public boolean isEmpty();
    public void addUser(UserComposite uc);
    public UserComposite findGroup(String group);
    public UserComposite findUser(String user);
}
