package com.test

import pages.IndexPage
import pages.SecureSuperuserPage
import pages.SecureUserPage

class SecureControllerFunctionalSpec extends AbstractSecurityFunctionalSpec {

	void 'secured urls are not visible without auth'() {
		when:
		go SecureUserPage.url

		then:
		assertContentContains 'Please Login'

		when:
		go SecureSuperuserPage.url

		then:
		assertContentContains 'Please Login'
	}

	def "login with a user present in the database"() {
		when:
		go SecureUserPage.url

		then:
		assertContentContains 'Please Login'

		when:
		login 'galieleo', 'password'

		then:
		at SecureUserPage

		and:
		assertContentContains('galieleo@ldap.forumsys.com')

		when:
		logout()

		then:
		at IndexPage
	}

}
