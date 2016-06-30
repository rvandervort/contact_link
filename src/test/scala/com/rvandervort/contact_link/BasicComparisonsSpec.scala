import com.rvandervort.contact_link._
import com.rvandervort.contact_link.comparisons._
import org.scalatest._

object TestTraitComparisons extends BasicComparisons { }

class BasicComparisonsSpec extends FunSpec with Matchers {
  describe(".equivalent()") {
    it("returns true if the strings are exactly equal") {
       assert(TestTraitComparisons.equivalent("a string", "a string"))
    }

    it("returns true if the strings are equal, case insensitive") {
      assert(TestTraitComparisons.equivalent("a string", "A STRING"))
    }

    it("returns true if the strings are equal, even with trailing spaces") {
       assert(TestTraitComparisons.equivalent("a string", "a string   "))
    }

    it("returns false if the strings aren't equal") {
       assert(TestTraitComparisons.equivalent("a string", "other string") == false)
    }
  }

  describe(".distance()") {
    it("returns the edit distance of the two strings as an Option[Int]") {
      val ret = TestTraitComparisons.distance("String 1", "String 2")

      ret shouldBe a [Option[_]]

      assert(ret.get == 1)
    }
  }
  
}

