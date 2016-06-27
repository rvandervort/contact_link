import com.rvandervort.contact_link._
import org.scalatest._

class WeightedComparisonSpec extends FunSpec with Matchers {

  describe(".compare") {
    it("returns a ComparisonResult") {
      val c1 = Contact("1","first_name","email","phone","last_name","street1","street2","city","state","zipcode")
      val c2 = Contact("2","first_name","email","phone","last_name","street1","street2","city","state","zipcode")

      WeightedComparison.compare(c1,c2) shouldBe a [ComparisonResult]
    }
  }

  describe(".equivalent()") {
    it("returns true if the strings are exactly equal") {
       assert(WeightedComparison.equivalent("a string", "a string"))
    }

    it("returns true if the strings are equal, case insensitive") {
      assert(WeightedComparison.equivalent("a string", "A STRING"))
    }

    it("returns true if the strings are equal, even with trailing spaces") {
       assert(WeightedComparison.equivalent("a string", "a string   "))
    }

    it("returns false if the strings aren't equal") {
       assert(WeightedComparison.equivalent("a string", "other string") == false)
    }
  }

  describe(".basic_check()") {
    it("returns 100.00 if the strings are equivalent") {
      assert(WeightedComparison.basic_check("the same", "the same") == 100.00)  
    }

    it("returns 0.00 if the strings are not equivalent") {
      assert(WeightedComparison.basic_check("the same", "NOT the same") == 0.00)  
    }
  }


}
