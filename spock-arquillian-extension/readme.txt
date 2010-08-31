                           Spock Arquillian Extension 

                             Test in the container!

 What is it?
 ============

 Arquillian is testing framework, developed at JBoss.org, that empowers
 developers to write integration tests for business objects that are executed
 inside of an embedded or remote container--options include a servlet
 container, a Java EE application server or a Java SE CDI environment.
 
 Spock is a testing and specification framework for Java and Groovy applications. 
 What makes it stand out from the crowd is its beautiful and highly expressive specification language. 
 Thanks to its JUnit runner, Spock is compatible with most IDEs, build tools, and continuous integration servers. 
 Spock is inspired from JUnit, jMock, RSpec, Groovy, Scala, Vulcans, and other fascinating life forms.
 
 The Spock Arquillian Extension opens up for the beauty of Spock tests running in-contianer using Arquillian with 
 full EJB, Resource and CDI injection.


 Example
 ========
 
 @Inject 
 AccountService service
        
 def "transferring between accounts should result in account withdrawl and diposit"() {
      when:
      service.transfer(from, to, amount)
        
      then:
      from.balance == fromBalance
      to.balance == toBalance
        
      where:
      from <<         [new Account(100),  new Account(10)]
      to <<           [new Account(50),   new Account(90)]
      amount <<       [50,                10]
      fromBalance <<  [50,                0]
      toBalance <<    [100,               100]
 }

 Contents of distribution
 ========================

 src/main/java/
   The Spock Extension
   
 src/test/groovy
   Arquillian integration tests using Spock

 Licensing
 =========
 
 This distribution, as a whole, is licensed under the terms of the Apache
 License, Version 2.0 (see license.txt).
 

 URLs
 ===============

 Spock:      http://spockframework.org/
 Arquillian: http://jboss.org/arquillian/
                             
