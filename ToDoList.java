import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

// Packages I am importing to make sure this app works
// I imported all of them to make my life easier instead of typing out too many import statements.

class Task extends JPanel {
    JLabel index;
    JTextField taskName;
    JButton done;

    Color pink = new Color(255, 161, 161);
    Color green = new Color(188, 226, 158);
    Color doneColor = new Color(233, 119, 119);

    private boolean Checked;

    Task () {

        this.setPreferredSize(new Dimension(400, 20));
        // Setting the size of the task
        this.setBackground(pink);
        // Setting the background of the task
        this.setLayout(new BorderLayout());
        // Set layout of the task

        Checked = false;

        index = new JLabel("");
        // Creating index label
        index.setPreferredSize(new Dimension(20,20));
        // Setting the size of the newly created index label
        index.setHorizontalAlignment(JLabel.CENTER);
        // Setting the alignment of our index label
        this.add(index, BorderLayout.WEST);
        // Adding index label to our task

        taskName = new JTextField("Write something you need to do currently : ");
        // Creating task name field, takes in text input
        taskName.setBorder(BorderFactory.createEmptyBorder());
        // Setting the border here.
        // I am using the BorderFactory method, which is a library of various borders.
        taskName.setBackground(pink);
    
        this.add(taskName, BorderLayout.CENTER);

        done = new JButton();
        // Created new button object
        done.setPreferredSize(new Dimension(80, 20));
        // Setting dimensions for new button
        done.setBorder(BorderFactory.createEmptyBorder());
        // Setting the border here. Same steps as earlier.
        done.setBackground(doneColor);
        // Setting the background color for the Done button
        done.setFocusPainted(false);
        // This boolean

        this.add(done, BorderLayout.EAST);
        // Adding the done button, setting it to the right
    } public void changeIndex(int num) {
        this.index.setText(num + "");
        // Setting the int variable num to a string
        this.revalidate();
        // Refreshes the screen
    } public JButton getDone() {
        return done;
        // Checks to see if a task has been completed
    } public boolean getState() {
        return Checked;
        // Returns the value of the boolean checked.
        // We used this to see if something on our list has been checked off.
    } public void changeState() {
        this.setBackground(green);
        taskName.setBackground(green);
        Checked = true;
        revalidate();
        // Changes the state of our tasks and refreshes accordingly.
    }
}
class List extends JPanel {
    Color lightColor = new Color(252, 221, 176);
    List () {
        GridLayout layout = new GridLayout(10, 1);
        layout.setVgap(5);
        // Setting the vertical gap

        this.setLayout(layout);
        // 10 Tasks. Let's just keep it at that for now.
        this.setPreferredSize(new Dimension(400, 560));
        this.setBackground(lightColor);

    } public void updateNumbers () {
        Component[] listItems = this.getComponents();

        for (int i = 0; i < listItems.length; i++) {
            if (listItems[i] instanceof Task) {
                ((Task)listItems[i]).changeIndex(i + 1);
                // If an item has an instance of a task,
                // it changes the index of said item by shifting it 1 to the right.
            }
        }
    } public void removeCompletedTasks () {
        for (Component c: getComponents()) {
            if (c instanceof Task) {
                if (((Task)c).getState()) {
                    remove(c);
                    // Removing completed item from list
                    updateNumbers();
                    // Updating the index of the list
                }
            }
        }
    }
}
class Footer extends JPanel {
    JButton addTask;
    JButton clear;

    Color orange = new Color(233, 133, 128);
    Color lightColor = new Color(252, 221, 176);
    Border emptyBorder = BorderFactory.createEmptyBorder();

    Footer() {
        this.setPreferredSize(new Dimension(400, 60));
        this.setBackground(lightColor);

        addTask = new JButton("Add Task");
        // Create and add a new task button
        addTask.setBorder(emptyBorder);
        // Set the border so that way we remove it.
        addTask.setFont(new Font("Sans-serif", Font.ITALIC, 20));
        // Set the font
        addTask.setVerticalAlignment(JButton.BOTTOM);
        // Align text to the bottom of our border
        addTask.setBackground(orange);
        // Set background color
        this.add(addTask);
        // Add to footer

        this.add(Box.createHorizontalStrut(20));
        // Create space between buttons

        clear = new JButton("Clear finished tasks");
        // Create and add a new button for clearing finished tasks
        clear.setFont(new Font("Sans-serif", Font.ITALIC, 20));
        // Set the font
        clear.setBorder(emptyBorder);
        // Setting the border of our clear button to empty
        clear.setBackground(orange);
        // Setting the color
        this.add(clear);

    }

    public JButton getNewTask() {
        return addTask;
    }

    public JButton getClear() {
        return clear;
    }
}
class TitleBar extends JPanel {

        Color lightColor = new Color(252, 221, 176);

        TitleBar() {
            this.setPreferredSize(new Dimension(400, 80));
            // Set the dimensions of the title bar object
            this.setBackground(lightColor);
            // Set background color
            JLabel titleText = new JLabel("To Do List");
            // Creating a new JLabel object for the title bar's text
            titleText.setPreferredSize(new Dimension(200, 60));
            // Setting the size of our new JLabel object
            titleText.setFont(new Font("Sans-serif", Font.BOLD, 20));
            // Configuring our font, note that it is the same as earlier but the
            // strength is bold.
            titleText.setHorizontalAlignment(JLabel.CENTER);
            // Align the text to the center
            this.add(titleText);
            // Adding our text to the new TitleBar object
        }
}
class AppFrame extends JFrame {
    private TitleBar title;
    private Footer footer;
    private List list;

    private JButton newTask;
    private JButton clear;

    AppFrame() {
        this.setSize(400, 600);
        // 400 width and 600 height for the size of our window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Exit when we click the close button
        this.setVisible(true);
        // Making our app frame visible

        title = new TitleBar();
        footer = new Footer();
        list = new List();

        this.add(title, BorderLayout.NORTH);
        this.add(footer, BorderLayout.SOUTH);
        this.add(list, BorderLayout.CENTER);

        newTask = footer.getNewTask();
        clear = footer.getClear();

        addListeners();
}
    public void addListeners() {
        newTask.addMouseListener(new MouseAdapter() {
            @override
            public void MousePressed(MouseEvent e) {
                Task task = new Task();
                list.add(task);
                // Add new task to list
                list.updateNumbers();
                // Updates the numbers of the tasks

                task.getDone().addMouseListener(new MouseAdapter() {
                    @override
                    public void MousePressed(MouseEvent e) {
                            task.changeState();
                            // Change color of the task
                            list.updateNumbers();
                            // Updates the numbers of the tasks
                            revalidate();
                            // Updates the frame
                    }
                });
            }
        });
        clear.addMouseListener(new MouseAdapter() {
                @override
            public void mousePressed(MouseEvent e) {
                list.removeCompletedTasks();
                // Removes all the tasks that are done
                repaint();
                // Repaints the list
                }
            });
    }
}
public class ToDoList {
    public static void main(String[] args) {
        AppFrame frame = new AppFrame();
        // Create the frame
    }
}
@ interface override {

}


