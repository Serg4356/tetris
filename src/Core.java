import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Core extends JComponent{

    private int thisShift;
    private int score = 0;
    private Color well[][] = new Color[Constants.xSize][Constants.ySize];
    private Color well2[][] = new Color[Constants.xSize][Constants.ySize];
    private int rotation;
    private int currentFigure = new Random().nextInt(Constants.figure.length);
    private int[] currentPosition;
    private int[] dropPosition = {1, Constants.ySize/2};
    private int[][] figurePosition = new int[4][2];
    private int[][] figurePositionTest = new int[4][2];
    private boolean isrunning = false;
    private Color currentColor;

    Core() {
        setPreferredSize(new Dimension(Constants.ySize * Constants.cellSize, Constants.xSize * Constants.cellSize));
        wellinit();
    }
    public void start(){

        errTest();
        eraseLine();
        wellinit();
        dropFigure();
        refreshCoord();
        drawFigure();
        repaint();

    }
    public void paint(Graphics g) {
        for (int i = 0; i < Constants.xSize; i++) {
            for (int y = 0; y < Constants.ySize; y++) {
                if(well2[i][y]!=null){
                    g.setColor(well2[i][y]);
                } else g.setColor(well[i][y]);

                g.fillRect(y * Constants.cellSize, i * Constants.cellSize,
                        Constants.cellSize - 1, Constants.cellSize - 1);
            }
        }

        Font font = new Font("Tahoma", Font.BOLD, 15);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString("Score: " + score,  (int) (Constants.cellSize*Constants.ySize*0.5),(int) (Constants.cellSize*Constants.xSize -10));

    }
    public String getPosition(){return Integer.toString(this.currentPosition[0]);}
    public void errTest(){
        for(int y = 0;y<figurePosition.length;y++) {
            try {
                if (well2[figurePosition[y][0] + 1][figurePosition[y][1]] != null) {
                    fusion();
                }
            } catch (ArrayIndexOutOfBoundsException e) {fusion();}
        }


    }
    public void setShift(int i){

        boolean err = false;

       for(int y = 0; y<figurePosition.length;y++){
           if((figurePosition[y][1]+i<0||figurePosition[y][1]+i>=Constants.ySize)){
               err = true;
           }
           if(well2[figurePosition[y][0]][figurePosition[y][1]+i] != null) {
               err = true;
           }
       }

        if(err) {
            i = 0;
        }else {
            this.thisShift += i;
            currentPosition[1] += i;
            wellinit();
            drawFigure();
            repaint();
            thisShift = 0;
        }
    }
    public void setRotation(int i){
        int newRot = rotation;
        boolean varRot = true;
        if(i>0) {
            if (rotation == 3) {
                newRot = 0;
            } else {
                newRot = rotation + 1;
            }
        }
        if(i<0) {
            if (rotation == 0) {
                newRot = 3;
            } else {
                newRot = rotation - 1;
            }
        }


        for (int z = 0; z < Constants.figure[currentFigure][newRot].length; z++) {
            figurePositionTest[z][0] = currentPosition[0] + Constants.figure[currentFigure][newRot][z][0];
            figurePositionTest[z][1] = currentPosition[1] + Constants.figure[currentFigure][newRot][z][1];
        }

        for(int y = 0;y<figurePositionTest.length;y++) {
            try {
                if (well2[figurePositionTest[y][0]][figurePositionTest[y][1]] != null) {
                    varRot = false;
                }
            } catch (ArrayIndexOutOfBoundsException e) {varRot = false;}
        }

        if(varRot) {
            rotation = newRot;
            wellinit();
            drawFigure();
            repaint();
        }
    }
    public void wellinit() {
        for (int i = 0; i < well.length; i++) {
            for (int y = 0; y < well[0].length; y++) {
                well[i][y] = Constants.figureColors[9];
            }
        }
        for(int i = 0; i<well2[0].length; i++){
            well2[Constants.xSize-1][i] = Constants.figureColors[7];
        }
        System.out.println("wellinit");
    }
    public String getShift(){
        return Integer.toString(thisShift);
    }
    public void drawFigure() {
        try {
            for (int i = 0; i < Constants.figure[currentFigure][rotation].length; i++) {
                well[currentPosition[0] + Constants.figure[currentFigure][rotation][i][0]][currentPosition[1] + Constants.figure[currentFigure][rotation][i][1]]
                        = currentColor;
                figurePosition[i][0] = currentPosition[0] + Constants.figure[currentFigure][rotation][i][0];
                figurePosition[i][1] = currentPosition[1] + Constants.figure[currentFigure][rotation][i][1];
            }
        }catch (ArrayIndexOutOfBoundsException e) {}
    }
    public void dropFigure() {
        if (!isrunning) {
            System.out.println("dropFigure");
            currentPosition = dropPosition;
            currentPosition[0] = 1;
            currentPosition[1] = Constants.ySize/2;
            rotation = 1;
            thisShift = 0;
            randColor();
            System.out.println(currentFigure);
            isrunning = true;
            currentFigure = new Random().nextInt(Constants.figure.length);
        }
    }
    public void randColor() {
        currentColor = Constants.figureColors[new Random().nextInt(7)];
    }
    public void refreshCoord(){
        currentPosition[0] +=1;
    }
    public void fusion(){
        System.out.println("fusion");
        try {
            for (int i = 0; i < Constants.figure[currentFigure][rotation].length; i++) {
                well2[currentPosition[0] + Constants.figure[currentFigure][rotation][i][0]][currentPosition[1] + Constants.figure[currentFigure][rotation][i][1]] = currentColor;
            }

            isrunning = false;
        }catch(ArrayIndexOutOfBoundsException e){}

    }
    public void eraseLine(){
        boolean erase = false;
        int i = 0;
        int y=well2.length-2;
        while(y>=0) {
            for (int i1 = 0; i1 < well2[0].length; i1++) {
               if(well2[y][i1]!=null) i++;
            }

            if(i==well2[0].length){
                for(int z = y; z>0;z--) {
                    for (int i1 = 0; i1 < well2[0].length; i1++) {
                        well2[z][i1] = well2[z - 1][i1];
                    }
                    score +=1;
                }
                y++;
            }
            i=0;
            y--;
        }
    }
    public boolean gameover(){
        boolean gameover = true;
        for(int i = 0; i<well2[0].length; i++){
            if(well2[2][i]!=null){
                gameover = false;
            }
        }
        return gameover;
    }
    public int getScore(){
        return score;
    }
}

