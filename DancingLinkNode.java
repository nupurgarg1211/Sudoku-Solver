
package sudoku;

/**
 *
 * @author nupur
 */
class SudokuStructure 
{
        int possible_val[];
	int value;
	int counter;
}

public class DancingLinkNode
{
    DancingLinkNode left;
    DancingLinkNode right,up,down;
    int row,col,nodeCount;
    SudokuNode sn=null;
    public DancingLinkNode(int row,int col) 
    {
        this.row=row;
        this.col=col;
        this.nodeCount=0;
       left=right=up=down=this;
        
    }
    public void InitializePointer(DancingLinkNode leftP,DancingLinkNode downP)
    {
        if(leftP!=null)
        {    
            left=leftP.left;
            right=leftP;
            left.right=this;
            right.left=this;
        }
        if(downP!=null)
        {    
            up=downP.up;
            down=downP;
            up.down=this;
            down.up=this;
        }   
    }
}
class SudokuNode
{
int row,col,value;

    public SudokuNode(int r,int c,int v)
    {
        row=r;
        col=c;
        value=v;
    }

}
