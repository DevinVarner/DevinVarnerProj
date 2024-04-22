
public class Pictures {

    public static final String wav = null;
    private String pic;
    private int x;
    private int y;
    private int dx;
    private int dy;
    private boolean movedn;
    private boolean movert;
    private int width;
    private int height;
    public Pictures () {
        pic ="";
        x=0;
        y=0;
        dx=0;
        dy=0;
        movert=true;
        movedn=false;
        width=0;
        height=0;
    }




    public Pictures(String s, int x1, int y1, int dx1, int dy1, int w1, int h1)
    {
    pic=s;
    x=x1;
    y=y1;
    dx=0;
    dy=0;
    movert=true;
    movedn=false;
    width=w1;
    height=h1;
    }

    public Pictures(String s, int x1, int y1, int dx1, int dy1, boolean rt, boolean dn, int w1, int h1)
    {
    pic=s;
    x=x1;
    y=y1;
    dx=dx1;
    dy=dy1;
    movert=rt;
    movedn=dn;
    width=w1;
    height=h1;
    }

public void move(){
    x+=dx;
    y+=dy;
}

public void bounce(){
    if (movert){
        x+=dx;
    }
    else {
        x-=dx;
    }
    if (movedn){
        y+=dy;
    }
    else {
        y-=dy;
    }
    if(x<0)
    movert=true;
    if(y<0)
    movedn=true;
    if(x+width>800)
    movert=false;
    if(y+height>600)
    movedn=false;
}





    public Pictures(String s, int x1, int y1, int w1, int h1)
    {
        pic=s;
        x=x1;
        y=y1;
        width=w1;
        height=h1;

    }
    public int getW(){
        return width;
    }
    public int getH(){
        return height;
    }
    public String getPic() {
        return pic;
    }

    public int getx() {
        return x;
    }
    public int gety() {
        return y;
    }
public int getdx() {
        return dx;
    }
    public int getdy() {
        return dy;
    }
    public void setDx(int c) {
        dx=c;
    }
    public void setDy(int t) {
        dy=t;
    }
}