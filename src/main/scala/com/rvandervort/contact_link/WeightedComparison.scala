package com.rvandervort.contact_link

import com.rvandervort.contact_link.comparisons._
import scala.math._

object WeightedComparison extends BasicComparisons {
  def compare(contactA: Contact, contactB: Contact): ComparisonResult = {

    val result = new ComparisonResult()

    result.addScore("email", check(contactA.email, contactB.email) * .25)
    result.addScore("phone", check(contactA.phone, contactB.phone) * 0.25)
    result.addScore("lastName", checkDistance(contactA.lastName,contactB.lastName) * 0.15)
    result.addScore("firstName", checkDistance(contactA.firstName,contactB.firstName) * 0.15)
    result.addScore("zipCode",check(contactA.zipCode, contactB.zipCode) * 0.10)
    result.addScore("address1",check(contactA.address1, contactB.address1) * 0.05)
    result.addScore("address2",check(contactA.address2, contactB.address2) * 0.05)
    result.addScore("city", check(contactA.city, contactB.city) * 0.025)
    result.addScore("state",check(contactA.state, contactB.state) * 0.025)

    result
  }

  def check(value1: String, value2: String) =
    if (equivalent(value1, value1)) 100.0 else 0.00


  def checkDistance(value1: String, value2: String): Double = {
    distance(value1, value2) match {
      case Some(edits) => (max(value1.length, value2.length) - edits).toDouble
      case None        => 0.0
    }
  }

}
