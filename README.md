# ADSex1

REPORT

name: Mckenzie Morrison
GUID: 2915568m


INSTRUCTIONS ON CODE:

go to Main.java and make sure the directory being fetch by line 22 is matching the location of the project. Then hit run and it should give an output of the names of the algs running, what data set theyre running with, and then a list of times for each alg


Implementation Task1:

I created a class SortingAlgorithms, with the methods being the algorithms from the labs and helper functions.

I then tweaked some code from a sem 1 lab for my LoadFromFile class which i use to read the data sets in the numbers folder to populate an array of arrays which the sorting algorithms are then passed.

The Main class is where i run the methods from SortingAlgorthims and output the algorithms name and run time for each data set.

i then passed this info to chatgbt to format into a table.

I used that table to then match the specification of the layout given by the AE1 pdf and what my analysis is based on

The tables provides.

    the run times

    max,min,average

    bad/dutch run times also seperately

    A bar chart of execution times of the alg based on what dataset was passed to it.


Task 2:

I have tweaked the version of insertion sort slightly to work with my class Video to simulate how it would work if we were actually sorting videos. Assuming we run it initially take the hit of O(n^2) the first time the alg runs, when we add in a video it will run as  O(n) which over time will result in a less taxing cost to run as the other options although fast initially, they lack as wide a range of possible run times/ they normally take around the same time each time they run. Insertion sort is very depended on the current order of the list so if we have a sorted list and add a video each time and run the alg it will be pretty fast
