import javax.swing.JFrame;

public class App implements Runnable {

    public static Renderer render;
    
    public static void main(String[] args) throws Exception {
        Thread gameThread = new Thread(new App());

        JFrame frame = new JFrame();
        render = new Renderer(800, 600);

        frame.add(render);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        gameThread.start();
    }

    @Override
    public void run() {
        int frameRate = 60;
        long millisPerFrame = 1000 / frameRate;
        long startTime;
        long endTime;
        long elapsedTime;

        while(true) {
            startTime = System.currentTimeMillis();

            if(render.xScroll < render.pixels.length) {
                render.xScroll++;
                render.repaint();
            } else {
                render.xScroll = 0;
                render.repaint();
            }

            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;

            if (elapsedTime < millisPerFrame) {
                try {
                    Thread.sleep(millisPerFrame - elapsedTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
