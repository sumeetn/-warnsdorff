package algorithm

import com.typesafe.scalalogging.LazyLogging

/**
  * Algorithm:
  * Using Warnsdorff Heuristic
  * The knight is moved so that it always proceeds to the square from which the knight will have the fewest onward
  * moves. When calculating the number of onward moves for each candidate square, we do not count moves that
  * revisit any square already visited
  * https://en.wikipedia.org/wiki/Knight%27s_tour#Warnsdorf.27s_rule
  *
  */

class Warnsdorff(dimension: Int = 10,
                 coordinates: Array[Tuple2[Int, Int]] = Array[Tuple2[Int, Int]]((0, -3), (0, 3), (3, 0), (-3, 0), (2, 2), (2, -2), (-2, -2), (-2, 2)),
                 printAllRoutes: Boolean = false) extends LazyLogging {

  val ROW = dimension
  val COLUMN = dimension


  def init(i: Int, j: Int) = -1

  def isValid(xy: Tile, maxX: Int, maxY: Int): Boolean = xy.x >= 0 && xy.x < maxX && xy.y >= 0 && xy.y < maxY

  def isVisited(xy: Tile, chequerboard: Array[Array[Int]]): Boolean = {
    val value: Int = chequerboard(xy.x)(xy.y)
    value != -1
  }

  def visit(xy: Tile, chequerBoard: Array[Array[Int]], value: Int): Array[Array[Int]] = {
    chequerBoard(xy.x)(xy.y) = value
    chequerBoard
  }

  def findAllRoutes(): Unit = {
    for (x <- 0 until ROW) {
      for (y <- 0 until COLUMN) {
        val chequerboard = Array.tabulate(ROW, COLUMN)(init)
        val currentStep = 1
        val initPoint = Tile(x, y)
        val board = visit(initPoint, chequerboard, currentStep)
        if (solve(board, initPoint, currentStep + 1)) {
          printBoard(board)
          //It is expected to that find at least one path, so breaking loop here
          if (!printAllRoutes) {
            return
          }

        }
      }
    }
    logger.warn("Route not available !!!!")
  }

  def printBoard(checkerBoard: Array[Array[Int]]) = {
    checkerBoard.map(arr => println(arr.mkString(",")))
  }


  def searchNumber(num: Int, arr: Array[Array[Int]]) = {
    val indices = for {
      (a, i) <- arr.iterator.zipWithIndex
      (c, j) <- a.iterator.zipWithIndex
      if (c == num)
    } yield i -> j

    indices.toList

  }

  /**
    * Iterate the entire board recursively.
    *
    * @param board
    * @param point
    * @param currentStep
    * @return
    */
  def solve(board: Array[Array[Int]], point: Tile, currentStep: Int): Boolean = {
    if (currentStep == ((ROW * COLUMN) + 1)) return true
    val neighbours: Array[Tile] = getNeighbours(point, board)
    // Choose the candidate with the min weight.
    val smallestCandidate: Option[(Tile, Int)] = neighbours.map(point => point -> weight(point, board))
      .minByOption(tup => tup._2)
    smallestCandidate match {
      case Some(tuple) => {
        visit(tuple._1, board, currentStep)
        solve(board, tuple._1, currentStep + 1)
      }
      case None => {
        return false
      }
    }
  }


  /**
    * Find number of accessible tiles
    */
  private def weight(tile: Tile, board: Array[Array[Int]]) = getNeighbours(tile, board).size


  private def getNeighbours(tile: Tile, board: Array[Array[Int]]): Array[Tile] = {

    val arr = coordinates.map(xy => {
      val newBoardPosition = Tile(xy._1 + tile.x, xy._2 + tile.y)
      if (isValid(newBoardPosition, ROW, COLUMN) && !isVisited(newBoardPosition, board)) {
        Some(newBoardPosition)
      }
      else
        None
    })
    arr.flatten
  }
}

case class Tile(x: Int, y: Int)