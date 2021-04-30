//package v2.component;
//
//
//import javax.swing.*;
//import java.awt.*;
//
//public class TimerStar {
//
//        private static int x =100;
//        private Timer t;
//
//        public TimerStar(){
//            t = new Timer(40, e -> {
//                if (x > 0) {
//                    x = x - 1;
//                    repaint();
//                }else {
//                    t.stop();
//                }
//            });
//            t.start();
//        }
//
//
//    public static void drawTimerStar(Graphics g){
//            g.setColor(Color.blue);
//            g.fillRect(200, 200, 200, x);
//        }
//
//}
