package demo6;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileWriter;

 
public class Demo6 extends JFrame implements ActionListener {
    JComboBox<String> itemBox;
    JTextField quantityField;
    JTextArea billArea;
    JButton addButton, totalButton, clearButton, logoutButton, printButton, saveButton;

    double totalAmount = 0;
    final double GST_RATE = 0.05;
    int receiptNumber = 1001;
    boolean billGenerated = false;

    String items[] = {
            "Idli - 30", "Dosa - 50", "Masala Dosa - 60", "Vada - 25",
            "Veg Sandwich - 40", "Burger - 70", "Pizza - 120",
            "French Fries - 50", "Tea - 15", "Coffee - 20",
            "Badam Milk - 40", "Ice Cream - 45"
    };

    double prices[] = {
            30, 50, 60, 25,
            40, 70, 120,
            50, 15, 20,
            40, 45
    };

   Demo6() {

        setTitle("College Canteen Ordering System");
        setSize(650, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220, 235, 255));
        setLayout(new FlowLayout());

        JLabel title = new JLabel("College Canteen Ordering System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(0, 51, 153));
        add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(6, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 248, 255));

        panel.add(new JLabel("Select Item:"));
        itemBox = new JComboBox<>(items);
        panel.add(itemBox);

        panel.add(new JLabel("Enter Quantity:"));
        quantityField = new JTextField();
        panel.add(quantityField);

        addButton = createButton("Add Item", new Color(0, 153, 76));
        totalButton = createButton("Generate Bill", new Color(255, 140, 0));
        clearButton = createButton("Clear", new Color(204, 0, 0));
        logoutButton = createButton("Logout", new Color(0, 102, 204));
        printButton = createButton("Print", new Color(102, 0, 153));
        saveButton = createButton("Save", new Color(0, 128, 128));

        panel.add(addButton);
        panel.add(totalButton);
        panel.add(clearButton);
        panel.add(logoutButton);
        panel.add(printButton);
        panel.add(saveButton);

        add(panel, BorderLayout.CENTER);

        billArea = new JTextArea();
        billArea.setEditable(false);
        billArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        billArea.setBackground(new Color(255, 255, 240));
        add(new JScrollPane(billArea), BorderLayout.SOUTH);

        addButton.addActionListener(this);
        totalButton.addActionListener(this);
        clearButton.addActionListener(this);
        logoutButton.addActionListener(this);
        printButton.addActionListener(this);
        saveButton.addActionListener(this);

        setVisible(true);
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }
     public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {
            try {
                int index = itemBox.getSelectedIndex();
                int qty = Integer.parseInt(quantityField.getText());

                if (qty <= 0 || qty > 50) {
                    JOptionPane.showMessageDialog(this, "Quantity must be 1-50");
                    return;
                }

                if (billArea.getText().isEmpty()) {
                    billArea.append("========= COLLEGE CANTEEN =========\n");
                    billArea.append("-----------------------------------\n");
                }

                double itemTotal = prices[index] * qty;
                totalAmount += itemTotal;

                billArea.append(items[index] + " x " + qty +
                        " = ₹" + itemTotal + "\n");

                quantityField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter valid quantity!");
            }
        }

        if (e.getSource() == totalButton && !billGenerated) {

            double gst = totalAmount * GST_RATE;
            double grand = totalAmount + gst;

            String dateTime = new SimpleDateFormat(
                    "dd/MM/yyyy HH:mm:ss").format(new Date());

            billArea.append("\n-----------------------------------\n");
            billArea.append("Receipt No: " + receiptNumber++ + "\n");
            billArea.append("Date: " + dateTime + "\n");
            billArea.append("-----------------------------------\n");
            billArea.append(String.format("Subtotal: ₹%.2f\n", totalAmount));
            billArea.append(String.format("GST (5%%): ₹%.2f\n", gst));
            billArea.append(String.format("Grand Total: ₹%.2f\n", grand));
            billArea.append("-----------------------------------\n");
            billArea.append("Thank You! Visit Again\n");
            billArea.append("===================================\n");

            billGenerated = true;
        }

        if (e.getSource() == clearButton) {
            totalAmount = 0;
            billGenerated = false;
            billArea.setText("");
        }

        if (e.getSource() == logoutButton) {
            dispose();
            
        }

        if (e.getSource() == printButton) {
            try {
                billArea.print();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Printing Failed!");
            }
        }

        if (e.getSource() == saveButton) {
            try {
                FileWriter fw = new FileWriter("Receipt_" + receiptNumber + ".txt");
                fw.write(billArea.getText());
                fw.close();
                JOptionPane.showMessageDialog(this, "Saved Successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error Saving File!");
            }
        }
    }


     
    public static void main(String[] args) {
     Demo6 d=new Demo6();
       
    }
          }
