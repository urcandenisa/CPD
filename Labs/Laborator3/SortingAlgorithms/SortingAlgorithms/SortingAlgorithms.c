//
//  SortingAlgorithms.c
//  SortingAlgorithms
//
//  Created by Denisa Urcan on 24.03.2021.
//

#include "include/SortingAlgorithms.h"
#include "include/QuickSort.h"
#include "include/MergeSort.h"
#include "include/RadixSort.h"

/* print array */
void print(int *array, int size) {
    for(int i = 0; i < size; i++) {
        printf("%d ", array[i]);
    }
    printf("\n");
}

/* generate random array */
int *randomArray(int size) {
    srand((unsigned int)(time(NULL)));
    
    int *array = (int*)calloc(size, sizeof(int));
    
    for(int i = 0; i < size; i++){
        array[i] = (int)(rand() % ARRAY_MAX_SIZE);
    }
    
    return array;
}

/* copy array */
int *copyArray(int *array) {
    return array;
}

/* maximum value from array */
int maximumValue(int *array, int size) {
    int max = array[0];
    
    for(int i = 0; i < size; i++) {
        if(array[i] > max){
            max = array[i];
        }
    }
    
    return max;
}

int main(int argc, const char * argv[]) {
    // insert code here...
    int *array = randomArray(ARRAY_MAX_SIZE);
    //print(array, ARRAY_MAX_SIZE);
    
    int *copy = copyArray(array);
    int *copy2 = copyArray(array);
    
    omp_set_num_threads(4);
    float start_time = omp_get_wtime();
    #pragma omp parallel
    {
        #pragma omp single
        {
            quickSort(copy, 0, ARRAY_MAX_SIZE - 1);
        }
    }
    
    float run_time = omp_get_wtime() - start_time;
    printf("Quick sort execution time: %f [ms]\n", run_time);
    //print(copy, ARRAY_MAX_SIZE);
    
    start_time = omp_get_wtime();
    #pragma omp parallel
    {
       #pragma omp single
       {
           mergeSort(copy2, 0, ARRAY_MAX_SIZE - 1);
       }
    }
    
    run_time = omp_get_wtime() - start_time;
    printf("Merge sort execution time: %f [ms]\n", run_time);
    //print(copy2, ARRAY_MAX_SIZE);
    
    start_time = omp_get_wtime();
    #pragma omp parallel
    {
       #pragma omp single
       {
           radixSort(array, ARRAY_MAX_SIZE);
       }
    }
    
    run_time = omp_get_wtime() - start_time;
    printf("Radix sort execution time: %f [ms]\n", run_time);
    //print(array, ARRAY_MAX_SIZE);
    
    return 0;
}
