package com.rvandervort.contact_link

import scala.collection.mutable.Map

class ComparisonResult {
  private var scoreList: Map[String, Double] = Map[String, Double]()

  def addScore(field_name: String, score: Double) =
    scoreList += (field_name ->  score)

  def overallScore: Double = scoreList.values.sum

  def scores = scoreList

  override def toString: String =
    overallScore + "," + scoreList.map { case (field, score) => s"${field}=${score}" }.mkString(",")
}
