package pages

import geb.Page

class SecureSuperuserPage extends Page {
	static url = 'secure/superusers'

	static at = { title == 'superusers' }

}
