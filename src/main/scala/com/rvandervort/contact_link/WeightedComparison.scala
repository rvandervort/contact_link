package com.rvandervort.contact_link

object WeightedComparison {
  def compare(contactA: Contact, contactB: Contact): ComparisonResult = {

    val result = new ComparisonResult()

    result.add_score("email", basic_check(contactA.email, contactB.email) * .25)
    result.add_score("phone", phone(contactA, contactB) * 0.25)
    result.add_score("lastName", basic_check(contactA.lastName,contactB.lastName) * 0.15)
    result.add_score("firstName", basic_check(contactA.firstName,contactB.firstName) * 0.15)
    result.add_score("zipCode",basic_check(contactA.zipCode, contactB.zipCode) * 0.10)
    result.add_score("address1",address_line(contactA.address1, contactB.address1) * 0.05)
    result.add_score("address2",address_line(contactA.address2, contactB.address2) * 0.05)
    result.add_score("city", basic_check(contactA.city, contactB.city) * 0.025)
    result.add_score("state",basic_check(contactA.state, contactB.state) * 0.025)

    result
  }

  def equivalent(val1: String, val2: String): Boolean =
    !(val1.isEmpty || val2.isEmpty) && (val1.toLowerCase.trim == val2.toLowerCase.trim)

  def basic_check(property_a: String, property_b: String) =
    if (equivalent(property_a, property_b)) 100.00 else 0.00

  def phone(contactA: Contact, contactB: Contact): Double =
    if (equivalent(contactA.phone, contactB.phone)) 100.0 else 0.0

  def address_line(address_a: String, address_b: String): Double =
    if (equivalent(address_a,address_b)) 100.0 else 0.0
}
