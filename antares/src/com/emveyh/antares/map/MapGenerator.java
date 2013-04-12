package com.emveyh.antares.map;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.emveyh.antares.utils.Coord;
import com.emveyh.antares.utils.Direction;

public class MapGenerator {

	public static void main(String[] args) {
		MapGenerator.generateSingleGameMap();
	}

	private static void createRiver(GameMap map) {

		Random random = new Random();

		// pick random land tile
		List<Coord> beachTiles = new LinkedList<Coord>();
		for (int x = 0; x < map.getTiles().length; x++) {
			for (int y = 0; y < map.getTiles()[x].length; y++) {
				if (map.getTiles()[x][y] == Tile.GRASS) {
					beachTiles.add(new Coord(x, y));
				}

			}
		}

		Coord startPoint = beachTiles.get(random.nextInt(beachTiles.size()));
	

		int currentX = startPoint.getX();
		int currentY = startPoint.getY();

		Direction basicDirection = Direction.values()[random.nextInt(Direction.values().length)];
		
		int differentDirectionChance = 60;
		
		while(map.getTiles()[currentX][currentY] != Tile.WATER) {
			
			Direction currentDirection = basicDirection;
			if(random.nextInt(100) < differentDirectionChance) {
				currentDirection = Direction.values()[random.nextInt(Direction.values().length)];
			}
			
			if(currentDirection == Direction.RIGHT) {
				currentX++;
			} else if(currentDirection == Direction.LEFT) {
				currentX--;
			} else if(currentDirection == Direction.DOWN) {
				currentY--;
			} else if(currentDirection == Direction.UP) {
				currentY++;
			}
			
			if(currentX < 0 || currentX > map.getTiles().length-1 || currentY < 0 || currentY > map.getTiles()[0].length-1 ) {
				break;
			} else	if(map.getTiles()[currentX][ currentY] != Tile.WATER) {
				map.getTiles()[currentX][currentY] = Tile.RIVER;
			}
			
		}

	}

	public static List<Coord> getNeighbourTiles(GameMap world, int x, int y, boolean directNeighbours) {

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

		if (x < world.getTiles().length - 1) {
			result.add(new Coord(x + 1, y));
		}
		if (y < world.getTiles().length - 1) {
			result.add(new Coord(x, y + 1));
		}
		if (x < world.getTiles().length - 1 && y < world.getTiles().length - 1 && !directNeighbours) {
			result.add(new Coord(x + 1, y + 1));
		}

		if (x < world.getTiles().length - 1 && y > 0 && !directNeighbours) {
			result.add(new Coord(x + 1, y - 1));
		}

		if (x > 0 && y < world.getTiles().length - 1 && !directNeighbours) {
			result.add(new Coord(x - 1, y + 1));
		}

		return result;
	}

	public static void printWorld(GameMap world) {
		System.out.println("====WORLD=====");
		for (int y = 0; y < world.getTiles()[0].length; y++) {
			for (int x = 0; x < world.getTiles().length; x++) {
				if (world.getTiles()[x][y] != Tile.WATER) {
					System.out.print(" 1 ");
				} else {
					System.out.print("   ");
				}
			}
			System.out.println("");
		}
	}

	public static GameMap generateSingleGameMap() {

		Random random = new Random();

		int worldScale = 9;
		int worldDimension = (int) Math.pow(2, worldScale) + 1;

		System.out.println("dimension: " + worldDimension);

		GameMap world = new GameMap(worldDimension, worldDimension, null);
		for (int x = 0; x < world.getTiles().length; x++) {
			for (int y = 0; y < world.getTiles()[x].length; y++) {
				world.getTiles()[x][y] = Tile.WATER;
			}
		}

		int middleX = (worldDimension) / 2;
		int middleY = (worldDimension) / 2;
		world.getTiles()[middleX][middleY] = Tile.GRASS;

		double chance = 100;
		double chanceLossPerStep = Double.valueOf(worldDimension * 0.013).intValue();
		double lossScale = 0.045;
		// down
		double step = 0;
		for (int y = middleY + 1; y < world.getTiles()[0].length; y++) {
			for (int xpos = middleX - (y - middleY); xpos <= middleX + (y - middleY); xpos++) {

				double currentChance = chance - chanceLossPerStep * step * lossScale;
				System.out.println(currentChance);
				if (random.nextDouble() * 100 < currentChance) {
					world.getTiles()[xpos][y] = Tile.GRASS;
				}
			}
			step++;
		}

		// up
		step = 0;
		for (int y = middleY - 1; y >= 0; y--) {
			for (int xpos = middleX - (middleY - y); xpos <= middleX + (middleY - y); xpos++) {
				double currentChance = chance - chanceLossPerStep * step * lossScale;
				System.out.println(currentChance);
				if (random.nextDouble() * 100 < currentChance) {
					world.getTiles()[xpos][y] = Tile.GRASS;
				}
			}
			step++;
		}

		// right
		step = 0;
		for (int x = middleX + 1; x < world.getTiles()[0].length; x++) {
			for (int ypos = middleY - (x - middleX); ypos <= middleY + (x - middleX); ypos++) {

				double currentChance = chance - chanceLossPerStep * step * lossScale;
				if (random.nextDouble() * 100 < currentChance) {
					world.getTiles()[x][ypos] = Tile.GRASS;
				}
			}
			step++;
		}

		// left
		step = 0;
		for (int x = middleX - 1; x >= 0; x--) {
			for (int ypos = middleY - (middleX - x); ypos <= middleY + (middleX - x); ypos++) {

				double currentChance = chance - chanceLossPerStep * step * lossScale;
				if (random.nextDouble() * 100 < currentChance) {
					world.getTiles()[x][ypos] = Tile.GRASS;
				}
			}
			step++;
		}

		System.out.println("==============================");

		smothGameMap(world);

		for (int i = 0; i < random.nextInt(30)+30; i++) {
			createRiver(world);
		}
		

		printWorld(world);
		return world;
	}

	private static void smothGameMap(GameMap world) {
		int smoothTime = 3;
		while (smoothTime > 0) {
			for (int x = 0; x < world.getTiles().length; x++) {
				for (int y = 0; y < world.getTiles()[x].length; y++) {
					int connectedLand = 0;
					int waterNeighbours = 0;
					List<Coord> neighbourTiles = getNeighbourTiles(world, x, y, false);
					for (Coord coord : neighbourTiles) {
						if (world.getTiles()[coord.getX()][coord.getY()] != Tile.WATER) {
							connectedLand++;
						}
					}
					neighbourTiles = getNeighbourTiles(world, x, y, true);
					for (Coord coord : neighbourTiles) {
						if (world.getTiles()[coord.getX()][coord.getY()] == Tile.WATER) {
							waterNeighbours++;
						}
					}

					if (connectedLand >= 5) {
						world.getTiles()[x][y] = Tile.GRASS;
					} else if (connectedLand <= 3) {
						world.getTiles()[x][y] = Tile.WATER;
					}

					if (world.getTiles()[x][y] == Tile.GRASS && waterNeighbours > 0) {
						world.getTiles()[x][y] = Tile.SAND;
					}

				}
			}
			smoothTime--;
		}
	}

}
