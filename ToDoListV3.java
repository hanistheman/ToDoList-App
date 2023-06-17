import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/* Version 3 of my To-Do List app. I made sure to add some extra buttons that could be
useful, such as a Search button allowing the user to search through their To-Do List,
as well as a button allows the user to mark a task as completed. */
public class ToDoListV3 extends JFrame {
    private DefaultListModel<String> todoListModel;
    private JList<String> todoList;
    private JTextField todoInput;

    public ToDoListV3 () {
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
        todoInput.setColumns(18);
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

        JButton markCompButton = new JButton("Complete task");
        markCompButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = todoList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String task = todoListModel.getElementAt(selectedIndex);
                    todoListModel.remove(selectedIndex);
                    todoListModel.addElement("[Completed] " + task);
                }
            }
        });
        // Create a button to mark tasks as completed and achieving functionality

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchQuery = JOptionPane.showInputDialog("Enter search keyword:");
                List<Integer> matchingIndices = new ArrayList <Integer>();
                if (searchQuery != null) {

                    for (int i = 0; i < todoListModel.getSize(); i++) {
                        String task = todoListModel.getElementAt(i);
                        if (task.contains(searchQuery)) {
                            matchingIndices.add(i);
                        }
                    }
                    if (matchingIndices.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No matching tasks found.");
                    } else {
                        todoList.setSelectedIndices(toIntArray(matchingIndices));
                    }
                }
            }
        });

        JPanel controlsPanel = new JPanel(new FlowLayout());
        controlsPanel.add(todoInput);
        controlsPanel.add(addButton);
        controlsPanel.add(removeButton);
        controlsPanel.add(markCompButton);
        controlsPanel.add(searchButton);
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

    private int[] toIntArray(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoListV3();
            }
        });
    }
}
