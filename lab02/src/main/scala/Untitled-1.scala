object Lab02 extends App {


@annotation.tailrec
def concatString(str: String,n : Int, acc: String): String =
    if (n <= 0) acc
    else concatString(str,n-1,str+acc)

//println(concatString("hello", 3,""))



def isPrime(n: Int): Boolean = {
    @annotation.tailrec
    def isPrimeTailRec(t: Int, isStillPrime: Boolean): Boolean = 
        if (!isStillPrime) false
        else if( t<=1) true
        else isPrimeTailRec(t-1, n % t != 0 && isStillPrime)
    
    isPrimeTailRec(n/2, true)
}

def fibonacci(n: Int): Int = {
    def fiboHelper(t: Int, acc1: Int, acc2: Int): Int =
        if(t>=n) acc1
        else fiboHelper(t+1, acc1+acc2, acc1)

    if (n<=2) 1
    else (fiboHelper(2,1,1))

}

//println(fibonacci(8))
@annotation.tailrec
def sum(x: List[Int], aku: Int = 0): Int = (x) match {
    case List()        => aku
    case glowa :: ogon => sum(ogon, aku + glowa)
    
  }

def succ(n: Int) = n + 1
  def pred(n: Int) = n - 1

val lista: List[Int] = List(1, 2, 3, 4)
println("sum:" + sum(lista))
def reverse[T](l: List[T], o: List[T] = List()): List[T] = (l) match{
    case List() => o
    case glowa :: ogon => reverse(ogon,glowa :: o)
}


@annotation.tailrec
def map[A, B](x: List[A], f: A => B, wynik:List[B] = List()): List[B] = (x) match{

case List() => reverse(wynik)
case glowa :: ogon => map(ogon,f,f(glowa) :: wynik)

}

println("map: "+map(lista,succ))

}