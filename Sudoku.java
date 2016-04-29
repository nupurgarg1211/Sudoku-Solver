/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;
import java.io.*;

/**
 *
 * @author nupur
 */
public class Sudoku
{

    public static void main(String[] args) throws Exception 
    {
        int order,num,i,j,empty_cell1,empty_cell2,num_empty_cell=0;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	System.out.println("enter the order of the sudoku");
        
        order=Integer.parseInt(br.readLine());
	num=order*order;
	SudokuStructure new_cell[][]=new SudokuStructure[num][num];
	for(i=0;i<num;i++)
	{
            for(j=0;j<num;j++)
                new_cell[i][j]=new SudokuStructure();
	}
        SudokuLogic sl=new SudokuLogic(order,num,num_empty_cell);
        System.out.println("enter the data for sudoku");
	sl.input_data(new_cell);
	
	//sl.print_data(new_cell);
	sl.find_p_value(new_cell);
	
	while(true)
	{
		
		empty_cell1=sl.rule1(new_cell);
                sl.rule3(new_cell);
                sl.rule4(new_cell);
          	empty_cell2=sl.rule2(new_cell);
                
		if(empty_cell1==empty_cell2)
		{
			break;
		}
	}
       
        
	if(sl.num_empty_cell==0)
        {
            System.out.println("Solved sudoku-");
            sl.print_data(new_cell);
        }
	else
        {
           // System.out.println("number of empty cells(After)="+sl.num_empty_cell);
            DancingLinkTable dt = new DancingLinkTable(order);
        
            int rowcount=dt.fillDLTable(new_cell);
           // dt.print();
            //System.out.println("row="+rowcount);
            DlLogic dl=new DlLogic(order);
            boolean flag=dl.sudokufill(dt);
            dl.finalSolution(new_cell);
            System.out.println("Solved sudoku-");
            sl.print_data(new_cell);  
            // System.out.println("number of empty cells(After dl)="+sl.num_empty_cell);
        }
    }
}
