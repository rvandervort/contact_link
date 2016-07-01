package com.rvandervort.contact_link

import com.rockymadden.stringmetric.similarity.LevenshteinMetric

package object cleansing {
  trait DataCleaner {
    def cleanPhone(phone: String): String = phone.filterNot("-. xX)(+" contains _)

    def cleanAddress(address: String) = address.filterNot("# ." contains _)
  }
}

package object comparisons {
  trait BasicComparisons {

    def equivalent(val1: String, val2: String): Boolean =
      !(val1.isEmpty || val2.isEmpty) && (val1.toLowerCase.trim == val2.toLowerCase.trim)

    def distance(val1: String, val2: String): Option[Int] =
      LevenshteinMetric.compare(val1, val2)
  }
}
