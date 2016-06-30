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


}
