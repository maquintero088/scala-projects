package lectures.typesystem


object InnerTypes extends App {

  class Outer {
    class Inner
    object InnerObject
    type InnerType

    def print(i: Inner) = println(i)
    def printGeneral(i: Outer#Inner) = println(i)
  }

  def aMethod: Int = {
    class HelperClass
    type HelperType = String
    2
  }

  // per-instance
  val o = new Outer
  val inner = new o.Inner 
  // o.Inner is a TYPE

  val oo = new Outer
  //  val otherInner: oo.Inner = new o.Inner

  o.print(inner)
  //  oo.print(inner)
}
