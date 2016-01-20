import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 
 */

/**
 * @author harinder
 *
 */
public class Solution {
	
	static class Node{
		int A[][];
		
		public Node(){
			this.A = new int[3][9];
		}
		
		public int getRowCount(int r){
			int count = 0;
			for(int i=0;i<9;i++){
				if(A[r][i]!=0) count++;
			}
			
			return count;
		}
		
		public int getColCount(int c){
			int count = 0;
			for(int i=0;i<3;i++){
				if(A[i][c]!=0) count++;
			}
			
			return count;
		}
	}
	
	static int getRand(int min, int max){
		Random rand = new Random();
		return rand.nextInt(max - min + 1) + min;
	}
	
	static int getNumberOfElementsInSet(List<List<Integer>> set){
		int count = 0;
		for(List<Integer> li : set) count += li.size();
		return count;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node []nodes = new Node[6];
		for(int i=0;i<6;i++){
			nodes[i] = new Node();
		}
		
		List<Integer> l1 = new ArrayList<Integer>();
		for(int i=1;i<=9;i++){
			l1.add(i);
		}
		
		List<Integer> l2 = new ArrayList<Integer>();
		for(int i=10;i<=19;i++){
			l2.add(i);
		}
		
		List<Integer> l3 = new ArrayList<Integer>();
		for(int i=20;i<=29;i++){
			l3.add(i);
		}
		
		List<Integer> l4 = new ArrayList<Integer>();
		for(int i=30;i<=39;i++){
			l4.add(i);
		}
		
		List<Integer> l5 = new ArrayList<Integer>();
		for(int i=40;i<=49;i++){
			l5.add(i);
		}
		
		List<Integer> l6 = new ArrayList<Integer>();
		for(int i=50;i<=59;i++){
			l6.add(i);
		}
		
		List<Integer> l7 = new ArrayList<Integer>();
		for(int i=60;i<=69;i++){
			l7.add(i);
		}
		
		List<Integer> l8 = new ArrayList<Integer>();
		for(int i=70;i<=79;i++){
			l8.add(i);
		}
		
		List<Integer> l9 = new ArrayList<Integer>();
		for(int i=80;i<=90;i++){
			l9.add(i);
		}
		
		List<List<Integer>> columns = new ArrayList<List<Integer>>();
		columns.add(l1);
		columns.add(l2);
		columns.add(l3);
		columns.add(l4);
		columns.add(l5);
		columns.add(l6);
		columns.add(l7);
		columns.add(l8);
		columns.add(l9);
		
		
		List<List<Integer>> set1 = new ArrayList<List<Integer>>();
		List<List<Integer>> set2 = new ArrayList<List<Integer>>();
		List<List<Integer>> set3 = new ArrayList<List<Integer>>();
		List<List<Integer>> set4 = new ArrayList<List<Integer>>();
		List<List<Integer>> set5 = new ArrayList<List<Integer>>();
		List<List<Integer>> set6 = new ArrayList<List<Integer>>();
		
		for(int i=0;i<9;i++){
			set1.add(new ArrayList<Integer>());
			set2.add(new ArrayList<Integer>());
			set3.add(new ArrayList<Integer>());
			set4.add(new ArrayList<Integer>());
			set5.add(new ArrayList<Integer>());
			set6.add(new ArrayList<Integer>());
		}
		
		List<List<List<Integer>>> sets = new ArrayList<List<List<Integer>>>();
		
		sets.add(set1);
		sets.add(set2);
		sets.add(set3);
		sets.add(set4);
		sets.add(set5);
		sets.add(set6);
		
		//assigning elements to each set for each column
		for(int i=0;i<9;i++) {
			List<Integer> li = columns.get(i);
			for(int j=0;j<6;j++){
				int randNumIndex = getRand(0, li.size()-1);
				int randNum = li.get(randNumIndex);
			
				List<Integer> set = sets.get(j).get(i);
				set.add(randNum);
				
				li.remove(randNumIndex);
			}
		}
		
		//assign element from last column to random set
		List<Integer> lastCol = columns.get(8);
		int randNumIndex = getRand(0, lastCol.size()-1);
		int randNum = lastCol.get(randNumIndex);
		
		int randSetIndex = getRand(0,sets.size()-1);
		List<Integer> randSet = sets.get(randSetIndex).get(8);
		randSet.add(randNum);
		
		lastCol.remove(randNumIndex);
		
		//3 passes over the remaining columns
		for(int pass=0;pass<3;pass++) {
			for(int i=0;i<9;i++) {
				List<Integer> col = columns.get(i);
				if(col.size()==0) continue;
				
				int randNumIndex_p = getRand(0, col.size()-1);
				int randNum_p = col.get(randNumIndex_p);
				
				boolean vacantSetFound = false;
				while(!vacantSetFound) {
					int randSetIndex_p = getRand(0,sets.size()-1);
					List<List<Integer>> randSet_p = sets.get(randSetIndex_p);
					
					if(getNumberOfElementsInSet(randSet_p)==15 || randSet_p.get(i).size()==2) continue;
					
					vacantSetFound = true;
					randSet_p.get(i).add(randNum_p);
					
					col.remove(randNumIndex_p);
				}
			}
		}
		
		//one more pass over the remaining columns
		for(int i=0;i<9;i++) {
			List<Integer> col = columns.get(i);
			if(col.size()==0) continue;
			
			int randNumIndex_p = getRand(0, col.size()-1);
			int randNum_p = col.get(randNumIndex_p);
			
			boolean vacantSetFound = false;
			while(!vacantSetFound) {
				int randSetIndex_p = getRand(0,sets.size()-1);
				List<List<Integer>> randSet_p = sets.get(randSetIndex_p);
				
				if(getNumberOfElementsInSet(randSet_p)==15 || randSet_p.get(i).size()==3) continue;
				
				vacantSetFound = true;
				randSet_p.get(i).add(randNum_p);
				
				col.remove(randNumIndex_p);
			}
		}
		
		//sort the internal sets
		for(int i=0;i<6;i++){
			for(int j=0;j<9;j++){
				Collections.sort(sets.get(i).get(j));
			}
		}
		
		
		//got the sets - need to arrange in tickets now
		for(int setIndex=0;setIndex<6;setIndex++) {
			List<List<Integer>> currSet = sets.get(setIndex);
			Node currTicket = nodes[setIndex];
			
			//fill first row 
			for(int size=3;size>0;size--){
				if(currTicket.getRowCount(0)==5) break;
				for(int colIndex=0;colIndex<9;colIndex++){
					if(currTicket.getRowCount(0)==5) break;
					if(currTicket.A[0][colIndex]!=0) continue;
					
					List<Integer> currSetCol = currSet.get(colIndex);
					if(currSetCol.size()!=size) continue;
					
					currTicket.A[0][colIndex]=currSetCol.remove(0);
	 			}
			}
			
			//fill second row 
			for(int size=2;size>0;size--){
				if(currTicket.getRowCount(1)==5) break;
				for(int colIndex=0;colIndex<9;colIndex++){
					if(currTicket.getRowCount(1)==5) break;
					if(currTicket.A[1][colIndex]!=0) continue;
					
					List<Integer> currSetCol = currSet.get(colIndex);
					if(currSetCol.size()!=size) continue;
					
					currTicket.A[1][colIndex]=currSetCol.remove(0);
	 			}
			}
			
			//fill third row 
			for(int size=1;size>0;size--){
				if(currTicket.getRowCount(2)==5) break;
				for(int colIndex=0;colIndex<9;colIndex++){
					if(currTicket.getRowCount(2)==5) break;
					if(currTicket.A[2][colIndex]!=0) continue;
					
					List<Integer> currSetCol = currSet.get(colIndex);
					if(currSetCol.size()!=size) continue;
					
					currTicket.A[2][colIndex]=currSetCol.remove(0);
	 			}
			}		
		}
		
		//print the tickets
		for(int i=0;i<6;i++){
			Node currTicket = nodes[i];
			
			for(int r=0;r<3;r++){
				for(int col=0;col<9;col++){
					int num = currTicket.A[r][col];
					if(num!=0) System.out.print(num);
					
					if(col!=8) System.out.print(",");
				}
				if(r!=2) System.out.println();
			}
			
			if(i!=5){
				System.out.println();
				System.out.println();
				System.out.println();
			}
		}
	}

}
