package solution2;

import java.util.*;

public class Solution2 {

	public static void main(String[] args) {
		List<Input> inputs = new ArrayList<>();
		inputs.add(new Input(4, new int[]{1, 2, 3}, 4));
		inputs.add(new Input(10, new int[]{2, 5, 3, 6}, 5));
		inputs.add(new Input(8, new int[]{2}, 1));
		inputs.add(new Input(7, new int[]{3, 5}, 0));
		inputs.add(new Input(0, new int[]{1, 2, 5}, 1));
		inputs.add(new Input(100, new int[]{5, 10, 25, 50}, 40));
		inputs.add(new Input(1, new int[]{5, 10}, 0));

		for (Input input : inputs) {
			int n = input.n;
			int[] coins = input.coins;
			int expected = input.expected;

			int result = solution(n, coins);

			if (result != expected) {
				System.out.println("Test failed: " + n + " " + Arrays.toString(coins) + " expected: " + expected + " output: " + result);
			} else {
				System.out.println("Test passed: " + n + " " + Arrays.toString(coins) + " expected: " + expected + " output: " + result);
			}
		}
	}

	private static class Input {
		int n, expected;
		int[] coins;
		Input(int n, int[] coins, int expected) {
			this.n = n;
			this.coins = coins;
			this.expected = expected;
		}
	}

	private static int solution (int money, int[] coins) {
		int[] dp = new int[money + 1];
		// 0 원은 아무것도 사용하지 않을 때 1가지
		dp[0] = 1;

		// 동전 순회
		for (int i=0 ; i<coins.length; i++) {
			int coin = coins[i];
			// 금액 순회, 동전을 사용할 수 있는 금액부터 시작
			for (int j=coin ; j<=money ; j++) dp[j] += dp[j-coin];
		}

		return dp[money];
	}
}
