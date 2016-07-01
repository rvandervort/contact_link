import com.rvandervort.contact_link._
import com.rvandervort.contact_link.cleansing._
import org.scalatest._

object TestTrait extends DataCleaner { }

class DataCleanerSpec extends FunSpec with Matchers {
  describe(".cleanPhone()") {
    it("returns the string minus invalid characters") {

      assert(TestTrait.cleanPhone("412-123.2435 x123") == "4121232435123")

      assert(TestTrait.cleanPhone("+1 (814)-234-4325") == "18142344325")
    }
  }

  describe(".cleanAddress()") {
    it("returns the string minus invalid characters") {
      assert(TestTrait.cleanAddress("123 W. 12th st Apt #124") == "123W12thstApt124") 
    }
  }
}

