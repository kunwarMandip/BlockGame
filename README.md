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

WHATS NEEDED TO SPAWN ENEMY
    WORLD -> To let the blocks spawn
    LOCATION -> at least for x since enemy has to be moving
    COLOR -> enemies have to be different colors
    FALLING speed -> Some blocks may move faster
    BODY SIZE -> Some enemies may be larger
Color doesn't need to be in entity constructor btw



ASPECT RATIO
18:9 (mobile devices)- double width compared to height
For TiledMap to properly be scaled properly, it should be same ratio as VIRTUAL_WIDTH AND VIRTUAL_HEIGHT

PIXEL PER METRE: PPM
PPM: Pixel for Metre to Map how many pixels should be 1 metre in game 
which makes scaling things dynamically better.
If 100 ppm, more pixels are needed for 1 metre, meaning if scaled, the objects looks better since more resolution used
If higher ppm: 200 ppm, when scaled, the objects look even better but takes more performance
Conversely, if lower ppm: 50ppm, lets pixels so lets performance time but will not scale as well


For enemies to spawn
Width of screen in world is 7.2 max -> starting from 0
height of where they spawn from is 14.4 


Simple difficulty spawning

After 2 seconds of game starting, spawn one enemy. Score=0
After enemy dodged. Score=1
keep on doing this


fortune favour lady nikuko: 1:13:0


As the games goes on, make the polygons smaller and quicker --> as time goes on
different shapes

