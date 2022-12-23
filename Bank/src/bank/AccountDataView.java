package bank;

import javax.swing.*;
import bamk.AccountView;
public class AccountDataView extends JPanel {

    private final JLabel nameLabel;
    private final JLabel accountIdLabel;
    private final JLabel balanceLabel;


    public AccountDataView(Account account)
    {
        nameLabel = createValueLabel(account.getName());
        accountNumberLabel = createValueLabel(Long.toString(account.getAccountNumber()));
        balanceLabel = createValueLabel(CurrencyHelper.getLocalCurrencyFormatter().format(account.getBalance()));

        setLayout(new GridLayout(3, 2, LayoutHelper.DefaultSize, LayoutHelper.DefaultSize));

        add(createTitleLabel("Name"));
        add(nameLabel);
        add(createTitleLabel("Account"));
        add(accountNumberLabel);
        add(createTitleLabel("Balance"));
        add(balanceLabel);
    }

    private static JLabel createTitleLabel(String text)
    {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.RIGHT);
        return label;
    }

    private static JLabel createValueLabel(String text)
    {
        JLabel label = new JLabel(text);
        Font font = label.getFont();
        label.setFont(new Font(font.getName(), Font.BOLD, font.getSize()));
        return label;
    }

    public void updateAccount(Account account)
    {
        accountNumberLabel.setText(Long.toString(account.getAccountNumber()));
        updateName(account.getName());
        updateBalance(account.getBalance());
    }

    public void updateName(String name)
    {
        nameLabel.setText(name);
    }

    public void updateBalance(double balance)
    {
        balanceLabel.setText(CurrencyHelper.getLocalCurrencyFormatter().format(balance));
    }

}
