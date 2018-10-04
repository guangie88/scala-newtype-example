import io.estatico.newtype.macros.newtype

package object types {
  @newtype case class Adv[T](value: T)
  @newtype case class AdvInt(value: Int)
}
