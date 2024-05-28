package lectures.typesystem

object FBoundedPolymorphism extends App {
  // Solution 2 - FBP

  trait Animal[A <: Animal[A]] {
    // recursive type: F-Bounded Polymorphism
    def breed: List[Animal[A]]
  }

  class Cat extends Animal[Cat] {
    override def breed: List[Animal[Cat]] = ???
    // List[Cat] !!
  }

  class Dog extends Animal[Dog] {
    override def breed: List[Animal[Dog]] = ???
    // List[Dog] !!
  }

  trait Entity[E <: Entity[E]] // ORM
  class Person extends Comparable[Person] {
    // FBP
    override def compareTo(o: Person): Int = ???
  }

  class Crocodile extends Animal[Dog] {
    override def breed: List[Animal[Dog]] = ???
    // List[Dog] !!
  }

  // Solution 3 - FBP + self-types

  trait AnimalS[A <: Animal[A]] { self: A =>
      def breed: List[Animal[A]]
    }

    class CatS extends Animal[Cat] {
      override def breed: List[Animal[Cat]] = ??? // List[Cat] !!
    }

    class DogS extends Animal[Dog] {
      override def breed: List[Animal[Dog]] = ??? // List[Dog] !!
    }

  class CrocodileS extends Animal[Dog] {
    override def breed: List[Animal[Dog]] = ??? // List[Dog] !!
  }

  trait Fish extends Animal[Fish]
  class Shark extends Fish {
    override def breed: List[Animal[Fish]] = List(new Cod) // wrong
  }

  class Cod extends Fish {
    override def breed: List[Animal[Fish]] = ???
  }
  // Solution #5

  trait AnimalType[A] {
    // pure type classes
    def breed(a: A): List[A]
  }

  class DogType
  object Dog {
    implicit object DogAnimal extends AnimalType[Dog] {
      override def breed(a: Dog): List[Dog] = List()
    }
  }

  class CatType
  object Cat {
    implicit object CatAnimal extends AnimalType[Dog] {
      override def breed(a: Dog): List[Dog] = List()
    }
  }

  implicit class AnimalOps[A](animal: A) {
    def breed(implicit animalTypeClassInstance: AnimalType[A]): List[A] =
      animalTypeClassInstance.breed(animal)
  }

  val dog = new Dog
  dog.breed

//  val cat = new Cat
//  cat.breed

}
