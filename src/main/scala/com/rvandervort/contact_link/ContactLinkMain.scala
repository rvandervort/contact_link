package com.rvandervort.contact_link

import com.rvandervort.contact_link.cleansing._

import com.bizo.mighty.csv.CSVReader

object ContactLink extends DataCleaner {
  def main(args: Array[String]) {
    val contacts = getContactsFromFile(args(0))

    if (contacts.isEmpty) println(s"No contacts found in file") else {
      val normalizedContacts = contacts.map(cleanContact).par

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

  def cleanContact(contact: Contact): Contact = {
    contact.copy(
      phone     =  cleanPhone(contact.phone),
      address1  =  cleanAddress(contact.address1),
      address2  =  cleanAddress(contact.address2)
    )
  }

}

