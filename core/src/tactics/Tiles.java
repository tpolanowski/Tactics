package tactics;

import java.util.ArrayList;

public class Tiles {
    private float totalX;
    private float totalY;
    private int count;

    private Coord[][] coords;

    public Tiles (float totalX, float totalY, int count) {
        this.totalX = totalX;
        this.totalY = totalY;
        this.count = count;

        coords = new Coord[count][count];

        int initX = 0;
        int initY = 30;

        for (int i = 0; i < count; i++) {

            float baseX = initX + i*21.58f;
            float baseY = initY + i*20.47f;
            for (int j = 0; j < count; j++) {
                //System.out.println("test ("+i+", "+j+")");
                if (j != 0) {
                    coords[i][j] = new Coord(baseX +j*21.58f, baseY
                            -j*20.47f , i, j);
                }
                else {
                    coords[i][j] = new Coord(baseX, baseY, i, j);
                }
            }
        }

//        for(Coord[] coordOuter : coords) {
//            for(Coord coordInner : coordOuter) {
//                System.out.println(coordInner);
//            }
//        }
    }

    public Coord getCoord(int x, int y) {
        return coords[x][y];
    }

    public Coord[][] getCoords() {
        return coords;
    }

    @Override
    public String toString() {
        return "Tiles{" +
                "totalX=" + totalX +
                ", totalY=" + totalY +
                ", count=" + count +
                ", coords=" + coords +
                '}';
    }
}
