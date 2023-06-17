import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

public class ToDoListV2 extends JFrame {
    private DefaultListModel<String> todoListModel;
    private JList<String> todoList;
    private JTextField todoInput;

    public ToDoListV2 () {
        super("To-Do List");

        Color veryLightBlue = new Color(51, 204,255);
        Color green = new Color(188, 226, 158);
        Color doneColor = new Color(233, 119, 119);
        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(veryLightBlue);


        todoListModel = new DefaultListModel<>();
        todoList = new JList<>(todoListModel);
        // Creating the to-do list model and list
        todoList.setBackground(veryLightBlue);

        todoInput = new JTextField();
        todoInput.setColumns(24);
        // Create the input field

        JButton addButton = new JButton("Add task");
        addButton.setBackground(green);
        addButton.addActionListener(new ActionListener() {
            @ Override
            public void actionPerformed(ActionEvent e) {
                String newItem = todoInput.getText();
                if (!newItem.isEmpty()) {
                    todoListModel.addElement(newItem);
                    todoInput.setText("");
                }
            }
        });
        // Create the add button and make it functional

        JButton removeButton = new JButton("Remove task");
        removeButton.setBackground(doneColor);
        removeButton.addActionListener(new ActionListener() {
            @ Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = todoList.getSelectedIndex();
                if (selectedIndex != -1) {
                    todoListModel.remove(selectedIndex);
                }
            }
        });
        // Create the remove button and make it functional


        JPanel controlsPanel = new JPanel(new FlowLayout());
        controlsPanel.add(todoInput);
        controlsPanel.add(addButton);
        controlsPanel.add(removeButton);
        // Add all the new buttons to a new controlsPanel object
        
        // Creating a panel that displays the time for the user to see.
        // Not necessary, just a potential bonus feature.

        mainPanel.add(new JScrollPane(todoList), BorderLayout.CENTER);
        mainPanel.add(controlsPanel, BorderLayout.SOUTH);
        // Add the components to the main panel

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
        // Configure the main frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoListV2();
            }
        });
    }
}
