import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AlphaBeta {

	int max, min;
	int counter = 0;
	List<Integer> set;

	public AlphaBeta(int max, int min) {
		this.max = max;
		this.min = min;
		set = new ArrayList<>();
	}

	public static void main(String[] args) throws IOException {

		// BufferedReader br = new BufferedReader(new
		// InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		int depth = Integer.parseInt(br.readLine()) * 2;

		int branch = Integer.parseInt(br.readLine());
		int min = Integer.parseInt(br.readLine());
		int max = Integer.parseInt(br.readLine());

		AlphaBeta alphabeta = new AlphaBeta(max, min);

		int value = alphabeta.execute(branch, depth, Integer.MAX_VALUE, true);

		//System.out.println();
		System.out.println("Depth: " + depth);
		System.out.println("Branch: " + branch);
		System.out.println("Terminal States: " + (int)Math.pow(branch, depth));
		System.out.println("Set: " + alphabeta.set);
		System.out.println("Collected: " + value);
		System.out.println("Comparisons: " + alphabeta.counter);
	}

	public int execute(int node, int depth, int alpha, boolean player) {

		if (depth == 0) {
			counter++;
			int range = (max - min) + 1;
			int random_number = (int) (Math.random() * range) + min;
			set.add(random_number);
			System.out.print(random_number+" ");
			return random_number;
		} else if (player == true) {
			int beta = -Integer.MAX_VALUE;
			for (int i = 0; i < node; i++) {
				int val = execute(node, depth - 1, beta, false);
				System.out.print("        ");
				beta = Math.max(beta, val);
				if (beta > alpha)
					return beta;
			}
			return beta;
		} else {
			int beta = Integer.MAX_VALUE;
			for (int i = 0; i < node; i++) {
				int val = execute(node, depth - 1, beta, true);
				System.out.print(" ");
				beta = Math.min(beta, val);
				if (beta < alpha) {
					return beta;
				}
			}
			return beta;
		}

	}

}
