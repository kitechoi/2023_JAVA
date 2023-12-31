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
    protected static final int WINDOW_WIDTH = 800;
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

        // 쓰레드 실행
        specialEnemyThread = new SpecialEnemyThread(this);
        specialEnemyThread.start();
    }

    public void addSpecialEnemy() {
        int enemyWidth = 40;
        int margin = 80;
        int enemySpawnX = new Random().nextInt(BeeGame.WINDOW_WIDTH - enemyWidth - 2 * margin) + margin;
        specialEnemies.add(new Enemy(enemySpawnX, 0, true));
    }

    private void checkGameOver() {
        if (System.currentTimeMillis() - startTime > 30000) {
            timer.stop();
            gameTimer.stop();
            specialEnemyThread.stopRunning();
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

        if (bg_img != null) {
            g.drawImage(bg_img, 0, 0, getWidth(), getHeight(), null);
        }

        player.draw(g);

        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        for (Enemy enemy : specialEnemies) {
            enemy.draw(g);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString("Score: " + score, 650, 30);
    }

    public void actionPerformed(ActionEvent e) {
        player.move();

        // 적 생성 조절: 화면에 적이 6개 미만일 때만 새로운 적 생성
        if (enemies.size() < 6 && new Random().nextInt(100) < 5) { // 5% 확률로 적 생성
            int enemyWidth = 40;
            int margin = 80;
            int enemySpawnX = generateNonOverlappingPosition(enemyWidth, margin);
            enemies.add(new Enemy(enemySpawnX, 0, false));
        }

        // 적 이동 및 제거
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).move();
            if (enemies.get(i).y > 600) {
                enemies.remove(i);
                i--;
            }
        }

        // 특별 적 이동 및 제거
        for (int i = 0; i < specialEnemies.size(); i++) {
            specialEnemies.get(i).move();
            if (specialEnemies.get(i).y > 600) {
                specialEnemies.remove(i);
                i--;
            }
        }

        // 벌침 제거
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

    // 적 겹침 방지를 위한 위치 생성 메서드
    private int generateNonOverlappingPosition(int enemyWidth, int margin) {
        int enemySpawnX;
        boolean positionValid;
        do {
            enemySpawnX = new Random().nextInt(BeeGame.WINDOW_WIDTH - enemyWidth - 2 * margin) + margin;
            positionValid = true;
            for (Enemy enemy : enemies) {
                if (Math.abs(enemy.x - enemySpawnX) < enemyWidth) { // 이미 존재하는 적과 위치가 겹치지 않도록 체크
                    positionValid = false;
                    break;
                }
            }
        } while (!positionValid);
        return enemySpawnX;
    }

    // 적이 벌침 맞았을 때
    private void checkCollision() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            for (int j = 0; j < enemies.size(); j++) {
                Enemy enemy = enemies.get(j);
                if (bullet.getRectangle().intersects(enemy.getRectangle())) {
                    score += 10;    // 10점 ++
                    enemies.remove(j);
                    bullets.remove(i);
                    i--;
                    playSound("hit.wav");
                    break;
                }
            }
        }
    }

    // 특별 적이 벌침 맞았을 때
    private void checkSpecialCollision() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            for (int j = 0; j < specialEnemies.size(); j++) {
                Enemy enemy = specialEnemies.get(j);
                if (bullet.getRectangle().intersects(enemy.getRectangle())) {
                    score -= 50; // 50점 --
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
                // 플레이어 중앙에서 벌침 시작
                int bulletStartX = player.x + player.width / 2 - 2;
                bullets.add(new Bullet(bulletStartX, player.y));
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
    protected int width = 80; // 플레이어 너비
    private int height = 80; // 플레이어 높이

    public Player(int x, int windowHeight) {
        this.x = x;
        this.y = windowHeight - this.height; // 화면 하단에 맞춤
        try {
            playerImg = ImageIO.read(new File("bee_img.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move() {
        x += dx;
        if (x < 0) x = 0;
        if (x > 780 - width) x = 780 - width;
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
            enemyImg = ImageIO.read(new File(isSpecial ? "special_enemy_img.png" : "cup_img.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 스페셜 적의 경우 속도 느리게 설정
        if (isSpecial) {
            dy = 1; // 스페셜 적의 속도
        } else {
            dy = 2; // 일반 적의 속도
        }
    }


    public void move() {
        y += dy;
    }

    public Rectangle getRectangle() {
        if (isSpecial) {
            return new Rectangle(x, y, width * 2, height * 2);
        } else {
            return new Rectangle(x, y, width, height);
        }
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
