import java.util.*;
import java.io.*;

public class bankersAlgorithm {
	static int n;
	static int m;
	static String mode;
	
	static int[][] current;
	static int[][] needed;
	static int[] available;
	static int[] availableAtStart;
	static boolean[] ended;
	static int numFinished;
	static int indexLambda;
	static int lambdaBefore;
	static int lambdaNow;
	static int lambdaHighestWorking;
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
		BufferedReader br = new BufferedReader(new FileReader("example8.txt"));			//insert your file here
		
		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		m = Integer.parseInt(line[1]);
		mode = line[2];
		
		current = new int[n][m];
		needed = new int[n][m];
		available = new int[m];
		availableAtStart = new int[m];
		ended = new boolean[n];
		
		for (int i=0; i< n; i++){
			ended[i] = false;
			line = br.readLine().split(" ");
			for (int j=0; j<m; j++){
				current[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		for (int i=0; i< n; i++){
			line = br.readLine().split(" ");
			for (int j=0; j<m; j++){
				needed[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		line = br.readLine().split(" ");
		for (int i=0; i<m; i++){
			available[i] = Integer.parseInt(line[i]);
			availableAtStart[i] = available[i];
		}
		
		if(mode.equals("M")){
			for(int i=0; i<n; i++){
				for(int j=0; j<m; j++){
					needed[i][j]-=current[i][j];
				}
			}
		}
		
		lambdaNow = -1;
		for(int i=0; i<m; i++){
			if(available[i] == -1){
				indexLambda = i;
				for(int j=0; j<n; j++){
					if(available[indexLambda]<needed[j][i]){
						available[indexLambda] = needed[j][i];
						lambdaNow = available[indexLambda];
					}
				}
			}
		}
		
		if(available[indexLambda] == lambdaNow){
			while(lambdaBefore != lambdaNow){
				System.out.println("Testing for lambda _"+lambdaNow+"_.");
				numFinished = 0;
				
				for (int i=0; i<m; i++){
				available[i] = availableAtStart[i];
				}
				available[indexLambda] = lambdaNow;
				
				for (int i=0; i< n; i++){
				ended[i] = false;
				}
				compute();
			}
			System.out.println("Smallest value of lambda for which the system is in a safe state _"+lambdaHighestWorking+"_.");
		}else{
			compute();
		}
	}
	
	public static void compute(){
		
			while (numFinished != n){
				
				int p = -1;
					
				for (int i=0; i<n; i++){
					if (ended[i]){
						continue;
					}
					boolean everythingOk = true;
					for (int j=0; j<m; j++){
						if (needed[i][j] > available[j]){
							everythingOk = false;
							break;
						}
					}
					if (everythingOk){
						p = i;
						break;
					}
				}
					
				if (p == -1){
					System.out.println("System is not in a safe state!!");
					System.out.println("-------------------------------");
					lambdaBefore = lambdaNow;
					lambdaNow = (lambdaHighestWorking + lambdaBefore) / 2;
					return;
				}
					
				System.out.println("Process #" +p+ " has ended!");
				ended[p] = true;
				numFinished++;
				for (int j=0; j<m; j++){
					available[j] += current[p][j];
				}
			}
		
		System.out.println("System is in a safe state!!");
		System.out.println("-------------------------------");
		lambdaBefore = lambdaNow;
		lambdaHighestWorking = lambdaNow;
		lambdaNow = lambdaBefore / 2;
		
	}
	
	
}