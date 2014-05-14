redstone-solver
===============

A solver for the RedStone game available for Android (https://play.google.com/store/apps/details?id=my.redstone)

--

There are 3 strategies used to solve the board

1. Using a priority queue (priority based on how high the Big Square is)
2. Using a depth first search which bails after a certain depth
3. Using a breadth first search (which guarantees the least # of steps to the solution)

--

Here are some interesting things I learnt

1. Use a priority queue to prioritize search of the solution board.
   I learnt this technique from the algorithms class at coursera.
   The class solved an 8 puzzle with the priority queue technique.

   Of course, this technique does not give an optimal solution.

2. Breadth first search gives an optimal solution.
   We can think of the search space as an implicit tree. Traversing this implicit tree in a BFS fashion
   is guaranteed to give us an optimal solution (least number of steps) because BFS gives us the shortest path.

3. I knew that depth first search could go on forever without finding a solution.
   It seeemed like a natural thing to restrict the depth of the search.
   A quick google search revealed that it was quite a common strategy.

4. In order to not re-consider a board we have already visited (search space pruning?) I wanted to keep a track of the
   already visited boards in a hashset. The ideas was to have quick (O(1)) insert and contains implementation.

   I need a predictable hash function to identify "same" boards. Now what is a same board?

   Different boards but with the same pieces in the same place should produce the same hashCode.

   Supposed we have a board with 2 small square pieces.
   Piece with id 0 is in position (0, 0)
   Piece with id 1 is in position (3, 0)

   and another board with 2 small square pieces.
   Piece with id 0 is in position (3, 0)
   Piece with id 1 is in position (0, 0)

   The hash code generated for both of the board should be identical.

   This is the stragey I used

   a. Scan the board from x positions 0 thru 3 and y positions 0 through 4
   b. If a piece was at the position, generate a string which was the concatenation of piece position (x, y)
      and height and width
   c. Concatenate such generated strings for all positions
   d. Used the hash code of that string as the hash code of the board

   As an example for the above board the hashcode would be "00113011".hashCode

