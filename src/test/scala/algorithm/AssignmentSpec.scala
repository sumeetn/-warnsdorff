package algorithm

import org.scalatest.FlatSpec

class AssignmentSpec extends FlatSpec {

  "Assignment" should "print single pawn path for default parameters" in {
    val algo = new Warnsdorff()
    algo.findAllRoutes()
    println(" 10 x 10 dimension ")
    println()
  }

  "Assignment" should "print single pawn path for custom dimension" in {
    val algo = new Warnsdorff(12)
    algo.findAllRoutes()
    println(" 12 x 12 dimension ")
    println()
  }

  "Assignment" should "print single pawn path for custom dimension and custom moves" in {
    val moves = Array[Tuple2[Int, Int]]((0, -1), (0, 1), (1, 0), (1, 0), (2, 2), (2, -2), (-2, -2), (-2, 2))
    val algo = new Warnsdorff(8,moves)
    algo.findAllRoutes()
    println(" 8 x 8 dimension custom moves")
    println()
  }

  "Assignment" should "should give warn message if route is not found " in {
    val moves = Array[Tuple2[Int, Int]]((0, -4), (0, 4), (4, 0), (-4, 0), (3, 3), (3, -3), (-3, -3), (-3, 3))
    val algo = new Warnsdorff(11,moves)
    algo.findAllRoutes()
    println(" 11 x 11 dimension custom moves")
    println()
  }

  "Assignment" should "should not throw exception for 0 dimension " in {
    val algo = new Warnsdorff(0)
    algo.findAllRoutes()
    println(" 0 x 0 dimension")
    println()
  }
}
