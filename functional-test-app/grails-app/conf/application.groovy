info {
	app {
		name = '@info.app.name@'
		version = '@info.app.version@'
		grailsVersion = '@info.app.grailsVersion@'
	}
}

server {
	port = 8238
}

spring.groovy.template.'check-template-location' = false

grails {

	profile = 'web'

	mime {
		disable {
			accept {
				header {
					userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
				}
			}
		}
		types = [
			all           : '*/*',
			atom          : 'application/atom+xml',
			css           : 'text/css',
			csv           : 'text/csv',
			form          : 'application/x-www-form-urlencoded',
			html          : ['text/html', 'application/xhtml+xml'],
			js            : 'text/javascript',
			json          : ['application/json', 'text/json'],
			multipartForm : 'multipart/form-data',
			pdf           : 'application/pdf',
			rss           : 'application/rss+xml',
			text          : 'text/plain',
			hal           : ['application/hal+json', 'application/hal+xml'],
			xml           : ['text/xml', 'application/xml']
		]
	}
	urlmapping {
		cache {
			maxsize = 1000
		}
	}
	controllers {
		defaultScope = 'singleton'
	}
	converters {
		encoding = 'UTF-8'
	}
	views {
		gsp {
			encoding = 'UTF-8'
			htmlcodec = 'xml'
			codecs {
				expression = 'html'
				scriptlet = 'html'
				taglib = 'none'
				staticparts = 'none'
			}
		}
	}
	views.default.codec = 'html'

	plugin {
		springsecurity {
			authority {
				className = 'com.test.Role'
			}
			controllerAnnotations.staticRules = [
				[pattern: '/',               access: 'permitAll'],
				[pattern: '/error',          access: 'permitAll'],
				[pattern: '/index',          access: 'permitAll'],
				[pattern: '/index.gsp',      access: 'permitAll'],
				[pattern: '/shutdown',       access: 'permitAll'],
				[pattern: '/assets/**',      access: 'permitAll'],
				[pattern: '/**/js/**',       access: 'permitAll'],
				[pattern: '/**/css/**',      access: 'permitAll'],
				[pattern: '/**/images/**',   access: 'permitAll'],
				[pattern: '/**/favicon.ico', access: 'permitAll']
			]
			password.algorithm = 'SHA-256'
			rememberMe {
				persistent = true
				persistentToken.domainClassName = 'com.test.PersistentLogin'
			}
			userLookup {
				userDomainClassName = 'com.test.User'
				authorityJoinClassName = 'com.test.UserRole'
			}

			ldap {
				authorities {
					groupSearchBase = 'ou=groups,dc=d1,dc=example,dc=com'
					groupSearchFilter = 'uniquemember={0}'
					retrieveDatabaseRoles = true
				}
				context {
					managerDn = 'uid=admin,ou=system'
					managerPassword = 'secret'
					server = 'ldap://localhost:10389'
				}
				rememberMe {
					detailsManager {
						groupRoleAttributeName = 'cn'
						groupSearchBase = 'ou=groups,dc=d1,dc=example,dc=com'
					}
					usernameMapper {
						userDnBase = 'dc=d1,dc=example,dc=com'
						usernameAttribute = 'cn'
					}
				}
				search {
					base = 'dc=d1,dc=example,dc=com'
					filter = '(uid={0})'
				}
				useRememberMe = true
			}
		}
	}
}

ldapServers {
	d1 {
		base = 'dc=d1,dc=example,dc=com'
		port = 10389
		indexed = ['objectClass', 'uid', 'mail', 'userPassword', 'description']
	}
}

hibernate {
	cache {
		queries = false
		use_second_level_cache = false
		use_query_cache = false
		region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory'
		format_sql = true
		use_sql_comments = true
	}
}

dataSource {
	pooled = true
	jmxExport = true
	driverClassName = 'org.h2.Driver'
	username = 'sa'
	password = ''
	dbCreate = 'update'
	url = 'jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE'
}
