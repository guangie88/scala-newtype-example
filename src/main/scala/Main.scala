import io.estatico.newtype.ops._

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import types._

object Main extends App {
  def printInt(x: Int) = println(x)
  def printAdv[T](x: Adv[T]) = println(x)

  val x = Adv[Int](777)

  // this will fail which is good
  // printInt(x)

  // this will work
  printAdv(x)
  println(x.getClass)

  // for coercing into base type
  printInt(x.coerce[Int])
  printInt(x.value)

  // for Kryo serialization
  val kryo = new Kryo()
  val byteArr = new Array[Byte](256)

  val output = new Output(byteArr);
  kryo.writeObject(output, x)
  output.close()

  val input = new Input(byteArr);
  val y = Adv[Int](kryo.readObject(input, classOf[AdvInt.Repr]))
  input.close()

  // this will fail which is good
  // printInt(y)

  // this will work
  printAdv(y)
  println(y.getClass)
}
