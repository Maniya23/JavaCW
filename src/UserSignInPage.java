import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserSignInPage implements ActionListener{
    JButton submitButton;
    boolean UserFound = false;
    JPanel usernamePanel;
    JLabel usernameText;
    JLabel passwordText;
    JPanel passwordPanel;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private JFrame frame = new JFrame();
    private ArrayList<User> RegUsers = new ArrayList<>();
    private WestminsterShoppingManager shoppingManager;

    public UserSignInPage(WestminsterShoppingManager shoppingManager) {
        User u1 = new User("admin", "admin1");
        User u2 = new User("jerry", "jerry1");
        RegUsers.add(u1);
        RegUsers.add(u2);
        writeUserFile();
        this.shoppingManager = shoppingManager;

        frame.setTitle("Sign In");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        // Components
        usernameText = new JLabel("Enter Username: ");
        passwordText = new JLabel("Enter Password: ");

        submitButton = new JButton("Submit");
        submitButton.setSize(20,20);
        submitButton.addActionListener(this);

        usernameInput = new JTextField(20);
        passwordInput = new JPasswordField(20);

        //Username Panel
        usernamePanel = new JPanel();
        usernamePanel.add(usernameText);
        usernamePanel.add(usernameInput);

        //Password Panel
        passwordPanel = new JPanel();
        passwordPanel.add(passwordText);
        passwordPanel.add(passwordInput);

        frame.setLayout(new BorderLayout());
        frame.add(usernamePanel, BorderLayout.NORTH);
        frame.add(passwordPanel, BorderLayout.CENTER);
        frame.add(submitButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton){
            String username = usernameInput.getText();
            String password = passwordInput.getText();
//            user.setUsername(usernameInput.getText());
//            user.setPassword(passwordInput.getText());
            validateUser(username, password);

        }
    }

    private void validateUser(String username, String password){
        readUserFile();
        if (RegUsers.size()==0){
            UserFound = true;
            User user = new User(username, password);
            JOptionPane.showMessageDialog(frame,"Hello " + username +"! get your 10% discount at the checkout");
            RegUsers.add(user);
            writeUserFile();}

        for (User user : RegUsers){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                UserFound = true;
                JOptionPane.showMessageDialog(frame, "Welcome " + username);
                new ShoppingPage(shoppingManager, user);
                frame.dispose();
                break;
            } else if (user.getUsername().equals(username) && !user.getPassword().equals(password)){
                JOptionPane.showMessageDialog(frame, "Incorrect Password, Try again");
            }
        }

        if (UserFound == false){
        User newUser = new User(username, password);
        RegUsers.add(newUser);
        writeUserFile();
        JOptionPane.showMessageDialog(frame, "Hello " + username + "! get your 10% discount at the checkout");
        newUser.setUserFound(true);
        new ShoppingPage(shoppingManager, newUser);
        frame.dispose();
        }
    }

    private void writeUserFile (){
        try{
            FileOutputStream fis = new FileOutputStream("userBin.txt"); // Create binary file (users)
            ObjectOutputStream UserBin = new ObjectOutputStream(fis);

            UserBin.writeObject(RegUsers);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private ArrayList<User> readUserFile (){
        try{
            FileInputStream fis = new FileInputStream("userBin.txt"); // Create binary file (users)
            ObjectInputStream UserBin = new ObjectInputStream(fis);

            RegUsers = (ArrayList<User>) UserBin.readObject();

        }catch (Exception e){
            e.printStackTrace();
        }
        return RegUsers;
    }

//    public boolean isUserFound() {
//        return UserFound;
//    }

    //    public static void main(String[] args) {
//        UserSignInPage userSignInPage = new UserSignInPage();
//    }
}
