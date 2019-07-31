package algorithm

import com.typesafe.scalalogging.LazyLogging

object RunMain extends LazyLogging {
  def main(args: Array[String]) = {
    val DEFAULT_DIMENSION = 10
    val moves = Array[Tuple2[Int, Int]]((0, -3), (0, 3), (3, 0), (-3, 0), (2, 2), (2, -2), (-2, -2), (-2, 2))
    val dimension: Int = try {
      args(0).toInt
    } catch {
      case outOfBoundExp: ArrayIndexOutOfBoundsException => {
        DEFAULT_DIMENSION
      }
      case parseException: NumberFormatException =>
        logger.warn("Invalid dimension " + args(0))
        logger.warn("Terminating .. ")
        System.exit(0)
        DEFAULT_DIMENSION
    }

    logger.info("Calculating path for default dimension : " + dimension + "X" + dimension)
    val algo = new Warnsdorff(dimension,moves)
    val t0 = System.nanoTime()
    algo.findAllRoutes()
    val t1 = System.nanoTime()
    logger.info("Execution time: " + (t1 - t0)/1000000 + " ms")
  }
}
