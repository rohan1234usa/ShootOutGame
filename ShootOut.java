/*
Rohan Singh
ShootOut.java
This game is a boss rush styled shooter
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class ShootOut //This class makes the JFrame and runs the panels
{
    public static void main(String[] args) //The main
    {
        ShootOut game = new ShootOut();
        game.createAndShowGUI();
    }
    private void createAndShowGUI() //Creates the JFrame and sets its properties
    {
        JFrame frame = new JFrame("Shoot-Out!");
        frame.setSize(1120, 630);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.GRAY);

        ShootOutGame game = new ShootOutGame();
        game.addComponentToPane(frame.getContentPane());

        frame.setVisible(true);
    }
}
class ShootOutGame implements ActionListener //runs the game and menus - uses a CardLayout
{
    JPanel cards, mainCard, levelsCard, powerUpsCard, characterSelectCard, instructionsCard, menuPanel;
    BossCardPanel bossCard;
    JLabel titleLabel;
    JButton levelButton, powerUpsButton, characterSelectButton, instructionButton;
    JCheckBox safety, easy, normal, hard, merciless, char1, char2, char3;
    Image backArrowImage;
    int lives, bossNum, charNum;
    MenuManager menuManager;

    public ShootOutGame() //constructor that sets the initial values of variables
    {
        lives = 10;
        charNum = 1;
        backArrowImage = new ImageIcon("back_arrow.png").getImage();
    }

    public void addComponentToPane(Container pane) //makes the panels for the menus and organizes them
    {
        mainCard = new JPanel();
        mainCard.setLayout(new BorderLayout());
        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(500,630));
        menuPanel.setLayout(new GridLayout(5,1));
        titleLabel = new JLabel("Shoot-Out!");
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        menuPanel.add(titleLabel);
        levelButton = new JButton("Levels");
        levelButton.setPreferredSize(new Dimension(500,110));
        levelButtonHandler levelHandler = new levelButtonHandler();
        levelButton.addActionListener(levelHandler);
        powerUpsButton = new JButton("Power-Ups");
        powerUpsButton.setPreferredSize(new Dimension(500,110));
        powerUpButtonHandler powerUpHandler = new powerUpButtonHandler();
        powerUpsButton.addActionListener(powerUpHandler);
        characterSelectButton = new JButton("Select Character");
        characterSelectButton.setPreferredSize(new Dimension(500,110));
        characterSelectButtonHandler characterSelectHandler = new characterSelectButtonHandler();
        characterSelectButton.addActionListener(characterSelectHandler);
        instructionButton = new JButton("Instructions");
        instructionButton.setPreferredSize(new Dimension(500,110));
        instructionButtonHandler instructionHandler = new instructionButtonHandler();
        instructionButton.addActionListener(instructionHandler);

        JPanel fillerPanelEast, fillerPanelWest, fillerPanelNorth, fillerPanelSouth;
        menuPanel.add(titleLabel);
        menuPanel.add(levelButton);
        menuPanel.add(powerUpsButton);
        menuPanel.add(characterSelectButton);
        menuPanel.add(instructionButton);
        fillerPanelEast = new JPanel();
        fillerPanelWest = new JPanel();
        fillerPanelNorth = new JPanel();
        fillerPanelSouth = new JPanel();
        fillerPanelEast.setPreferredSize(new Dimension(310, 630));
        fillerPanelWest.setPreferredSize(new Dimension(310, 630));
        fillerPanelNorth.setPreferredSize(new Dimension(310, 10));
        fillerPanelSouth.setPreferredSize(new Dimension(310, 10));
        mainCard.add(fillerPanelEast, BorderLayout.EAST);
        mainCard.add(fillerPanelWest, BorderLayout.WEST);
        //mainCard.add(fillerPanelNorth, BorderLayout.NORTH);
        mainCard.add(fillerPanelSouth, BorderLayout.SOUTH);
        mainCard.add(menuPanel, BorderLayout.CENTER);

        buildLevelCard();


        powerUpsCard = new JPanel();


        buildCharacterSelectCard();


        buildInstructionsCard();



        cards = new JPanel(new CardLayout());
        menuManager = new MenuManager(cards);
        bossCard = new BossCardPanel(menuManager);

        cards.add(mainCard);
        cards.add(levelsCard);
        cards.add(powerUpsCard);
        cards.add(characterSelectCard);
        cards.add(instructionsCard);
        cards.add(bossCard);

        pane.add(cards, BorderLayout.CENTER);

    }


    private void buildLevelCard() // this method makes the level card with all the levels and difficulties
    {
        levelsCard = new JPanel();
        levelsCard.setLayout(new BorderLayout());
        JPanel levelSelectPanel, difficultySelectPanel;
        levelSelectPanel = new JPanel();
        JPanel levelsPanel = new JPanel();
        levelSelectPanel.setLayout(new GridLayout(2,1));
        levelsPanel.setLayout(new GridLayout(1,5));
        difficultySelectPanel = new JPanel();
        JPanel difficultyPanel = new JPanel();
        difficultySelectPanel.setLayout(new GridLayout(3,1));
        difficultyPanel.setLayout(new GridLayout(1,5));
        JPanel difficultySelectImagesPanel = new JPanel();
        difficultySelectImagesPanel.setLayout(new GridLayout(1,5));

        JLabel levelLabel = new JLabel("Select Level:");
        levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        levelLabel.setPreferredSize(new Dimension(20,50));
        levelLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        JLabel difficultyLabel = new JLabel("Select Difficulty:");
        difficultyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        difficultyLabel.setPreferredSize(new Dimension(20,50));
        difficultyLabel.setFont(new Font("Serif", Font.PLAIN, 30));

        JButton level1 = new JButton("Level 1");
        JButton level2 = new JButton("Level 2");
        JButton level3 = new JButton("Level 3");
        JButton level4 = new JButton("Level 4");
        JButton level5 = new JButton("Level 5");
        Level1ButtonHandler level1Handler = new Level1ButtonHandler();
        Level2ButtonHandler level2Handler = new Level2ButtonHandler();
        Level3ButtonHandler level3Handler = new Level3ButtonHandler();
        Level4ButtonHandler level4Handler = new Level4ButtonHandler();
        Level5ButtonHandler level5Handler = new Level5ButtonHandler();


        safety = new JCheckBox("Safety");
        easy = new JCheckBox("Easy");
        normal = new JCheckBox("Normal");
        hard = new JCheckBox("Hard");
        merciless = new JCheckBox("Merciless");
        ShootOutGame.CheckBoxListener cbListener = new ShootOutGame.CheckBoxListener();
        safety.addActionListener(cbListener);
        safety.setSelected(false);
        easy.addActionListener(cbListener);
        easy.setSelected(false);
        normal.addActionListener(cbListener);
        normal.setSelected(true);
        hard.addActionListener(cbListener);
        hard.setSelected(false);
        merciless.addActionListener(cbListener);
        merciless.setSelected(false);

        level1.addActionListener(level1Handler);
        level2.addActionListener(level2Handler);
        level3.addActionListener(level3Handler);
        level4.addActionListener(level4Handler);
        level5.addActionListener(level5Handler);

        levelsPanel.add(level1);
        levelsPanel.add(level2);
        levelsPanel.add(level3);
        levelsPanel.add(level4);
        levelsPanel.add(level5);

        levelSelectPanel.add(levelLabel);
        levelSelectPanel.add(levelsPanel);

        Image safetyImage = new ImageIcon("safetyDifficulty.png").getImage();
        Image easyImage = new ImageIcon("easyDifficulty.png").getImage();
        Image normalImage = new ImageIcon("normalDifficulty.png").getImage();
        Image hardImage = new ImageIcon("hardDifficulty.png").getImage();
        Image mercilessImage = new ImageIcon("mercilessDifficulty.png").getImage();

        JLabel safetyLabel = new JLabel(new ImageIcon(safetyImage.getScaledInstance(135,90, Image.SCALE_DEFAULT), ""));
        JLabel easyLabel = new JLabel(new ImageIcon(easyImage.getScaledInstance(135,90, Image.SCALE_DEFAULT), ""));
        JLabel normalLabel = new JLabel(new ImageIcon(normalImage.getScaledInstance(135,90, Image.SCALE_DEFAULT), ""));
        JLabel hardLabel = new JLabel(new ImageIcon(hardImage.getScaledInstance(135,90, Image.SCALE_DEFAULT), ""));
        JLabel mercilessLabel = new JLabel(new ImageIcon(mercilessImage.getScaledInstance(135,90, Image.SCALE_DEFAULT), ""));

        difficultySelectImagesPanel.add(safetyLabel);
        difficultySelectImagesPanel.add(easyLabel);
        difficultySelectImagesPanel.add(normalLabel);
        difficultySelectImagesPanel.add(hardLabel);
        difficultySelectImagesPanel.add(mercilessLabel);

        difficultyPanel.add(safety);
        difficultyPanel.add(easy);
        difficultyPanel.add(normal);
        difficultyPanel.add(hard);
        difficultyPanel.add(merciless);

        JPanel difficultyCheckBoxPanel = new JPanel();
        difficultyCheckBoxPanel.setLayout(new BorderLayout());
        JPanel fillerPanelDifficulty = new JPanel();
        fillerPanelDifficulty.setPreferredSize(new Dimension(1,2));
        difficultyCheckBoxPanel.add(fillerPanelDifficulty, BorderLayout.WEST);
        difficultyCheckBoxPanel.add(difficultyPanel, BorderLayout.CENTER);

        difficultySelectPanel.add(difficultyLabel);
        difficultySelectPanel.add(difficultySelectImagesPanel);
        difficultySelectPanel.add(difficultyCheckBoxPanel);

        JPanel levelAndDifficultyPanel = new JPanel();
        levelAndDifficultyPanel.setLayout(new BorderLayout());
        levelAndDifficultyPanel.add(levelSelectPanel, BorderLayout.NORTH);
        levelAndDifficultyPanel.add(difficultySelectPanel, BorderLayout.SOUTH);

        JPanel fillerPanelEast2 = new JPanel();
        JPanel fillerPanelWest2 = new JPanel();
        JPanel fillerPanelNorth2 = new JPanel();
        JPanel fillerPanelSouth2 = new JPanel();
        fillerPanelEast2.setPreferredSize(new Dimension(200,10));
        fillerPanelNorth2.setPreferredSize(new Dimension(10,85));
        fillerPanelWest2.setPreferredSize(new Dimension(200,10));
        fillerPanelSouth2.setPreferredSize(new Dimension(10,65));
        //fillerPanelSouth2.setPreferredSize(new Dimension(10,115));

        JButton backButton = new JButton(new ImageIcon(backArrowImage.getScaledInstance(60,60,Image.SCALE_DEFAULT), ""));
        backButton.setPreferredSize(new Dimension(60,60));
        BackButtonHandler backButtonHandler = new BackButtonHandler();
        backButton.addActionListener(backButtonHandler);
        fillerPanelNorth2.add(backButton);

        levelsCard.add(levelAndDifficultyPanel, BorderLayout.CENTER);
        levelsCard.add(fillerPanelEast2, BorderLayout.EAST);
        levelsCard.add(fillerPanelNorth2, BorderLayout.NORTH);
        levelsCard.add(fillerPanelWest2, BorderLayout.WEST);
        levelsCard.add(fillerPanelSouth2, BorderLayout.SOUTH);
    }

    private void buildInstructionsCard() { //this method makes the instructions card
        instructionsCard = new JPanel();
        instructionsCard.setLayout(new BorderLayout());
        JPanel instructionsPanel = new JPanel();
        //instructionsPanel.setPreferredSize(new Dimension(100,500));
        JButton backButton = new JButton(new ImageIcon(backArrowImage.getScaledInstance(60,60,Image.SCALE_DEFAULT), ""));
        backButton.setPreferredSize(new Dimension(60,60));
        BackButtonHandler backButtonHandler = new BackButtonHandler();
        backButton.addActionListener(backButtonHandler);
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setPreferredSize(new Dimension(500,85));
        backButtonPanel.add(backButton);

        instructionsPanel.setLayout(new GridLayout(2,1));
        JLabel instructionsLabel = new JLabel("Instructions:");
        instructionsLabel.setHorizontalAlignment(JLabel.CENTER);
        instructionsLabel.setFont(new Font("Serif", Font.PLAIN, 50));

        /*
        JTextArea instructionsTextArea = new JTextArea("W to aim up\nA to move left\nS to crouch\nD to move right\nJ or Space Bar to jump\nK to shoot\nL to dash\nEscape Key or Pause Button to pause");
        instructionsTextArea.setFont(new Font("Serif", Font.PLAIN, 30));
        instructionsTextArea.setPreferredSize(new Dimension(500, 1000));
        */

        //I had originally used a JTextArea, but switched to JTextPane so that I could center text and make a clear background
        JTextPane instructionsTextPane = new JTextPane();
        instructionsTextPane.setText("\nW to aim up\n\nA to move left\n\nS to crouch\n\nD to move right\n\nJ or Space Bar to jump\n\nK to shoot\n\nL to dash\n\nEscape Key or Pause Button to pause");
        instructionsTextPane.setFont(new Font("Serif", Font.PLAIN, 30));
        instructionsTextPane.setPreferredSize(new Dimension(500, 1000));
        instructionsTextPane.setBackground(null);
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_CENTER);

        StyledDocument doc = instructionsTextPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), attributes, false);

        JPanel instructionsTextPanel = new JPanel();
        instructionsTextPanel.setPreferredSize(new Dimension(500, 510));
        instructionsTextPanel.setLayout(new BorderLayout());
        instructionsTextPanel.add(instructionsLabel, BorderLayout.NORTH);
        //instructionsTextPanel.add(instructionsTextArea, BorderLayout.CENTER);
        instructionsTextPanel.add(instructionsTextPane, BorderLayout.CENTER);

        JPanel emptyPanelBottom = new JPanel();
        emptyPanelBottom.setPreferredSize(new Dimension(500, 10));

        JPanel emptyRightPanel = new JPanel();
        emptyRightPanel.setPreferredSize(new Dimension(200, 10));

        JPanel emptyLeftPanel = new JPanel();
        emptyLeftPanel.setPreferredSize(new Dimension(200, 10));

        JPanel instructionAndBackPanel = new JPanel();
        instructionAndBackPanel.setPreferredSize(new Dimension(1000, 500));
        instructionAndBackPanel.setLayout(new BorderLayout());


        instructionAndBackPanel.add(backButtonPanel, BorderLayout.NORTH);
        instructionAndBackPanel.add(instructionsTextPanel, BorderLayout.CENTER);
        instructionAndBackPanel.add(emptyRightPanel, BorderLayout.EAST);
        instructionAndBackPanel.add(emptyLeftPanel, BorderLayout.WEST);

        instructionsPanel.add(instructionAndBackPanel, BorderLayout.CENTER);
        instructionsPanel.add(emptyPanelBottom, BorderLayout.SOUTH);
        instructionsCard.add(instructionsPanel, BorderLayout.NORTH);
    }

    public void buildCharacterSelectCard() //this method makes the character select card
    {
        Image char1Pic = new ImageIcon("cupheadCharacterSelectIcon.png").getImage();
        Image char2Pic = new ImageIcon("penguin_normal_left.png").getImage();
        Image char3Pic = new ImageIcon("astronaut_icon.png").getImage();

        JLabel char1PicLabel = new JLabel(new ImageIcon(char1Pic.getScaledInstance(250,250,Image.SCALE_DEFAULT), ""));
        JLabel char2PicLabel = new JLabel(new ImageIcon(char2Pic.getScaledInstance(165,250,Image.SCALE_DEFAULT), ""));
        JLabel char3PicLabel = new JLabel(new ImageIcon(char3Pic.getScaledInstance(165,183,Image.SCALE_DEFAULT), ""));


        characterSelectCard = new JPanel();
        characterSelectCard.setLayout(new BorderLayout());
        JPanel characterSelectPanel;
        characterSelectPanel = new JPanel();
        characterSelectPanel.setLayout(new GridLayout(1,6));

        JLabel levelLabel = new JLabel("Select Character:");
        levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        levelLabel.setPreferredSize(new Dimension(20,50));
        levelLabel.setFont(new Font("Serif", Font.PLAIN, 30));


        char1 = new JCheckBox("Captain Cup");
        char2 = new JCheckBox("Private Penguin");
        char3 = new JCheckBox("Sergeant Space");
        CheckBoxListener cbListener = new CheckBoxListener();
        char1.addActionListener(cbListener);
        char1.setSelected(true);
        char2.addActionListener(cbListener);
        char2.setSelected(false);
        char3.addActionListener(cbListener);
        char3.setSelected(false);

        characterSelectPanel.add(char1PicLabel);
        characterSelectPanel.add(char1);
        characterSelectPanel.add(char2PicLabel);
        characterSelectPanel.add(char2);
        characterSelectPanel.add(char3PicLabel);
        characterSelectPanel.add(char3);

        JPanel fillerPanelEast3 = new JPanel();
        JPanel fillerPanelWest3 = new JPanel();
        JPanel fillerPanelNorth3 = new JPanel();
        JPanel fillerPanelSouth3 = new JPanel();
        fillerPanelEast3.setPreferredSize(new Dimension(10,10));
        fillerPanelNorth3.setPreferredSize(new Dimension(10,150));
        fillerPanelWest3.setPreferredSize(new Dimension(100,10));
        fillerPanelSouth3.setPreferredSize(new Dimension(30,150));

        JButton backButton = new JButton(new ImageIcon(backArrowImage.getScaledInstance(60,60,Image.SCALE_DEFAULT), ""));
        backButton.setPreferredSize(new Dimension(60,60));
        BackButtonHandler backButtonHandler = new BackButtonHandler();
        backButton.addActionListener(backButtonHandler);
        fillerPanelNorth3.add(backButton);

        characterSelectCard.add(characterSelectPanel, BorderLayout.CENTER);
        characterSelectCard.add(fillerPanelEast3, BorderLayout.EAST);
        characterSelectCard.add(fillerPanelNorth3, BorderLayout.NORTH);
        characterSelectCard.add(fillerPanelWest3, BorderLayout.WEST);
        characterSelectCard.add(fillerPanelSouth3, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent evt) //this method handles input through buttons pressed
    {

    }

    class levelButtonHandler implements ActionListener //This buttonHandler takes user to level card
    {
        public void actionPerformed(ActionEvent e) //This method takes user to level card
        {
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.next(cards);
        }
    }
    class powerUpButtonHandler implements ActionListener //This buttonHandler takes user to power ups card
    {
        public void actionPerformed(ActionEvent e) //This method takes user to power ups card
        {
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.next(cards);
            cl.next(cards);
        }
    }
    class characterSelectButtonHandler implements ActionListener //This buttonHandler takes user to character select card
    {
        public void actionPerformed(ActionEvent e) //This method takes user to character select card
        {
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.next(cards);
            cl.next(cards);
            cl.next(cards);
        }
    }
    class instructionButtonHandler implements ActionListener //This buttonHandler takes user to instructions card
    {
        public void actionPerformed(ActionEvent e) { //This method takes user to instructions card
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.next(cards);
            cl.next(cards);
            cl.next(cards);
            cl.next(cards);
        }
    }
    class Level1ButtonHandler implements ActionListener //This buttonHandler takes user to boss card and loads boss 1
    {
        public void actionPerformed(ActionEvent e) { //This method takes user to boss card and loads boss 1
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.next(cards);
            cl.next(cards);
            cl.next(cards);
            cl.next(cards);
            bossNum = 1;
            bossCard.resetGame(lives, bossNum, charNum);
        }
    }
    class Level2ButtonHandler implements ActionListener //This buttonHandler takes user to boss card and loads boss 2
    {
        public void actionPerformed(ActionEvent e) { //This method takes user to boss card and loads boss 2
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.next(cards);
            cl.next(cards);
            cl.next(cards);
            cl.next(cards);
            bossNum = 2;
            bossCard.resetGame(lives, bossNum, charNum);
        }
    }
    class Level3ButtonHandler implements ActionListener //This buttonHandler takes user to boss card and loads boss 3
    {
        public void actionPerformed(ActionEvent e) { //This method takes user to boss card and loads boss 3
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.next(cards);
            cl.next(cards);
            cl.next(cards);
            cl.next(cards);
            bossNum = 3;
            bossCard.resetGame(lives, bossNum, charNum);
        }
    }
    class Level4ButtonHandler implements ActionListener //This buttonHandler takes user to boss card and loads boss 4
    {
        public void actionPerformed(ActionEvent e) { //This method takes user to boss card and loads boss 4
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.next(cards);
            cl.next(cards);
            cl.next(cards);
            cl.next(cards);
            bossNum = 4;
            bossCard.resetGame(lives, bossNum, charNum);
        }
    }
    class Level5ButtonHandler implements ActionListener //This buttonHandler takes user to boss card and loads boss 5
    {
        public void actionPerformed(ActionEvent e) { //This method takes user to boss card and loads boss 5
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.next(cards);
            cl.next(cards);
            cl.next(cards);
            cl.next(cards);
            bossNum = 5;
            bossCard.resetGame(lives, bossNum, charNum);
        }
    }
    class CheckBoxListener implements ActionListener { //this class makes the checkboxes used to select difficulty and character
        public void actionPerformed(ActionEvent e) { //this method makes the checkboxes used to select difficulty and character
            String cmd = e.getActionCommand();
            switch (cmd) {
                case "Safety":
                    safety.setSelected(true);
                    easy.setSelected(false);
                    normal.setSelected(false);
                    hard.setSelected(false);
                    merciless.setSelected(false);
                    lives = 99;
                    break;
                case "Easy":
                    safety.setSelected(false);
                    easy.setSelected(true);
                    normal.setSelected(false);
                    hard.setSelected(false);
                    merciless.setSelected(false);
                    lives = 25;
                    break;
                case "Normal":
                    safety.setSelected(false);
                    easy.setSelected(false);
                    normal.setSelected(true);
                    hard.setSelected(false);
                    merciless.setSelected(false);
                    lives = 10;
                    break;
                case "Hard":
                    safety.setSelected(false);
                    easy.setSelected(false);
                    normal.setSelected(false);
                    hard.setSelected(true);
                    merciless.setSelected(false);
                    lives = 5;
                    break;
                case "Merciless":
                    safety.setSelected(false);
                    easy.setSelected(false);
                    normal.setSelected(false);
                    hard.setSelected(false);
                    merciless.setSelected(true);
                    lives = 1;
                    break;
                case "Captain Cup":
                    char1.setSelected(true);
                    char2.setSelected(false);
                    char3.setSelected(false);
                    charNum = 1;
                    break;
                case "Private Penguin":
                    char1.setSelected(false);
                    char2.setSelected(true);
                    char3.setSelected(false);
                    charNum = 2;
                    break;
                case "Sergeant Space":
                    char1.setSelected(false);
                    char2.setSelected(false);
                    char3.setSelected(true);
                    charNum = 3;
                    break;
            }
        }
    }

    class BackButtonHandler implements ActionListener //This buttonHandler goes back to the main card
    {
        public void actionPerformed(ActionEvent e) //This method goes back to the main card
        {
            goToMenu();
        }
    }
    public void goToMenu() //takes user back to title screen
    {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.first(cards);
    }
}

class MenuManager //This class is used to allow the user to go back to the main menu from the boss card
{
    JPanel cards;
    public MenuManager(JPanel card) //Constructor to load cards into this class
    {
        cards = card;
    }
    public void goToMenu() //takes user back to title screen
    {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.first(cards);
    }
}

class BossCardPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, FocusListener //This class loads the images, graphics, keys pressed, and mouse actions for the boss card
{
    int xMouse, yMouse, startingLiveCount, liveCount, characterXVal, characterYVal, characterWidth, characterHeight, jumpDelay, jumpNum, dashNum, characterNum, bossNum, bossHealth, startingBossHealth;
    Bullet[] bullets;
    int bulletCount;
    Image healthBarImage, bossHealthBarImage, pauseIconImage, playIconImage, backArrowImage, deathScreenImage, exitToMenu;
    Image char1DuckingRight, char1DuckingLeft, char1NormalRight, char1NormalLeft, char1NormalUpRight, char1NormalUpLeft, char1NormalDiagonalRight, char1NormalDiagonalLeft, char1RunningRight, char1RunningLeft, char1RunningUpRight, char1RunningUpLeft;
    Image char2DuckingRight, char2DuckingLeft, char2NormalRight, char2NormalLeft, char2RunningRight, char2RunningLeft;
    Image char3DuckingRight, char3DuckingLeft, char3NormalRight, char3NormalLeft, char3RunningRight, char3RunningLeft;
    Image bulletLeft, bulletUpLeft, bulletUp, bulletUpRight, bulletRight;
    Image boss1Background, boss2Background, boss3Background, boss4Background, boss5Background;
    Image boss1Normal, boss1Attacking;
    Image boss1ProjectileLeft, boss1ProjectileRight, boss1ProjectileDown, boss1ProjectileUp, boss1ProjectileUpRight, boss1ProjectileUpLeft, boss1ProjectileDownRight, boss1ProjectileDownLeft;
    Jumper jumper;
    MoveLeft leftMover;
    MoveRight rightMover;
    Dash dasher;
    Shoot shooter;
    Timer jumpTimer, leftTimer, rightTimer, dashTimer, bulletMover, shootTimer;
    boolean crouching, movingRight, movingLeft, paused, jumping, crouchAfterJump, facingRight, facingLeft, dashing, dashingRight, dashingLeft, facingUp, shooting;
    Rectangle pauseButton, backButton;
    Polygon bossHitBox;
    MenuManager menuManager;
    public BossCardPanel(MenuManager menu) //constructor that sets the initial values of variables
    {
        grabFocus();
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
        healthBarImage = new ImageIcon("health_bar.png").getImage();
        bossHealthBarImage = new ImageIcon("bossHealthBar.png").getImage();
        pauseIconImage = new ImageIcon("pause_icon.png").getImage();
        playIconImage = new ImageIcon("play_icon.jpeg").getImage();
        backArrowImage = new ImageIcon("back_arrow_with_white_background.png").getImage();
        deathScreenImage = new ImageIcon("deathScreenImage.jpeg").getImage();
        exitToMenu = new ImageIcon("exitToMenu.jpeg").getImage();

        char1DuckingRight = new ImageIcon("cuphead_crouching_right.png").getImage();
        char1DuckingLeft = new ImageIcon("cuphead_crouching_left.png").getImage();
        char1NormalRight = new ImageIcon("cuphead_normal_right.png").getImage();
        char1NormalLeft = new ImageIcon("cuphead_normal_left.png").getImage();
        char1NormalUpRight = new ImageIcon("cuphead_up_right.png").getImage();
        char1NormalUpLeft = new ImageIcon("cuphead_up_left.png").getImage();
        //char1NormalDiagonalRight = new ImageIcon("cuphead_diagonal_right.png").getImage();
        //char1NormalDiagonalLeft = new ImageIcon("cuphead_diagonal_left.png").getImage();
        char1RunningRight = new ImageIcon("cuphead_running_right.png").getImage();
        char1RunningLeft = new ImageIcon("cuphead_running_left.png").getImage();
        char1RunningUpRight = new ImageIcon("cuphead_running_up_right.png").getImage();
        char1RunningUpLeft = new ImageIcon("cuphead_running_up_left.png").getImage();

        char2DuckingRight = new ImageIcon("penguin_crouching_right.png").getImage();
        char2DuckingLeft = new ImageIcon("penguin_crouching_left.png").getImage();
        char2NormalRight = new ImageIcon("penguin_normal_right.png").getImage();
        char2NormalLeft = new ImageIcon("penguin_normal_left.png").getImage();
        char2RunningRight = new ImageIcon("penguin_running_right.png").getImage();
        char2RunningLeft = new ImageIcon("penguin_running_left.png").getImage();

        char3DuckingRight = new ImageIcon("astronaut_crouching_right.png").getImage();
        char3DuckingLeft = new ImageIcon("astronaut_crouching_left.png").getImage();
        char3NormalRight = new ImageIcon("astronaut_normal_right.png").getImage();
        char3NormalLeft = new ImageIcon("astronaut_normal_left.png").getImage();
        char3RunningRight = new ImageIcon("astronaut_running_right.png").getImage();
        char3RunningLeft = new ImageIcon("astronaut_running_left.png").getImage();

        bulletLeft = new ImageIcon("bulletLeft.png").getImage();
        bulletUpLeft = new ImageIcon("bulletUpLeft.png").getImage();
        bulletUp = new ImageIcon("bulletUp.png").getImage();
        bulletUpRight = new ImageIcon("bulletUpRight.png").getImage();
        bulletRight = new ImageIcon("bulletRight.png").getImage();

        boss1Background = new ImageIcon("Boss1Background.jpeg").getImage();
        boss2Background = new ImageIcon("Boss2Background.jpeg").getImage();
        boss3Background = new ImageIcon("Boss3Background.jpeg").getImage();
        boss4Background = new ImageIcon("Boss4Background.jpeg").getImage();
        boss5Background = new ImageIcon("Boss5Background.jpeg").getImage();

        boss1Normal = new ImageIcon("boss1Normal.png").getImage();
        boss1Attacking = new ImageIcon("boss1Attacking.png").getImage();

        boss1ProjectileLeft = new ImageIcon("boss1ProjectileLeft.png").getImage();
        boss1ProjectileRight = new ImageIcon("boss1ProjectileRight.png").getImage();
        boss1ProjectileDown = new ImageIcon("boss1ProjectileDown.png").getImage();
        boss1ProjectileUp = new ImageIcon("boss1ProjectileUp.png").getImage();
        boss1ProjectileUpRight = new ImageIcon("boss1ProjectileUpRight.png").getImage();
        boss1ProjectileUpLeft = new ImageIcon("boss1ProjectileUpLeft.png").getImage();
        boss1ProjectileDownRight = new ImageIcon("boss1ProjectileDownRight.png").getImage();
        boss1ProjectileDownLeft = new ImageIcon("boss1ProjectileDownLeft.png").getImage();


        jumpDelay = 10;
        characterXVal = 90;
        characterYVal = 410;
        characterWidth = 100;
        characterHeight = 140;
        jumpNum = 0;
        dashNum = 0;
        liveCount = 10;
        jumper = new Jumper();
        jumpTimer = new Timer(10, jumper);
        leftMover = new MoveLeft();
        leftTimer = new Timer(10,leftMover);
        rightMover = new MoveRight();
        rightTimer = new Timer(10, rightMover);
        dasher = new Dash();
        dashTimer = new Timer(10, dasher);
        bulletMover = new Timer(10, new BulletMover());
        bulletMover.start();
        shooter = new Shoot();
        shootTimer = new Timer(200, shooter);
        crouching = false;
        movingRight = false;
        movingLeft = false;
        pauseButton = new Rectangle(30,15,60,60);
        backButton = new Rectangle(1030,15,60,60);
        paused = false;
        jumping = false;
        crouchAfterJump = false;
        facingRight = true;
        facingLeft = false;
        dashing = false;
        shooting = false;
        menuManager = menu;
    }
    public void resetGame(int lives, int whichBoss, int whichCharacter) //this method is used to reset the variables everytime the user enters the game
    {
        jumpDelay = 10;
        characterXVal = 90;
        characterYVal = 410;
        characterWidth = 100;
        characterHeight = 140;
        jumpNum = 0;
        jumper = new Jumper();
        jumpTimer = new Timer(10, jumper);
        leftMover = new MoveLeft();
        leftTimer = new Timer(10,leftMover);
        rightMover = new MoveRight();
        rightTimer = new Timer(10, rightMover);
        bullets = new Bullet[100000];
        bulletCount = 0;
        crouching = false;
        movingRight = false;
        movingLeft = false;
        pauseButton = new Rectangle(30,15,60,60);
        backButton = new Rectangle(1030,15,60,60);
        paused = false;
        jumping = false;
        crouchAfterJump = false;
        facingUp = false;
        facingRight = true;
        facingLeft = false;
        dashing = false;
        liveCount = lives;
        startingLiveCount = lives;
        bossNum = whichBoss;
        characterNum = whichCharacter;
        grabFocus();

        if(whichBoss == 1)
        {
            bossHealth = 1000;
            startingBossHealth = 1000;
        }
        else if(whichBoss == 2)
        {
            bossHealth = 1000;
            startingBossHealth = 1000;
        }
        else if(whichBoss == 3)
        {
            bossHealth = 1000;
            startingBossHealth = 1000;
        }
        else if(whichBoss == 4)
        {
            bossHealth = 1000;
            startingBossHealth = 1000;
        }
        else if(whichBoss == 5)
        {
            bossHealth = 1000;
            startingBossHealth = 1000;
        }

        repaint();
    }
    public void paintComponent(Graphics g) //this creates the objects inside the frame
    {
        super.paintComponent(g);

        if(liveCount <= 0)
        {
            deathScreen(g);
            return;
        }

        if(bossNum == 1)
        {
            g.drawImage(boss1Background, 0, 0, 1120, 630, null);
            drawBoss1(g);
        }
        else if(bossNum == 2)
        {
            g.drawImage(boss2Background, 0, 0, 1120, 630, null);
            drawBoss2(g);
        }
        else if(bossNum == 3)
        {
            g.drawImage(boss3Background, 0, 0, 1120, 630, null);
            drawBoss3(g);
        }
        else if(bossNum == 4)
        {
            g.drawImage(boss4Background, 0, 0, 1120, 630, null);
            drawBoss4(g);
        }
        else
        {
            g.drawImage(boss5Background, 0, 0, 1120, 630, null);
            drawBoss5(g);
        }


        drawChar(g);

        g.setColor(Color.RED);

        g.drawImage(healthBarImage, 170,-35,270,162, null);
        int healthWidth = (int)(178*(1.0*(liveCount)/startingLiveCount));
        g.fillRect(238,45, healthWidth,16);

        g.setColor(Color.BLACK);
        String liveCountString = "" + liveCount;
        g.setFont(new Font("Times−Roman", Font.PLAIN, 18));
        if(liveCount < 10) g.drawString(liveCountString, 197, 49);
        else g.drawString(liveCountString, 192, 49);

        g.setColor(Color.RED);
        int bossHealthWidth = (int)(219*(1.0*(bossHealth)/startingBossHealth));
        g.fillRect(718,34,bossHealthWidth,33);
        g.drawImage(bossHealthBarImage, 715,10,225,60, null);

        if(!paused) g.drawImage(pauseIconImage, 30,15,60,60, null);
        else g.drawImage(playIconImage, 30,15,60,60, null);
        g.drawImage(backArrowImage, 1030,15,60,60, null);

        paintBullets(g);
    }

    private void drawChar(Graphics g) //this method draws the character being played as
    {
        if(characterNum == 1)
        {
            if(paused && facingRight) g.drawImage(char1NormalRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(paused && facingLeft) g.drawImage(char1NormalLeft, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(crouching && facingRight) g.drawImage(char1DuckingRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(crouching && facingLeft) g.drawImage(char1DuckingLeft, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(facingUp && facingRight && !movingRight && !dashing) g.drawImage(char1NormalUpRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(facingUp && facingLeft && !movingLeft && !dashing) g.drawImage(char1NormalUpLeft, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(facingUp && (movingRight || dashingRight)) g.drawImage(char1RunningUpRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(facingUp && (movingLeft || dashingLeft)) g.drawImage(char1RunningUpLeft, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(facingRight && !movingRight && !dashing) g.drawImage(char1NormalRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(facingLeft && !movingLeft && !dashing) g.drawImage(char1NormalLeft, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(dashingRight) g.drawImage(char1RunningRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(dashingLeft) g.drawImage(char1RunningLeft, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(movingRight) g.drawImage(char1RunningRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(movingLeft) g.drawImage(char1RunningLeft, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else g.drawImage(char1NormalRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
        }
        else if(characterNum == 2)
        {
            if(paused && facingRight) g.drawImage(char2NormalRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(paused && facingLeft) g.drawImage(char2NormalLeft, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(crouching && facingRight) g.drawImage(char2DuckingRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(crouching && facingLeft) g.drawImage(char2DuckingLeft, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(facingRight && !movingRight && !dashing) g.drawImage(char2NormalRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(facingLeft && !movingLeft && !dashing) g.drawImage(char2NormalLeft, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(dashingRight) g.drawImage(char2RunningRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(dashingLeft) g.drawImage(char2RunningLeft, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(movingRight) g.drawImage(char2RunningRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(movingLeft) g.drawImage(char2RunningLeft, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else g.drawImage(char2NormalRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
        }
        else if(characterNum == 3)
        {
            if(paused && facingRight) g.drawImage(char3NormalRight, characterXVal, characterYVal, (int)(characterWidth/1.2), characterHeight, null);
            else if(paused && facingLeft) g.drawImage(char3NormalLeft, characterXVal, characterYVal, (int)(characterWidth/1.2), characterHeight, null);
            else if(crouching && facingRight) g.drawImage(char3DuckingRight, characterXVal, characterYVal, (int)(characterWidth/1.2), characterHeight, null);
            else if(crouching && facingLeft) g.drawImage(char3DuckingLeft, characterXVal, characterYVal, (int)(characterWidth/1.2), characterHeight, null);
            else if(facingRight && !movingRight && !dashing) g.drawImage(char3NormalRight, characterXVal, characterYVal, (int)(characterWidth/1.2), characterHeight, null);
            else if(facingLeft && !movingLeft && !dashing) g.drawImage(char3NormalLeft, characterXVal, characterYVal, (int)(characterWidth/1.2), characterHeight, null);
            else if(dashingRight) g.drawImage(char3RunningRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(dashingLeft) g.drawImage(char3RunningLeft, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(movingRight) g.drawImage(char3RunningRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(movingLeft) g.drawImage(char3RunningLeft, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else g.drawImage(char3NormalRight, characterXVal, characterYVal, (int)(characterWidth/1.2), characterHeight, null);
        }
    }

    public void paintBullets(Graphics g) //this method loads and paints every bullet that has been loaded
    {
        for(int i = 0; i < bulletCount; i++)
        {
            if(i >= bulletCount) return;
            bullets[i].paintBullet(g);
        }
    }

    public void keyTyped(KeyEvent e) //Used to handle when a key is typed
    {
        char charOfKeyTyped = e.getKeyChar();
    }
    
    public void keyPressed(KeyEvent e) { //Used to handle when a key is pressed and allow the user to move around
        int key = e.getKeyCode();
        if(liveCount <= 0)
        {
            grabFocus();
            return;
        }
        if(paused)
        {
            switch(key)
            {
                case KeyEvent.VK_W:
                    //facingUp = true;
                    break;
                case KeyEvent.VK_A:
                    if(!facingUp) movingLeft = true;
                    break;
                case KeyEvent.VK_D:
                    if(!facingUp) movingRight = true;
                    break;
                case KeyEvent.VK_S:
                    crouchAfterJump = true;
                    break;
                case KeyEvent.VK_K:
                    shooting = true;
                    break;
                case KeyEvent.VK_ESCAPE:
                    resumeGame();
                    break;
            }
            return;
        }
        switch(key)
        {
            case KeyEvent.VK_W:
                facingUp = true;
                break;
            case KeyEvent.VK_A:
                facingRight = false;
                facingLeft = true;
                movingLeft = true;
                leftTimer.start();
                repaint();
                break;
            case KeyEvent.VK_D:
                facingRight = true;
                facingLeft = false;
                movingRight = true;
                rightTimer.start();
                repaint();
                break;
            case KeyEvent.VK_S:
                if(!jumping) crouch();
                crouchAfterJump = true;
                break;
            case KeyEvent.VK_J:
                if(!crouching) jumpTimer.start();
                break;
            case KeyEvent.VK_SPACE:
                if(!crouching) jumpTimer.start();
                break;
            case KeyEvent.VK_K:
                shooting = true;
                shootTimer.start();
                break;
            case KeyEvent.VK_L:
                if(jumping) jumpTimer.stop();
                dashTimer.start();
                break;
            case KeyEvent.VK_ESCAPE:
                pauseGame();
                break;
        }
    }
    public void keyReleased(KeyEvent e) { //handles when a key is released to stop moving
        int key = e.getKeyCode();
        if(liveCount <= 0)
        {
            grabFocus();
            return;
        }
        if(paused)
        {
            switch(key)
            {
                case KeyEvent.VK_W:
                    facingUp = false;
                    break;
                case KeyEvent.VK_A:
                    movingLeft = false;
                    break;
                case KeyEvent.VK_D:
                    movingRight = false;
                    repaint();
                    break;
                case KeyEvent.VK_K:
                    shooting = false;
                    break;
                case KeyEvent.VK_S:
                    crouchAfterJump = false;
                    break;
            }
            return;
        }
        if(dashing)
        {
            switch(key)
            {
                case KeyEvent.VK_W:
                    facingUp = false;
                    break;
                case KeyEvent.VK_A:
                    movingLeft = false;
                    leftTimer.stop();
                    repaint();
                    break;
                case KeyEvent.VK_D:
                    movingRight = false;
                    rightTimer.stop();
                    repaint();
                    break;
                case KeyEvent.VK_K:
                    shooting = false;
                    shootTimer.stop();
                    break;
            }
            return;
        }
        switch(key)
        {
            case KeyEvent.VK_W:
                facingUp = false;
                break;
            case KeyEvent.VK_A:
                movingLeft = false;
                leftTimer.stop();
                repaint();
                break;
            case KeyEvent.VK_D:
                movingRight = false;
                rightTimer.stop();
                repaint();
                break;
            case KeyEvent.VK_S:
                if(!jumping) stopCrouching();
                crouchAfterJump = false;
                break;
            case KeyEvent.VK_K:
                shooting = false;
                shootTimer.stop();
                break;
        }
    }
    public void mousePressed (MouseEvent e) {} //handles when the mouse is pressed
    public void mouseReleased (MouseEvent e) {} //handles when the mouse is released
    public void mouseClicked (MouseEvent e) //handles when the mouse is clicked
    {
        grabFocus();
        xMouse = e.getX();
        yMouse = e.getY();
        if(pauseButton.contains(xMouse,yMouse) && !paused) pauseGame();
        else if(pauseButton.contains(xMouse,yMouse) && paused) resumeGame();
        if(backButton.contains(xMouse,yMouse))
        {
            rightTimer.stop();
            leftTimer.stop();
            jumpTimer.stop();
            shootTimer.stop();
            menuManager.goToMenu();
        }
        grabFocus();
    }
    public void mouseEntered (MouseEvent e) {} //handles when the mouse enters the frame
    public void mouseExited (MouseEvent e) {} //handles when the mouse exits the frame
    public void mouseDragged (MouseEvent e) {} //handles when the mouse is dragged
    public void mouseMoved (MouseEvent e) {} //handles when the mouse is moved
    public void focusGained(FocusEvent e) //brings focus to the card when it focus is on it
    {
        grabFocus();
    }
    public void focusLost(FocusEvent e) //automatically pauses game when focus is lost
    {
        pauseGame();
    }
    public void crouch() //this method is used to crouch when the 's' key is pressed
    {
        characterYVal = 470;
        characterHeight = 80;
        crouching = true;
        repaint();
    }
    public void stopCrouching() //this method is used to stop crouching when the 's' key is released
    {
        characterYVal = 410;
        characterHeight = 140;
        crouching = false;
        repaint();
    }
    public void pauseGame() //this method is used to pause the game when the pause button is pressed
    {
        grabFocus();
        paused = true;
        rightTimer.stop();
        leftTimer.stop();
        jumpTimer.stop();
        shootTimer.stop();
        repaint();
    }
    public void resumeGame() //this method is used to resume the game when the button is pressed again
    {
        grabFocus();
        paused = false;
        if(crouchAfterJump && !jumping) crouch();
        if(!crouchAfterJump && !jumping) stopCrouching();
        if(jumping) jumpTimer.start();
        if(movingRight) rightTimer.start();
        if(!movingRight) rightTimer.stop();
        if(movingLeft) leftTimer.start();
        if(!movingLeft) leftTimer.stop();
        if(shooting) shootTimer.start();
        repaint();
    }
    public void drawBoss1(Graphics g) //this method draws boss 1, when level 1 is being played
    {
        g.drawImage(boss1Normal, 448,120,224,378, null);
    }
    public void drawBoss2(Graphics g) //this method draws boss 2, when level 2 is being played
    {

    }
    public void drawBoss3(Graphics g) //this method draws boss 3, when level 3 is being played
    {

    }
    public void drawBoss4(Graphics g) //this method draws boss 4, when level 4 is being played
    {

    }
    public void drawBoss5(Graphics g) //this method draws boss 5, when level 5 is being played
    {

    }
    public void deathScreen(Graphics g) //this method handles when the character's lives reach 0
    {
        grabFocus();
        g.drawImage(deathScreenImage, 0, 0, 1120, 630, null);
        g.drawImage(exitToMenu, 345, 480 , 430, 49, null);
        backButton = new Rectangle(345, 480 , 430, 49);
    }
    public void winScreen(Graphics g) //this method handles when the boss' health reach 0
    {
        grabFocus();
        //g.drawImage(deathScreenImage, 0, 0, 1120, 630, null);
        //g.drawImage(exitToMenu, 345, 480 , 430, 49, null);
        //backButton = new Rectangle(345, 480 , 430, 49);
    }
    public void exitGame() //this method is used whenever the user goes to the main menu
    {
        bullets = new Bullet[100000];
    }
    public void bossHit() //this method handles when the boss is hit by the player
    {
        bossHealth -= 10;
    }
    class Jumper implements ActionListener //This class activates when 'j' is pressed, and it makes the character jump
    {
        public void actionPerformed(ActionEvent e) //This method activates when 'j' is pressed, and it makes the character jump
        {
            jumping = true;
            jumpNum++;
            if(jumpNum <= 34)
            {
                characterYVal -= 3;
                repaint();
            }
            else if( jumpNum <= 44)
            {
                repaint();
            }
            else if(jumpNum <= 78)
            {
                characterYVal += 3;
                repaint();
            }
            else
            {
                jumpNum = 0;
                jumping = false;
                jumpTimer.stop();
                if(crouchAfterJump) crouch();
            }
            grabFocus();
        }
    }
    class MoveLeft implements ActionListener //This class activates when 'a' is pressed, and it makes the character move left
    {
        public void actionPerformed(ActionEvent e) //This method activates when 'a' is pressed, and it makes the character move left
        {
            if(movingRight) return;
            if(characterXVal > 0) characterXVal -= 3;
            if(crouching) characterXVal += 3;
            repaint();
        }
    }
    class MoveRight implements ActionListener //This class activates when 'd' is pressed, and it makes the character move right
    {
        public void actionPerformed(ActionEvent e) //This method activates when 'd' is pressed, and it makes the character move right
        {
            if(movingLeft) return;
            if(characterXVal < 1120 - characterWidth) characterXVal += 3;
            if(crouching) characterXVal -= 3;
            repaint();
        }
    }
    class Dash implements ActionListener //This class activates when 'l' is pressed, and it makes the character dash either right or left
    {
        public void actionPerformed(ActionEvent e) //This method activates when 'l' is pressed, and it makes the character dash either right or left
        {

            dashing = true;
            dashNum++;
            if(dashNum <= 10)
            {
                if(characterXVal > 1120 - characterWidth || characterXVal < 0);
                else if(facingLeft)
                {
                    characterXVal -= 7;
                    dashingLeft = true;
                }
                else if(facingRight)
                {
                    characterXVal += 7;
                    dashingRight = true;
                }
            }
            else
            {
                dashNum = 0;
                dashing = false;
                dashTimer.stop();
                grabFocus();
                if(jumping) jumpTimer.start();
                dashingLeft = false;
                dashingRight = false;
            }
            repaint();
        }
    }
    class Shoot implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            bullets[bulletCount] = new Bullet(characterXVal, characterYVal, movingLeft, movingRight, facingUp, facingLeft, facingRight, characterHeight, characterWidth);
            bulletCount++;
        }
    }
    class Bullet //this Class is used to load each individual bullet
    {
        int x, y;
        int charHeight, charWidth;
        boolean movingLeft, movingUp, movingRight, lookingLeft, lookingRight;
        boolean finished;
        public Bullet(int xVal, int yVal, boolean left, boolean right, boolean up, boolean facingLeft, boolean facingRight, int height, int width) //this constructors gets information about the user's placement and direction
        {
            x = xVal;
            y = yVal;
            movingLeft = left;
            movingRight = right;
            movingUp = up;
            lookingLeft = facingLeft;
            lookingRight = facingRight;
            charHeight = height;
            charWidth = width;
            finished = false;
        }
        public void paintBullet(Graphics g) //this method creates the bullet and sets which image to use based on the direction
        {
            if(finished) return;

            if(!movingUp && lookingRight) g.drawImage(bulletRight, x+charWidth, y + (int)(2*charHeight/5), 70, 25, null);
            else if(!movingUp && lookingLeft) g.drawImage(bulletLeft, x-70, y + (int)(2*charHeight/5), 70, 25, null);
            else if(movingUp && lookingLeft && !movingLeft && !movingRight) g.drawImage(bulletUp, x, y-50, 26, 70, null);
            else if(movingUp && lookingRight && !movingLeft && !movingRight) g.drawImage(bulletUp, x + (2*charWidth/3), y-50, 26, 70, null);
            else if(movingUp && movingRight) g.drawImage(bulletUpRight, x+charWidth, y, 50, 50, null);
            else if(movingUp && movingLeft) g.drawImage(bulletUpLeft, x-50, y, 50, 50, null);
        }
        public void moveBullet() //this method makes each bullet move in whatever direction it is headed in
        {
            if(x < 0 || x > 1120 || y < 0 || y > 630) finished = true;

            if(finished) return;
            if(!movingUp && lookingRight)
            {
                x += 4;
                /*
                if(bossHitBox.intersects(new Rectangle(x+charWidth, y + (int)(2*charHeight/5), 70, 25)))
                {
                    finished = true;
                    bossHit();
                }
                */
            }
            else if(!movingUp && lookingLeft)
            {
                x -= 4;
            }
            else if(movingUp && !movingRight && !movingLeft)
            {
                y -= 4;
            }
            else if(movingUp && movingRight)
            {
                x += 2;
                y -= 2;
            }
            else if(movingUp && movingLeft)
            {
                x -= 2;
                y -= 2;
            }
        }
    }
    class BulletMover implements ActionListener //This class runs on a timer to move each bullet
    {
        public void actionPerformed(ActionEvent e) //This method runs on a timer to move each bullet
        {
            for(int i = 0; i < bulletCount; i++)
            {
                if(paused || i >= bulletCount) return;
                bullets[i].moveBullet();
            }
            repaint();
        }
    }
}
