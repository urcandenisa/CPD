//
//  QuickSort.c
//  SortingAlgorithms
//
//  Created by Denisa Urcan on 24.03.2021.
//

#include "include/QuickSort.h"
#include "include/SortingAlgorithms.h"

/* swap two numbers */
void swap(int* x, int* y)
{
    int temp = *x;
    *x = *y;
    *y = temp;
}
/* pick last element as a pivot */
int partition(int *array, int low, int high){
    
    int pivot = array[high];
    int i = (low - 1);
    
    for(int j = low; j < high; j++){
        if(array[j] < pivot){
            i++;
            swap(&array[i], &array[j]);
        }
    }
    
    swap(&array[i + 1], &array[high]);
    return (i + 1);
}

/* quickSort */
void quickSort(int *array, int low, int high){
    
    if(low < high){
        int pi = partition(array, low, high);
        /*
        #pragma omp parallel sections
        {
            #pragma omp section
            {
                quickSort(array, low, pi - 1);
            }
            #pragma omp section
            {
                quickSort(array, pi + 1, high);
            }
        }
        */
        #pragma omp task shared(array) if(high - low > TASK_SIZE)
        quickSort(array, low, pi - 1);
        
        #pragma omp task shared(array) if(high - low > TASK_SIZE)
        quickSort(array, pi + 1, high);
    }
}



