package games;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;

public class Gametcp extends JFrame implements Runnable {

    private final int LAYOUT_WIDTH = 600;
    private final int LAYOUT_HEIGHT = 500;

    private int x = 0;
    private int y = 0;
    private int tendenciaX, tendenciaY;

    private int vetX[] = new int[10];
    private int vetY[] = new int[10];

    public Gametcp() {
        initComponents();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            vetX[i] = rand.nextInt(LAYOUT_WIDTH - 150) + 26;
            vetY[i] = rand.nextInt(LAYOUT_HEIGHT - 150) + 26;
        }
        new Thread(this).start();
    }

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, LAYOUT_WIDTH, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, LAYOUT_HEIGHT, Short.MAX_VALUE));

        pack();
    }// </editor-fold>

    @Override
    public void paint(Graphics gr) {
        gr.clearRect(0, 0, getWidth(), getHeight());
        gr.setColor(new Color(0, 0, 0));
        Player player = new Player(500 + x, 450 + y);
        player.draw(gr);
        // gr.drawRect(50 + x, 50 + y, 50, 50);

        for (int i = 0; i < 10; i++) {
            gr.setColor(new Color(i * 2, i * 8, i * 14));
            gr.drawOval(vetX[i], vetY[i], 5, 5);
        }

        if (Math.random() > 0.9) {
            tendenciaX = 2;
            tendenciaY = 2;
        } else {
            tendenciaX = 0;
            tendenciaY = 0;
        }
    }

    private void formKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            y = y - 1;
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            y = y + 1;
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            x = x - 1;
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            x = x + 1;
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
