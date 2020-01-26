abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](b: B): MyList[B]
    protected def elements: String
    override def toString: String = s"MyList(${elements})"
}
/*
  Zadanie 1: Pusta lista powinna być jedna. Jak to zrobić?
  Wskazówka: dla dowolnego typu X w Scali mamy „Nothing <: X”
*/
object MyEmptyList extends MyList[Nothing] {
// class MyEmptyList[A] extends MyList[A] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B](a: B): MyList[B] = new MyNonEmptyList[B](a, this)
    def elements: String = ""
}
class MyNonEmptyList[A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](b: B): MyList[B] = new MyNonEmptyList[B](b, this)
    private def elems(x: MyList[A] = t, aku: String = s"$h"): String = {
      if(x.isEmpty) {
        aku
      } else {
        elems(x.tail, s"$aku,${x.head}")
      }
    }
    def elements: String = {
      elems()
    }
}
/*
  Zadanie 2: Spowoduj, żeby możliwe było definiowanie list tak, jak poniżej?

  MyList()
  MyList(1, 2, 3)
*/

object MyList {
  def apply[A](as: A*): MyList[A] = {
    var wynik: MyList[A] = MyEmptyList
    //Powinienem zmienic fora na rekurencje
    //for (a <- as) {
    //  wynik = wynik.add(a)
   // }
    wynik
  }
}

/*
  Zadanie 3: Zdefiniuj (przesłoń) metodę toString dla klasy MyList tak, żeby
  produkowała napisy postaci „[el1, ... , eln]”.
*/
