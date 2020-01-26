/*
    PRZYPOMNIENIE:
    =============

    Ćwiczenie 6
    funkcja "cleanup" powinna usuwać wielokrotne, następujące po sobie
    wystąpienia tego samego elementu na liście
    np. cleanup(List(1,1,2,1,3,3)) == List(1,2,1,3)

    Ćwiczenie 7
    funkcja "chop" powinna wycinać „podlistę” zaczynającą się od elementu
    o numerze "b" i kończącą na elemencie o numerze "e" - przyjmijmy, że
    pierwszy element listy ma numer 1. Przykład:
    chop(List('a,2,'b,3,'c,4),2,4) == List(2,'b,3)

    Ćwiczenie 8
    funkcja "remEls" powinna usuwać co "k"-ty element listy. Przykład:
    remEls(List(1,1,2,1,3,3),3) == List(1,1,1,3)
*/

object Lab03 extends App {

    // Ćwiczenie 10. Zdefiniuj funkcję flatenList, która „spłaszcza” ewentualne listy
    // zagnieżdżone w liście będącej jej argumentem:
    // flatenList(List(1,List('a',List(2,'b'))),'c') == List(1,'a',2,'b','c')
    // W definicji flatenList wykorzystaj rekurencję ogonową i wzorce.
    def flatenList(xs: List[Any]): List[Any] = {
        @annotation.tailrec
        def flatenPomoc(x: List[Any], acc: List[Any]): List[Any] = acc match {
            case Nil => x
            case (glowa: List[_]) :: reszta => flatenPomoc(x, glowa ::: reszta)
            case element :: reszta => flatenPomoc(x ::: List(element),reszta)
        }
        flatenPomoc(List(),xs);
    }

    println(flatenList(List(1,List('a',List(2,'b')),'c')) == List(1,'a',2,'b','c'))


    // // Ćwiczenie 11. Zaimplementuj uogólnienie funkcji cleanup z Ćwiczenia 6.
    // // stosując standardową metodę foldRight (Scala API - IterableOps)
     val list = List(1,1,2,1,3,3) 
     def cleanup2[A](l: List[A]):List[A] = l.foldRight(List[A]()) {
          case (e, ls) 
          if (ls.isEmpty || ls.head != e) => e::ls 
          case (e, ls) => ls 
     } 
        println(cleanup2(list))

    // // Ćwiczenie 12. Zaimplementuj funkcję chop z Ćwiczenia 7. stosując
    // // standardowe operacje drop i take (Scala API - IterableOps)
      def chop2[A](x: List[A], b: Int, e: Int): List[A] = {
           x.drop(b - 1).take(e - b + 1) 
           } 
           
    println(chop2(List('a,2,'b,3,'c,4),2,4))

    // // Ćwiczenie 13. Zaimplementuj funkcję remEls z Ćwiczenia 8. przy pomocy
    // // standardowych metod: filter, map i zipWithIndex (Scala API - IterableOps)
    def remEls2[A](x: List[A], k: Int): List[A] = {
        x.zipWithIndex .filter { 
            case (_, i) => (i + 1) % k != 0 } .map { 
                case (e, _) => e }
        } 
                
    println(remEls2(List(1,1,2,1,3,3),3))


}
