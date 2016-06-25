package com.rvandervort.contact_link

import scala.collection.mutable.Map

class ComparisonResult {
  private var scoreList: Map[String, Double] = Map[String, Double]()

  def add_score(field_name: String, score: Double) =
    scoreList += (field_name ->  score)

  def overall_score: Double = scoreList.values.sum

  def scores = scoreList

  override def toString: String =
    overall_score + "," + scoreList.map { case (field, score) => s"${field}=${score}" }.mkString(",")
}
