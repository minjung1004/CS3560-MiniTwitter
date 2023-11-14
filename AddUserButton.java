public class AddUserButton implements Button{
    private static AddUserButton instance;
    private ListView view;

    private AddUserButton() {
        view = ListView.getInstance();
        System.out.println("Loading add user button");
    }

    public static AddUserButton getInstance() {
        if( instance == null){
            instance = new AddUserButton();
        }
        return instance;
    }

    @Override
    public void add(String n, String group){
        User user1 = new User();
        user1.setName(n);
        group = group.replace(" ", "");
        group = group.replace("-", "");
        group = group.replace("<", "");
        group = group.replace(">", "");
        UserComposite uc = view.findGroup(group);
        if (uc == null){
            view.addUser(user1);
        }else{
            uc.addUser(user1);
        }
    }
}
