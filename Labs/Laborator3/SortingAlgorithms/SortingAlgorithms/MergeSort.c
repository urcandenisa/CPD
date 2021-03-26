//
//  MergeSort.c
//  SortingAlgorithms
//
//  Created by Denisa Urcan on 24.03.2021.
//

#include "include/MergeSort.h"
#include "include/SortingAlgorithms.h"

/* merge two subarrays */
void merge(int *array, int left, int middle, int right) {
    int n1 = middle - left + 1;
    int n2 = right - middle;
    
    int Left[n1], Right[n2];
    
    for(int i = 0; i < n1; i++){
        Left[i] = array[left + i];
    }
    for(int i = 0; i < n2; i++){
        Right[i] = array[middle + 1 + i];
    }
    
    int i = 0, j = 0, k = left;
    
    while (i < n1 && j < n2) {
        if(Left[i] <= Right[j]){
            array[k] = Left[i++];
        }
        else {
            array[k] = Right[j++];
        }
        k++;
    }
    
    while (i < n1) {
        array[k++] = Left[i++];
    }
    
    while(j < n2) {
        array[k++] = Right[j++];
    }
}

/* mergeSort */
void mergeSort(int *array, int left, int right){
    
    if(left < right) {
        int middle = left + (right - left)/2;
        
        #pragma omp task shared(array) if (ARRAY_MAX_SIZE > TASK_SIZE)
        mergeSort(array, left, middle);
        
        #pragma omp task shared(array) if (ARRAY_MAX_SIZE > TASK_SIZE)
        mergeSort(array, middle + 1, right);
        
        #pragma omp taskwait
        merge(array, left, middle, right);
    }
}
