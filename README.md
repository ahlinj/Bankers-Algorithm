# Bankers-Algorithm
Classic bankers algorithm that determines if a system is in a safe state or not. This version uses a txt file where resources information has to be provided and gives output based on that (I gave few examples). 

  This version can also find the smallest value for which the system will be safe if one of the resources values in the available array is not given (you need to write -1 on that spot - look in the example down).

Txt file should be written in the following format for the program to work:
  x y M
  Allocation[x][y]
  Max/Need[x][y]
  Available[y]

  -x: number of processes/rows
  -y: number of different types of ressources/columns
  -M: mode -> either M (Max) or N (Need)
  -Allocation[x][y]: Allocation matrix of size x*y
  -Max/Need[x][y]: Max/Need matrix of size x*y (needs to be declared in the mode section)
  -Available[y]: Available array of size y

Example:

  5 5 N      
  1 2 0 3 0   \
  0 1 1 1 1    \
  2 2 0 0 3     |=>Allocation matrix 
  2 1 1 1 0    /
  2 0 2 1 2   /
  1 6 4 3 2   \
  3 4 2 2 2    \
  2 4 3 4 2     |=> Need matrix
  2 2 1 1 0    /
  0 4 2 3 3   /
  2 -1 1 1 3  <- the 2nd value is -1, which means that we don't know how many ressources we have available of that type. Program will determine what's the smallest value for which the system will be in a safe state.
