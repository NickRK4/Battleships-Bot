## Motivation
I created this to practice OOP and 2D arrays, although the code now uses 1D points

## Rules
The user starts with a choice: either create and place their own ships, or use the robot class' createBoard() method.
After creating the board, the user starts by guessing the opponent's ship pixels.
After each guess, the user's minimap updates to show both the hit and missed pixels.
The robot guesses, and the result is shown on the console.
Whoever sinks all the ships, wins.

## Robot "Create Board" method
For each length in {2,3,3,4,5}, the robot tries to place a ship at a given row, col value.
The algorithm checks first, if the pixel is valid (i.e. the ship won't go out of bounds)
Then it checks if the ship enters a "buffer zone", an area 1-pixel around the perimeter around the ship.
If not, the ship is placed and the algorithm updates the buffer zone.

## Robot "Guess" method
Robot starts by guessing a pixel.
If the pixel hits your ship, that is stored in an Array List called hitsOneAway.
There is a hierachy of guesses: if a pixel is both one and two pixels away from a hit pixel, the robot will guess that pixel.
If a pixel is one away from a hit pixel, then the robot will choose a random pixel from that list to maximize its chances of hitting a ship.
The robot will only guess pixels diagonal from one another, since ships cannot be 1 length, so that it guesses more efficiently. 


