package bank;

import javax.swing.*;
import java.awt.*;
import bank.AccountView;
public class AccountView extends JPanel {

    private JLabel  AccountDataView accountDataView;
    private JTextField amountField;
    private JButton depositButton;
    private JButton withdrawButton;


    private final AccountDataView accountDataView;
    private final JLabel messageLabel;
    private final InputField amountField;
    private final JButton depositButton;
    private final JButton withdrawButton;

    public AccountView(Account account)
    {
        accountDataView = new AccountDataView(account);
        messageLabel = new JLabel("Enter an amount.");
        amountField = new InputField("Amount");
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(LayoutHelper.createLargeEmptyBorder());

        add(accountDataView);
        add(LayoutHelper.createRigidArea());
        add(createMessagePanel());
        add(LayoutHelper.createRigidArea());
        add(amountField);
        add(LayoutHelper.createRigidArea());
        add(createButtonsPanel());
    }

    private JPanel createMessagePanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(messageLabel);
        return panel;
    }

    private JPanel createButtonsPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(depositButton);
        panel.add(LayoutHelper.createSmallRigidArea());
        panel.add(withdrawButton);
        return panel;
    }

    public void addDepositListener(ActionListener listener)
    {
        depositButton.addActionListener(listener);
    }

    public void addWithdrawListener(ActionListener listener)
    {
        withdrawButton.addActionListener(listener);
    }

    public void updateAccount(Account account)
    {
        accountDataView.updateAccount(account);
    }

    public void updateName(String name)
    {
        accountDataView.updateName(name);
    }

    public void updateBalance(double balance)
    {
        accountDataView.updateBalance(balance);
    }

    public void displayMessage(String message)
    {
        messageLabel.setText(message);
    }

    public void resetAmount()
    {
        amountField.clearText();
    }

    public String getAmount()
    {
        return amountField.getText();
    }



}
