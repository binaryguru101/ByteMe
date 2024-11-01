public abstract class User {
    int ID;
    String Name;
    String Password;

    public User(int ID, String Name, String Password) {
        this.ID = ID;
        this.Name = Name;
        this.Password = Password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
