package lectures.typesystem

object RecursiveTypes extends App {

  trait Animal {
    def breed: List[Animal]
  }

  class Cat extends Animal {
    override def breed: List[Animal] = ??? 
    // List[Cat] !!
  }

  class Dog extends Animal {
    override def breed: List[Animal] = ??? 
    // List[Dog] !!
  }

//   Solution 1 - native

  trait AnimalAsNative {
    def breed: List[AnimalAsNative]
  }

  class CatAsNative extends AnimalAsNative {
    override def breed: List[CatAsNative] = ??? // List[Cat] !!
  }

  class DogAsNative extends AnimalAsNative {
    override def breed: List[CatAsNative] = ??? // List[Dog] !!
  }

//   Exercise

//   Solution 4 type classes!

  trait AnimalType
  trait CanBreed[A] {
    def breed(a: A): List[A]
  }

  class DogType extends AnimalType
  object DogType {
    implicit object DogsCanBreed extends CanBreed[Dog] {
      def breed(a: Dog): List[Dog] = List()
    }
  }

  implicit class CanBreedOps[A](animal: A) {
    def breed(implicit canBreed: CanBreed[A]): List[A] =
      canBreed.breed(animal)
  }

  val dog = new Dog
  dog.breed // List[Dog]!!
  /*
    new CanBreedOps[Dog](dog).breed(Dog.DogsCanBreed)
    implicit value to pass to breed: Dog.DogsCanBreed
   */

  class CatType extends AnimalType
  object CatType {
    implicit object CatsCanBreed extends CanBreed[Dog] {
      def breed(a: Dog): List[Dog] = List()
    }
  }

  val cat = new Cat
  cat.breed
  val anotherCat = new Cat
  anotherCat.breed

}
