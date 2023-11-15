// Observer Pattern

import java.util.ArrayList;
import java.util.List;

public class UserSubject {

    private List<UserObservers> followers = new ArrayList<>();

    public void attach (UserObservers observer){
        followers.add(observer);
    }
    
    public void updateFollowers(){
        for(UserObservers observer : followers){
            observer.update(this);
        }
    }
}
