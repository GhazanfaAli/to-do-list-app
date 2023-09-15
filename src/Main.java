import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main extends JFrame {
    JTable table1;
    JPanel panel1;
    String data[][] = {{"task 1"}};
    String column[] = {"Enter your tasks here"};
    JButton button1, button2, button3;
    JTextField field1;
    FlowLayout fl1 = new FlowLayout(FlowLayout.CENTER);

    Main() {
        DefaultTableModel model = new DefaultTableModel(data, column);
        table1 = new JTable(model);

        // Set custom cell renderer for coloring rows
        table1.setDefaultRenderer(Object.class, new CustomCellRenderer());

        table1.setRowHeight(0, 50); // Set the height of the first row to 50 pixels
        table1.getColumnModel().getColumn(0).setPreferredWidth(200); // Set the width of the first column to 200 pixels

        this.setLayout(fl1);

        panel1 = new JPanel();
        FlowLayout fl2 = new FlowLayout(FlowLayout.TRAILING);
        panel1.setLayout(fl2);

        this.add(new JScrollPane(table1));
        this.add(new JPanel());
        this.add(panel1);

        button1 = new JButton("Add");
        button2 = new JButton("Update");
        button3 = new JButton("Delete");

        button1.setFont(new Font("Arial", Font.BOLD, 16));
        button2.setFont(new Font("Arial", Font.BOLD, 16));
        button3.setFont(new Font("Arial", Font.BOLD, 16));
        button1.setBackground(new Color(52, 152, 219)); // Set button background color
        button2.setBackground(new Color(231, 76, 60));
        button3.setBackground(new Color(46, 204, 113));
        button1.setForeground(Color.white); // Set button text color
        button2.setForeground(Color.white);
        button3.setForeground(Color.white);

        field1 = new JTextField();
        field1.setPreferredSize(new Dimension(150, 25));

        panel1.add(new JLabel("Field"));
        panel1.add(field1);
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (field1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fill the fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String task = field1.getText();
                    Object[] newTask = {task};

                    model.addRow(newTask);

                    // Set the height of the newly added row
                    table1.setRowHeight(model.getRowCount() - 1, table1.getRowHeight(0));

                    // Set the preferred width of the newly added row's column
                    int columnWidth = table1.getColumnModel().getColumn(0).getWidth();
                    table1.getColumnModel().getColumn(0).setPreferredWidth(columnWidth);

                    field1.setText(null);
                }
            }
        });

        table1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int row = table1.getSelectedRow();
                String task = field1.getText();

                String text = (String) model.getValueAt(row, 0);

                field1.setText(text);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (field1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fill the fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int row = table1.getSelectedRow();
                    String task = field1.getText();
                    model.setValueAt(task, row, 0);
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (field1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fill the fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int select = JOptionPane.showConfirmDialog(null, "Error", "Fill the fields first", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (select == JOptionPane.YES_OPTION) {
                        model.removeRow(table1.getSelectedRow());
                    }
                }
            }
        });
    }

    // Custom cell renderer for coloring rows
    class CustomCellRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Set row background color
            if (row % 2 == 0) {
                cellComponent.setBackground(Color.lightGray);
            } else {
                cellComponent.setBackground(Color.white);
            }

            // Set column background color
            if (column == 0) {
                cellComponent.setBackground(new Color(9, 227, 119));
            }

            return cellComponent;
        }
    }

    public static void main(String[] args) {
        Main obj1 = new Main();
        obj1.setBounds(200, 200, 800, 550);
        obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj1.setVisible(true);
    }
}
