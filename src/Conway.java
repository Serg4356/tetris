import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Пользователь on 05.10.2016.
 */
public class Conway extends JComponent{
    private boolean isRunning = false;
    private Color[][] well = new Color[Constants.xSize][Constants.ySize];
    private Color[][] well2 = new Color[Constants.xSize][Constants.ySize];
    private int[][] test = {{-1, 0, 1},{-1, 0, 1}};
    Conway(){
        setPreferredSize(new Dimension(Constants.ySize * Constants.cellSize, Constants.xSize * Constants.cellSize));
        wellinit();
    }
    public void wellinit() {
        for (int i = 0; i < well.length; i++) {
            for (int y = 0; y < well[0].length; y++) {
                well[i][y] = Constants.figureColors[9];
            }
        }
    }
    public void paint(Graphics g){
        for (int i = 0; i < Constants.xSize; i++) {
            for (int y = 0; y < Constants.ySize; y++) {
                g.setColor(well[i][y]);
                g.fillRect(y * Constants.cellSize, i * Constants.cellSize,
                        Constants.cellSize - 1, Constants.cellSize - 1);
            }
        }
    }
    public void setClickedCellColor(int x, int y){
        if(well[x][y] != Color.white) {
            well[x][y] = Color.white;
        } else well[x][y] = Constants.figureColors[9];
        this.repaint();
    }
    public void randomize(){
        wellinit();
        for (int i = 0; i < Constants.xSize; i++) {
            for (int y = 0; y < Constants.ySize; y++) {
                if(new Random().nextInt(10)<3) {
                    well[i][y] = Color.white;
                }
            }
        }
        this.repaint();
    }
    public void engine() {
        int count = 0;
        if (isRunning) {
            for(int i = 0; i<well.length; i++){
                for(int y = 0; y<well[0].length; y++) {
                    well2[i][y] = well[i][y];
                }
            }
            for(int i = 0; i<well.length; i++){
                for(int y = 0; y<well[0].length; y++){
                    if(well2[i][y]!=Color.white){
                        if(countWhite(i,y)==3){
                            well[i][y]=Color.white;
                        }
                    } else {
                        if(countWhite(i,y)==3||countWhite(i,y)==2){
                            well[i][y]=Color.white;
                        } else well[i][y]=Constants.figureColors[9];
                    }
                }
            }
            for(int i = 0; i<well.length; i++){
                for(int y = 0; y<well[0].length; y++) {
                   if(well[i][y]==Color.white) count+=1;
                }
            }
            if(count==0) isRunning = false;
        }
    }
    public void setisRunning(){
        if(isRunning){
            isRunning = false;
        } else{
            isRunning = true;
        }
    }
    private int countWhite(int x, int y){
        int prex = 0, prey = 0;
        int count = 0;

        for(int z=-1; z<2;z++){
             outer: for(int c=-1; c<2;c++) {
                 prex = x;
                 prey = y;
                if(z==0&&c==0) continue outer;
                if(x==0&&z==-1) prex = well2.length;
                if(y==0&&c==-1)prey = well2[0].length;
                if(x==well2.length-1&&z==1)prex = -1;
                if(y==well2[0].length-1&&c==1)prey = -1;
                 if(well2[prex+z][prey+c] == Color.white){
                     count++;
                 }
            }
        }
        return count;
    }
    public void setField() {
        int c = 2, v = 0;
        wellinit();
        outer1:
        for (int i = 0; i < well[0].length; i++) {
            if(v<2) {
                outer2:
                for (int y = 0; y < well.length; y++) {
                    if (c < 2) {
                        well[y][i] = Color.white;
                    } else if (c <= 2) {
                        c = 0;
                        continue outer2;
                    }
                    c++;
                }
                v++;
            } else if(v==2) v=0;
        }
    }
    public void setField2() {
        int c = 2, v = 0;
        wellinit();
        outer1:
        for (int i = 0; i < well[0].length; i++) {
            if(v<2) {
                outer2:
                for (int y = 0; y < well.length; y++) {
                    if (c < 2) {
                        well[y][i] = Color.white;
                    } else if (c == 3) {
                        c = 0;
                        continue outer2;
                    }
                    c++;
                }
                v++;
            } else if(v==2) v=0;
        }
    }
}
