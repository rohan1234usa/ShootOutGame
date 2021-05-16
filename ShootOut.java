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
    JPanel cards, mainCard, levelsCard, upgradesCard, characterSelectCard, instructionsCard, menuPanel;
    BossCardPanel bossCard;
    JLabel titleLabel;
    JButton levelButton, upgradesButton, characterSelectButton, instructionButton;
    JCheckBox safety, easy, normal, hard, merciless, char1, char2, char3, char4;
    Image backArrowImage;
    int lives, cardNum, bossNum;
    MenuManager menuManager;

    public ShootOutGame() //constructor that sets the initial values of variables
    {
        lives = 3;
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
        upgradesButton = new JButton("Upgrades");
        upgradesButton.setPreferredSize(new Dimension(500,110));
        upgradeButtonHandler upgradeHandler = new upgradeButtonHandler();
        upgradesButton.addActionListener(upgradeHandler);
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
        menuPanel.add(upgradesButton);
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


        upgradesCard = new JPanel();


        buildCharacterSelectCard();


        buildInstructionsCard();



        cards = new JPanel(new CardLayout());
        menuManager = new MenuManager(cards);
        bossCard = new BossCardPanel(bossNum, menuManager);

        cards.add(mainCard);
        cards.add(levelsCard);
        cards.add(upgradesCard);
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
        difficultySelectPanel.setLayout(new GridLayout(2,1));
        difficultyPanel.setLayout(new GridLayout(1,5));

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

        difficultyPanel.add(safety);
        difficultyPanel.add(easy);
        difficultyPanel.add(normal);
        difficultyPanel.add(hard);
        difficultyPanel.add(merciless);

        difficultySelectPanel.add(difficultyLabel);
        difficultySelectPanel.add(difficultyPanel);

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
        fillerPanelSouth2.setPreferredSize(new Dimension(10,115));

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
        instructionsLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        JTextArea instructionsTextArea = new JTextArea("W to aim up\nA to move left\nS to crouch\nD to move right\nJ or Space Bar to jump\nK to shoot\nL to dash\nEscape Key or Pause Button to pause");
        instructionsTextArea.setFont(new Font("Serif", Font.PLAIN, 30));
        instructionsTextArea.setPreferredSize(new Dimension(500, 1000));

        JPanel instructionsTextPanel = new JPanel();
        instructionsTextPanel.setPreferredSize(new Dimension(500, 500));
        instructionsTextPanel.setLayout(new BorderLayout());
        instructionsTextPanel.add(instructionsLabel, BorderLayout.NORTH);
        instructionsTextPanel.add(instructionsTextArea, BorderLayout.CENTER);

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
        JLabel char1Pic = new JLabel(new ImageIcon("pause_icon.png"));
        JLabel char2Pic = new JLabel(new ImageIcon("pause_icon.png"));
        JLabel char3Pic = new JLabel(new ImageIcon("pause_icon.png"));
        JLabel char4Pic = new JLabel(new ImageIcon("pause_icon.png"));

        characterSelectCard = new JPanel();
        characterSelectCard.setLayout(new BorderLayout());
        JPanel characterSelectPanel;
        characterSelectPanel = new JPanel();
        characterSelectPanel.setLayout(new GridLayout(3,4));

        JLabel levelLabel = new JLabel("Select Character:");
        levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        levelLabel.setPreferredSize(new Dimension(20,50));
        levelLabel.setFont(new Font("Serif", Font.PLAIN, 30));

        //characterSelectCard.add(images)


        char1 = new JCheckBox("Safety");
        char2 = new JCheckBox("Easy");
        char3 = new JCheckBox("Normal");
        char4 = new JCheckBox("Hard");
        CheckBoxListener cbListener = new CheckBoxListener();
        char1.addActionListener(cbListener);
        char1.setSelected(true);
        char2.addActionListener(cbListener);
        char2.setSelected(false);
        char3.addActionListener(cbListener);
        char3.setSelected(false);
        char4.addActionListener(cbListener);
        char4.setSelected(false);

        characterSelectPanel.add(char1Pic);
        characterSelectPanel.add(char1);
        characterSelectPanel.add(char2Pic);
        characterSelectPanel.add(char2);


        JPanel barrier1 = new JPanel();
        barrier1.setPreferredSize(new Dimension(100,20));
        characterSelectPanel.add(barrier1);
        JPanel barrier2 = new JPanel();
        barrier2.setPreferredSize(new Dimension(100,20));
        characterSelectPanel.add(barrier2);
        JPanel barrier3 = new JPanel();
        barrier3.setPreferredSize(new Dimension(100,20));
        characterSelectPanel.add(barrier3);
        JPanel barrier4 = new JPanel();
        barrier4.setPreferredSize(new Dimension(100,20));
        characterSelectPanel.add(barrier4);

        characterSelectPanel.add(char3Pic);
        characterSelectPanel.add(char3);
        characterSelectPanel.add(char4Pic);
        characterSelectPanel.add(char4);


        JPanel fillerPanelEast3 = new JPanel();
        JPanel fillerPanelWest3 = new JPanel();
        JPanel fillerPanelNorth3 = new JPanel();
        JPanel fillerPanelSouth3 = new JPanel();
        fillerPanelEast3.setPreferredSize(new Dimension(50,10));
        fillerPanelNorth3.setPreferredSize(new Dimension(10,85));
        fillerPanelWest3.setPreferredSize(new Dimension(240,10));
        fillerPanelSouth3.setPreferredSize(new Dimension(30,10));

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
    class upgradeButtonHandler implements ActionListener //This buttonHandler takes user to upgrades card
    {
        public void actionPerformed(ActionEvent e) //This method takes user to upgrades card
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
            bossCard.showCard(bossNum);
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
            bossCard.showCard(bossNum);
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
            bossCard.showCard(bossNum);
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
            bossCard.showCard(bossNum);
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
            bossCard.showCard(bossNum);
        }
    }
    class CheckBoxListener implements ActionListener { //this class makes the checkboxes used to select difficulty
        public void actionPerformed(ActionEvent e) { //this method makes the checkboxes used to select difficulty
            String cmd = e.getActionCommand();
            if(cmd.equals("Safety")) {
                safety.setSelected(true);
                easy.setSelected(false);
                normal.setSelected(false);
                hard.setSelected(false);
                merciless.setSelected(false);
                lives = 999;
            }
            else if(cmd.equals("Easy")) {
                safety.setSelected(false);
                easy.setSelected(true);
                normal.setSelected(false);
                hard.setSelected(false);
                merciless.setSelected(false);
                lives = 50;
            }
            else if(cmd.equals("Normal")) {
                safety.setSelected(false);
                easy.setSelected(false);
                normal.setSelected(true);
                hard.setSelected(false);
                merciless.setSelected(false);
                lives = 10;
            }
            else if(cmd.equals("Hard")) {
                safety.setSelected(false);
                easy.setSelected(false);
                normal.setSelected(false);
                hard.setSelected(true);
                merciless.setSelected(false);
                lives = 5;
            }
            else {
                safety.setSelected(false);
                easy.setSelected(false);
                normal.setSelected(false);
                hard.setSelected(false);
                merciless.setSelected(true);
                lives = 1;
            }
        }
    }

    class BackButtonHandler implements ActionListener //This buttonHandler goes back to the main card
    {
        public void actionPerformed(ActionEvent e) { //This method goes back to the main card
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
    public MenuManager(JPanel card)
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
    int xMouse, yMouse, characterXVal, characterYVal, characterWidth, characterHeight, jumpDelay, jumpNum, dashNum;
    Image healthBarImage, pauseIconImage, playIconImage, backArrowImage, char1DuckingRight, char1DuckingLeft, char1NormalRight, char1NormalLeft, char1RunningRight, char1RunningLeft;
    Jumper jumper;
    MoveLeft leftMover;
    MoveRight rightMover;
    Dash dasher;
    Shoot shooter;
    Timer jumpTimer, leftTimer, rightTimer, dashTimer, shootTimer;
    boolean crouching, movingRight, movingLeft, paused, jumping, crouchAfterJump, facingRight, facingLeft, dashing;
    Rectangle pauseButton, backButton;
    MenuManager menuManager;
    public BossCardPanel(int level, MenuManager menu) //constructor that sets the initial values of variables
    {
        grabFocus();
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
        healthBarImage = new ImageIcon("health_bar.png").getImage();
        pauseIconImage = new ImageIcon("pause_icon.png").getImage();
        playIconImage = new ImageIcon("play_icon.jpeg").getImage();
        backArrowImage = new ImageIcon("back_arrow_with_white_background.png").getImage();

        char1DuckingRight = new ImageIcon("ducking.jpeg").getImage();
        char1NormalRight = new ImageIcon("normal.jpeg").getImage();
        char1RunningRight = new ImageIcon("running.png").getImage();

        jumpDelay = 10;
        characterXVal = 90;
        characterYVal = 410;
        characterWidth = 70;
        characterHeight = 140;
        jumpNum = 0;
        dashNum = 0;
        jumper = new Jumper();
        jumpTimer = new Timer(10, jumper);
        leftMover = new MoveLeft();
        leftTimer = new Timer(10,leftMover);
        rightMover = new MoveRight();
        rightTimer = new Timer(10, rightMover);
        dasher = new Dash();
        dashTimer = new Timer(10, dasher);
        crouching = false;
        movingRight = false;
        movingLeft = false;
        pauseButton = new Rectangle(530,15,60,60);
        backButton = new Rectangle(1030,15,60,60);
        paused = false;
        jumping = false;
        crouchAfterJump = false;
        facingRight = true;
        facingLeft = false;
        dashing = false;
        menuManager = menu;
    }
    public void showCard(int whichBoss) //this method is used to get focus on the game
    {
        jumpDelay = 10;
        characterXVal = 90;
        characterYVal = 410;
        characterWidth = 70;
        characterHeight = 140;
        jumpNum = 0;
        jumper = new Jumper();
        jumpTimer = new Timer(10, jumper);
        leftMover = new MoveLeft();
        leftTimer = new Timer(10,leftMover);
        rightMover = new MoveRight();
        rightTimer = new Timer(10, rightMover);
        crouching = false;
        movingRight = false;
        movingLeft = false;
        pauseButton = new Rectangle(530,15,60,60);
        backButton = new Rectangle(1030,15,60,60);
        paused = false;
        jumping = false;
        crouchAfterJump = false;
        grabFocus();

        //if(whichBoss == 1) boss1();
        //else if(whichBoss == 2) boss2();
        //else if(whichBoss == 3) boss3();
        //else if(whichBoss == 4) boss4();
        //else if(whichBoss == 5) boss5();
    }
    public void paintComponent(Graphics g) //this creates the objects inside the frame
    {
        super.paintComponent(g);
        g.setColor(Color.red);
        //g.fillRect(characterXVal, characterYVal, characterWidth, characterHeight);

        if(crouching) g.drawImage(char1DuckingRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
        else if(paused) g.drawImage(char1NormalRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
        else if(movingLeft || movingRight || dashing) g.drawImage(char1RunningRight, characterXVal, characterYVal, characterWidth, characterHeight, null);
        else g.drawImage(char1NormalRight, characterXVal, characterYVal, characterWidth, characterHeight, null);

        /* This was used for debugging and making sure the jumping and moving was working
        String xString = "charXVal - " + characterXVal;
        g.drawString(xString,222,390);
        String yString = "charTVal - " + characterYVal;
        g.drawString(yString,222,430);
        */

        g.drawImage(healthBarImage, 30,15,180,108, null);
        if(!paused) g.drawImage(pauseIconImage, 530,15,60,60, null);
        else g.drawImage(playIconImage, 530,15,60,60, null);
        g.drawImage(backArrowImage, 1030,15,60,60, null);
    }
    public void keyTyped(KeyEvent e) //Used to handle when a key is typed
    {
        char charOfKeyTyped = e.getKeyChar();
    }
    
    public void keyPressed(KeyEvent e) { //Used to handle when a key is pressed and allow the user to move around
        int key = e.getKeyCode();
        if(paused)
        {
            switch(key)
            {
                case KeyEvent.VK_A:
                    movingLeft = true;
                    break;
                case KeyEvent.VK_D:
                    movingRight = true;
                    break;
                case KeyEvent.VK_S:
                    crouchAfterJump = true;
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
                //shoot
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
        if(paused)
        {
            switch(key)
            {
                case KeyEvent.VK_A:
                    movingLeft = false;
                    break;
                case KeyEvent.VK_D:
                    movingRight = false;
                    repaint();
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
            }
            return;
        }
        switch(key)
        {
            case KeyEvent.VK_W:
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
                break;
        }
    }
    public void mousePressed (MouseEvent e) {} //handles when the mouse is pressed
    public void mouseReleased (MouseEvent e) {} //handles when the mouse is released
    public void mouseClicked (MouseEvent e) //handles when the mouse is clicked
    {
        xMouse = e.getX();
        yMouse = e.getY();
        if(pauseButton.contains(xMouse,yMouse) && !paused) pauseGame();
        else if(pauseButton.contains(xMouse,yMouse) && paused) resumeGame();
        if(backButton.contains(xMouse,yMouse))
        {
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
        repaint();
    }
    public void resumeGame() //this method is used to resume the game when the pause button is pressed again
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
                else if(facingLeft) characterXVal -= 7;
                else if(facingRight) characterXVal += 7;
            }
            else
            {
                dashNum = 0;
                dashing = false;
                dashTimer.stop();
                grabFocus();
                if(jumping) jumpTimer.start();
            }
            repaint();
        }
    }
    class Shoot implements ActionListener //This class activates when 'l' is pressed, and it makes the character dash either right or left
    {
        public void actionPerformed(ActionEvent e) //This method activates when 'l' is pressed, and it makes the character dash either right or left
        {

            repaint();
        }
    }
}
