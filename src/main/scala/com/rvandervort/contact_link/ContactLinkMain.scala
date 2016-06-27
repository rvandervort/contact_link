package com.rvandervort.contact_link

import com.bizo.mighty.csv.CSVReader

object ContactLink {
  def main(args: Array[String]) {
    val contacts = getContactsFromFile(args(0))

    if (contacts.isEmpty) println(s"No contacts found in file") else {
      val normalizedContacts = contacts.par

      for (A <- normalizedContacts; B <- normalizedContacts; if B.id > A.id) {
        val result = WeightedComparison.compare(A,B)

        println(s"${A.id},${B.id},${result}")
      }
    }
  }


  def getContactsFromFile(fileName: String):  List[Contact] = {
    // Assumes fields in file match layout of Contact constructor
    CSVReader.readAs[Contact](fileName).toList
  }
}

