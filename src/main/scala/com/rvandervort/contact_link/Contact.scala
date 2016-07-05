package com.rvandervort.contact_link

case class Contact (
  id: String,
  firstName: String,
  lastName: String,
  phone: String,
  email: String,
  address1: String,
  address2: String,
  zipCode: String,
  city: String,
  state: String
)

object Contact {
  def attrMap(c: Contact): Map[String, String] =
    Map(
      "id" -> c.id,
      "firstName" -> c.firstName,
      "lastName" -> c.lastName,
      "phone" -> c.phone,
      "email" -> c.email,
      "address1" -> c.address1,
      "address2" -> c.address2,
      "zipCode" -> c.zipCode,
      "city" -> c.city,
      "state" -> c.state
  )

  def zip(c1: Contact, c2: Contact): Map[String, (String, String)] = {
    val c1m = attrMap(c1)
    val c2m = attrMap(c2)

    (c1m.keys zip (c1m.values zip c2m.values)).toMap
  }
}
