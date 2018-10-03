import io.estatico.newtype.ops._

import types._

object Main extends App {
  def printBase[T](x: T) = println(x)
  def printAdv[T](x: Adv[T]) = println(x)

  val v = Adv[Int](777)

  // this will fail which is good
  // printBase(v)

  // this will work
  printAdv(v)

  // for coercing into base type
  printBase(v.coerce[Int])
}
