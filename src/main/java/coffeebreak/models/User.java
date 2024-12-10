package coffeebreak.models;

import coffeebreak.interfaces.Observer;

public class User implements Observer {
    private String email;

    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void update(String message) {
        System.out.println("Notification sent to " + email + ": " + message);
    }
}
