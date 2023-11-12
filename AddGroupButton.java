public class AddGroupButton implements Button{
    private static AddGroupButton instance;
    private ListView view;

    private AddGroupButton() {
        view = ListView.getInstance();
        System.out.println("Loading add group button");
    }

    public static AddGroupButton getInstance() {
        if(instance == null){
            instance = new AddGroupButton();
        }
        return instance;
    }

    @Override
    public void add(String n, String group){
        UserGroup group1 = new UserGroup();
        group1.setName("<"+n+">");
        group = group.replace(" ","");
        group = group.replace("-", "");
        group = group.replace("<", "");
        group = group.replace(">", "");
        UserComposite uc = view.findGroup(group);
        if(uc == null){
            view.addUser(group1);
        }else{
            uc.addUser(group1);
        }
    }
    
}
