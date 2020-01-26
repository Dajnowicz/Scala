/*
    Uzupełnij poniższą definicję klasy liczb wymiernych tak, aby:

    1. posiadała operacje: dodawania (+), odejmowania (-), dzielenia (/)
    2. minus jednoargumentowy zachowywał się sensowniej w przypadkach takich
       jak np. „-Q(1,-2)”
    3. możliwe było wykonywanie operacji arytmetycznych postaci:

           n # Q(?,?)
           Q(?,?) # n

       gdzie # oznacza dowolną z operacji na Int (+, -, /, *)

       oraz deklarowanie i inicjalizacja postaci:

       val liczba: Q = n

       gdzie n: Int
    4. obiekty klasy Q można było wykorzystywać wszędzie tam, gdzie oczekiwane
       jest „posiadanie cechy” math.Ordering[Q] – skonstruuj odpowiedniego „świadka”
       (wykorzystaj konstrukcję implicit).
*/
class Q(l: Int, m: Int) {
    private val nd = nwd(l, m)
    val licz = l / nd
    val mian = m / nd
    private def nwd(a: Int, b: Int): Int = {
        val x = a.abs
        val y = b.abs
        if (x > y) nwd(x - y, y)
        else {
            if (x == y) x
            else nwd(x, y - x)
        }
    }
    override def toString: String = s"$licz/$mian"
    override def equals(that: Any): Boolean = that match {
        case v: Q => v.licz == licz && v.mian == mian
        case _ => false
    }
    override def hashCode: Int = licz * 7 + mian * 13

    def *(q: Q): Q = Q(licz * q.licz, mian * q.mian)
    def unary_- : Q = Q(-licz, mian)
}
object Q {
    def apply(l: Int, m: Int) = new Q(l, m)
}
