=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: akashkau
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Array - I use a 2D Array to store the state of the game. It stores integers
  which represent different objects in the game. 0 represents empty space, 1 represents
  wall, 2 represents apple, 3 represents snake head, 4 represents snake body, 5 represents
  banana, and 6 represents blueberry. This feature allows me to easily represent the game
  state because the indices of the 2D array easily represent coordinates that I can
  manipulate. It also makes painting the game easy because I can simply iterate through the
  array and paint square colors according to the numbers. This is an appropriate use of the
  2D Array because it stores a significant portion of my game (the game state) and is how
  I update my game.

  2. File I/O - I use File I/O when I save and load my game. How it works is I first store
  the whole game board in the text file by parsing through the 2D Array of the board and
  storing the integers in the text file. I then store each aspect of my game from the
  snake head position to the fruit type each on its own line in the text file. When I want
  to load my game, I parse through each line and run the game again with the different
  properties in my save file. This feature is appropriate for File I/O because I save the
  game data onto a file and am able to load the data back up again even after I close the
  game.

  3. Inheritance - I use inheritance in my game in two areas: the game object and the fruit.
  First, with the GameObj class, this is the superclass with its subclasses being the Apple
  Class, the SnakeHead Class and the SnakeBody Class. Mainly the function of the superclass
  is to store the x position and the y position of the object in addition to storing the
  previous position of the object in an array. It also implements complex logic to set a new
  position depending on the type of the object. The difference in the subclasses is the
  implementation of the move method. The Apple class doesn't implement this because it doesn't
  move. The SnakeHead class moves based on the arrow keys and has multiple interactions
  that occur as a result of the move method. The SnakeBody Class moves based on the previous
  position of its leader. This satisfies inheritance because the superclass has common variables
  and methods that cannot easily be represented by a single field (the setPx, setPy and prevPos),
  and the subclasses differentiate substantially from the superclass with the complex
  implementation of the move method.

  The other way in which I implement inheritance is the fruit. Here, I implement inheritance
  by having a superclass of Apple and subclasses of Banana and Blueberry. What is common and
  implemented in the superclass is the constructor to generate a random position on the board
  that is not already taken up by wall or snake. This is shared across all these classes.
  What is different among the classes is the action that happens when the snake touches these
  fruit. For the Apple class, nothing happens because it is the default fruit. For the
  Banana class, the snake changes color. For the Blueberry class, the snake changes speed. This
  is a sufficient implementation of inheritance because they share the complex logic of
  generating a position which cannot easily be represented by a single variable. They also
  differ substantially in the action method. The actions in the action method between the Banana
  and Blueberry classes are substantially different and cannot be implemented by just switching
  the value of one variable because for the Banana, it must pick a random color and for the
  Blueberry it must change the speed depending on the previous speed.

  4. JUnit Testing - I implement JUnit Testing by adding multiple tests to test the interactions
  in the game. My code is unit testable because the game state is stored in a 2D array and my
  objects are easily creatable allowing the creation of specific situations very easily. I also
  use assertEquals and assertTrue appropriately and I have more than 5 tests, so I should get
  credit for this section.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

 - The Board class stores most of the game data. It stores the 2D array storing
   the game state in addition to the main snake in the game (a snakehead). It
   also stores many other things pertinent to the game functioning including
   the color of the snake, the speed of the snake, the direction of the snake
   etc. Finally, the board class also has the reset, save, and load functions
   required to implement the file i/o.

 - The GameObj class stores an individual pixel in the game and its properties
   associated with it. It has instance variables for the x and y position of the
   object. It also stores the previous position of the object. The main functions
   that the GameObj class implements is the setPx and setPy functions which give
   the object a new location, sets the previous position to the current location,
   and depending on the type of object, will make the previous location an empty
   pixel. This class also has an abstract move function which the children classes
   will each implement differently.

 - The SnakeHead Class stores the head of the snake in the game. It extends the
   GameObj class, so it has information on its own and previous position.
   Additionally, it stores a linked list full of the body pixels and a fruit
   associated with the snake. The main function is the move function which moves
   depending on the direction of the board. If the snakehead is about to intersect
   with a wall or itself, the intersect variable will turn true and the player will
   lose. It will also make the snake longer (add a SnakeBody object) and generate
   a new random fruit.

 - The SnakeBody Class stores an individual body pixel in the game. It extends the
   GameObj class, so it has information on its own and previous position. When it
   gets generated it gets put into the previous position of the leader GameObj it
   is instantiated with. This leader game object is stored in the class. Whenever
   the body moves, it moves into the previous position of its leader.

 - The Apple Class stores a fruit (default an apple) in the game. It extends the
   GameObj class, so it has information on its position. The main function of this
   class is to generate a random position on the board that is not taken up by wall
   or snake and generate a fruit there. It also has an unimplemented action method
   that gets implemented by the children classes.

 - The Banana Class stores a banana in the game. It extends the Apple class so the
   generation of the fruit is taken care of. The main function of this class is
   the action method in which the class will give the snake a random color. It
   does this by randomly generating a number and giving the snake the color
   corresponding to the map in the class.

 - The Blueberry Class stores a blueberry in the game. It extends the Apple class so
   the generation of the fruit is taken care of. The main function of this class is
   the action method in which the class will change the snake's speed. If the snake is
   slow it will make the snake have medium speed, if the snake is medium speed, it will
   make the snake have a fast speed, and if the snake is fast, it will make the snake
   have slow speed.

 - The FileLineIterator Class is taken straight from last week's homework.

 - The RunSnake Class is what implements the swing and GUI components. This class first
   displays the instructions in one window. After clicking play, it opens another
   window and runs the game with a save, load, and reset button at the top. It also
   displays the status of the game at the bottom (if you lost).

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

 - First it took me a while to get the code to even display anything and I had to look
   back at the mushroom game and how that implemented the run class.
 - Then it took me a while to get the snake head to start moving and I solved that by
   looking at the reset and tick function of the mushroom game and matching that.
 - It also took me a while for my snake getting longer to start working. The bug ended up
   being because my SnakeBody move function was trying to draw the body on the snake head
   instead of the previous position of the snake head.
 - The File I/O all in all took a lot of trial and error.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

 - I think my file design is fairly good in that the hierarchy makes sense. If anything
   I would move some of the variables in the snake classes into the board class to add more
   centralization. I went back and made sure to make all my variables private and added
   getters and setters accordingly, so I believe the code is well encapsulated. Given the
   chance, I would refactor the code by adding more helper functions to make it simpler.
   I found myself casing by the direction a lot so I would add something to simplify that
   process so I don't have to write the same code four times with slight variation.


========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.

    I mostly used the example games provided (mushroom/tictactoe) in addition to
    JavaDocs to complete this game.