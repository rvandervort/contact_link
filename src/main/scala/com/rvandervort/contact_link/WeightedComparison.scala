package com.rvandervort.contact_link

import com.rvandervort.contact_link.comparisons._
import scala.math._

object WeightedComparison extends BasicComparisons {
  def compare(contactA: Contact, contactB: Contact): ComparisonResult = {
    val result = new ComparisonResult()

    val weights: Map[String, Double] = Map(
      "email"     -> 0.25,
      "phone"     -> 0.25,
      "lastName"  -> 0.15,
      "firstName" -> 0.15,
      "zipCode"   -> 0.10,
      "address1"  -> 0.05,
      "address2"  -> 0.05,
      "city"      -> 0.025,
      "state"     -> 0.025
    )

    def score(propertyName: String, value1: String, value2: String)(f: (String, String) => Double) = {
      result.addScore(propertyName, f(value1, value2) * weights(propertyName))
    }

    score ("email"     , contactA.email     , contactB.email    ) (check        )
    score ("phone"     , contactA.phone     , contactB.phone    ) (check        )
    score ("lastName"  , contactA.lastName  , contactB.lastName ) (checkDistance)
    score ("firstName" , contactA.firstName , contactB.firstName) (checkDistance)
    score ("zipCode"   , contactA.zipCode   , contactB.zipCode  ) (check        )
    score ("address1"  , contactA.address1  , contactB.address1 ) (check        )
    score ("address2"  , contactA.address2  , contactB.address2 ) (check        )
    score ("city"      , contactA.city      , contactB.city     ) (check        )
    score ("state"     , contactA.state     , contactB.state    ) (check        )

    result
  }

  def check(value1: String, value2: String): Double =
    if (equivalent(value1, value1)) 100.0 else 0.00

  def checkDistance(value1: String, value2: String): Double = {
    distance(value1, value2) match {
      case Some(edits) => (max(value1.length, value2.length) - edits).toDouble
      case None        => 0.0
    }
  }
}
