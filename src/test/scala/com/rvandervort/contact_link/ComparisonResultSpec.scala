import com.rvandervort.contact_link._
import org.scalatest._

class ComparisonResultSpec extends FunSpec with Matchers {
  describe(".addScore") {
    it("records the score correctly") {
      val result = new ComparisonResult()

      assert(result.scores.size == 0)

      result.addScore("field1", 10.0)
      result.addScore("field2", 10.0)
      result.addScore("field3", 10.0)

      result.scores.size shouldEqual 3
    }
  }

  describe(".overallScore") {
    it("returns the sum of all of the recorded sub-scores") {
      val result = new ComparisonResult()

      result.addScore("field1", 10.0)
      result.addScore("field2", 23.5)

      result.overallScore shouldEqual 33.5
    }
  }

  describe(".toString") {
    it("it returns the overall score concatenated with the individual scores") {
      val result = new ComparisonResult()

      result.addScore("field1", 10.0)
      result.addScore("field2", 23.5)

      result.toString should be ("33.5,field2=23.5,field1=10.0")
    }
  }
}
