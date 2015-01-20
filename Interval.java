import java.lang.Object;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

public class Interval
{
	String[][] data;
		
	Interval(String[][] myData){
		data = myData;
	}
	public static void main(String[] args)
    {
		String[] jobList;
		Vector<Integer> timeline;
		String[][] myData;
		int x = 0;
		StringTokenizer st;
		Interval myInterval;
		
		if( args.length < 1 ){
			System.err.println("Usage:  java Interval [.txt Data File]");
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
				myData = new String[x][4];
				reader = new FileReader(file);
				buffReader = new BufferedReader(reader);
				x = 0;
				while(( s = buffReader.readLine() ) != null ){
					st = new StringTokenizer(s);
					while( st.hasMoreTokens() ){
						myData[x][0] = st.nextToken();
						myData[x][1] = st.nextToken();
						myData[x][2] = st.nextToken();
						myData[x][3] = String.valueOf(Integer.parseInt(myData[x][2])-Integer.parseInt(myData[x][1]));
					}
					x++;
				}
				myInterval = new Interval( myData );
			}
			catch(IOException e){
				return;
			}
		}
		
		/*
		for( int i = 0; i < myInterval.data.length; i++ ){
			for( int y = 0; y < myInterval.data[0].length; y++ ){
				System.out.printf("%s ", myInterval.data[i][y]);
			}
			System.out.printf("\n");
		}	*/
		
		Mergesort sorter = new Mergesort();
		sorter.sort(myInterval.data);
		int oldSize;
		/*
		for( int i = 0; i < myInterval.data.length; i++ ){
			for( int y = 0; y < myInterval.data[0].length; y++ ){
				System.out.printf("%s ", myInterval.data[i][y]);
			}
			System.out.printf("\n");
		}*/
		timeline = new Vector<Integer>(Integer.parseInt(myInterval.data[0][2]));		//creates a timeline of size = end time of smallest job
		for ( int y = 0; y < timeline.capacity(); y++ ){ 								//initialize timeline to empty
			timeline.addElement(0);
		}
		for ( int y = Integer.parseInt(myInterval.data[0][1]); y < Integer.parseInt(myInterval.data[0][2]); y++ ){
			timeline.set(y,1);
		}
		System.out.printf("%s\n", myInterval.data[0][0]);
		int fits = 1;
		for( int i = 1; i < myInterval.data.length; i++ ){
			fits = 1;
			if( Integer.parseInt(myInterval.data[i][1]) >= timeline.size() ){
				oldSize = timeline.size();
				timeline.setSize( Integer.parseInt(myInterval.data[i][2]) );
				for( int z = Integer.parseInt(myInterval.data[i][1]); z < timeline.size(); z++ ){
					timeline.set(z,1);
				}
				for( int z = oldSize; z < Integer.parseInt(myInterval.data[i][1]); z++ ){
					timeline.set(z,0);
				}
			}
			else{
				for( int z = Integer.parseInt(myInterval.data[i][1]); z < timeline.size(); z++ ){
					if( timeline.elementAt(z) == 1 ){
						fits = 0;
					}
				}
			}
			
			if( fits == 1 ){
				for( int y = Integer.parseInt(myInterval.data[i][1]); y < Integer.parseInt(myInterval.data[i][2]); y++ ){
					timeline.set(y,1);
				}
				System.out.printf("%s\n", myInterval.data[i][0]);
			}
		}/*
		System.out.printf("\nScheduling looks like: ");
		
		for( int i = 0; i < timeline.size(); i++ ){
			System.out.printf("%d", timeline.elementAt(i));
		}*/
		System.out.println();
	}

}