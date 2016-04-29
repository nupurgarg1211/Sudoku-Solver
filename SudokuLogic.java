
package sudoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author nupur
 */
public class SudokuLogic 
{
    int order,num,num_empty_cell,row_i,col_i;
    public SudokuLogic(int order,int num,int num_empty_cell)
    {
    this.order=order;
    this.num=num;
    this.num_empty_cell=num_empty_cell;
    }
    Scanner sc=new Scanner(System.in);
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public void input_data(SudokuStructure new_cell[][]) throws IOException
    {
	
	int i,j,k;
        for(i=0;i<num;i++)
	{
                String s=br.readLine();
		String s1[]=s.split("\\s+");
		for(j=0;j<num;j++)
		{
			new_cell[i][j].value=Integer.parseInt(s1[j]);
			new_cell[i][j].possible_val=new int[num];
			if(new_cell[i][j].value==0)
			{
				num_empty_cell++;
				for(k=0;k<num;k++)
				{
					new_cell[i][j].possible_val[k]=1;	
				}
			}
			else
			{
				for(k=0;k<num;k++)
				{
					if(k==new_cell[i][j].value-1)
					{
						new_cell[i][j].possible_val[k]=1;
					}
					else
					{
						new_cell[i][j].possible_val[k]=0;
					}	
				}
					
			}
			new_cell[i][j].counter=num;		
		}	
	}
}
public void print_data(SudokuStructure new_cell[][])
{	
	int i,j,k;
	for(i=0;i<num;i++)
	{		
		for(j=0;j<num;j++)
		{			
				System.out.print(new_cell[i][j].value+" ");
		}
		System.out.print("\n");
	}
}

public void find_p_value(SudokuStructure new_cell[][])
{
	int l,m,row_i,col_i,k,i,j;
	SudokuLogic sl=new SudokuLogic(order, num, num_empty_cell);
	for(i=0;i<num;i++)
	{
		for(j=0;j<num;j++)
		{
			k=0;
			if(new_cell[i][j].value==0)
			{
				for(k=0;k<num;k++)
				{
					if(new_cell[i][k].value!=0 && new_cell[i][j].possible_val[new_cell[i][k].value-1]==1)
					{
						new_cell[i][j].possible_val[new_cell[i][k].value-1]=0;
						new_cell[i][j].counter--;	
					}
					if(new_cell[k][j].value!=0 && new_cell[i][j].possible_val[new_cell[k][j].value-1]==1)
					{
						new_cell[i][j].possible_val[new_cell[k][j].value-1]=0;	
						new_cell[i][j].counter--;
					}
				}
				getIndex(i,j,this.row_i,this.col_i);
				for(l=this.row_i;l<this.row_i+order;l++)
				{
					for(m=this.col_i;m<this.col_i+order;m++)
					{
						if(new_cell[l][m].value!=0 && new_cell[i][j].possible_val[new_cell[l][m].value-1]==1)
						{
							new_cell[i][j].possible_val[new_cell[l][m].value-1]=0;
							new_cell[i][j].counter--;
						}	
					}
				}
			}
			
		}
		
	}


}
public void update_p_value(int i,int j,SudokuStructure new_cell[][])
{
	int k,m,l,row_i,col_i;
	for(k=0;k<num;k++)
	{
		if(new_cell[i][k].value==0 && new_cell[i][k].possible_val[new_cell[i][j].value-1]==1)
		{
			new_cell[i][k].possible_val[new_cell[i][j].value-1]=0;
			new_cell[i][k].counter--;	
		}
		if(new_cell[k][j].value==0 && new_cell[k][j].possible_val[new_cell[i][j].value-1]==1)
		{
			new_cell[k][j].possible_val[new_cell[i][j].value-1]=0;	
			new_cell[k][j].counter--;
		}
	}
	getIndex(i,j,this.row_i,this.col_i);
	for(l=this.row_i;l<this.row_i+order;l++)
	{
		for(m=this.col_i;m<this.col_i+order;m++)
		{
			if(new_cell[l][m].value==0 && new_cell[l][m].possible_val[new_cell[i][j].value-1]==1)
			{
				new_cell[l][m].possible_val[new_cell[i][j].value-1]=0;
				new_cell[l][m].counter--;
			}	
		}
	}
	
	


}
public void print_p_value(SudokuStructure new_cell[][])
{
	int i,j,k;
	for(i=0;i<num;i++)
	{
		for(j=0;j<num;j++)
		{
			k=0;
			System.out.print("cell["+i+"]["+j+"]->");
			for(k=0;k<num;k++)
			{
				System.out.print(new_cell[i][j].possible_val[k]);	
			}
			System.out.print("\n");
		}
	}
	

}
void getIndex(int row,int col,int row_i,int col_i)
{
	int k;
	for(k=1;;k++)
	{
		if(row<order*k)
		{
			this.row_i=order*(k-1);
			break;
		}
	}
	k=0;
	for(k=1;;k++)
	{
		if(col<order*k)
		{
			this.col_i=order*(k-1);
			break;
		}
	}

}

int rule1(SudokuStructure new_cell[][])
{
	int i,j,k;
	for(i=0;i<num;i++)
	{
		for(j=0;j<num;j++)
		{
			k=0;
			if(new_cell[i][j].counter==1)
			{
				
				for(k=0;k<num;k++)
				{
					if(new_cell[i][j].possible_val[k]==1)
					{
						new_cell[i][j].value=k+1;
						new_cell[i][j].counter=0;
						num_empty_cell--;
						update_p_value(i,j,new_cell);
					}
				}
				
			}	
		}	
	
	}
	return num_empty_cell;
}

public int rule2(SudokuStructure new_cell[][])
{
	int i,j,count=0,l,m,loc=0;
	int a[]=new int[num];
	for(i=0;i<num;i++)
	{
		for(j=0;j<num;j++)
		{
			count=0;
			if(new_cell[i][j].value==0)
			{
				for(m=0;m<num;m++)
				{
					a[m]=0;
				}
				for(l=0;l<num;l++)
				{
					if(j==l)
					continue;
					for(m=0;m<num;m++)
					{
						a[m]=a[m]|new_cell[i][l].possible_val[m];
					}
					
				}
				for(m=0;m<num;m++)
				{
					if(a[m]==0)
					{
						count++;
						loc=m;
					}
				}
				if(count==1)
				{
					new_cell[i][j].value=loc+1;
					new_cell[i][j].counter=0;
					num_empty_cell--;
					update_p_value(i,j,new_cell);
					for(m=0;m<num;m++)
					{
						if(m!=loc)
						{
							new_cell[i][j].possible_val[m]=0;
						}
					}
				}
			}
		}
	}
	return num_empty_cell;
    }


void rule3(SudokuStructure new_cell[][])
{
	int loc1=0,loc2=0,i,j,l,count=0,zero_count=0,m,k;
	int a[]=new int[num];
	for(i=0;i<num;i++)
	{
		int r,loc3;
		for(j=0;j<num;j++)
		{			
			if(new_cell[i][j].value==0)
			{
			}
			else
			continue;
			for(r=j+1;r<num;r++)
			{
				for(m=0;m<num;m++)
				{
					a[m]=0;
				}
				count=0;
				zero_count=0;
				if(new_cell[i][j].value==0)
				{
					for(l=0;l<num;l++)
					{
						a[l]=a[l]+new_cell[i][j].possible_val[l];
											
					}
					
				}
				if(new_cell[i][r].value==0)
				{
					for(l=0;l<num;l++)
					{
						a[l]=a[l]+new_cell[i][r].possible_val[l];
					}
					for(l=0;l<num;l++)
					{
						if(a[l]==2)
						{
							count++;
							if(count==1)
							loc1=l;
							else
							loc2=l;
						}						
					}
					if(count==2)
					{
						for(l=0;l<num;l++)
						{
							if(a[l]==0)
							{
								zero_count++;	
							}
						}
					}					
					if(count==2 && zero_count==num-count)
					{
                                            for(k=0;k<num;k++)
                                            {	
						if(k==r || k==j)
						{
                                                    continue;
						}
						if(new_cell[i][k].possible_val[loc1]==1)
						{
							new_cell[i][k].possible_val[loc1]=0;
							new_cell[i][k].counter=new_cell[i][k].counter-1;
						}
						if(new_cell[i][k].possible_val[loc2]==1)
						{
							new_cell[i][k].possible_val[loc2]=0;
							new_cell[i][k].counter=new_cell[i][k].counter-1;
						}
                                            }			
        				}
                			else 
                        		continue;
                               	}
                            }
                           
	for(i=0;i<num;i++)
	{
		r=0;
                loc3=0;		
		for(j=0;j<num;j++)
		{		
			if(new_cell[j][i].value==0)
			{
			}
			else
			continue;
			
			for(r=j+1;r<num;r++)
			{
				for(m=0;m<num;m++)
				{
					a[m]=0;
				}
				count=0;
				zero_count=0;
				if(new_cell[j][i].value==0)
				{
					for(l=0;l<num;l++)
					{
						a[l]=a[l]+new_cell[j][i].possible_val[l];						
					}
					loc3=j;
				}
				if(new_cell[r][i].value==0)
				{					
					for(l=0;l<num;l++)
					{
						a[l]=a[l]+new_cell[r][i].possible_val[l];						
					}
					for(l=0;l<num;l++)
					{
						if(a[l]==2)
						{
							count++;
							if(count==1)
							loc1=l;
							else
							loc2=l;						
						}				
					}
					if(count==2)
					{
						for(l=0;l<num;l++)
						{
							if(a[l]==0)
							{
								zero_count++;	
							}
						}
					}					
					if(count==2 && zero_count==num-count)
					{
						for(k=0;k<num;k++)
						{	
							if(k==r || k==j)
							{
								continue;
							}
							if(new_cell[k][i].possible_val[loc1]==1)
							{
								new_cell[k][i].possible_val[loc1]=0;
								new_cell[k][i].counter=new_cell[k][i].counter-1;
							}
							if(new_cell[k][i].possible_val[loc2]==1)
							{
								new_cell[k][i].possible_val[loc2]=0;
								new_cell[k][i].counter=new_cell[k][i].counter-1;
							}
						}
					}			

				}
				else 
				continue;
				
                	}
                    }
                }	
            }
        }
}

void rule4(SudokuStructure new_cell[][])
{
	int i,j,r,loc1=0,loc2=0,loc3=0,m,l,skip_loc1=0,skip_loc2=0,skip_loc3=0,count1=0,count=0,count2=0,k;
	for(i=0;i<num;i++)
	{
		for(j=0;j<num;j++)
		{
			
			if(new_cell[i][j].value==0)
			{	
				count=0;count1=0;count2=0;		
				for(l=0;l<num;l++)
				{
					if(new_cell[i][j].possible_val[l]!=0)
					{
						count++;
						if(count==1)
						{
							loc3=l;
						}
						else if(count==2)
						{
							loc2=l;
						}
						else if(count==3)
						{
							loc1=l;
							skip_loc3=j;	
						}				
						
					}
				

				}
				if(count!=3)
				{
					continue;
				}
				else
				{
					count1=0;
					for(k=0;k<num;k++)
					{
						if(k==j||new_cell[i][k].value!=0)
						{
							continue;
						}
						if(new_cell[i][k].counter==2)
						{
							if((new_cell[i][k].possible_val[loc1]==1 && new_cell[i][k].possible_val[loc2]==1)||(new_cell[i][k].possible_val[loc3]==1 && new_cell[i][k].possible_val[loc1]==1)||(new_cell[i][k].possible_val[loc2]==1 && new_cell[i][k].possible_val[loc3]==1))
							{
								count1++;
								if(count1==1)
								{
									skip_loc1=k;
								}
								if(count1==2)
								{
									skip_loc2=k;
								}
								
							}
						}
						else if(new_cell[i][k].counter==3)
						{
							if(new_cell[i][k].possible_val[loc1]==1 && new_cell[i][k].possible_val[loc2]==1 && new_cell[i][k].possible_val[loc3]==1)
							{
								count2++;
								if(count2==1)
								{
									skip_loc1=k;
								}
								if(count2==2)
								{
									skip_loc2=k;
								}
							}
						}
					}
					if(count1==2||count2==2)
					{
						for(k=0;k<num;k++)
						{
							if(k==skip_loc1 ||k==skip_loc2||new_cell[i][k].value!=0||k==skip_loc3)
							{
								continue;
							}
						
							else
							{
								if(new_cell[i][k].possible_val[loc1]==1)
								{
									new_cell[i][k].possible_val[loc1]=0;
									new_cell[i][k].counter--;
								}
								if(new_cell[i][k].possible_val[loc2]==1)
								{
									new_cell[i][k].possible_val[loc2]=0;
									new_cell[i][k].counter--;
								}
								if(new_cell[i][k].possible_val[loc3]==1)
								{
									new_cell[i][k].possible_val[loc3]=0;
									new_cell[i][k].counter--;
								}
							}
						}					
							
					}
					
				}
			}	
		}
       	}
    }

}