public class Solution {
	// 排列组合模板
	// 组合传当前index start
	// 排列用visited数组
	// 有重复元素sort
	// 重复组合表示每个元素只取一次
	public static void main(String[] args) {
		int[] nums = {1, 2, 2, 3, 4};
		Solution solution = new Solution();
		int k = 2;

		for (List<Integer> list : solution.combination(nums, k)) {
			System.out.println(list);
		}

		for (List<Integer> list : solution.permuteUnique(nums, k)) {
			System.out.println(list);
		}
	}

	public List<List<Integer>> combination(int[] candidates, int k) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> row = new ArrayList<>();
		if (candidates == null || candidates.length == 0) return result;
		Arrays.sort(candidates);
		int n = candidates.length;
		combinationHelper(candidates, 0, result, row, k);
		return result;
	}

	public void combinationHelper(int[] A, int start, List<List<Integer>> result, List<Integer> row, int k) {
		int n = A.length;
		if (row.size() == k) {
			result.add(new ArrayList<>(row));
			return;
		}
		for (int i = start; i < n; i++) {
			// 当前第一个元素 加入 i == start
					// if (i > start && A[i] == A[i - 1]) continue;
			row.add(A[i]);
			combinationHelper(A, i + 1, result, row, k);
			row.remove(row.size() - 1);
		}
	}

	public List<List<Integer>> permuteUnique(int[] nums, int k) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> row = new ArrayList<>();
		if (nums == null || nums.length == 0) return result;
		int n = nums.length;
		Arrays.sort(nums);
		boolean[] visited = new boolean[n];
		permutationHelper(nums, result, row, visited, k);
		return result;
	}

	public void permutationHelper(int[] A, List<List<Integer>> result, List<Integer> row, boolean[] visited, int k) {
		int n = A.length;
		if (row.size() == k) {
			result.add(new ArrayList<>(row));
			return;
		}
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				//if (i != 0 && A[i] == A[i - 1] && !visited[i - 1]) continue;
				visited[i] = true;
				row.add(A[i]);
				permutationHelper(A, result, row, visited, k);
				row.remove(row.size() - 1);
				visited[i] = false;
			}
		}
	}
}