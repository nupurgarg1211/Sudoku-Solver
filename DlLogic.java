
package sudoku;
import java.util.Stack;

/**
 *
 * @author nupur
 */
public class DlLogic
{
    int order;
    DancingLinkTable dt;
    Stack<SudokuNode> Final=new Stack<SudokuNode>();
  //  DancingLinkNode start=null;
    public DlLogic(int order)
    {
        order=this.order;
    }
    public DancingLinkNode FindMinColheader(DancingLinkTable dt)
    {
        DancingLinkNode dn=null;
        int min=Integer.MAX_VALUE;
        for(DancingLinkNode d=dt.start.right;d!=dt.start;d=d.right)
        {
            if(d.nodeCount<min)
            {
                dn=d;
                min=d.nodeCount;
            }
        }
        return dn;
    }
    public boolean sudokufill(DancingLinkTable dt)
    {
      
        if(dt.start.right==dt.start)
            return true;
      
      DancingLinkNode dn=FindMinColheader(dt);
       cover(dn.col,dt);
     
       for(DancingLinkNode i=dn.down; i!=dn; i=i.down)
       {
            Final.push(i.sn);
         // System.out.println("i:"+i.sn.row+" j:"+i.sn.col+" value:"+i.sn.value);
            for(DancingLinkNode j=i.right; j!=i; j=j.right)
            {
		cover(j.col,dt);
            }
           
            if(sudokufill(dt))
            {
                return true;
            }
            for(DancingLinkNode j=i.left; j!=i; j=j.left)
            {
		uncover(j.col,dt);
            }
            Final.pop();
	}
	uncover(dn.col,dt);
	return false;
    }
    public void cover(int loc,DancingLinkTable dt)
    {
    	DancingLinkNode temp = dt.columnHeader[loc];
	
        temp.right.left=temp.left;
	temp.left.right=temp.right;
	for(DancingLinkNode i=temp.down; i!=temp; i=i.down)
	{
		for(DancingLinkNode j=i.right; j!=i; j=j.right)
		{        		
                        j.up.down=j.down;
                        j.down.up=j.up;
			dt.columnHeader[j.col].nodeCount--;
		}
	}
    }
    public void uncover(int index,DancingLinkTable dt)
    {
	DancingLinkNode temp = dt.columnHeader[index];
	
        temp.right.left=temp.left;
	temp.left.right=temp.right;
	for(DancingLinkNode i=temp.up; i!=temp; i=i.up)
	{
            for(DancingLinkNode j=i.left; j!=i; j=j.left)
            {
		dt.columnHeader[j.col].nodeCount++;		
                j.down.up=j;
		j.up.down=j;
            }
	}	
        temp.left.right= temp;
	temp.right.left= temp;
    }
	
    public void finalSolution(SudokuStructure new_cell[][])
    {
	while(!Final.isEmpty())
	{
        	SudokuNode sn = Final.pop();
		new_cell[sn.row][sn.col].value = sn.value;
               // System.out.print("row="+sn.row+"col="+sn.col);
	}
    }
      
}
