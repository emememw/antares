package com.emveyh.antares.map;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.emveyh.antares.utils.Coord;

public class MapGenerator {

	public static void main(String[] args) {
		new MapGenerator().generateSingleGameMap();
	}

	private static void createRiver(GameMap map) {

		Random random = new Random();

		// pick random beach tile
		List<Coord> beachTiles = new LinkedList<Coord>();
		List<Coord> waterTiles = new LinkedList<Coord>();
		for (int x = 0; x < map.getTiles().length; x++) {
			for (int y = 0; y < map.getTiles()[x].length; y++) {
				if (map.getTiles()[x][y] == Tile.GRASS) {
					beachTiles.add(new Coord(x, y));
				} else if (map.getTiles()[x][y] == Tile.WATER) {
					waterTiles.add(new Coord(x, y));
				}

			}
		}

		Coord startPoint = beachTiles.get(random.nextInt(beachTiles.size()));
		Coord endPoint = null;

		Collections.shuffle(waterTiles);

		for (Coord coord : waterTiles) {
			int difX = coord.getX() - startPoint.getX();
			if (difX < 0) {
				difX *= -1;
			}

			int difY = coord.getY() - startPoint.getY();
			if (difY < 0) {
				difY *= -1;
			}

			if (difX > 30 || difY > 30) {
				endPoint = new Coord(difX, difY);
			}
		}

		int currentX = startPoint.getX();
		int currentY = startPoint.getY();

		int maxLoop = random.nextInt(map.getTiles().length) * 3;
		int curLoop = 0;
		while ((currentX != endPoint.getX() || currentY != endPoint.getY()) && curLoop < maxLoop) {
			int direction = random.nextInt(4);
			int x = currentX;
			int y = currentY;

			if (direction == 0) {
				x++;
			} else if (direction == 1) {
				x--;
			} else if (direction == 2) {
				y++;
			} else if (direction == 3) {
				y--;
			}

			if (x >= 0 && x < map.getTiles().length && y >= 0 && y < map.getTiles()[x].length) {
				map.getTiles()[x][y] = Tile.WATER;
				currentX = x;
				currentY = y;
			}

			if (random.nextInt(100) < 20) {
				break;
			}

			curLoop++;

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

		int worldScale = 8;
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
		double chanceLossPerStep = Double.valueOf(worldDimension * 0.02).intValue();
		double lossScale = 0.085;
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

		for (int i = 0; i < 10; i++) {
			createRiver(world);
		}

		//
		smoothTime = 3;
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

		printWorld(world);
		return world;
	}

}
