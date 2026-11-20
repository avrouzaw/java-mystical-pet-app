/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class MysticalPet extends JFrame {
    private Statuses pet;
    private final Random rand = new Random();
    private int day = 0;
    
    // UI Components
    private JPanel mainPanel;
    private JLabel dayLabel;
    private JLabel[] statLabels;
    private RoundedProgressBar[] statBars;
    private JTextArea eventArea;
    private JButton[] choiceButtons;
    private JPanel choicePanel;
   
    
    // Colors
    private final Color DARK_BG = new Color(20, 25, 35);
    private final Color GOLD = new Color(255, 215, 0);
    private final Color PURPLE = new Color(126, 77, 120);
    private final Color DPURPLE = new Color(77, 54, 74);
    

    static class Scenario {
        String description;
        Events[] choices;

        Scenario(String description, Events... choices) {
            this.description = description;
            this.choices = choices;
        }
    }

    private final Scenario[] scenarios = {
        new Scenario("Your pet's stomach growls! It's hungry! 🍒",
            new Events("Feed enchanted berries", "Premium glowing food restores vitality.", 0, 20, 0, 5, 15, -15),
            new Events("Let it hunt wild game", "Pet ventures out independently for food.", 0, 15, 15, 0, 10, 5),
            new Events("Share your humble meal", "A bonding moment over simple dinner.", 15, 10, 0, 0, 0, -10)
        ),
        new Scenario("Your pet found a shiny mystical gem! 💎",
            new Events("Sell it at market", "Quick gold for your adventures.", 0, 0, 0, 0, 0, 30),
            new Events("Keep as spirit talisman", "Infuses magical energy into soul.", 0, 5, 0, 20, 5, 0),
            new Events("Trade for love toy", "Pet adores the new plaything!", 15, 0, 0, 10, 0, -10)
        ),
        new Scenario("A fierce storm approaches the forest! ⛈️",
            new Events("Huddle in cozy cave", "Safe but closeness builds bond.", 10, 10, -10, 0, 20, 0),
            new Events("Brave the winds together", "Tests courage and independence.", 0, -10, 20, 10, -5, 0),
            new Events("Build magical shelter", "Clever use of twigs and spells.", 0, 5, 15, 5, -15, -10)
        ),
        new Scenario("Your pet is full of playful energy! 🎾",
            new Events("Play endless fetch", "Laughter and exhaustion ensue.", 15, 5, 5, 5, -25, 0),
            new Events("Teach dazzling new trick", "Pet masters magic flips!", 5, 0, 15, 15, -15, 0),
            new Events("Rest and cuddle instead", "Recharge with gentle affection.", 10, 10, -10, 0, 25, 0)
        ),
        new Scenario("Your pet looks ill and weary... 😷",
            new Events("Visit wise healer owl", "Expensive but expert care.", -5, 30, 0, 5, 0, -30),
            new Events("Home-brew potion", "Risky but resourceful remedy.", 0, 20, -5, 10, 5, -10),
            new Events("Tough love recovery", "Rest and willpower prevail.", -10, 10, 10, 0, 15, 0)
        ),
        new Scenario("A mysterious stranger offers a quest! 🗺️",
            new Events("Accept the challenge", "Adventure yields treasure but risks.", 0, -10, 15, 10, -15, 25),
            new Events("Decline politely", "Safe but opportunity slips.", 5, 5, 0, 0, 5, -5),
            new Events("Negotiate better terms", "Clever bargaining pays off.", 10, 0, 5, 5, -5, 15)
        ),
        new Scenario("Pet knocked over sacred vase! 💔",
            new Events("Scold harshly", "Discipline costs but fixes vase.", -20, 0, 0, 0, 0, -25),
            new Events("Laugh it off", "Forgiveness lightens mood.", 0, 0, 0, -10, 0, -10),
            new Events("Teach responsibility", "Lesson in care and caution.", -5, 5, 15, 5, -5, -5)
        ),
        new Scenario("Village festival calls! 🎉",
            new Events("Party all night", "Fun overload drains reserves.", 25, -15, -10, 20, -35, -25),
            new Events("Stay home quiet", "Missed joy but safe recharge.", 10, 15, 0, 0, 20, 0),
            new Events("Perform tricks for coin", "Show off earns cheers and gold.", 15, 0, 5, 15, -20, 20)
        ),
        new Scenario("Pet dreams of soaring skies! 🦅",
            new Events("Enchant temporary wings", "Thrilling flight costs magic.", 0, 0, 0, 25, 10, -25),
            new Events("Encourage grounded fun", "Safe play builds steady bond.", 10, 10, 10, 0, 0, 0),
            new Events("Share your dream story", "Inspiration without risk.", 15, 5, 5, 15, -5, 0)
        ),
        new Scenario("Ancient treasure map discovered! 🏴‍☠️",
            new Events("Follow the map boldly", "Riches with high peril.", 5, -20, 20, 15, -30, 50),
            new Events("Sell the map", "Easy profit, no sweat.", 0, 0, 0, 0, 0, 30),
            new Events("Frame as wall art", "Decor boosts home spirit.", 5, 5, 0, 20, 0, -15)
        ),
        new Scenario("Pet befriends wild sprite! 🧚",
            new Events("Encourage the friendship", "Social growth with chaos.", -5, 0, 20, 15, -10, 0),
            new Events("Act jealous and separate", "Secures bond but stifles.", 20, 0, -20, -10, 0, -10),
            new Events("Join the fun trio", "Group play builds all.", 10, -5, 10, 10, -20, -5)
        ),
        new Scenario("A lost child ask for help! 🧒✨",
            new Events("Guide them home", "Kindness warms hearts.", 10, 0, 0, 10, -10, 0),
            new Events("Give gold for a ride", "Quick solution but costly.", 5, 0, 0, 0, 0, -20),
            new Events("Ignore and move on", "Selfish choice preserves strength.", 0, 0, 10, -15, 0, 0)
        ),
        new Scenario("A dragon's roar shakes the valley! 🐲🔥",
            new Events("Hide in safety", "Better safe than sorry.", 0, 10, -10, 0, 10, 0),
            new Events("Stand your ground", "Courage sparks spirit!", 5, -20, 0, 25, 0, 0),
            new Events("Offer peace gift", "Diplomacy avoids disaster.", 0, 0, 0, 15, -5, -20)
        ),
        new Scenario("Your pet feels insecure... 😢",
            new Events("Give heartfelt comfort", "Emotional bonding moment.", 25, 0, 0, 5, 0, 0),
            new Events("Motivate with training", "Confidence through growth.", 5, 0, 15, 0, -10, 0),
            new Events("Ignore emotions", "Strength through struggle?", -20, 0, 10, 0, 0, 0)
        ),
        new Scenario("Magical fruits bloom nearby! 🍑✨",
            new Events("Harvest carefully", "Fresh healthier snacks!", 0, 15, 0, 0, 10, 0),
            new Events("Sell at high price", "Rare goods make profit.", 0, 0, 0, -5, 0, 35),
            new Events("Share with villagers", "Goodwill spreads far.", 20, 0, 0, 0, -5, -5)
        ),
        new Scenario("A rival pet challenges yours! ⚔️🐾",
            new Events("Accept the duel", "Bragging rights await!", 0, -15, 10, 20, 0, 0),
            new Events("Avoid conflict", "Safety first", 10, 0, -10, 0, 0, 0),
            new Events("Make peace treaty", "Friendship beats rivalry.", 15, 0, 0, 10, 0, -10)
        ),
        new Scenario("Mysterious potion seller appears! 🧪🕶️",
            new Events("Buy vitality potion", "Heals body nicely!", 0, 25, 0, 0, 0, -20),
            new Events("Buy spirit elixir", "Boosts magic inside.", 0, 0, 0, 25, 0, -20),
            new Events("Walk away suspiciously", "Trust your instincts.", 0, 0, 10, -5, 0, 0)
        ),
        new Scenario("Your pet attempts cleaning! 🧹💦",
            new Events("Praise messy effort", "Chaos but adorable", 20, -5, 10, 0, 0, 0),
            new Events("Teach proper cleaning", "Life skills improved.", 0, 0, 20, 0, -10, 0),
            new Events("Hire cleaner", "No effort required.", 0, 0, 0, 0, 15, -15)
        ),
        new Scenario("The moon radiates ancient magic! 🌙✨",
            new Events("Full moon ritual", "Spiritual awakening.", 0, 0, 0, 30, -20, 0),
            new Events("Moonlight cuddles", "Romantic and cozy.", 25, 5, 0, 0, 0, 0),
            new Events("Sleep early", "Wake refreshed.", 0, 0, 0, -5, 25, 0)
        ),
        new Scenario("Your pet discovers a secret hobby! 🎨",
            new Events("Support its art", "Creativity flourishes!", 10, 0, 0, 20, 0, -10),
            new Events("Turn hobby into business", "Profit from passion.", 0, 0, 10, 0, -15, 25),
            new Events("Discourage distraction", "Focus on main goals.", -15, 0, 15, 0, 0, 0)
        ),
    };

    public MysticalPet() {
        ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);
        setupWindow();
        showWelcomeDialog();
        initializeGame();
        startNewDay();
    }

    private void setupWindow() {
        setTitle("🌙 Mystical Pet Caretaking 🌟");
        setSize(700, 500);
        BackgroundPanel bg = new BackgroundPanel();
        setContentPane(bg);
        bg.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void showWelcomeDialog() {
        JDialog welcomeDialog = new JDialog(this, "Welcome!", true);
        BackgroundPanel wbg = new BackgroundPanel();
        welcomeDialog.setContentPane(wbg);
            wbg.setLayout(new BorderLayout());
        welcomeDialog.setSize(500, 350);
        welcomeDialog.setLocationRelativeTo(this);
        

        JLabel titleLabel = new JLabel(" Mystical Pet Caretaking ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        titleLabel.setForeground(DPURPLE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));

        JPanel inputPanel = new JPanel(new BorderLayout(40, 20));
        inputPanel.setBackground(DARK_BG);
        inputPanel.setOpaque(false);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        JLabel nameLabel = new JLabel("Enter your magical pet's name:");
        nameLabel.setForeground(GOLD);
        nameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        RoundedTextField nameField = new RoundedTextField(1, 40);
        nameField.setPreferredSize(new Dimension(150, 20));
        nameField.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
        nameField.setBackground(Color.WHITE);
        nameField.setForeground(Color.BLACK);
        nameField.setCaretColor(Color.BLACK);
        nameField.setHorizontalAlignment(JTextField.CENTER);
        
        inputPanel.add(nameLabel, BorderLayout.NORTH);
        inputPanel.add(nameField, BorderLayout.CENTER);

        RoundedButton startButton = new RoundedButton(" Start ");
        startButton.setRadius(40);
        startButton.setBackground(GOLD);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
        startButton.setPreferredSize(new Dimension(110, 40));
        startButton.addActionListener(e -> {
            String petName = nameField.getText().trim();
            if (petName.isEmpty()) petName = " ";
            pet = new Statuses(petName, 50, 50, 50, 50, 50, 50);
            welcomeDialog.dispose();
        });

        JPanel buttonPanel = new RoundedPanel(80);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 30, 40));
        buttonPanel.setOpaque(false);
        buttonPanel.setBackground(new Color(0, 0, 0, 0));
        buttonPanel.add(startButton);

        welcomeDialog.add(titleLabel, BorderLayout.NORTH);
        welcomeDialog.add(inputPanel, BorderLayout.CENTER);
        welcomeDialog.add(buttonPanel, BorderLayout.SOUTH);
        
        nameField.addActionListener(e -> startButton.doClick());
        
        welcomeDialog.setVisible(true);
    }

    private void initializeGame() {
        mainPanel = new BackgroundPanel();
        mainPanel.setLayout(new BorderLayout(15, 15));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top Panel - Day and Pet Name
        JPanel topPanel = new RoundedPanel(50);
        topPanel.setBackground(PURPLE);
   
        dayLabel = new JLabel("Day 1", SwingConstants.CENTER);
        dayLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        dayLabel.setForeground(GOLD);

        JLabel petNameLabel = new JLabel("" + pet.getName(), SwingConstants.CENTER);
        petNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
        petNameLabel.setForeground(GOLD);
        
        topPanel.setLayout(new BorderLayout());
        topPanel.add(dayLabel, BorderLayout.NORTH);
        topPanel.add(petNameLabel, BorderLayout.CENTER);

        // Stats Panel
        JPanel statsPanel = createStatsPanel();
        
        JPanel eventPanel = new RoundedPanel(40);
        eventPanel.setBackground(DPURPLE);
        eventPanel.setPreferredSize(new Dimension(300, 280));
        eventPanel.setMinimumSize(new Dimension(300, 280));

        eventPanel.setBorder(BorderFactory.createEmptyBorder(25, 10, 10, 10));
        
        JLabel eventTitle = new JLabel(" MYSTICAL EVENT ");
        eventTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        eventTitle.setForeground(GOLD);
        eventTitle.setHorizontalAlignment(SwingConstants.CENTER);
        eventPanel.add(eventTitle, BorderLayout.NORTH);
        
        
        // Event Area
        eventArea = new JTextArea();
        eventArea.setEditable(false);
        eventArea.setLineWrap(true);
        eventArea.setWrapStyleWord(true);
        eventArea.setFont(new Font("Segoe UI Emoji", Font.BOLD, 18));
        eventArea.setBackground(DPURPLE);
        eventArea.setForeground(Color.WHITE);
        eventArea.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JScrollPane eventScroll = new JScrollPane(eventArea);
        eventScroll.setBorder(null);
        eventScroll.setPreferredSize(new Dimension(200, 100));
        eventPanel.add(eventScroll, BorderLayout.CENTER);
        
        
        JPanel middlePanel = new JPanel(new BorderLayout(5, 0));
        middlePanel.setBackground(DARK_BG);
        middlePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 15));
        middlePanel.setOpaque(false);
        middlePanel.setPreferredSize(new Dimension(400, 380));
        middlePanel.setPreferredSize(new Dimension(400, 380));
        middlePanel.setMinimumSize(new Dimension(400, 380));

        middlePanel.add(statsPanel, BorderLayout.CENTER);
        middlePanel.add(eventPanel, BorderLayout.EAST);
        
        // Choice Panel
        choicePanel = new JPanel(new GridBagLayout());
        choicePanel.setBackground(DARK_BG);
        choicePanel.setOpaque(false);
        choicePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 40, 10));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(6, 8, 6, 8);
        
        choiceButtons = new JButton[3];
        for (int i = 0; i < 3; i++) {
            choiceButtons[i] = createStyledButton("Option " + (i + 1));
            choiceButtons[i].setPreferredSize(new Dimension(220, 36));
            gbc.gridy = i;
            choicePanel.add(choiceButtons[i], gbc);
        }

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);

        // ===== BOTTOM PANEL (gabungan text + choices) =====
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(DARK_BG);
        bottomPanel.setOpaque(false);


        // --- Panel text ---
        JPanel actionTextPanel = new JPanel();
        actionTextPanel.setBackground(DARK_BG);
        actionTextPanel.setOpaque(false);

        JLabel actionLabel = new JLabel("What are you gonna do?");
        actionLabel.setForeground(DARK_BG);
        actionLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        actionTextPanel.add(actionLabel);

        // Masukkan text ke atas
        bottomPanel.add(actionTextPanel, BorderLayout.NORTH);

        // Masukkan choice panel ke bawah
        bottomPanel.add(choicePanel, BorderLayout.SOUTH);

        // Tambahkan semuanya ke frame
        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }

    private JPanel createStatsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DARK_BG);
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        String[] statNames = {"❤️ Love", "🛡️ Health", "🏹 Independence", "✨ Spirit", "⚡ Energy", "💰 Money"};
        Color[] statColors = {
            new Color(255, 105, 180),
            new Color(46, 204, 113),
            new Color(155, 89, 182),
            new Color(255, 40, 0),
            new Color(52, 152, 219),
            Color.ORANGE,
        };

        statLabels = new JLabel[6];
        statBars = new RoundedProgressBar[6];

        for (int i = 0; i < 6; i++) {
            JPanel statPanel = new JPanel(new BorderLayout());
            statPanel.setBackground(DARK_BG);
            statPanel.setOpaque(false);
            statPanel.setPreferredSize(new Dimension(330, 350));
            statPanel.setBorder(null);

            
            statLabels[i] = new JLabel(statNames[i]);
            statLabels[i].setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
            statLabels[i].setForeground(DARK_BG);
            statLabels[i].setPreferredSize(new Dimension(150, 18));

            statBars[i] = new RoundedProgressBar(0, 100);
            statBars[i].setRadius(10);
            statBars[i].setValue(50);

            statBars[i].setPreferredSize(new Dimension(115, 20));
            statBars[i].setMaximumSize(new Dimension(115, 20));
            statBars[i].setMinimumSize(new Dimension(115, 20));

            statBars[i].setStringPainted(true);
            statBars[i].setFont(new Font("Comic Sans MS", Font.BOLD, 10));
            statBars[i].setForeground(statColors[i]);
            statBars[i].setBorder(null);

            statPanel.add(statLabels[i], BorderLayout.WEST);
            JPanel barHolder = new JPanel();
            barHolder.setLayout(new BoxLayout(barHolder, BoxLayout.X_AXIS));
            barHolder.setBackground(DARK_BG);
            barHolder.setOpaque(false);

            barHolder.add(statBars[i]);

            statPanel.add(barHolder, BorderLayout.CENTER);

            panel.add(statPanel);
            panel.add(Box.createVerticalStrut(3));
        }

        return panel;
    }

    private void updateStats() {
        int[] values = {pet.getLove(), pet.getHealth(), pet.getIndependence(), 
                       pet.getSpirit(), pet.getEnergy(), pet.getMoney()};
        String[] names = {"❤️ Love", "🛡️ Health", "🏹 Independence", "✨ Spirit", "⚡ Energy", "💰 Money"};

        for (int i = 0; i < 6; i++) {
            statLabels[i].setText(names[i]);
            statBars[i].setValue(values[i]);
        }
    }

    private void startNewDay() {
        day++;
        dayLabel.setText("Day " + day);
        
        if (checkEndGame()) return;

        Scenario current = scenarios[rand.nextInt(scenarios.length)];
        eventArea.setText(current.description);

        choicePanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 10, 5, 10);
        
        for (int i = 0; i < current.choices.length; i++) {
            Events event = current.choices[i];
            choiceButtons[i].setText(event.getName());
            choiceButtons[i].setToolTipText(event.getDescription());
            gbc.gridy = i;
            
            RoundedButton btn = new RoundedButton((i + 1) + ". " + event.getName());
            btn.setRadius(30);
            btn.setBackground(GOLD);
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
            btn.setPreferredSize(new Dimension(250, 30));
            btn.setToolTipText(event.getDescription());
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            btn.addActionListener(e -> {
                System.out.println("Button clicked: " + event.getName());
                handleChoice(event);
            });
            choicePanel.add(btn, gbc);
            choiceButtons[i] = btn;

        }

        choicePanel.revalidate();
        choicePanel.repaint();
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        button.setBackground(GOLD);
        button.setForeground(GOLD);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 6, 5, 6));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(GOLD.darker());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(GOLD);
            }
        });
        
        return button;
    }

    private void handleChoice(Events event) {
        pet.setLove(pet.getLove() + event.getLoveEffect());
        pet.setHealth(pet.getHealth() + event.getHealthEffect());
        pet.setIndependence(pet.getIndependence() + event.getIndependenceEffect());
        pet.setSpirit(pet.getSpirit() + event.getSpiritEffect());
        pet.setEnergy(pet.getEnergy() + event.getEnergyEffect());
        pet.setMoney(pet.getMoney() + event.getMoneyEffect());

        clampStats();
        updateStats();

        String result = ">>> " + event.getName() + "\n" + event.getDescription() + "\n\n" +
                       "Changes: " +
                       "❤️ " + signed(event.getLoveEffect()) + "  " +
                       "🛡️ " + signed(event.getHealthEffect()) + "  " +
                       "🏹 " + signed(event.getIndependenceEffect()) + "  " +
                       "✨ " + signed(event.getSpiritEffect()) + "  " +
                       "⚡ " + signed(event.getEnergyEffect()) + "  " +
                       "💰 " + signed(event.getMoneyEffect());

        JOptionPane.showMessageDialog(this, result, "Action Result", JOptionPane.INFORMATION_MESSAGE);

        if (!checkEndGame()) {
            startNewDay();
        }
    }

    private String signed(int n) {
        return n > 0 ? "+" + n : n < 0 ? String.valueOf(n) : "0";
    }

    private void clampStats() {
        pet.setLove(Math.max(0, Math.min(100, pet.getLove())));
        pet.setHealth(Math.max(0, Math.min(100, pet.getHealth())));
        pet.setIndependence(Math.max(0, Math.min(100, pet.getIndependence())));
        pet.setSpirit(Math.max(0, Math.min(100, pet.getSpirit())));
        pet.setEnergy(Math.max(0, Math.min(100, pet.getEnergy())));
        pet.setMoney(Math.max(0, Math.min(100, pet.getMoney())));
    }

    private boolean checkEndGame() {
        if (pet.getLove() >= 80 && pet.getHealth() >= 75 && pet.getIndependence() >= 70 && 
            pet.getSpirit() >= 80 && pet.getEnergy() >= 70 && pet.getMoney() >= 90) {
            showEndDialog("🏆 LEGENDARY VICTORY! 🏆", 
                         pet.getName() + " has ASCENDED to eternal bliss! 🌟✨", true);
            return true;
        }
        
        String[] messages = {
            "💔 GAME OVER: " + pet.getName() + " felt unloved and vanished into the mist...",
            "💀 GAME OVER: " + pet.getName() + "'s health faded away...",
            "🏹 GAME OVER: " + pet.getName() + " lost all independence and withered...",
            "✨ GAME OVER: " + pet.getName() + "'s spirit dimmed to nothing...",
            "⚡ GAME OVER: " + pet.getName() + " collapsed from total exhaustion...",
            "💸 GAME OVER: Broke! You had to abandon " + pet.getName() + "..."
        };
        
        int[] values = {pet.getLove(), pet.getHealth(), pet.getIndependence(), 
                       pet.getSpirit(), pet.getEnergy(), pet.getMoney()};
        
        for (int i = 0; i < values.length; i++) {
            if (values[i] <= 0) {
                showEndDialog("Game Over", messages[i], false);
                return true;
            }
        }
        
        return false;
    }

    private void showEndDialog(String title, String message, boolean victory) {
        int option = JOptionPane.showConfirmDialog(this, 
            message + "\n\nPlay again?", 
            title, 
            JOptionPane.YES_NO_OPTION);
        
        if (option == JOptionPane.YES_OPTION) {
            dispose();
            new MysticalPet();
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new MysticalPet());
    }
}