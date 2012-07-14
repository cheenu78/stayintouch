package org.srini.stayintouch.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.springframework.stereotype.Service;
import org.srini.stayintouch.controllers.model.User;
import org.srini.stayintouch.controllers.model.UserDetails;

import com.thoughtworks.xstream.core.util.Base64Encoder;

@Service("ldapService")
public class LDAPService {
	
	public boolean authenticateUser(String email, String password){
		
		Hashtable<String, String> env = new Hashtable<String, String>(11);

		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "uid="+email+",ou=users,ou=system");
		env.put(Context.SECURITY_CREDENTIALS, password);

		try {
			DirContext ctx = new InitialDirContext(env);
			return true;
		}catch(NamingException e){
			return false;
		}
	}
	
	public void saveUserDetails(User user, UserDetails userDetails) throws NamingException{
		Hashtable<String, String> env = new Hashtable<String, String>(11);

		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
		env.put(Context.SECURITY_CREDENTIALS, "secret");
		
		DirContext ctx = new InitialDirContext(env);
		
		String entryDN = "uid="+user.getEmail()+",ou=users,ou=system";  
		
		Attribute cn = new BasicAttribute("cn", userDetails.getFirstName());
        Attribute sn = new BasicAttribute("sn", userDetails.getLastName());
        Attribute uid = new BasicAttribute("uid", user.getEmail());
        Attribute userPassword = new BasicAttribute("userPassword", new Base64Encoder().encode(user.getPassword().getBytes()));
        Attribute oc = new BasicAttribute("objectClass");
        oc.add("inetOrgPerson");
        oc.add("organizationalPerson");
        oc.add("person");
        oc.add("top");
        
        Attributes entry = new BasicAttributes();
        entry.put(cn);
        entry.put(sn);
        entry.put(uid);
        entry.put(userPassword);
        entry.put(oc);
        
        ctx.createSubcontext(entryDN, entry);
		
		ctx.close();
	}
}
