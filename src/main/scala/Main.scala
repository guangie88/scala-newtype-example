import io.estatico.newtype.ops._

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import types._

object Main extends App {
  def printInt(x: Int) = println(s"${x.getClass}: $x")
  def printAdv[T](x: Adv[T]) = println(s"${x.getClass}: $x")

  val x = Adv[Int](777)

  // this will fail which is good
  // printInt(x)

  // this will work
  printAdv(x)

  // for coercing into base type
  printInt(x.coerce[Int])
  printInt(x.value)

  // for Kryo serialization
  val kryo = new Kryo()
  val byteArr = new Array[Byte](256)

  val output = new Output(byteArr);
  kryo.writeObject(output, x)
  output.close()

  val inputY = new Input(byteArr);
  // weird syntax, it's Adv.Repr[Int] instead of Adv[Int].Repr
  val y = Adv[Int](kryo.readObject(inputY, classOf[Adv.Repr[Int]]))
  inputY.close()

  // this will fail which is good
  // printInt(y)

  // this will work
  printAdv(y)

  // reading into primitive works too
  val inputZ = new Input(byteArr);
  val z = kryo.readObject(inputZ, classOf[Int])
  inputZ.close()

  printInt(z)
}
