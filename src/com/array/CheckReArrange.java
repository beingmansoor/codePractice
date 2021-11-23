package com.array;

public class CheckReArrange {
	// Re-Arrange Positive and Negative Values of Given Array
	public static void reArrange(int[] arr) {
		int j = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < 0) { // if negative number found
				if (i != j) {
					int temp = arr[i];
					arr[i] = arr[j]; // swapping with leftmost positive
					arr[j] = temp;
				}
				j++;
			}
		}
	} // end of reArrange()
	
	static void rearrange(int []a)
	{
		int n=0, p =a.length-1;
		
		while(a[n] <0){n++;}
		while(a[p]>0){p--;}
		for(int i=n; i<=p;i++)
		{
			if(a[i] <0)
			{
				swap(a,i,n);
				n++;
				while(a[n] <0){n++;}
			}
		}
		
	}

	private static void swap(int[] a, int i, int j) {
		int  t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void main(String args[]) {

		int[] arr = { 2, 4, -6, 8, -5, -10 };
//		int[] arr = {-6, -5, -10, 8, 4, 2};

		System.out.print("Array before re-arranging: ");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();

		rearrange(arr);

		System.out.print("Array after rearranging: ");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
	}
}