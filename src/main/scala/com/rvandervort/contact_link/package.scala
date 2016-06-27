package com.rvandervort.contact_link

package object cleansing {
  trait DataCleaner {
    def clean_phone(phone: String): String = phone.filterNot("-. xX)(+" contains _)

    def clean_address(address: String) = address.filterNot("# ." contains _)
  }
}
