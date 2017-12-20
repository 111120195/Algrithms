import javax.swing.*;
import java.awt.*;

public class View {
    private int countNum = -1;
    private boolean pause;
    private int[] countArr;
    private int curIndex = 0;

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void go() {
        JFrame frame = new JFrame("随机排列");
        JPanel pnSet = new JPanel();
        DigitDisplayPn pnDisplay = new DigitDisplayPn();
        JButton btStart = new JButton("start");
        JButton btPause = new JButton("pause");

        JLabel lbNUm = new JLabel("Count ");
        JTextField tfNum = new JTextField(6);

        pnSet.add(lbNUm);
        pnSet.add(tfNum);
        pnSet.add(btStart);
        pnSet.add(btPause);

        frame.getContentPane().add(pnDisplay, BorderLayout.CENTER);
        frame.getContentPane().add(pnSet, BorderLayout.SOUTH);

        tfNum.addActionListener(e -> {
            countNum = Integer.valueOf(tfNum.getText());
            countArr = new int[countNum];
            for (int i = 0; i < countArr.length; i++) {
                countArr[i] = i + 1;
            }
        });

        btStart.addActionListener(e -> {
            pause = false;

            new Thread(() -> {
                while (!pause) {
                    pnDisplay.repaint();
                    curIndex++;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException p) {
                        p.printStackTrace();
                    }
                }
            }).start();

        });

        btPause.addActionListener(e -> {
            pause = true;
            if (countNum > 0)
                swap(countArr, curIndex % countNum, countNum - 1);
            countNum--;
        });

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public class DigitDisplayPn extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(getRandomColor());
            int x0 = getWidth() / 2;
            int y0 = getHeight() / 2;
            int r = getHeight() / 3;
            Font font = new Font("Tahoma", Font.BOLD, 240);
            g.setFont(font);
            g.fillOval(x0 - r, y0 - r, 2 * r, 2 * r);

            g.setColor(getRandomColor());
            if (countArr != null) {
                if (countNum > 0)
                    drawStrCenter(g, String.valueOf(countArr[curIndex % countNum]), x0, y0);
                else
                    drawStrCenter(g, "E", x0, y0);

            } else {
                drawStrCenter(g, "S", x0, y0);
            }
        }

    }


    private void drawStrCenter(Graphics g, String str, int x0, int y0) {
        int strWidth = g.getFontMetrics().stringWidth(str);
        int strHeight = g.getFontMetrics().getHeight();
        g.drawString(str, x0 - strWidth / 2, y0 + strHeight / 3);
    }

    private Color getRandomColor() {
        return new Color(
                ((int) (Math.random() * 225)),
                ((int) (Math.random() * 225)),
                ((int) (Math.random() * 225))
        );
    }

    public static void main(String args[]) {
        new View().go();
    }

}
