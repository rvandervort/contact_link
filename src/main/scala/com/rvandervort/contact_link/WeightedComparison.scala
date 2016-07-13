package com.rvandervort.contact_link

import com.rvandervort.contact_link.comparisons._
import scala.math._

object WeightedComparison extends BasicComparisons {
  type Weight = Double
  type FieldName = String
  type Processor = (String, String) => Double

  def compare(contactA: Contact, contactB: Contact): ComparisonResult = {
    val result = new ComparisonResult()

    val processors: Map[FieldName, (Weight, Processor)] = Map(
      "email"     ->  (0.25, check),
      "phone"     ->  (0.25, check),
      "lastName"  ->  (0.15, checkDistance),
      "firstName" ->  (0.15, checkDistance),
      "zipCode"   ->  (0.10, check),
      "address1"  ->  (0.05, check),
      "address2"  ->  (0.05, check),
      "city"      ->  (0.025, check),
      "state"     ->  (0.025, check)
    )

    Contact.zip(contactA,contactB).filterKeys(_ != "id").foreach {
      case (fieldName, valueTuple) =>
        val func = processors(fieldName)._2
        val weight = processors(fieldName)._1

        result.addScore(fieldName, func(valueTuple._1, valueTuple._2) * weight)
    }

    result
  }

  def check(value1: String, value2: String): Double =
    if (equivalent(value1, value1)) 100.0 else 0.00

  def checkDistance(value1: String, value2: String): Double = {
    distance(value1, value2) match {
      case Some(edits) =>  ((1 - (edits / max(value1.length, value2.length))) * 100).toDouble
      case None        => 0.0
    }
  }
}
