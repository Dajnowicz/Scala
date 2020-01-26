import akka.actor.{Actor, ActorLogging, Props, ActorSystem, ActorRef}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import scala.math.abs

case object Tick
case object RadarTick
case object CoordinatesRequest
case class Echo(ships: Set[(Int, Int, ActorRef)])
case class Pozycja(x: Int, y: Int)
case class Ognia(x: Int, y: Int)

case class Coordinates(aref: ActorRef, x: Int, y: Int) {

}

class Ship(x: Int, y: Int, val dim: Int) extends Actor with ActorLogging {
  var posX = x
  var posY = y

  def receive: Receive = {
    case Tick =>
      if( tossACoinTwice() ) move
      // log.info(s"[${self.path.name}] ($posX, $posY)")
    case RadarTick => 
       context.actorSelection("/user/ship*") ! Pozycja(posX, posY)
    case Pozycja(x,y) =>
      if(sender != self) {
        if (x == posX && y == posY) {
          log.info(s"Tu [${self.path.name}] Nie umiem prowadzić, idę na dno *dies of embarassment* LNC: ($posX, $posY)")
          context.stop(self)
        } else {
          if(inRange(x,y)) {
            // log.info(s"Tu [${self.path.name}], ${sender.path.name}($x, $y) w zasiegu strzalu!")
            if( tossACoin() ) sender ! Ognia(x,y)
            else move
          }
        }
      }
    case Ognia(x,y) =>
      if(x == posX && y == posY) {
        log.info(s"Tu [${self.path.name}] OH NIE! Oberwalem od ${sender.path.name} *umiera* LNC: ($posX, $posY)")
        context.stop(self)
      }
  }

  def move: Unit = {
    tossTwoCoins() match {
      case 0 => posX+=1
      case 1 => posX-=1
      case 2 => posY+=1
      case 3 => posY-=1
    }
    if(posX > 25) posX = -25
    if(posX < -25) posX = 25
    if(posY > 25) posY = -25
    if(posY < -25) posY = 25
  }

  def inRange(x: Int, y: Int): Boolean = {
    (abs(x - posX) <= 3) && (abs(y - posY) <= 3)
  }

  def tossACoin(): Boolean = {
    val ran = new util.Random()
    ran.nextInt % 2 == 0
  }

  def rzutZnakiem(): Int = {
    if( tossACoin() ) 1
    else -1
  }

  def tossACoinTwice(): Boolean = {
    !(tossACoin() && tossACoin())
  }

  def tossTwoCoins(): Int = {
    val ran = new util.Random()
    ran.nextInt(4)
  }

}
object Ship {
  def props(x: Int, y: Int, dim: Int): Props = {
    Props(classOf[Ship], x, y, dim)
  }
}

class Radar() extends Actor with ActorLogging {
  var ships: Set[(Int, Int, ActorRef)] = Set()
  def receive: Receive = {
    case Pozycja(x,y) => 
      log.info(s"Dostalem pozycje ($x, $y) od $sender")
    case Tick => 
      context.actorSelection("/user/ship*") ! CoordinatesRequest
  }


 }
 object Radar {
   def props(): Props = {
     Props(classOf[Radar])
   }
 }

object Demo extends App {

  val system = ActorSystem("DispDemo")
  // wprowadźmy sobie skrótową nazwę na „planistę” systemowego:
  val scheduler = system.scheduler

  val r = new util.Random()
  val dim = 50

  // val rad = system.actorOf(Radar.props(), "radar")

  val actors = for (i <- 1 to 10) {
    val x = (r.nextInt(dim) - (dim / 2)) // jakoś losowo zmieniać znak
    val y = (r.nextInt(dim) - (dim / 2)) // jakoś losowo zmieniać znak
    system.actorOf(Ship.props(x, y, dim), s"ship$i")
  }

  /*
    Operacjom które wymagają wykorzystania wątków, a taką
    jest cykliczne wysyłanie wiadomości, musimy dostarczyć
    domyślne źródło wątków – tzw. „kontekst wykonawczy”.
    Można to zrobić na kilka sposobów:
  */
  import ExecutionContext.Implicits.global
  /*
    powyższe jest równoważne zdefiniowaniu „niejawnego kontekstu”:

    implicit val ec: ExecutionContext = ExecutionContext.global

    Mając do dyspozycji system aktorów możemy też użyć jego „dyspozytora”
    jako kontektu wykonawczego:

    import system.dispatcher

    Obie powyższe możliwości są dobre w naszym przypadku, ale w sytuacji
    gdy operacja może zablokować wątek warto użyć kontekstu wykonawczego
    stworzonego specjalnie do tego celu – patrz dokumentacja Akki, hasło
    „Dedicated dispatcher for blocking operations”.
  */
  val cancellable = scheduler.scheduleWithFixedDelay(1.seconds, 350.milliseconds) {
    new Runnable {
      def run(): Unit = {
        system.actorSelection("/user/ship*") ! Tick
      }
    }
  }

  val radar = scheduler.scheduleWithFixedDelay(1.seconds, 500.milliseconds) {
    new Runnable {
      def run(): Unit = {
        system.actorSelection("/user/ship*") ! RadarTick
      }
    }
  }

}
