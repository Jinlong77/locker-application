import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LockerApplication extends JFrame {
    private String password = "";
    private boolean isPasswordSet = false;
    private JTextField passwordField;
    private JLabel statusLabel;

    public LockerApplication() {
        createUI();
    }

    private void createUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 300)); // Set the preferred size of the main window

        // Password field
        passwordField = new JTextField();
        passwordField.setEditable(false);
        passwordField.setPreferredSize(new Dimension(400, 50)); // Set the preferred size of the password field
        add(passwordField, BorderLayout.NORTH);

        // Status label
        statusLabel = new JLabel(" ");
        statusLabel.setPreferredSize(new Dimension(400, 50)); // Set the preferred size of the status label
        add(statusLabel, BorderLayout.SOUTH);

        // Numeric keypad panel
        JPanel keypadPanel = new JPanel(new GridLayout(4, 3));
        keypadPanel.setPreferredSize(new Dimension(300, 200)); // Set the preferred size of the keypad panel
        for (int i = 1; i <= 9; i++) {
            addButton(keypadPanel, String.valueOf(i));
        }
        addButton(keypadPanel, "0");
        addButton(keypadPanel, "Clear");
        addButton(keypadPanel, "Enter");
        add(keypadPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private void addButton(JPanel parent, String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100, 50)); // Set the preferred size of each button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.equals("Clear")) {
                    passwordField.setText("");
                } else if (text.equals("Enter")) {
                    if (!isPasswordSet) {
                        password = passwordField.getText();
                        isPasswordSet = true;
                        statusLabel.setText("Password Set");
                    } else {
                        if (passwordField.getText().equals(password)) {
                            statusLabel.setText("Correct Password");
                        } else {
                            statusLabel.setText("Incorrect Password");
                        }
                    }
                    passwordField.setText("");
                } else {
                    passwordField.setText(passwordField.getText() + text);
                }
            }
        });
        parent.add(button);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LockerApplication().setVisible(true);
            }
        });
    }
}