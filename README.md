Pénélope HENNER: Penelope.henner@ensea.fr

**Dungeon Crawler**

A simple 2D dungeon crawler game built in Java. Navigate the mape, avoid obstacles, and reach the exit in the shortest possible time!

**Features**

Dynamic gameplay: Control a character that can move in all four directions.  
Collision detection: Prevent the hero from walking through solid objects.  
Health bar: Decreases when colliding with obstacles, with immunity intervals.   
Victory condition: Exit the labyrinth to win the game.  
Defeat condition: Lose if health reaches zero.  
Timer: Tracks how long it takes to complete the labyrinth.  
Dynamic user interface:   
	- Displays a health bar during gameplay.  
	- Shows the elapsed time in the game.  
	- Displays "Game Over - You Win" with the total time upon completion.  
	- Displays "Game Over - You Lost" when health is depleted.  

**Project Structure**	

```
Dungeon Crawler
├── Main.java             # Entry point for the game
├── GameEngine.java       # Manages game logic and key handling
├── RenderEngine.java     # Handles rendering of the game UI and elements
├── PhysicEngine.java     # Handles collision detection and physics
├── DynamicSprite.java    # Represents the main character with movement and health
├── SolidSprite.java      # Represents immovable obstacles like rocks or traps
├── Sprite.java           # Base class for game elements
├── PlayGround.java       # Handles the generation of the game environment
├── img/                  # Image assets (tree, rock, trap, hero sprites, etc.)
└── data/
    └── level1.txt        # Labyrinth structure

```

**Project Tree**

```
.
├── Dongeon_Crawler_Penelope_Henner
│   ├── Dongeon_Crawler_Penelope_Henner.iml
│   ├── data
│   │   └── level1.txt
│   ├── img
│   │   ├── grass.png
│   │   ├── heroTileSheet.png
│   │   ├── heroTileSheetLowRes.png
│   │   ├── rock.png
│   │   ├── tileSet.png
│   │   ├── tileSetCompleted.png
│   │   ├── tileSetTest.png
│   │   ├── trap.png
│   │   └── tree.png
│   ├── out
│   │   └── production
│   │       └── Dongeon_Crawler_Penelope_Henner
│   │           ├── Direction.class
│   │           ├── Displayable.class
│   │           ├── DynamicSprite$1.class
│   │           ├── DynamicSprite.class
│   │           ├── Engine.class
│   │           ├── GameEngine.class
│   │           ├── GameState.class
│   │           ├── Main.class
│   │           ├── PhysicEngine.class
│   │           ├── PlayGround.class
│   │           ├── RenderEngine.class
│   │           ├── SolidSprite.class
│   │           └── Sprite.class
│   └── src
│       ├── Direction.java
│       ├── Displayable.java
│       ├── DynamicSprite.java
│       ├── Engine.java
│       ├── GameEngine.java
│       ├── GameState.java
│       ├── Main.java
│       ├── PhysicEngine.java
│       ├── PlayGround.java
│       ├── RenderEngine.java
│       ├── SolidSprite.java
│       └── Sprite.java
└── README.md

```


