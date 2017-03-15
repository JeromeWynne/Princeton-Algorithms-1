public class RightTriangle{
    public static void main(String[] args){
        double RADIUS = 0.25;
        double TH = 1.;
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.circle(0.5, 0.5, RADIUS);
        double[] tri_x = {(0.5 - RADIUS*Math.sin(TH)),
                          (0.5 - RADIUS*Math.sin(TH)),
                          (0.5 + RADIUS*Math.sin(TH))};
        double[] tri_y = {(0.5 + RADIUS*Math.cos(TH)),
                          (0.5 - RADIUS*Math.cos(TH)),
                          (0.5 - RADIUS*Math.cos(TH))};
        StdDraw.polygon(tri_x, tri_y);
    }
}