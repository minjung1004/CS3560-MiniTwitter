// Singleton Pattern

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AdminControlPanel extends JFrame implements ActionListener{

    private JFrame admin = new JFrame("Admin Control Panel");
    private JPanel treeView;
    private JButton AddUser, AddGroup, UserView, UserTotal, GroupTotal, TweetTotal, PositiveTweet;
    private JTextField user, group;
    private JLabel tvText;
    private String clickOn = "Root";
    static JList<Object> list;
    private DefaultListModel<Object> model = new DefaultListModel<>();

    private ListView view = ListView.getInstance();
    private AddUserButton userBtn = AddUserButton.getInstance();
    private AddGroupButton groupBtn = AddGroupButton.getInstance();
    private NewsFeed news = NewsFeed.getInstance();

    private int count;
    private int userCount = 0;
    private int groupCount = 0;
    private float positiveTweets;
    private Visitor visitor = new VisitorCount();

    // Add Singleton Pattern for AdminControlPanel
    private static AdminControlPanel instance;
    public static AdminControlPanel getInstance() {
        if ( instance == null){
            synchronized (AdminControlPanel.class){
                instance = new AdminControlPanel();
            }
        }
        return instance;
    }

    private AdminControlPanel(){
        // Tree View
        tvText = new JLabel();
        tvText.setText("Tree View");
        tvText.setFont(tvText.getFont().deriveFont(Font.BOLD, 14f));
        tvText.setBounds(20, 0, 100, 50);
        admin.add(tvText);
        treeView = new JPanel();
        treeView.setBackground(Color.gray);
        treeView.setBounds(10, 35, 400, 540);
        list = refreshTreeView();
        admin.add(list);

        // User Input
        user = new JTextField();
        user.setBounds(450, 30, 300, 40);
        user.addActionListener(this);
        admin.add(user);
 
        // User Button
        AddUser = new JButton("Add User");
        AddUser.setBounds(750, 30, 100, 40);
        AddUser.setFocusable(false);
        MouseListener addUserListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(!(user.getText().equals(""))){
                    userBtn.add(user.getText(), clickOn);
                    user.setText("");
                    refreshTreeView();
                    clickOn = "<root>";
                    userCount++;
                }
            }
        };
        AddUser.addMouseListener(addUserListener);
        admin.add(AddUser);

        // Group Input
        group = new JTextField();
        group.setBounds(450, 80,300, 40);
        group.addActionListener(this);
        admin.add(group);

        // Group Button
        AddGroup = new JButton("Add Group");
        AddGroup.setBounds(750, 80, 100, 40);
        AddGroup.setFocusable(false);
        MouseListener addGroupListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(!(group.getText().equals(""))){
                    groupBtn.add(group.getText(), clickOn);
                    group.setText("");
                    refreshTreeView();
                    clickOn = "<root>";
                    groupCount++;
                }
            }
        };
        AddGroup.addMouseListener(addGroupListener);
        admin.add(AddGroup);

        // User View
        UserView = new JButton("Open User View");
        UserView.setBounds(450, 150, 400, 50);
        UserView.addActionListener(this);
        UserView.setFocusable(false);
        admin.add(UserView);

        // User Total
        UserTotal = new JButton("User Total");
        UserTotal.setBounds(450, 390, 200, 50);
        UserTotal.addActionListener(this);
        UserTotal.setFocusable(false);
        admin.add(UserTotal);

        // Group Total
        GroupTotal = new JButton("Group Total");
        GroupTotal.setBounds(650, 390, 200, 50);
        GroupTotal.addActionListener(this);
        GroupTotal.setFocusable(false);
        admin.add(GroupTotal);

        // Tweet Total
        TweetTotal = new JButton("Tweet Total");
        TweetTotal.setBounds(450, 440, 200, 50);
        TweetTotal.addActionListener(this);
        TweetTotal.setFocusable(false);
        admin.add(TweetTotal);

        // Postive Message Percentage
        PositiveTweet = new JButton("Positive Tweet Percentage");
        PositiveTweet.setBounds(650, 440, 200, 50);
        PositiveTweet.addActionListener(this);
        PositiveTweet.setFocusable(false);
        admin.add(PositiveTweet);

        // Admin Control Panel Window Frame
        admin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        admin.setLayout(null);
        admin.setSize(870, 550);
        admin.setVisible(true);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e){
                if(e.getValueIsAdjusting()){
                    return;
                }
                clickOn = (String) list.getSelectedValue();
             }
        });
    }

    // Control the size of the tree view box
    private JList<Object> refreshTreeView() {
        model.clear();
        for(Object s : view.getUserList().toArray()){
            model.addElement(s);
        }
        JList list = new JList<>(model);
        list.setBounds(20, 35, 400, 450);
        return list;
    }
    
    // Actions when click on UserTotal, GroupTotal, TweetTotal, PositiveMessaage buttons
    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource() == UserView){
        User u = (User) view.findUser(clickOn);
        new UserUI(u);
      }
      else if(e.getSource() == UserTotal){
        count = view.acceptMessage(visitor);
        popUpWindow("User Total", "Total User: ", userCount);
      }
      else if(e.getSource() == GroupTotal){
        count = view.acceptMessage(visitor);
        popUpWindow("Group Total", "Total Group: ", groupCount);
      }
      else if(e.getSource() == TweetTotal){
        count = news.acceptMessage(visitor);
        popUpWindow("Tweet Total", "Total Tweets: ", count);
      }
      else if(e.getSource() == PositiveTweet){
        positiveTweets = news.acceptMessage(visitor);
        popUpWindow("Positive Tweet Total", "Positive Tweet Percentage: ", positiveTweets + "%");
      }
    }


    private void popUpWindow(String name, String msg, Object count) {
        JFrame frame = new JFrame(name);

        JLabel message = new JLabel();
        message.setText(msg + count);
        message.setBounds(20, 0, 400, 50);
        frame.add(message);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(400, 100);
        frame.setVisible(true);
    }
}