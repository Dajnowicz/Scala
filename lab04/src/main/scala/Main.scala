object Main extends App {
  import Q._

  println(Q(-2,-4))
  println(Q(-2,4))
  println(Q(2,-4))
  println(Q(1,2))
  println(Q(2,4) == Q(1,2))
  println(Q(2,4)*Q(1,2))

  val zbiór = Set(Q(1,2), Q(1,3), Q(1,4), Q(1,5), Q(1,6))

  println(zbiór contains Q(1,2))
  println(Q(1,2).hashCode)
  println(Q(1,2).hashCode)
  println("For my next trick, unary -")
  println(-Q(1,-2))

  //Ta linijka to kolejna część punktu 3. 
  implicit def intToQ(n: Int): Q = Q(n,1)

  println("n # Q")
  println(2 + Q(1,3))
  println(2 - Q(1,3))
  println(2 * Q(1,3))
  println(2 / Q(1,3))
  println("Q # n")
  println(Q(1,3) + 2)
  println(Q(1,3) - 2)
  println(Q(1,3) * 2)
  println(Q(1,3) / 2)

  println("val liczba: Q = 2")
  val liczba: Q = 2
  println(liczba)

  println("compare")
  val lis = List( Q(1,2), Q(-2,4), Q(2,3), Q(1,4), Q(1,1) ).sorted
  println(lis)
}
