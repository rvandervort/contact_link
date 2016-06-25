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
    val contactList = CSVReader(fileName).toList

   contactList.map(csvToContact)
  }

  def csvToContact(csv: Array[String]): Contact = {
    Contact(
      csv(0),  // id
      csv(1),  // firstName
      csv(2),  // lastName
      csv(3),  // phone
      csv(4),  // email
      csv(5),  // address1
      csv(6),  // address2
      csv(7),  // zipCode
      csv(8),  // city
      csv(9)   // state
    )
  }
}

