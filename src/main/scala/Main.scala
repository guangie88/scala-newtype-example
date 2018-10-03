import io.estatico.newtype.ops._

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import types._

object Main extends App {
  def printBase[T](x: T) = println(x)
  def printAdv[T](x: Adv[T]) = println(x)

  val x = Adv[Int](777)

  // this will fail which is good
  // printBase(x)

  // this will work
  printAdv(x)

  // for coercing into base type
  printBase(x.coerce[Int])

  // for Kryo serialization
  val kryo = new Kryo()
  val byteArr = new Array[Byte](256)

  val output = new Output(byteArr);
  kryo.writeObject(output, x)
  output.close()

  val input = new Input(byteArr);
  val y = kryo.readObject(input, classOf(Adv[Int].Type))
  input.close()

  println(y)
}
