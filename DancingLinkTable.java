
package sudoku;

/**
 *
 * @author nupur
 */
public class DancingLinkTable
{
    DancingLinkNode start,columnHeader[];
    int order,num;
    int tableRow=0;
    public DancingLinkTable(int order)
    {
        this.order=order;
        this.num=order*order;
        start=new DancingLinkNode(-1, -1);
       // start.InitializePointer(null,null);
        columnHeader=new DancingLinkNode[4*num*num];
        for(int i=0;i<4*num*num;i++)
        {
            columnHeader[i]=new DancingLinkNode(-1, i);
            columnHeader[i].InitializePointer(start,null);
        }    
    }
    public void print()
    {
        for(DancingLinkNode i=start.right;i!=start;i=i.right)
        {
            for(DancingLinkNode j=i.down;j!=i;j=j.down)
            System.out.println("nodecount="+i.nodeCount+"j.col="+j.sn.col+"j.row="+j.sn.row);
        }       
    
    }
    public int fillDLTable(SudokuStructure new_cell[][])
    {
        for(int i=0;i<num;i++)
        {
            for(int j=0;j<num;j++)
            {
                if(new_cell[i][j].value==0)
                {
                     
                    for(int k=0;k<num;k++)
                    {
                        int columnCell=i*num+j;
                        int columnR=((num*num)-1)+((i*num)+k+1);
                        int columnC=(((2*num*num)-1)+(j*num)+k+1);
                        int columnG=(((3*num*num)-1)+((((i/order)*order)+(j/order))*num)+k+1);              
                        if(new_cell[i][j].possible_val[k]==1)
                        {
                            SudokuNode node=new SudokuNode(i,j,k+1);
                            DancingLinkNode dn=null;
                            DancingLinkNode Dnode=new DancingLinkNode(tableRow,columnCell);
                            Dnode.InitializePointer(Dnode,columnHeader[columnCell]);
                            columnHeader[columnCell].nodeCount++;
                            Dnode.sn=node;
                            
                            dn=new DancingLinkNode(tableRow, columnR);
                            dn.InitializePointer(Dnode,columnHeader[columnR]);
                            columnHeader[columnR].nodeCount++;
                            dn.sn=node;
                              
                            dn=new DancingLinkNode(tableRow, columnC);
                            dn.InitializePointer(Dnode,columnHeader[columnC]);
                            columnHeader[columnC].nodeCount++;
                            dn.sn=node;
                              
                            dn=new DancingLinkNode(tableRow, columnG);
                            dn.InitializePointer(Dnode,columnHeader[columnG]);
                            columnHeader[columnG].nodeCount++;
                            dn.sn=node;
                            
                            tableRow++;
                        }
                                           
                    }
                    
                }
                else
                {
                    int columnCell=i*num+j;
                    int columnR=((num*num)-1)+((i*num)+new_cell[i][j].value);
                    int columnC=(((2*num*num)-1)+(j*num)+new_cell[i][j].value);
                    int columnG=(((3*num*num)-1)+((((i/order)*order)+(j/order))*num)+new_cell[i][j].value);  
                    SudokuNode node=new SudokuNode(i, j,new_cell[i][j].value);
                    DancingLinkNode Dnode=new DancingLinkNode(tableRow,columnCell);
                    Dnode.InitializePointer(Dnode,columnHeader[columnCell]);
                    columnHeader[columnCell].nodeCount++;
                    Dnode.sn=node;
                    DancingLinkNode dn=new DancingLinkNode(tableRow,columnR);
                    dn.InitializePointer(Dnode,columnHeader[columnR]);
                    columnHeader[columnR].nodeCount++;
                    dn.sn=node;
                    dn=new DancingLinkNode(tableRow,columnC);
                    dn.InitializePointer(Dnode,columnHeader[columnC]);
                    columnHeader[columnC].nodeCount++; 
                    dn.sn=node;
                    dn=new DancingLinkNode(tableRow,columnG);
                    dn.InitializePointer(Dnode,columnHeader[columnG]);
                    columnHeader[columnG].nodeCount++; 
                    dn.sn=node;
                    tableRow++;
                }
            }
        }
        return tableRow;
    } 
}
