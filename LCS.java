import java.lang.Object;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

public class LCS
{
	String[][] data;
		
	LCS(String[][] myData){
		data = myData;
	}
	public static void main(String[] args)
    {
		int x = 0;
		StringTokenizer st;
		LCS myLCS;
		String myData[][];
		
		if( args.length < 1 ){
			System.err.println("Usage:  java LCS [.txt Data File]");
			return;
		}
		else{
			File file = new File(args[0]);
			
			try{
				FileReader reader = new FileReader(file);
				BufferedReader buffReader = new BufferedReader(reader);
				String s;
				
				//Figures out points and dimensions 
				while(( s = buffReader.readLine() ) != null ){
					x++;
				}
				myData = new String[x][2];
				reader = new FileReader(file);
				buffReader = new BufferedReader(reader);
				x = 0;
				while(( s = buffReader.readLine() ) != null ){
					st = new StringTokenizer(s);
					while( st.hasMoreTokens() ){
						myData[x][0] = st.nextToken();
						myData[x][1] = st.nextToken();
					}
					x++;
				}
				myLCS = new LCS( myData );
			}
			catch(IOException e){
				return;
			}
		}
		/*
		for( int i = 0; i < myLCS.data.length; i++ ){
			for( int y = 0; y < myLCS.data[0].length; y++ ){
				System.out.printf("%s ", myLCS.data[i][y]);
			}
			System.out.println();
		}*/
		String thisLCS;
		for( int i = 0; i < myLCS.data.length; i++ ){
			thisLCS = LCSof(myLCS.data[i][0], myLCS.data[i][1]);
			System.out.printf("%s\n", thisLCS);
		}
		
	}
	
	public static String LCSof(String X, String Y){
		String toReturn = "";
		int m = X.length()+1;
		int n = Y.length()+1;
		int[][] B = new int[m][n];
		int[][] C = new int[m+1][n];
		for( int i = 1; i < m+1; i++ ){
			C[i][0] = 0;
		}
		for( int i = 0; i < n; i++ ){
			C[0][i] = 0;
		}

		for( int i = 1; i < m; i++ ){
			for( int j = 1; j < n; j++ ){
				if( X.charAt(i-1) == Y.charAt(j-1) ){
					C[i][j] = C[i-1][j-1] + 1;
					B[i][j] = 3;
				}
				else if( C[i-1][j] >= C[i][j-1] ){
					C[i][j] = C[i-1][j];
					B[i][j] = 2;
				}
				else{
					C[i][j] = C[i][j-1];
					B[i][j] = 1;
				}
			}
		}/*
		System.out.println();
		for( int i = 0; i < m; i++ ){
				
			for( int j = 0; j < n; j++ ){
				System.out.printf("%d ", C[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		for( int i = 0; i < m; i++ ){
				
			for( int j = 0; j < n; j++ ){
				System.out.printf("%d ", B[i][j]);
			}
			System.out.println();
		}*/
		
		int maxX, maxY, currentMax;
		maxX = 0;
		maxY = 0;
		currentMax = 0;
		for( int i = 0; i < m; i++ ){
				
			for( int j = 0; j < n; j++ ){
				if( C[i][j] > currentMax ){
					currentMax = C[i][j];
					maxX = i;
					maxY = j;
				}
			}
		}
		//System.out.printf("maxY is %d maxX is %d\nCurrentmax is %d\n", maxY, maxX, currentMax);
		int j = maxY;
		int i = maxX;
		while( currentMax > 0 ){
			if( B[i][j] == 3 ){
				//System.out.printf("i is %d and j is %d ", i, j);
				toReturn += X.charAt(i-1);
				i--;
				j--;
				currentMax--;
			}
			else if( B[i][j] == 2){
				i--;
			}
			else{
				j--;
			}
		}
		toReturn = new StringBuilder(toReturn).reverse().toString();
		return toReturn;
	}
}