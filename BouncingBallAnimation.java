import java.util.ArrayList;
import java.util.List;

public class BouncingBallAnimation{
    public static void main(String[] args){
        StdDraw.setScale(-1, +1); // Canvas extents
        StdDraw.enableDoubleBuffering(); // Draw to offscreen canvas, call show() to bring it to the onscreen canvas
        double x = 0;
        double y = 0;
        double u = 0.;
        double v = 0.;
        double delta_t = 0.005;
        List<double[]> trace = new ArrayList<double[]>();
        double[] init = {0, 0};
        trace.add(init);
        
        while (true){
            u += 0.2*(Math.random() - 0.5);
            v += 0.2*(Math.random() - 0.5);
            u = Math.min(u, 1);
            x += u*delta_t;
            y += v*delta_t;
            double[] tmp = {x, y};
            trace.add(tmp);
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledCircle(x, y, 0.025); // Draw marker
            for (int i = 1; i < trace.size(); i++){
                StdDraw.line(trace.get(i-1)[0], trace.get(i-1)[1], trace.get(i)[0], trace.get(i)[1]);
            }
            StdDraw.show(); // Bring drawing forward to visible screen
            StdDraw.pause(20); // Wait a short while
            if (1 - Math.abs(x) < 0.001) u = -u;
            if (1 - Math.abs(y) < 0.001) v = -v;
        }
    }
}