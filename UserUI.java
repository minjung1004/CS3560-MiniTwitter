import javax.swing.DefaultListModel;
import javax.swing.JList;

public class UserUI {
    private User user;
    private DefaultListModel followModel = new DefaultListModel();
    private DefaultListModel tweetModel = new DefaultListModel();
    private Message messageTrack = new Message();

    // Initaize user
    public UserUI(User user){
        this.user = user;
        // Components();

        userName.setText(user.getId());

    }
    
}
