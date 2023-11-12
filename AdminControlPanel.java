import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;


public class AdminControlPanel extends JFrame implements ActionListener{

    private JFrame admin = new JFrame("Admin Control Panel");
    private JPanel treeView;
    private JButton AddUser, AddGroup, UserView, UserTotal, GroupTotal, MessageTotal, PositiveMessage;
    private JTextField user, group;
    private JLabel tvText;
    private String clickOn = "<root>";
    static JList<Object> list;
    private DefaultListModel<Object> model = new DefaultListModel<>();

    private ListView view = ListView.getInstance();
    private AddUserButton userBtn = AddUserButton.getInstance();
    private AddGroupButton groupBtn = AddGroupButton.getInstance();
    private NewsFeed news = NewsFeed.getInstance();

    private int count;
    private int userCount = 0;
    private int groupCount = 0;
    private float positiveMessages;
    private Visitor visitor = new VisitorCount();

    // Add Singleton Pattern for AdminControlPanel
    private static AdminControlPanel instance;
    public static AdminControlPanel getInstance() {
        if ( instance == null){
            instance = new AdminControlPanel();
        }
        return instance;
    }
    private AdminControlPanel(){
        // Tree View
        tvText = new JLabel();
        tvText.setText("Tree View");
        tvText.setBounds(20, 0, 100, 50);
        admin.add(tvText);
        treeView = new JPanel();
        treeView.setBackground(Color.gray);
        treeView.setBounds(10, 35, 400, 540);
        list = refreshTreeView();
        admin.add(list);

        // User Button
        AddUser = new JButton("Add User");
        AddUser.setBounds(750, 20, 100, 50);
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

        // Group Button
        AddGroup = new JButton("Add Group");
        AddGroup.setBounds(750, 80, 100, 50);
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
        UserView.setBounds(550, 150, 200, 50);
        UserView.addActionListener(this);
        UserView.setFocusable(false);
        admin.add(UserView);

        // User Total
        UserTotal = new JButton("User Total");
        UserTotal.setBounds(450, 400, 200, 50);
        UserTotal.addActionListener(this);
        UserTotal.setFocusable(false);
        admin.add(UserTotal);

        // Group Total
        GroupTotal = new JButton("Group Total");
        GroupTotal.setBounds(650, 400, 200, 50);
        GroupTotal.addActionListener(this);
        GroupTotal.setFocusable(false);
        admin.add(GroupTotal);

        // Message Total
        MessageTotal = new JButton("Message Total");
        MessageTotal.setBounds(450, 450, 200, 50);
        MessageTotal.addActionListener(this);
        MessageTotal.setFocusable(false);
        admin.add(MessageTotal);

        // Postive Message Percentage
        PositiveMessage = new JButton("Positive Message Percentage");
        PositiveMessage.setBounds(650, 450, 200, 50);
        PositiveMessage.addActionListener(this);
        PositiveMessage.setFocusable(false);
        admin.add(PositiveMessage);

        // User Input
        user = new JTextField();
        user.setBounds(450, 27, 300, 400);
        user.addActionListener(this);
        admin.add(user);

        // Group Input
        group = new JTextField();
        group.setBounds(450, 87,300, 40);
        group.addActionListener(this);
        admin.add(group);

        admin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        admin.setLayout(null);
        admin.setSize(900, 600);
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

    private JList<Object> refreshTreeView() {
        return null;
    }
    
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
      else if(e.getSource() == MessageTotal){
        count = view.acceptMessage(visitor);
        popUpWindow("Message Total", "Total Messages: ", count);
      }
      else if(e.getSource() == PositiveMessage){
        positiveMessages = view.acceptMessage(visitor);
        popUpWindow("Positive Message Total", "Positive Message Percentage: ", positiveMessages + "%");
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