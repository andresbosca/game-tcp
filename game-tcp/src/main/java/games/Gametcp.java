package games;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Gametcp extends JFrame implements Runnable {

    private final int LAYOUT_WIDTH = 600;
    private final int LAYOUT_HEIGHT = 500;

    private int x = 0;
    private int y = 0;

    private int vetX[] = new int[10];
    private int vetY[] = new int[10];

    private Evil[] evils = new Evil[3];

    private Player player = new Player(new Point(500, 450));

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

        evils[0] = new Evil(new Point(100, 100));
        evils[1] = new Evil(new Point(80, 200));
        evils[2] = new Evil(new Point(100, 190));

        pack();
    }// </editor-fold>

    @Override
    public void paint(Graphics gr) {
        gr.clearRect(0, 0, getWidth(), getHeight());

        gr.setColor(Color.BLACK);
        player.draw(gr);

        for (int i = 0; i < evils.length; i++) {
            evils[i].draw(gr);
        }

        for (int i = 0; i < 10; i++) {
            gr.setColor(new Color(i * 2, i * 8, i * 14));
            gr.drawOval(vetX[i], vetY[i], 5, 5);

            if (player.isPointInside(new Point(vetX[i], vetY[i]))) {
                vetX[i] = -100;
                vetY[i] = -100;
            }

        }

        try {
            // ChatGPTClient.sendMessage("Hello, how are you? need help with java api");
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.openai.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ChatGPTService service = retrofit.create(ChatGPTService.class);
            Response<MessageResponse> response = service.sendMessage(new MessageRequest(
                    new Point(player.getX(), player.getY()),
                    new Point(evils[0].getX(), evils[0].getY()),
                    0.5))
                    .execute();

            if (response.isSuccessful()) {
                MessageResponse messageResponse = response.body();
                // System.out.println(messageResponse.getChoices()[0].getText());
                String text = messageResponse.getChoices()[0].getText();
                Point newEvilPoint = new Point(
                        Integer.parseInt(text.split(",")[0].replace("(", "").replace(")", "").trim()),
                        Integer.parseInt(text.split(",")[1].replace("(", "").replace(")", "").trim()));

                evils[0].setPoint(newEvilPoint);
            } else {
                System.out.println(response.errorBody().string());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void formKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            y = y - player.getSpeed();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            y = y + player.getSpeed();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            x = x - player.getSpeed();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            x = x + player.getSpeed();
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP && evt.getKeyCode() == KeyEvent.VK_LEFT) {
            x = x - player.getSpeed();
            y = y - player.getSpeed();
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP && evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            x = x + player.getSpeed();
            y = y - player.getSpeed();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN && evt.getKeyCode() == KeyEvent.VK_LEFT) {
            x = x - player.getSpeed();
            y = y + player.getSpeed();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN && evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            x = x + player.getSpeed();
            y = y + player.getSpeed();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                player.move(x, y);
                for (var evil : evils) {
                    evil.moveToDirection(player.getX() - 25, player.getY() - 50);
                }
                Thread.sleep(100);

                repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
