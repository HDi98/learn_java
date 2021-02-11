
public class test {

	//merge sort
	public static void mergeSort(int[] arr){
	    int [] aux = new int [arr.length];
	    sort(arr, aux, 0, arr.length - 1);
	}

	private static void sort(int[] arr, int[] aux, int lo, int hi){
	    if (lo >= hi) return;
	    int mid = lo + (hi - lo)/2;
	    sort(arr, aux, lo, mid);
	    sort(arr, aux, mid+1, hi);
	    merge(arr, aux, lo, mid, hi);
	}

	private static void merge(int[] arr, int[] aux, int lo, int mid, int hi){
	    System.arraycopy(arr, 0, aux, 0, arr.length);
	    int i = lo, j = mid+1;
	    for(int k = lo; k <= hi; k++){
	        if(i > mid) arr[k] = aux[j++];
	        else if(j > hi) arr[k] = aux[i++];
	        else if (aux[i] > aux[j]) arr[k] = aux[j++];
	        else arr[k] = aux[i++];
	    }
	}
	
	//quick sort
	public static void quickSort(int[] arr, int lo, int hi){
	    if(lo >= hi) return;
	    int index = getIndex(arr, lo, hi);
	    quickSort(arr, lo, index);
	    quickSort(arr, index+1, hi);
	}

	private static int getIndex(int[] arr, int lo, int hi){

	    int temp = arr[lo];
	    while(lo < hi){
	        while (arr[hi] > temp && lo < hi){
	            hi--;
	        }
	        arr[lo] = arr[hi];
	        while(arr[lo] < temp && lo < hi){
	            lo++;
	        }
	        arr[hi] = arr[lo];
	    }
	    arr[lo] = temp;
	    return lo;
	}
	
	
	//heap sort
	public static void heapSort(int [] arr) {
		for(int i = arr.length/2 - 1; i >=0; i--) {
			adjustHeap(arr, i, arr.length);
		}
		
		for(int j = arr.length - 1; j>=0; j--) {
			swap(arr, 0, j);
			adjustHeap(arr, 0 , j);
		}
	}
	
	
	private static void adjustHeap(int[] arr, int i, int length) {
		int temp = arr[i];
		
		for(int k = i*2 +1; k < length; k = k*2 + 1) {
			if(k+1 < length && arr[k] < arr[k+1]) {
				k++;
			}
			if(arr[k] > temp) {
				arr[i] = arr[k];
				i = k;
			}
			else break;
		}
		arr[i] = temp;
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] arr = {665,32,};
		//mergeSort(arr);
		//quickSort(arr, 0, arr.length-1);
		heapSort(arr);
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

}
