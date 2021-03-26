//
//  MergeSort.h
//  SortingAlgorithms
//
//  Created by Denisa Urcan on 24.03.2021.
//

#ifndef MergeSort_h
#define MergeSort_h

#include <stdio.h>

/* merge two subarrays */
void merge(int *array, int left, int middle, int right);

/* mergeSort */
void mergeSort(int *array, int left, int right);
#endif /* MergeSort_h */
