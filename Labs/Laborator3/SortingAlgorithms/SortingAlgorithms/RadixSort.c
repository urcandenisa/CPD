//
//  RadixSort.c
//  SortingAlgorithms
//
//  Created by Denisa Urcan on 24.03.2021.
//

#include "include/RadixSort.h"
#include "include/SortingAlgorithms.h"

/* counting sort */
void countSort(int *array, int size, int digit) {
    int *output = (int*)calloc(size, sizeof(int));
    int count[10] = {0};
    
    for(int i = 0; i < size; i++) {
        count[(array[i] / digit) % 10]++;
    }
    
    for(int i = 1; i < 10; i++) {
        count[i] += count[i - 1];
    }
    
    for(int i = size - 1; i >= 0; i--) {
        output[count[(array[i] / digit) % 10] - 1] = array[i];
        count[(array[i] / digit) % 10]--;
    }
    
    for (int i = 0; i < size; i++) {
        array[i] = output[i];
    }
}

/* radixSort */
void radixSort(int *array, int size) {
    int max = maximumValue(array, size);
    int i;
    
    #pragma omp parallel num_threads(5) shared(array, size) private(i)
    for(int i = 1; max / i > 0; i *= 10) {
        
        #pragma omp critical
        countSort(array, size, i);
    }
}
