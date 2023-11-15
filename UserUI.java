// Singleton Pattern

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserUI {

    JButton Follow, postBtn;
    JTextField user, tweet;
    JPanel followList, newsFeed;
    JLabel FollowTweet, NewsText;
    JFrame userWindow;
    ListView view = ListView.getInstance();
    NewsFeed news = NewsFeed.getInstance();
    DefaultListModel<Object> model = new DefaultListModel<>();
    DefaultListModel<Object> model2 = new DefaultListModel<>();
    User thiUser;
    
    // Open user window
    UserUI(User u){
        thiUser = u;
        userWindow = new JFrame(u.getName() + "'s View");

        followList = new JPanel();
        followList.setBackground(Color.gray);
        followList.setBounds(10, 300, 300, 200);
        refreshFollowingList();

        newsFeed = new JPanel();
        newsFeed.setBackground(Color.gray);
        newsFeed.setBounds(10, 300, 300, 200);
        refreshNewsFeed();

        // Follow User Button
        Follow = new JButton("Follow User");
        Follow.setBounds(420,10,150,40);
        Follow.setFocusable(false);
        MouseListener addFollower = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent){
                if(!(user.getText().equals(""))){
                    User target = (User) view.findUser(user.getText());
                    if( target == null){
                        System.out.println("Can't find user Id: ");
                    }else {
                        System.out.println("Following: " + target.getName());
                        u.follow(target);
                        refreshFollowingList();
                        refreshNewsFeed();
                    }
                    user.setText("");
                }
            }
        };
        Follow.addMouseListener(addFollower);
        userWindow.add(Follow);

        // Post Tweet Button
        postBtn = new JButton("Post Tweet");
        postBtn.setBounds(420, 310, 150, 40);
        postBtn.setFocusable(false);
        MouseListener postTweet = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent){
                if(!(tweet.getText().equals(""))){
                    news.postMessage(u, tweet.getText());
                    tweet.setText("");
                    refreshNewsFeed();
                }
            }
        };
        postBtn.addMouseListener(postTweet);
        userWindow.add(postBtn);

        // User Input
        user = new JTextField();
        user.setBounds(20, 10, 400, 40);
        userWindow.add(user);

        // Tweet Input
        tweet = new JTextField();
        tweet.setBounds(20, 310, 400, 40);
        userWindow.add(tweet);

        userWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        userWindow.setLayout(null);
        userWindow.setSize(600, 700);
        userWindow.setVisible(true);

    }

    private void refreshNewsFeed() {
        model2.clear();
        for(Object s: news.printNewsFeed(thiUser, thiUser.getFollowing())){
            model2.addElement(s);
        }
        JList list2 = new JList<>(model2);
        list2.setBounds(20, 355, 550, 300);
        userWindow.add(list2);
    }

    private void refreshFollowingList() {
        model.clear();
        for(Object s : thiUser.getFollowing().toArray()){
            model.addElement(s);
        }
        JList list = new JList<>(model);
        list.setBounds(20, 55, 550, 250);
        userWindow.add(list);
    }
}
