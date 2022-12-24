package bath.views;

import bath.events.DrainChangedEvent;
import bath.events.FaucetChangedEvent;
import bath.events.LevelChangedEvent;
import bath.models.Bath;
import bath.models.IBathListener;
import utility.swing.layout.LayoutHelper;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * Do not modify this class, except for the constructor initialization and the bath listener method implementations.
 * @author Jared Chevalier
 */
public final class BathView extends JPanel implements IBathListener
{
    private static final DecimalFormat levelFormatter = new DecimalFormat("0.0");

    private final JLabel faucetLabel;
    private final JLabel drainLabel;
    private final JLabel levelLabel;
    private final JLabel capacityLabel;
    private final JLabel messageLabel;
    private final JProgressBar levelProgressBar;
    private final JButton startRunningButton;
    private final JButton stopRunningButton;
    private final JButton startDrainingButton;
    private final JButton stopDrainingButton;
    private final JButton setupButton;
    private final JButton exitButton;

    public BathView(Bath bath)
    {
        // TODO: Initialize components to show initial state of bath model
        JFrame frame = new JFrame();
        frame.setContentPane(new BathView(bath));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);

        faucetLabel = createLabel(" "); // TODO: "Running" or "Stopped"
        drainLabel = createLabel(" "); // TODO: "Draining" or "Stopped"
        levelLabel = createLabel(" "); // TODO: Use level formatter to format level
        capacityLabel = createLabel(" "); // TODO: Use level formatter to format capacity
        messageLabel = createLabel("Ready");
        levelProgressBar = createProgressBar(0, 100, 0); // TODO: Specify correct min + max + value
        startRunningButton = createButton("Start running");
        stopRunningButton = createButton("Stop running");
        startDrainingButton = createButton("Start draining");
        stopDrainingButton = createButton("Stop draining");
        setupButton = createButton("Setup");
        exitButton = createButton("Exit");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(LayoutHelper.createLargeEmptyBorder());

        add(createControlsPanel());
        add(LayoutHelper.createRigidArea());
        add(createMessagePanel());
        add(LayoutHelper.createRigidArea());
        add(createLevelPanel());
        add(LayoutHelper.createLargeRigidArea());
        add(createNavigationPanel());
    }

    private static JLabel createLabel(String text)
    {
        JLabel label = new JLabel(text);
        label.setAlignmentX(0.5f);
        label.setAlignmentY(0.5f);
        return label;
    }

    private static JProgressBar createProgressBar(int min, int max, int value)
    {
        JProgressBar progressBar = new JProgressBar(min, max);
        progressBar.setValue(value);
        progressBar.setAlignmentX(0.5f);
        progressBar.setAlignmentY(0.5f);
        return progressBar;
    }

    private static JButton createButton(String text)
    {
        JButton button = new JButton(text);
        button.setAlignmentX(0.5f);
        button.setAlignmentY(0.5f);
        return button;
    }

    private JPanel createControlsPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(createRunningPanel());
        panel.add(LayoutHelper.createRigidArea());
        panel.add(createDrainingPanel());
        return panel;
    }

    private JPanel createRunningPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(LayoutHelper.addPadding(BorderFactory.createTitledBorder("Faucet")));
        panel.add(startRunningButton);
        panel.add(LayoutHelper.createRigidArea());
        panel.add(stopRunningButton);
        panel.add(LayoutHelper.createRigidArea());
        panel.add(faucetLabel);
        return panel;
    }

    private JPanel createDrainingPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(LayoutHelper.addPadding(BorderFactory.createTitledBorder("Drain")));
        panel.add(startDrainingButton);
        panel.add(LayoutHelper.createRigidArea());
        panel.add(stopDrainingButton);
        panel.add(LayoutHelper.createRigidArea());
        panel.add(drainLabel);
        return panel;
    }

    private JPanel createLevelPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(LayoutHelper.addPadding(BorderFactory.createTitledBorder("Level")));
        panel.add(createLevelProgressPanel());
        panel.add(LayoutHelper.createSmallRigidArea());
        panel.add(createLevelLabelsPanel());
        return panel;
    }

    private JPanel createLevelProgressPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(levelProgressBar);
        return panel;
    }

    private JPanel createLevelLabelsPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(createLabel("Level"));
        panel.add(LayoutHelper.createSmallRigidArea());
        panel.add(levelLabel);
        panel.add(LayoutHelper.createRigidArea());
        panel.add(createLabel("Capacity"));
        panel.add(LayoutHelper.createSmallRigidArea());
        panel.add(capacityLabel);
        return panel;
    }

    private JPanel createMessagePanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(LayoutHelper.addPadding(BorderFactory.createEtchedBorder()));
        panel.add(Box.createHorizontalGlue());
        panel.add(messageLabel);
        panel.add(Box.createHorizontalGlue());
        return panel;
    }

    private JPanel createNavigationPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(setupButton);
        panel.add(LayoutHelper.createRigidArea());
        panel.add(exitButton);
        return panel;
    }

    public void initialize(Bath bath)
    {
        faucetLabel.setText(getFaucetText(bath.isRunningWater()));
        drainLabel.setText(getDrainText(bath.isDrainingWater()));
        levelLabel.setText(levelFormatter.format(bath.getLevel()));
        capacityLabel.setText(levelFormatter.format(bath.getCapacity()));

        levelProgressBar.setMinimum(0);
        levelProgressBar.setMaximum((int)bath.getCapacity());
        levelProgressBar.setValue((int)bath.getLevel());
    }

    public void addStartRunningListener(ActionListener listener)
    {
        startRunningButton.addActionListener(listener);
    }

    public void addStopRunningListener(ActionListener listener)
    {
        stopRunningButton.addActionListener(listener);
    }

    public void addStartDrainingListener(ActionListener listener)
    {
        startDrainingButton.addActionListener(listener);
    }

    public void addStopDrainingListener(ActionListener listener)
    {
        stopDrainingButton.addActionListener(listener);
    }

    public void addSetupListener(ActionListener listener)
    {
        setupButton.addActionListener(listener);
    }

    public void addExitListener(ActionListener listener)
    {
        exitButton.addActionListener(listener);
    }

    public void displayMessage(String message)
    {
        messageLabel.setText(message);
    }

    @Override
    public void drainChanged(DrainChangedEvent event)
    {
        if (event.drainIsDraining())
            if (event.drainIsDraining()) {
                drainLabel.setText("Draining");
            } else {
                drainLabel.setText("Stopped");
            }
        // TODO: Bonus: Make method thread-safe by using SwingUtilities.invokeLater to update label on EDT


    }

    @Override
    public void faucetChanged(FaucetChangedEvent event)
    {
        if (event.faucetIsRunning())
        {
            faucetLabel.setText("Running");
        }
        else
        {
            faucetLabel.setText("Stopped");
        }

    }

    @Override
    public void levelChanged(LevelChangedEvent event)
    {
        levelLabel.setText(levelFormatter.format(event.getLevel()));

        if (event.bathIsEmpty())
        {
            messageLabel.setText("Bath is empty");
        }
        else if (event.bathIsFull())
        {
            messageLabel.setText("Bath is full");
        }
    }

    private static String getDrainText(boolean draining)
    {
        return draining ? "Draining" : "Stopped";
    }

    private static String getFaucetText(boolean running)
    {
        return running ? "Running" : "Stopped";
    }
}
