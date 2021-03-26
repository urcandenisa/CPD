//
//  QuickSort.h
//  SortingAlgorithms
//
//  Created by Denisa Urcan on 24.03.2021.
//

#ifndef QuickSort_h
#define QuickSort_h
#include <omp.h>
#include <stdio.h>

/* swap two numbers */
void swap(int* x, int* y);

/* pick last element as a pivot */
int partition(int *array, int low, int high);

/* quickSort */
void quickSort(int *array, int low, int high);

#endif /* QuickSort_h */
