import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListApp extends JFrame {
    private ToDoList toDoList = new ToDoList();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> taskList = new JList<>(listModel);

    public ToDoListApp() {
        setTitle("To-Do List Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI components
        JTextField taskInput = new JTextField();
        taskInput.setPreferredSize(new Dimension(200, 30));

        JButton addButton = new JButton("Add Task");
        JButton completeButton = new JButton("Mark as Completed");
        JButton deleteButton = new JButton("Delete Task");

        // Layout
        JPanel inputPanel = new JPanel();
        inputPanel.add(taskInput);
        inputPanel.add(addButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(completeButton);
        buttonPanel.add(deleteButton);

        JScrollPane scrollPane = new JScrollPane(taskList);

        // Add components to the frame
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event Handlers
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String description = taskInput.getText().trim();
                if (!description.isEmpty()) {
                    toDoList.addTask(description);
                    updateTaskList();
                    taskInput.setText("");
                }
            }
        });

        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    toDoList.markTaskAsCompleted(selectedIndex);
                    updateTaskList();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    toDoList.deleteTask(selectedIndex);
                    updateTaskList();
                }
            }
        });
    }

    private void updateTaskList() {
        listModel.clear();
        for (Task task : toDoList.getTasks()) {
            listModel.addElement(task.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoListApp().setVisible(true);
            }
        });
    }
}