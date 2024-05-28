package lectures.implicits

object Givens extends App {
  val aList = List(2,4,3,1)
  val anOrderedList = aList.sorted // implicit Ordering[Int]

  // Scala 2 style
  object Implicits {
    implicit val descendingOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)
  }

  // Scala 3 style
  object Givens {
    given descendingOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)
    // givens <=> implicit vals
  }

  // instantiating an anonymous class
  object GivenAnonymousClassNaive {
    given descendingOrdering_v2: Ordering[Int] = new Ordering[Int] {
      override def compare(x: Int, y: Int) = y - x
    }
  }

}
