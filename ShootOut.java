/*
Rohan Singh
ShootOut.java
This game is a boss rush styled shooter
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShootOut //This class makes the JFrame and runs the panels
{
    public static void main(String[] args) //The main
    {
        ShootOut game = new ShootOut();
        game.createAndShowGUI();
    }
    public void createAndShowGUI() //Creates the JFrame and sets its properties
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
    JPanel cards, mainCard, levelsCard, powerUpsCard, characterSelectCard, instructionsCard;
    BossCardPanel bossCard;
    JLabel titleLabel;
    JButton levelButton, powerUpsButton, characterSelectButton, instructionButton;
    JCheckBox safety, easy, normal, hard, merciless, char1, char2, char3;
    Image levelImage, powerUpImage, characterSelectImage, instructionsImage, backArrowImage, mainBackground, instructionsBackground;
    int lives, bossNum, charNum;
    MenuManager menuManager;

    public ShootOutGame() //constructor that sets the initial values of variables
    {
        lives = 10;
        charNum = 1;
        levelImage = new ImageIcon("Levels.png").getImage();
        powerUpImage = new ImageIcon("PowerUps.png").getImage();
        characterSelectImage = new ImageIcon("SelectCharacter.png").getImage();
        instructionsImage = new ImageIcon("Instructions.png").getImage();
        backArrowImage = new ImageIcon("back_arrow.png").getImage();
        mainBackground = new ImageIcon("ShootOutBackground.jpeg").getImage();
        instructionsBackground = new ImageIcon("InstructionsBackground.jpg").getImage();
    }

    public void addComponentToPane(Container pane) //makes the panels for the menus and organizes them
    {
        buildMenuCard();

        buildLevelCard();

        buildPowerUpCard();

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
    class MainPanel extends JPanel //this class is used to set the background image of the main panel
    {

        Image backgroundImage;
        public MainPanel(Image img) //this constructor is used to bring the image into the class and set its dimension and size
        {
            backgroundImage = img;
            Dimension size = new Dimension(1120,630);
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
        }

        public void paintComponent(Graphics g) //this method draws the background image
        {
            g.drawImage(backgroundImage, 0, 0, 1120, 630, null);
        }
    }
    public void buildMenuCard() //this method makes the menu card with the buttons and background image
    {
        mainCard = new MainPanel(mainBackground);
        mainCard.setVisible(true);
        mainCard.setLayout(null);

        titleLabel = new JLabel("Shoot-Out!");
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 70));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(710, 100, 360, 75);

        levelButton = new JButton(new ImageIcon(levelImage.getScaledInstance(300,75,Image.SCALE_DEFAULT), ""));
        levelButtonHandler levelHandler = new levelButtonHandler();
        levelButton.addActionListener(levelHandler);
        levelButton.setBounds(700, 175, 300, 75);

        powerUpsButton = new JButton(new ImageIcon(powerUpImage.getScaledInstance(300,75,Image.SCALE_DEFAULT), ""));
        powerUpButtonHandler powerUpHandler = new powerUpButtonHandler();
        powerUpsButton.addActionListener(powerUpHandler);
        powerUpsButton.setBounds(660, 250, 300, 75);

        characterSelectButton = new JButton(new ImageIcon(characterSelectImage.getScaledInstance(300,75,Image.SCALE_DEFAULT), ""));
        characterSelectButtonHandler characterSelectHandler = new characterSelectButtonHandler();
        characterSelectButton.addActionListener(characterSelectHandler);
        characterSelectButton.setBounds(620, 325, 300, 75);

        instructionButton = new JButton(new ImageIcon(instructionsImage.getScaledInstance(300,75,Image.SCALE_DEFAULT), ""));
        instructionButtonHandler instructionHandler = new instructionButtonHandler();
        instructionButton.addActionListener(instructionHandler);
        instructionButton.setBounds(565, 400, 300, 75);

        mainCard.add(titleLabel);
        mainCard.add(levelButton);
        mainCard.add(powerUpsButton);
        mainCard.add(characterSelectButton);
        mainCard.add(instructionButton);
    }
    class LevelsPanel extends JPanel //this class is used to set the background image of the levels panel
    {

        Image backgroundImage;
        public LevelsPanel(Image img) //this constructor is used to bring the image into the class and set its dimension and size
        {
            backgroundImage = img;
        }

        public void paintComponent(Graphics g) //this method draws the background image
        {
            g.drawImage(backgroundImage, 0, 0, 1120, 610, null);
        }
    }
    public void buildLevelCard() // this method makes the level card with all the levels and difficulties
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
    class PowerUpPanel extends JPanel //this class is used to set the background image of the power ups panel
    {

        Image backgroundImage;
        public PowerUpPanel(Image img) //this constructor is used to bring the image into the class and set its dimension and size
        {
            backgroundImage = img;
        }

        public void paintComponent(Graphics g) //this method draws the background image
        {
            g.drawImage(backgroundImage, 0, 0, 1120, 610, null);
        }
    }

    public void buildPowerUpCard() //this method makes the power up card
    {
        powerUpsCard = new JPanel();
        //powerUpsCard = new PowerUpPanel(instructionsBackground);
        powerUpsCard.setVisible(true);
        powerUpsCard.setLayout(null);

        JButton backButton = new JButton(new ImageIcon(backArrowImage.getScaledInstance(60,60, Image.SCALE_DEFAULT), ""));
        backButton.setPreferredSize(new Dimension(60,60));
        BackButtonHandler backButtonHandler = new BackButtonHandler();
        backButton.setBounds(530,15,60,60);
        backButton.addActionListener(backButtonHandler);
        powerUpsCard.add(backButton);
    }
    class CharacterSelectPanel extends JPanel //this class is used to set the background image of the character select panel
    {

        Image backgroundImage;
        public CharacterSelectPanel(Image img) //this constructor is used to bring the image into the class and set its dimension and size
        {
            backgroundImage = img;
        }

        public void paintComponent(Graphics g) //this method draws the background image
        {
            g.drawImage(backgroundImage, 0, 0, 1120, 610, null);
        }
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

    class InstructionPanel extends JPanel //this class is used to set the background image of the instructions panel
    {

        Image backgroundImage;
        public InstructionPanel(Image img) //this constructor is used to bring the image into the class and set its dimension and size
        {
            backgroundImage = img;
        }

        public void paintComponent(Graphics g) //this method draws the background image
        {
            g.drawImage(backgroundImage, 0, 0, 1120, 610, null);
        }
    }

    public void buildInstructionsCard() //this method makes the instructions card
    {
        instructionsCard = new InstructionPanel(instructionsBackground);
        instructionsCard.setVisible(true);
        instructionsCard.setLayout(null);

        JButton backButton = new JButton(new ImageIcon(backArrowImage.getScaledInstance(60,60, Image.SCALE_DEFAULT), ""));
        backButton.setPreferredSize(new Dimension(60,60));
        BackButtonHandler backButtonHandler = new BackButtonHandler();
        backButton.setBounds(530,15,60,60);
        backButton.addActionListener(backButtonHandler);
        instructionsCard.add(backButton);
    }

    public void actionPerformed(ActionEvent evt) {} //this method handles input through buttons pressed

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
    class CheckBoxListener implements ActionListener //this class makes the checkboxes used to select difficulty and character
    {
        public void actionPerformed(ActionEvent e) //this method makes the checkboxes used to select difficulty and character
        {
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
    int boss2Num, boss3Num;
    int[] boss3Array;
    Image healthBarImage, bossHealthBarImage, pauseIconImage, playIconImage, backArrowImage, winScreenImage, deathScreenImage, exitDeath, exitWin;
    Image char1DuckingRight, char1DuckingLeft, char1NormalRight, char1NormalLeft, char1NormalUpRight, char1NormalUpLeft, char1NormalDiagonalRight, char1NormalDiagonalLeft, char1RunningRight, char1RunningLeft, char1RunningUpRight, char1RunningUpLeft;
    Image char2DuckingRight, char2DuckingLeft, char2NormalRight, char2NormalLeft, char2RunningRight, char2RunningLeft;
    Image char3DuckingRight, char3DuckingLeft, char3NormalRight, char3NormalLeft, char3RunningRight, char3RunningLeft;
    Image bulletLeft, bulletUpLeft, bulletUp, bulletUpRight, bulletRight;
    Image boss1Background, boss2Background, boss3Background, boss4Background, boss5Background;
    Image boss1Normal;
    Image boss2NormalLeft, boss2NormalRight, boss2AttackingLeft, boss2AttackingRight, boss2AboutToAttackLeft, boss2AboutToAttackRight;
    Image boss3Left, boss3Right;
    Image boss4Normal, boss4Attacking;
    Image boss5Normal, boss5SharkLeft, boss5SharkRight;
    Image boss2Projectile;
    Image boss3Projectile;
    Image boss4ProjectileLeft, boss4ProjectileRight, boss4ProjectileDown, boss4ProjectileUp, boss4ProjectileUpRight, boss4ProjectileUpLeft, boss4ProjectileDownRight, boss4ProjectileDownLeft;
    Jumper jumper;
    MoveLeft leftMover;
    MoveRight rightMover;
    Dash dasher;
    Shoot shooter;
    Invincible invincibility;
    Attacking attack;
    Timer jumpTimer, leftTimer, rightTimer, dashTimer, bulletMover, shootTimer, invincibilityTimer, attackTimer;
    Boss1 boss1;
    Boss2 boss2;
    Boss3 boss3;
    Boss4 boss4;
    Boss5 boss5;
    Timer boss1Timer, boss2Timer, boss3Timer, boss4Timer, boss5Timer;
    boolean crouching, movingRight, movingLeft, paused, jumping, crouchAfterJump, facingRight, facingLeft, dashing, dashingRight, dashingLeft, facingUp, shooting, invincible, attacking, aboutToAttack;
    boolean leftOfBoss2, rightOfBoss2;
    Rectangle pauseButton, backButton, charHitBox;
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
        winScreenImage = new ImageIcon("youWin.jpeg").getImage();
        exitDeath = new ImageIcon("exitToMenu.jpeg").getImage();
        exitWin = new ImageIcon("exitButtonWin.png").getImage();

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

        boss2NormalLeft = new ImageIcon("boss2NormalLeft.png").getImage();
        boss2NormalRight = new ImageIcon("boss2NormalRight.png").getImage();
        boss2AttackingLeft = new ImageIcon("boss2AttackingLeft.png").getImage();
        boss2AttackingRight = new ImageIcon("boss2AttackingRight.png").getImage();
        boss2AboutToAttackLeft = new ImageIcon("boss2AboutToAttackLeft.png").getImage();
        boss2AboutToAttackRight = new ImageIcon("boss2AboutToAttackRight.png").getImage();

        boss3Left = new ImageIcon("boss3Left.png").getImage();
        boss3Right = new ImageIcon("boss3Right.png").getImage();

        boss4Normal = new ImageIcon("boss4Normal.png").getImage();
        boss4Attacking = new ImageIcon("boss4Attacking.png").getImage();

        boss5Normal = new ImageIcon("boss5Normal.png").getImage();
        boss5SharkLeft = new ImageIcon("boss5SharkLeft.png").getImage();
        boss5SharkRight = new ImageIcon("boss5SharkRight.png").getImage();

        boss2Projectile = new ImageIcon("boss2Projectile.png").getImage();

        boss3Projectile = new ImageIcon("boss3Projectile.png").getImage();

        boss4ProjectileLeft = new ImageIcon("boss4ProjectileLeft.png").getImage();
        boss4ProjectileRight = new ImageIcon("boss4ProjectileRight.png").getImage();
        boss4ProjectileDown = new ImageIcon("boss4ProjectileDown.png").getImage();
        boss4ProjectileUp = new ImageIcon("boss4ProjectileUp.png").getImage();
        boss4ProjectileUpRight = new ImageIcon("boss4ProjectileUpRight.png").getImage();
        boss4ProjectileUpLeft = new ImageIcon("boss4ProjectileUpLeft.png").getImage();
        boss4ProjectileDownRight = new ImageIcon("boss4ProjectileDownRight.png").getImage();
        boss4ProjectileDownLeft = new ImageIcon("boss4ProjectileDownLeft.png").getImage();

        int[] xValuesPlaceholder = {-1, -2, -3};
        int[] yValuesPlaceholder = {-1, -2, -3};
        bossHitBox = new Polygon(xValuesPlaceholder, yValuesPlaceholder,3);

        jumpDelay = 10;
        characterXVal = 90;
        characterYVal = 410;
        characterWidth = 100;
        characterHeight = 140;
        jumpNum = 0;
        dashNum = 0;
        liveCount = 10;
        boss2Num = 0;
        boss3Num = 2;
        boss3Array = new int[9];
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
        invincibility = new Invincible();
        invincibilityTimer = new Timer(500, invincibility);
        attack = new Attacking();
        attackTimer = new Timer(2000, attack);

        boss1 = new Boss1();
        boss1Timer = new Timer(4000, boss1);
        boss2 = new Boss2();
        boss2Timer = new Timer(800, boss2);
        boss3 = new Boss3();
        boss3Timer = new Timer(600, boss3);
        boss4 = new Boss4();
        boss4Timer = new Timer(4000, boss4);
        boss5 = new Boss5();
        boss5Timer = new Timer(4000, boss5);

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
        invincible = false;
        attacking = false;
        aboutToAttack = false;
        leftOfBoss2 = true;
        rightOfBoss2 = false;
        menuManager = menu;

    }
    public void resetGame(int lives, int whichBoss, int whichCharacter) //this method is used to reset the variables everytime the user enters the game
    {
        jumpDelay = 10;
        characterXVal = 90;
        characterYVal = 410;
        if(whichBoss == 5) characterYVal = 390;
        characterWidth = 100;
        characterHeight = 140;
        jumpNum = 0;
        bullets = new Bullet[100000];
        bulletCount = 0;
        boss2Num = 0;
        boss3Num = 2;
        boss3Array = new int[9];
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
        shooting = false;
        invincible = false;
        attacking = false;
        aboutToAttack = false;
        leftOfBoss2 = true;
        rightOfBoss2 = false;
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
            bossHealth = 1700;
            startingBossHealth = 1700;
            int[] xValues = {930, 750, 730, 810, 850, 870, 820, 820, 780, 840, 845, 890, 930, 980, 980, 1000, 1010, 970, 980, 950, 920};
            int[] yValues = {260, 280, 290, 350, 350, 380, 430, 530, 540, 540, 480, 440, 480, 520, 540, 540, 510, 460, 400, 310, 290};
            bossHitBox = new Polygon(xValues, yValues, xValues.length);
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
    public void paintComponent(Graphics g) //this creates the objects inside the frame such as the boss, main character, background, health bar, pause button, and exit button
    {
        super.paintComponent(g);

        if(liveCount <= 0)
        {
            deathScreen(g);
            return;
        }

        if(bossHealth <= 0)
        {
            winScreen(g);
            return;
        }

        if(bossNum == 1)
        {
            g.drawImage(boss1Background, 0, 0, 1120, 630, null);
            drawBoss1(g);
            if(!paused) boss1Timer.start();
        }
        else if(bossNum == 2)
        {
            g.drawImage(boss2Background, 0, 0, 1120, 630, null);
            if(attacking) drawBoss2Attacking(g);
            else if(aboutToAttack) drawBoss2AboutToAttack(g);
            else drawBoss2Normal(g);
            if(!paused) boss2Timer.start();
            if(attacking)
            {
                Rectangle boss2;
                if(leftOfBoss2) boss2 = new Rectangle(50,400,560,237);
                else boss2 = new Rectangle(510,400,560,237);
                if(charHitBox.intersects(boss2) && !invincible && !paused) charHit();
            }

            if(attacking)
            {
                if(leftOfBoss2)
                {
                    int[] xValues = {610, 50, 50, 560};
                    int[] yValues = {450, 480, 630, 600};
                    bossHitBox = new Polygon(xValues, yValues, xValues.length);
                    g.fillPolygon(xValues, yValues, xValues.length);
                }
                else
                {
                    int[] xValues = {510, 1150, 1150, 540};
                    int[] yValues = {450, 480, 630, 600};
                    bossHitBox = new Polygon(xValues, yValues, xValues.length);
                    g.fillPolygon(xValues, yValues, xValues.length);
                }
            }
            if(aboutToAttack)
            {
                if(leftOfBoss2)
                {
                    int[] xValues = {};
                    int[] yValues = {};
                    bossHitBox = new Polygon(xValues, yValues, xValues.length);
                }
                else
                {
                    int[] xValues = {};
                    int[] yValues = {};
                    bossHitBox = new Polygon(xValues, yValues, xValues.length);
                }
            }
            else
            {
                if(characterXVal < 560 - characterWidth)
                {
                    int[] xValues = {};
                    int[] yValues = {};
                    bossHitBox = new Polygon(xValues, yValues, xValues.length);
                }
                else
                {
                    int[] xValues = {};
                    int[] yValues = {};
                    bossHitBox = new Polygon(xValues, yValues, xValues.length);
                }
            }
        }
        else if(bossNum == 3)
        {
            g.drawImage(boss3Background, 0, 0, 1120, 630, null);
            drawBoss3(g);
            if(!paused) boss3Timer.start();
        }
        else if(bossNum == 4)
        {
            if(!attacking)
            {
                int[] xValues = {560, 480, 448, 460, 495, 520, 530, 590, 623, 650, 668, 640, 635};
                int[] yValues = {120, 190, 240, 280, 300, 360, 460, 460, 310, 270, 230, 180, 170};
                bossHitBox = new Polygon(xValues, yValues, 13);
            }
            else
            {
                int[] xValues = {554, 500, 505, 450, 510, 530, 590, 606, 662, 610, 613};
                int[] yValues = {130, 220, 310, 410, 320, 460, 460, 310, 410, 320, 220};
                bossHitBox = new Polygon(xValues, yValues, 11);

            }
            g.drawImage(boss4Background, 0, 0, 1120, 630, null);
            drawBoss4(g);
            if(!paused) boss4Timer.start();
        }
        else
        {
            g.drawImage(boss5Background, 0, 0, 1120, 630, null);
            drawBoss5(g);
            if(!paused) boss5Timer.start();
        }

        charHitBox = new Rectangle(characterXVal, characterYVal, characterWidth, characterHeight);
        if(characterNum == 3 && !dashingRight && !dashingLeft && !movingRight && !movingLeft) charHitBox = new Rectangle(characterXVal, characterYVal, (int)(characterWidth/1.2), characterHeight);

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

        /*
        String xval = "Character X-Value: " + characterXVal;
        String yval = "Character Y-Value: " + characterYVal;
        g.drawString(xval, 50, 100);
        g.drawString(yval, 50, 130);
        */

        paintBullets(g);
    }

    public void drawChar(Graphics g) //this method draws the character being played as
    {
        if(characterNum == 1)
        {
            if(paused && facingRight && !crouching) g.drawImage(char1NormalRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(paused && facingLeft && !crouching) g.drawImage(char1NormalLeft, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(paused && facingRight && crouching) g.drawImage(char1DuckingRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
            else if(paused && facingLeft && crouching) g.drawImage(char1DuckingLeft, characterXVal, characterYVal, characterWidth, characterHeight, null);
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

    public void keyTyped(KeyEvent e) {} //Used to handle when a key is typed
    
    public void keyPressed(KeyEvent e)  //Used to handle when a key is pressed and allow the user to move around
    {
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
    public void keyReleased(KeyEvent e) //handles when a key is released to stop moving
    {
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
            boss1Timer.stop();
            boss2Timer.stop();
            boss3Timer.stop();
            boss4Timer.stop();
            boss5Timer.stop();
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
        invincibilityTimer.stop();
        boss1Timer.stop();
        boss2Timer.stop();
        boss3Timer.stop();
        boss4Timer.stop();
        boss5Timer.stop();
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
        if(bossNum == 1) boss1Timer.start();
        if(bossNum == 2) boss2Timer.start();
        if(bossNum == 3) boss3Timer.start();
        if(bossNum == 4) boss4Timer.start();
        if(bossNum == 5) boss5Timer.start();
        repaint();
    }
    public void paintBullets(Graphics g) //this method loads and paints every bullet that has been loaded
    {
        for(int i = 0; i < bulletCount; i++)
        {
            if(i >= bulletCount) return;
            bullets[i].paintBullet(g);
        }
    }
    public void drawBoss1(Graphics g) //this method draws boss 1, when level 1 is being played
    {
        g.drawImage(boss1Normal, 700,107,400,393, null);
    }
    public void drawBoss2Normal(Graphics g)  //this method draws boss 2 normally
    {
        if(characterXVal < 560 - characterWidth)
        {
            g.drawImage(boss2NormalLeft, 460,184,200,406, null);
            int[] xValues = {};
            int[] yValues = {};
            bossHitBox = new Polygon(xValues, yValues, xValues.length);
        }
        else
        {
            g.drawImage(boss2NormalRight, 460,184,200,406, null);
            int[] xValues = {};
            int[] yValues = {};
            bossHitBox = new Polygon(xValues, yValues, xValues.length);
        }
    }
    public void drawBoss2Attacking(Graphics g)  //this method draws boss 2 attacking
    {
        if(leftOfBoss2)
        {
            g.drawImage(boss2AttackingLeft, 50,443,560,187, null);
        }
        else
        {
            g.drawImage(boss2AttackingRight, 510,443,560,187, null);
        }
    }
    public void drawBoss2AboutToAttack(Graphics g)  //this method draws boss 2, when it is about to attack
    {
        if(leftOfBoss2)
        {
            g.drawImage(boss2AboutToAttackLeft, 420,310,280,280, null);
        }
        else
        {
            g.drawImage(boss2AboutToAttackRight, 420,310,280,280, null);
        }
    }
    public void drawBoss3(Graphics g) //this method draws boss 3, when level 3 is being played
    {
        g.drawImage(boss3Left,730, 250, 290, 300, null);
    }
    public void drawBoss4(Graphics g) //this method draws boss 4, when level 4 is being played
    {
        if(!attacking) g.drawImage(boss4Normal, 448,120,224,378, null);
        else g.drawImage(boss4Attacking, 450,130,219,368, null);
    }
    public void drawBoss5(Graphics g) //this method draws boss 5, when level 5 is being played
    {
        g.drawImage(boss5Normal,767, 162, 253, 358, null);
    }
    public void deathScreen(Graphics g) //this method handles when the character's lives reach 0
    {
        grabFocus();
        g.drawImage(deathScreenImage, 0, 0, 1120, 630, null);
        g.drawImage(exitDeath, 345, 480 , 430, 49, null);
        backButton = new Rectangle(345, 480 , 430, 49);
    }
    public void winScreen(Graphics g) //this method handles when the boss' health reach 0
    {
        grabFocus();
        invincible = true;
        g.drawImage(winScreenImage, 0, 0, 1120, 630, null);
        g.drawImage(exitWin, 373, 450 , 325, 107, null);
        backButton = new Rectangle(373, 450 , 325, 107);
    }
    public void bossHit() //this method handles when the boss is hit by the player
    {
        bossHealth -= 10;
        repaint();
    }
    public void charHit()  //this method handles when the player is hit by the boss
    {
        liveCount--;
        invincible = true;
        invincibilityTimer.start();
        repaint();
    }
    class Jumper implements ActionListener //This class activates when 'j' is pressed, and it makes the character jump
    {
        public void actionPerformed(ActionEvent e) //This method activates when 'j' is pressed, and it makes the character jump
        {
            jumping = true;
            jumpNum++;
            if(jumpNum <= 34)
            {
                if(jumpNum % 2 == 1) characterYVal -= 4;
                else characterYVal -= 3;
                repaint();
            }
            else if( jumpNum <= 44)
            {
                repaint();
            }
            else if(jumpNum <= 78)
            {
                if(jumpNum % 2 == 1) characterYVal += 4;
                else characterYVal += 3;
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
    class Shoot implements ActionListener //This class is set on a timer to let the player shoot at a consistent rate
    {
        public void actionPerformed(ActionEvent e) //This method is set on a timer to let the player shoot at a consistent rate
        {
            bullets[bulletCount] = new Bullet(characterXVal, characterYVal,0, movingLeft, movingRight, facingUp, false, facingLeft, facingRight, characterHeight, characterWidth);
            bulletCount++;
        }
    }
    class Bullet //this Class is used to load each individual bullet
    {
        int x, y;
        int charHeight, charWidth, whichBoss;
        boolean movingLeft, movingUp, movingRight, lookingLeft, lookingRight, movingDown;
        boolean finished;
        Rectangle bossProjectile;
        public Bullet(int xVal, int yVal, int boss, boolean left, boolean right, boolean up, boolean down, boolean facingLeft, boolean facingRight, int height, int width) //this constructors gets information about the user's placement and direction
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
            whichBoss = boss;
            movingDown = down;
        }
        public void paintBullet(Graphics g) //this method creates the bullet and sets which image to use based on the direction
        {
            if(finished) return;

            if(whichBoss == 0)
            {
                if(!movingUp && lookingRight) g.drawImage(bulletRight, x+charWidth, y + (int)(2*charHeight/5), 70, 25, null);
                else if(!movingUp && lookingLeft) g.drawImage(bulletLeft, x-70, y + (int)(2*charHeight/5), 70, 25, null);
                else if(characterNum == 1 && movingUp && lookingLeft && !movingLeft && !movingRight) g.drawImage(bulletUp, x, y-50, 26, 70, null);
                else if(characterNum == 1 && movingUp && lookingRight && !movingLeft && !movingRight) g.drawImage(bulletUp, x + (2*charWidth/3), y-50, 26, 70, null);
                else if(characterNum == 2 && movingUp && !movingLeft && !movingRight) g.drawImage(bulletUp, x + (int)(characterWidth/3), y-50, 26, 70, null);
                else if(characterNum == 3 && movingUp && !movingLeft && !movingRight) g.drawImage(bulletUp, x + (int)(characterWidth/3), y-50, 26, 70, null);
                else if(movingUp && movingRight) g.drawImage(bulletUpRight, x+charWidth, y, 50, 50, null);
                else if(movingUp && movingLeft) g.drawImage(bulletUpLeft, x-50, y, 50, 50, null);
            }
            else if(whichBoss == 1)
            {

            }
            else if(whichBoss == 2)
            {

            }
            else if(whichBoss == 3)
            {
                g.drawImage(boss3Projectile, x, y, charWidth, charHeight, null);
            }
            else if(whichBoss == 4)
            {
                if(movingUp && !movingRight && !movingLeft) g.drawImage(boss4ProjectileUp, x - charWidth/2, y - charHeight/2, charWidth, charHeight, null);
                else if(movingUp && movingRight) g.drawImage(boss4ProjectileUpRight, x, y - charHeight/2, charWidth, charHeight, null);
                else if(movingRight && !movingDown && !movingUp) g.drawImage(boss4ProjectileRight, x, y - charHeight/2, charWidth, charHeight, null);
                else if(movingDown && movingRight) g.drawImage(boss4ProjectileDownRight, x, y, charWidth, charHeight, null);
                else if(movingDown && !movingLeft && !movingRight) g.drawImage(boss4ProjectileDown, x - charWidth/2, y, charWidth, charHeight, null);
                else if(movingDown && movingLeft) g.drawImage(boss4ProjectileDownLeft, x - charWidth/2, y, charWidth, charHeight, null);
                else if(movingLeft && !movingDown && !movingUp) g.drawImage(boss4ProjectileLeft, x - charWidth/2, y, charWidth, charHeight, null);
                else if(movingLeft && movingUp) g.drawImage(boss4ProjectileUpLeft, x - charWidth/2, y - charWidth/2, charWidth, charHeight, null);
            }
            else
            {

            }
        }
        public void moveBullet() //this method makes each bullet move in whatever direction it is headed in
        {
            if(x < 0 || x > 1120 || y < 0 || y > 750) finished = true;

            //if(!playerOwned && (y < 0 || y > characterYVal + characterHeight)) finished = true;

            if(whichBoss == 1)
            {

            }
            else if(whichBoss == 2)
            {

            }
            else if(whichBoss == 3)
            {
                x -= 4;
                bossProjectile = new Rectangle(x, y + 10, charWidth - 30, charHeight - 20);
                if(bossProjectile.intersects(charHitBox) && !invincible) charHit();
            }
            else if(whichBoss == 4)
            {
                if(movingUp && !movingRight && !movingLeft)
                {
                    y -= 5;
                    bossProjectile = new Rectangle(x - charWidth/2, y - charHeight/2, charWidth, charHeight);
                }
                else if(movingUp && movingRight)
                {
                    x += 3;
                    y -= 3;
                    bossProjectile = new Rectangle(x, y - charHeight/2, charWidth, charHeight);
                }
                else if(movingRight && !movingDown && !movingUp)
                {
                    x += 5;
                    bossProjectile = new Rectangle(x, y - charHeight/2, charWidth, charHeight);
                }
                else if(movingDown && movingRight)
                {
                    x += 3;
                    y += 3;
                    bossProjectile = new Rectangle(x, y, charWidth, charHeight);
                }
                else if(movingDown && !movingLeft && !movingRight)
                {
                    y += 5;
                    bossProjectile = new Rectangle(x - charWidth/2, y, charWidth, charHeight);
                }
                else if(movingDown && movingLeft)
                {
                    y += 3;
                    x -= 3;
                    bossProjectile = new Rectangle(x - charWidth/2, y, charWidth, charHeight);
                }
                else if(movingLeft && !movingDown && !movingUp)
                {
                    x -= 5;
                    bossProjectile = new Rectangle(x - charWidth/2, y, charWidth, charHeight);
                }
                else if(movingLeft && movingUp)
                {
                    x -= 3;
                    y -= 3;
                    bossProjectile = new Rectangle(x - charWidth/2, y - charWidth/2, charWidth, charHeight);
                }
                if(bossProjectile.intersects(charHitBox) && !invincible) charHit();
            }
            else
            {

            }

            if(!(whichBoss == 0)) return;

            if(finished) return;
            if(!movingUp && lookingRight)
            {
                x += 4;
                if(bossHitBox.intersects(new Rectangle(x+charWidth, y + (int)(2*charHeight/5), 70, 25)))
                {
                    finished = true;
                    bossHit();
                }

            }
            else if(!movingUp && lookingLeft)
            {
                x -= 4;
                if(bossHitBox.intersects(new Rectangle(x-70, y + (int)(2*charHeight/5), 70, 25)))
                {
                    finished = true;
                    bossHit();
                }
            }
            else if(movingUp && !movingRight && !movingLeft)
            {
                y -= 4;

                if(lookingLeft)
                {
                    if(bossHitBox.intersects(new Rectangle(x, y-50, 26, 70)))
                    {
                        finished = true;
                        bossHit();
                    }
                }
                else if(lookingRight)
                {
                    if(bossHitBox.intersects(new Rectangle(x + (2*charWidth/3), y-50, 26, 70)))
                    {
                        finished = true;
                        bossHit();
                    }
                }
            }
            else if(movingUp && movingRight)
            {
                x += 2;
                y -= 2;
                if(bossHitBox.intersects(new Rectangle(x+charWidth, y, 50, 50)))
                {
                    finished = true;
                    bossHit();
                }
            }
            else if(movingUp && movingLeft)
            {
                x -= 2;
                y -= 2;
                if(bossHitBox.intersects(new Rectangle(x-50, y, 50, 50)))
                {
                    finished = true;
                    bossHit();
                }
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
    class Invincible implements ActionListener //this class activates after the player is hit and gives them temporary invincibility
    {
        public void actionPerformed(ActionEvent e) //this method activates after the player is hit and gives them temporary invincibility
        {
            invincible = false;
            invincibilityTimer.stop();
        }
    }
    class Attacking implements ActionListener //this class works to change the boss image when it is attacking
    {
        public void actionPerformed(ActionEvent e) //this method works to change the boss image when it is attacking
        {
            attacking = false;
            attackTimer.stop();
        }
    }
    class Boss1 implements ActionListener //this class is set on a timer to allow the boss to attack at a consistent rate
    {
        public void actionPerformed(ActionEvent e) //this method is set on a timer to allow the boss to attack at a consistent rate
        {

        }
    }
    class Boss2 implements ActionListener //this class is set on a timer to allow the boss to attack at a consistent rate
    {
        public void actionPerformed(ActionEvent e) //this method is set on a timer to allow the boss to attack at a consistent rate
        {
            boss2Num++;
            if(boss2Num < 6);
            else if(boss2Num == 6)
            {
                if(characterXVal < 560 - characterWidth)
                {
                    leftOfBoss2 = true;
                    rightOfBoss2 = false;
                }
                else
                {
                    leftOfBoss2 = false;
                    rightOfBoss2 = true;
                }
            }
            else if(boss2Num < 9)
            {
                attacking = false;
                aboutToAttack = true;
            }
            else if(boss2Num < 12)
            {
                attacking = true;
                aboutToAttack = false;
            }
            else
            {
                attacking = false;
                aboutToAttack = false;
                boss2Num = 0;
                if(characterXVal < 560 - characterWidth)
                {
                    leftOfBoss2 = true;
                    rightOfBoss2 = false;
                }
                else
                {
                    leftOfBoss2 = false;
                    rightOfBoss2 = true;
                }
            }
            repaint();
        }
    }
    class Boss3 implements ActionListener //this class is set on a timer to allow the boss to attack at a consistent rate
    {
        public void actionPerformed(ActionEvent e) //this method is set on a timer to allow the boss to attack at a consistent rate
        {
            boss3Num++;
            if(boss3Num < 7);
            else if(boss3Num == 7)
            {
                for(int i = 0; i < 9; i++)
                {
                    boss3Array[i] = 0;
                }
                int firstSeparation, secondSeparation, thirdSeparation, fourthSeparation;
                firstSeparation = (int)(Math.random()*2);
                secondSeparation = (int)(Math.random()*2 + 3);
                thirdSeparation = secondSeparation;
                while(secondSeparation == thirdSeparation)
                {
                    thirdSeparation = (int)(Math.random()*2 + 4);
                }
                fourthSeparation = (int)(Math.random()*2 + 7);

                boss3Array[firstSeparation] = 1;
                boss3Array[secondSeparation] = 1;
                boss3Array[thirdSeparation] = 1;
                boss3Array[fourthSeparation] = 1;
            }
            else if(boss3Num == 8 && !(boss3Array[0] == 1))
            {
                bullets[bulletCount] = new Bullet(730, 441, 3, false, false, false, false, false, false, 109, 176);
                bulletCount++;
            }
            else if(boss3Num == 9 && !(boss3Array[1] == 1))
            {
                bullets[bulletCount] = new Bullet(730, 346, 3, false, false, false, false, false, false, 109, 176);
                bulletCount++;
            }
            else if(boss3Num == 10 && !(boss3Array[2] == 1))
            {
                bullets[bulletCount] = new Bullet(730, 250, 3, false, false, false, false, false, false, 109, 176);
                bulletCount++;
            }
            else if(boss3Num == 11 && !(boss3Array[3] == 1))
            {
                bullets[bulletCount] = new Bullet(730, 346, 3, false, false, false, false, false, false, 109, 176);
                bulletCount++;
            }
            else if(boss3Num == 12 && !(boss3Array[4] == 1))
            {
                bullets[bulletCount] = new Bullet(730, 441, 3, false, false, false, false, false, false, 109, 176);
                bulletCount++;
            }
            else if(boss3Num == 13 && !(boss3Array[5] == 1))
            {
                bullets[bulletCount] = new Bullet(730, 346, 3, false, false, false, false, false, false, 109, 176);
                bulletCount++;
            }
            else if(boss3Num == 14 && !(boss3Array[6] == 1))
            {
                bullets[bulletCount] = new Bullet(730, 250, 3, false, false, false, false, false, false, 109, 176);
                bulletCount++;
            }
            else if(boss3Num == 15 && !(boss3Array[7] == 1))
            {
                bullets[bulletCount] = new Bullet(730, 346, 3, false, false, false, false, false, false, 109, 176);
                bulletCount++;
            }
            else if(boss3Num == 16 && !(boss3Array[8] == 1))
            {
                bullets[bulletCount] = new Bullet(730, 441, 3, false, false, false, false, false, false, 109, 176);
                bulletCount++;
            }
            else if(boss3Num < 17);
            else
            {
                boss3Num = 4;
            }

        }
    }
    class Boss4 implements ActionListener //this class is set on a timer to allow the boss to attack at a consistent rate
    {
        public void actionPerformed(ActionEvent e) //this method is set on a timer to allow the boss to attack at a consistent rate
        {
            attacking = true;
            attackTimer.start();
            bullets[bulletCount] = new Bullet(560, 120, 4, false, false, true, false, false, false, 213, 78);
            bulletCount++;
            bullets[bulletCount] = new Bullet(672, 120, 4,false, true, true, false, false, false, 177, 174);
            bulletCount++;
            bullets[bulletCount] = new Bullet(672, 309, 4, false, true, false, false, false, false, 78, 213);
            bulletCount++;
            bullets[bulletCount] = new Bullet(672, 498, 4, false, true, false, true, false, false, 177, 174);
            bulletCount++;
            bullets[bulletCount] = new Bullet(560, 498, 4, false, false, false, true, false, false, 213, 78);
            bulletCount++;
            bullets[bulletCount] = new Bullet(378, 498, 4, true, false, false, true, false, false, 177, 174);
            bulletCount++;
            bullets[bulletCount] = new Bullet(378, 309, 4, true, false, false, false, false, false, 78, 213);
            bulletCount++;
            bullets[bulletCount] = new Bullet(378, 120, 4, true, false, true, false, false, false, 177, 174);
            bulletCount++;
            repaint();
        }
    }
    class Boss5 implements ActionListener //this class is set on a timer to allow the boss to attack at a consistent rate
    {
        public void actionPerformed(ActionEvent e) //this method is set on a timer to allow the boss to attack at a consistent rate
        {

        }
    }
}
