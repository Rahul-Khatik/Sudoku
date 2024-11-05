package sudoku.userinterface;

import java.awt.*;

public class SudokuTextField extends TextField {
    private final int x;
    private final int y;

//    throws HeadlessException
    public SudokuTextField(int x, int y)  {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void replaceText(int i,int i1,String s){
        if(!s.matches("[0-9]")){
            super.replaceText(i,i1,s);
        }
    }

    @Override
    public void replaceSelection(String s){
        if(!s.matches("[0-9]")){
            super.replaceSelection(s);
        }
    }
}
