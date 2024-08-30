Android SDK path=C:/Users/mandi/AppData/Local/Android/Sdk

# BlockGame
Game idea
->  Player is a block of color
->  Every CERTAIN PERIOD OF TIME color of the block changes
->  Enemies are block of colors as well

HOW TO PLAY 
->  Player has to dodge incoming enemies 
->  Enemies blocks fall from the sky
->  Player has to dodge incoming enemies blocks
->  However, color matching with the player color might drop as well
->  Player doesn't have to dodge them 
->  Those blocks could even be used as power drops

OBJECTIVES
->  As player keeps dodging enemies
->  More enemies could fall from the sky
->  Some could even be larger or smaller blocks


PERKS
->  Maybe some blocks fall faster than the other
->  Maybe some MATCHING BLOCKS drop power off where the player blocks can move 
several places in one second.

WHAT'S NEEDED TO SPAWN ENEMY
    WORLD -> To let the blocks spawn
    LOCATION -> at least for x since enemy has to be moving
    COLOR -> enemies have to be different colors
    FALLING speed -> Some blocks may move faster
    BODY SIZE -> Some enemies may be larger
Color doesn't need to be in entity constructor btw


## TODO List
Here are the tasks that need to be completed:

- [x] Implement the collision detection feature
- [x] Add a main menu screen
- [x] Integrate the game with a physics engine
- [x] Create sound effects for game events
- [x] Add a pause effect
- [ ] Optimize the rendering performance
- [ ] Write unit tests for critical components
- [ ] Improve player character animations
- [ ] Add multiplayer functionality