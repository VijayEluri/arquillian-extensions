/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package spock.arquillian

import spock.lang.*
import javax.inject.Inject
import org.jboss.arquillian.api.Deployment
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.asset.EmptyAsset
import org.jboss.shrinkwrap.api.spec.JavaArchive
import org.jboss.arquillian.framework.spock.SecureAccountService
import org.jboss.arquillian.framework.spock.AccountService
import org.jboss.arquillian.framework.spock.Account

class AccountServiceSpecification extends Specification {

    @Deployment
    def static JavaArchive "create deployment"() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(AccountService.class, Account.class, SecureAccountService.class)
                .addManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

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
}