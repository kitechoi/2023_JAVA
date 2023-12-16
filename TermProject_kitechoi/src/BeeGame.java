import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.*;
import java.io.File;

// 메인 클래스---------------------------------------------------------------------->
public class BeeGame extends JFrame {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    public BeeGame() {
        setTitle("벌침 쏘기 게임");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new GamePanel(WINDOW_HEIGHT));
        setVisible(true);
    }

    public static void main(String[] args) {
        new BeeGame();
    }
}

// 게임 패널 클래스----------------------------------------------------------------------------------->
class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer, gameTimer;
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Enemy> specialEnemies;
    private ArrayList<Bullet> bullets;
    private int score;
    private long startTime;
    private BufferedImage bg_img;
    private SpecialEnemyThread specialEnemyThread;

    public GamePanel(int windowHeight) {
        setFocusable(true);
        addKeyListener(this);

        // 배경 이미지 로드
        try {
            bg_img = ImageIO.read(new File("bg_img.jpg"));
        } catch (IOException e) {
            System.err.println("배경 이미지 로드 실패: " + e.getMessage());
            e.printStackTrace();
        }

        player = new Player(400, windowHeight - 40);
        enemies = new ArrayList<>();
        specialEnemies = new ArrayList<>();
        bullets = new ArrayList<>();
        score = 0;
        timer = new Timer(10, this);
        gameTimer = new Timer(1000, e -> checkGameOver());
        startTime = System.currentTimeMillis();
        timer.start();
        gameTimer.start();

        specialEnemyThread = new SpecialEnemyThread(this);
        specialEnemyThread.start();
    }

    public void addSpecialEnemy() {
        specialEnemies.add(new Enemy(new Random().nextInt(750), 0, true)); // 특별 이벤트 적 추가
    }

    private void checkGameOver() {
        if (System.currentTimeMillis() - startTime > 30000) {
            timer.stop();
            gameTimer.stop();
            specialEnemyThread.stopRunning(); // 특별 이벤트 적 스레드 종료
            String message;

            if (score >= 300) {
                message = "당신은 이 게임 최강자!";
            } else if (score >= 200) {
                message = "게임 좀 하시네요?";
            } else if (score >= 100) {
                message = "나쁘지 않아요!";
            } else {
                message = "분발하세요!";
            }

            JOptionPane.showMessageDialog(this, "게임 종료! 점수: " + score + "\n" + message);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 배경 이미지 그리기
        if (bg_img != null) {
            g.drawImage(bg_img, 0, 0, getWidth(), getHeight(), null);
        }

        // 플레이어 그리기
        player.draw(g);
        // 적 그리기
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
        // 특별 이벤트 적 그리기
        for (Enemy enemy : specialEnemies) {
            enemy.draw(g);
        }
        // 총알 그리기
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        // 점수 표시
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 650, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move();
        if (new Random().nextInt(100) < 2) {
            enemies.add(new Enemy(new Random().nextInt(750), 0, false));
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).move();
            if (enemies.get(i).y > 600) {
                enemies.remove(i);
                i--;
            }
        }
        for (int i = 0; i < specialEnemies.size(); i++) {
            specialEnemies.get(i).move();
            if (specialEnemies.get(i).y > 600) {
                specialEnemies.remove(i);
                i--;
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).move();
            if (bullets.get(i).y < 0) {
                bullets.remove(i);
                i--;
            }
        }
        checkCollision();
        checkSpecialCollision();
        repaint();
    }

    private void checkCollision() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            for (int j = 0; j < enemies.size(); j++) {
                Enemy enemy = enemies.get(j);
                if (bullet.getRectangle().intersects(enemy.getRectangle())) {
                    score += 10;
                    enemies.remove(j);
                    bullets.remove(i);
                    i--;
                    playSound("hit.wav");
                    break;
                }
            }
        }
    }

    private void checkSpecialCollision() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            for (int j = 0; j < specialEnemies.size(); j++) {
                Enemy enemy = specialEnemies.get(j);
                if (bullet.getRectangle().intersects(enemy.getRectangle())) {
                    score -= 50; // 특별 이벤트 적 점수
                    specialEnemies.remove(j);
                    bullets.remove(i);
                    i--;
                    playSound("hit.wav");
                    break;
                }
            }
        }
    }

    private void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println("오디오 파일 재생 오류: " + e.getMessage());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                player.dx = -5;
                break;
            case KeyEvent.VK_RIGHT:
                player.dx = 5;
                break;
            case KeyEvent.VK_SPACE:
                bullets.add(new Bullet(player.x + 20, player.y));
                playSound("shoot.wav");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                player.dx = 0;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}

// 플레이어 클래스----------------------------------------------------------------------------------->
class Player {
    int x, y;
    int dx; // x축 이동 속도
    private BufferedImage playerImg;
    private int width = 80; // 플레이어 너비
    private int height = 80; // 플레이어 높이

    public Player(int x, int windowHeight) {
        this.x = x;
        this.y = windowHeight - this.height; // 화면 하단에 맞춤
        try {
            playerImg = ImageIO.read(new File("bee_img.png")); // 플레이어 이미지 로드
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move() {
        x += dx;
        if (x < 0) x = 0;
        if (x > 760 - width) x = 760 - width;
    }

    public void draw(Graphics g) {
        g.drawImage(playerImg, x, y, width, height, null); // 이미지 크기 조정하여 그리기
    }
}

// 적 클래스---------------------------------------------------------------------------------->
class Enemy {
    int x, y;
    int dy = 2; // y축 이동 속도
    private BufferedImage enemyImg;
    private int width = 40; // 적 너비
    private int height = 50; // 적 높이

    private boolean isSpecial; // 특별 이벤트 적 여부



    public Enemy(int x, int y, boolean isSpecial) {
        this.x = x;
        this.y = y;
        this.isSpecial = isSpecial;
        try {
            enemyImg = ImageIO.read(new File(isSpecial ? "special_enemy_img.png" : "cup_img.png")); // 특별 이벤트 적은 다른 이미지
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move() {
        y += dy;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        if (isSpecial) {
            // 특별 이벤트 적의 경우 이미지 크기를 2배로 늘림
            g.drawImage(enemyImg, x, y, width * 2, height * 2, null);
        } else {
            // 일반 적의 경우 기존 크기 유지
            g.drawImage(enemyImg, x, y, width, height, null);
        }
    }
}

// 벌침 클래스 ------------------------------------------------------------------------------->
class Bullet {
    int x, y;
    int dy = -10; // y축 이동 속도

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        y += dy;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, 5, 10);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, 5, 10);
    }
}

// 특별 적 클래스 ------------------------------------------------------------------------>
class SpecialEnemyThread extends Thread {
    private GamePanel gamePanel;
    private Random random;
    private boolean running;

    public SpecialEnemyThread(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.random = new Random();
        this.running = true;
    }

    public void run() {
        while (running) {
            try {
                Thread.sleep(random.nextInt(10000) + 5000); // 5-15초 사이 무작위 대기
                if (running) {
                    gamePanel.addSpecialEnemy();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopRunning() {
        running = false;
    }
}
