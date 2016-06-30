package com.rvandervort.contact_link

import com.rvandervort.contact_link.comparisons._
import scala.math._

object WeightedComparison extends BasicComparisons {
  def compare(contactA: Contact, contactB: Contact): ComparisonResult = {

    val result = new ComparisonResult()

    result.add_score("email", check(contactA.email, contactB.email) * .25)
    result.add_score("phone", check(contactA.phone, contactB.phone) * 0.25)
    result.add_score("lastName", check_distance(contactA.lastName,contactB.lastName) * 0.15)
    result.add_score("firstName", check_distance(contactA.firstName,contactB.firstName) * 0.15)
    result.add_score("zipCode",check(contactA.zipCode, contactB.zipCode) * 0.10)
    result.add_score("address1",check(contactA.address1, contactB.address1) * 0.05)
    result.add_score("address2",check(contactA.address2, contactB.address2) * 0.05)
    result.add_score("city", check(contactA.city, contactB.city) * 0.025)
    result.add_score("state",check(contactA.state, contactB.state) * 0.025)

    result
  }

  def check(value1: String, value2: String) =
    if (equivalent(value1, value1)) 100.0 else 0.00


  def check_distance(value1: String, value2: String): Double = {
    distance(value1, value2) match {
      case Some(edits) => (max(value1.length, value2.length) - edits).toDouble
      case None        => 0.0
    }
  }

}
