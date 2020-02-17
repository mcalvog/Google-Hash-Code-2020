import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Copy of https://github.com/Xuntron/Google-Hash-Code-2020 using Java All
 * rights go to Xuntron.
 */

public class Algorithm {

	private String[] files = { "a_example", "b_small", "c_medium", "d_quite_big", "e_also_big" };
	private int finalScore = 0;

	public void start() {
		long startTime = System.currentTimeMillis();

		for (String file : files) {
			try {
				Scanner scanner = new Scanner(new File("files/" + file + ".in"));
				List<String[]> data = new ArrayList<String[]>();

				while (scanner.hasNextLine()) {
					data.add(scanner.nextLine().split(" "));
				}

				solve(data.get(0)[0], data.get(0)[1], data.get(1), file);
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		long endTime = System.currentTimeMillis();
		long duration = (endTime - startTime) / 1000;

		System.out.println("\nFinal score: " + finalScore);
		System.out.println("Total time: " + duration + "s");
	}

	private void solve(String max, String n, String[] inputs, String filename) {
		int index = 0, total = 0;
		List<Integer> solve = new ArrayList<Integer>();

		// Decrease the traversable size of the initial Pizza array in reverse order, by
		for (int i = Integer.parseInt(n) - 1; i >= 0; i--) {
			int sum = 0;
			index = i;
			List<Integer> tempSolve = new ArrayList<Integer>();

			// Traverse the current Pizza array in reverse order
			for (int j = index; j >= 0; j--) {
				int value = Integer.parseInt(inputs[j]);
				int tempSum = sum + value;

				if (tempSum == Integer.parseInt(max)) {
					sum = tempSum;
					tempSolve.add(0, j);
					break;
				} else if (tempSum > Integer.parseInt(max)) {
					continue;
				} else if (tempSum < Integer.parseInt(max)) {
					sum = tempSum;
					tempSolve.add(0, j);
					continue;
				}
			}

			if (total < sum) {
				total = sum;
				solve = tempSolve;
			}

		}

		System.out.println("File name: " + filename);
		System.out.println("Total score: " + finalScore);
		System.out.println("No. of pizzas: " + solve.size());
		System.out.println("----------");

		finalScore += total;
	}

}
