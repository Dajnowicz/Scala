/*
  Rozwiązując poniższe ćwiczenia NIE korzystaj z następujących
  standardowych operacji na listach:
    – length/size
    – sum
    – map
    – filter
    – ::: (oraz „odmian” typu ++)
    – flatten
    – flatMap
    – reverse (oraz „odmian” tzn. reverseMap, reverse_:::)

  Nie używaj też zmiennych (wprowadzanych za pomocą „var”).
 */

object Lab02 extends App {

  def succ(n: Int) = n + 1
  def pred(n: Int) = n - 1

  // Ćwiczenie 1
  // Nie używaj + ani - na Int. Użyj succ/pred zdefiniowanych powyżej.
  @annotation.tailrec
  def add(x: Int, y: Int): Int = ???

  // Ćwiczenie 2
  @annotation.tailrec
  def sum(x: List[Int]): Int = ???

  // Ćwiczenie 3
  @annotation.tailrec
  def length[A](x: List[A]): Int = ???

  // Ćwiczenie 4
  @annotation.tailrec
  def map[A, B](x: List[A], f: A => B): List[B] = ???

  // Ćwiczenie 5
  @annotation.tailrec
  def filter[A](x: List[A], f: A => Boolean): List[A] = ???

  // Ćwiczenie 6
  // funkcja "cleanup" powinna usuwać wielokrotne, następujące po sobie
  // wystąpienia tego samego elementu na liście
  // np. cleanup(List(1,1,2,1,3,3)) == List(1,2,1,3)
  @annotation.tailrec
  def cleanup(x: List[Int]): List[Int] = ???

  // Ćwiczenie 7
  // funkcja "chop" powinna wycinać „podlistę” zaczynającą się od elementu
  // o numerze "b" i kończącą na elemencie o numerze "e" - przyjmijmy, że
  // pierwszy element listy ma numer 1. Przykład
  // chop(List('a,2,'b,3,'c,4),2,4) == List(2,'b,3)
  @annotation.tailrec
  def chop[A](x: List[A], b: Int, e: Int): List[A] = ???

  // Ćwiczenie 8
  // funkcja "remEls" powinna usuwać co "k"-ty element listy. Przykład:
  // remEls(List(1,1,2,1,3,3),3) == List(1,1,1,3)
  @annotation.tailrec
  def remEls[A](x: List[A], k: Int): List[A] = ???

  // Ćwiczenie 9
  // funkcja "rot" powinna przesuwać cyklicznie elementy listy o wartość "k".
  // Przykład:
  // rot(List(1,2,3,4,5,6),3) == List(4,5,6,1,2,3)
  @annotation.tailrec
  def rot[A](x: List[A], k: Int): List[A] = ???

}
