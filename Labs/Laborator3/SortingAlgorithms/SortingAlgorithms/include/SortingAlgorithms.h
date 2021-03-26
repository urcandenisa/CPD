//
//  SortingAlgorithms.h
//  SortingAlgorithms
//
//  Created by Denisa Urcan on 24.03.2021.
//

#ifndef SortingAlgorithms_h
#define SortingAlgorithms_h

#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <time.h>
#include <omp.h>

#ifndef ARRAY_MAX_SIZE
#define ARRAY_MAX_SIZE 100000
#endif

#ifndef TASK_SIZE
#define TASK_SIZE 100000
#endif

/* print array */
void print(int *array, int size);

/* generate random array */
int *randomArray(int size);

/* copy array */
int *copyArray(int *array);


/* maximum value from array */
int maximumValue(int *array, int size);

#endif /* SortingAlgorithms_h */
