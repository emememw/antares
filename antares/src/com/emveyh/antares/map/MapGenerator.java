package com.emveyh.antares.map;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.emveyh.antares.utils.Coord;

public class MapGenerator {

	public static void main(String[] args) {
		new MapGenerator().generateWorld();
	}

	public static GameMap[][] generateWorld() {
		Random random = new Random();

		int worldScale = 5;
		int worldDimension = (int) Math.pow(2, worldScale) + 1;

		int mapScale = 4;
		int mapDimension = (int) Math.pow(2, mapScale);

		System.out.println("dimension: " + worldDimension);

		GameMap[][] world = new GameMap[worldDimension][worldDimension];
		for (int x = 0; x < world.length; x++) {
			for (int y = 0; y < world[x].length; y++) {
				world[x][y] = new GameMap(mapDimension, mapDimension, WorldTileType.WATER);
			}
		}

		int middleX = (worldDimension) / 2;
		int middleY = (worldDimension) / 2;
		world[middleX][middleY].setWorldTileType(WorldTileType.LAND);

		System.out.println("middlex: " + middleX + " middley:" + middleY);

		int chance = 100;
		int chanceLossPerStep = Double.valueOf(worldDimension * 0.05).intValue();
		int lossScale = 8;
		// down
		int step = 0;
		for (int y = middleY + 1; y < world[0].length; y++) {
			for (int xpos = middleX - (y - middleY); xpos <= middleX + (y - middleY); xpos++) {

				int currentChance = chance - chanceLossPerStep * step * lossScale;
				if (random.nextInt(100) < currentChance) {
					world[xpos][y].setWorldTileType(WorldTileType.LAND);
				}
			}
			step++;
		}

		// up
		step = 0;
		for (int y = middleY - 1; y >= 0; y--) {
			for (int xpos = middleX - (middleY - y); xpos <= middleX + (middleY - y); xpos++) {
				int currentChance = chance - chanceLossPerStep * step * lossScale;
				System.out.println(currentChance);
				if (random.nextInt(100) < currentChance) {
					world[xpos][y].setWorldTileType(WorldTileType.LAND);
				}
			}
			step++;
		}

		// right
		step = 0;
		for (int x = middleX + 1; x < world[0].length; x++) {
			for (int ypos = middleY - (x - middleX); ypos <= middleY + (x - middleX); ypos++) {

				int currentChance = chance - chanceLossPerStep * step * lossScale;
				if (random.nextInt(100) < currentChance) {
					world[x][ypos].setWorldTileType(WorldTileType.LAND);
				}
			}
			step++;
		}

		// left
		step = 0;
		for (int x = middleX - 1; x >= 0; x--) {
			for (int ypos = middleY - (middleX - x); ypos <= middleY + (middleX - x); ypos++) {

				int currentChance = chance - chanceLossPerStep * step * lossScale;
				if (random.nextInt(100) < currentChance) {
					world[x][ypos].setWorldTileType(WorldTileType.LAND);
				}
			}
			step++;
		}

		printWorld(world);

		System.out.println("==============================");

		int smoothTime = 3;
		while (smoothTime > 0) {
			for (int x = 0; x < world.length; x++) {
				for (int y = 0; y < world[x].length; y++) {
					int connectedLand = 0;
					int waterNeighbours = 0;
					List<Coord> neighbourTiles = getNeighbourTiles(world, x, y, false);
					for (Coord coord : neighbourTiles) {
						if (world[coord.getX()][coord.getY()].getWorldTileType() != WorldTileType.WATER) {
							connectedLand++;
						}
					}
					neighbourTiles = getNeighbourTiles(world, x, y, true);
					for (Coord coord : neighbourTiles) {
						if (world[coord.getX()][coord.getY()].getWorldTileType() == WorldTileType.WATER) {
							waterNeighbours++;
						}
					}

					if (connectedLand >= 5) {
						world[x][y].setWorldTileType(WorldTileType.LAND);
					} else if (connectedLand == 0) {
						world[x][y].setWorldTileType(WorldTileType.WATER);
					}

					if (world[x][y].getWorldTileType() == WorldTileType.LAND && waterNeighbours > 0) {
						world[x][y].setWorldTileType(WorldTileType.BEACH);
					}

				}
			}
			smoothTime--;
		}
		printWorld(world);

		for (int x = 0; x < world.length; x++) {
			for (int y = 0; y < world[x].length; y++) {
				generateGameMapTiles(world[x][y], world, x, y);
			}
		}

		return world;
	}

	public static void generateGameMapTiles(GameMap gameMap, GameMap[][] world, int worldX, int worldY) {
		Random random = new Random();
		for (int x = 0; x < gameMap.getWidth(); x++) {
			for (int y = 0; y < gameMap.getHeight(); y++) {

				if (gameMap.getWorldTileType() == WorldTileType.WATER) {
					gameMap.getTiles()[x][y] = Tile.WATER;
				} else if (gameMap.getWorldTileType() == WorldTileType.LAND) {
					gameMap.getTiles()[x][y] = Tile.GRASS;
				} else if (gameMap.getWorldTileType() == WorldTileType.BEACH) {
					gameMap.getTiles()[x][y] = Tile.GRASS;
					//insert beach logic ;)
					
					
					//first sand
					//left
					if(worldX > 0 && world[worldX-1][worldY].getWorldTileType() == WorldTileType.WATER) {
						if(x < 4) {
							gameMap.getTiles()[x][y] = Tile.SAND;
						}
					}
					//bottom
					if(worldY > 0 && world[worldX][worldY-1].getWorldTileType() == WorldTileType.WATER) {
						if(y < 4) {
							gameMap.getTiles()[x][y] = Tile.SAND;
						}
					}
					
					
					//then water
					//left
					if(worldX > 0 && world[worldX-1][worldY].getWorldTileType() == WorldTileType.WATER) {
						if(x < 2) {
							gameMap.getTiles()[x][y] = Tile.WATER;
						}
					}
					//bottom
					if(worldY > 0 && world[worldX][worldY-1].getWorldTileType() == WorldTileType.WATER) {
						if(y < 2) {
							gameMap.getTiles()[x][y] = Tile.WATER;
						}
					}

				}

			}
		}

	}

	public static List<Coord> getNeighbourTiles(GameMap[][] world, int x, int y, boolean directNeighbours) {

		List<Coord> result = new LinkedList<Coord>();

		if (x > 0) {
			result.add(new Coord(x - 1, y));
		}
		if (y > 0) {
			result.add(new Coord(x, y - 1));
		}
		if (x > 0 && y > 0 && !directNeighbours) {
			result.add(new Coord(x - 1, y - 1));
		}

		if (x < world.length - 1) {
			result.add(new Coord(x + 1, y));
		}
		if (y < world.length - 1) {
			result.add(new Coord(x, y + 1));
		}
		if (x < world.length - 1 && y < world.length - 1 && !directNeighbours) {
			result.add(new Coord(x + 1, y + 1));
		}

		if (x < world.length - 1 && y > 0 && !directNeighbours) {
			result.add(new Coord(x + 1, y - 1));
		}

		if (x > 0 && y < world.length - 1 && !directNeighbours) {
			result.add(new Coord(x - 1, y + 1));
		}

		return result;
	}

	public static void printWorld(GameMap[][] world) {
		System.out.println("====WORLD=====");
		for (int y = 0; y < world[0].length; y++) {
			for (int x = 0; x < world.length; x++) {
				if (world[x][y].getWorldTileType() != WorldTileType.WATER) {
					System.out.print(" " + world[x][y].getWorldTileType().getId() + " ");
				} else {
					System.out.print("   ");
				}
			}
			System.out.println("");
		}
	}

}
