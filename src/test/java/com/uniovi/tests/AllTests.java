package com.uniovi.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * 
 * @version $Id$
 */
@RunWith(Suite.class)
@SuiteClasses({ SignUpTests.class, LoginTests.class, LogOutTests.class, AdminListUsersTest.class,
		AdminDeletionTests.class, AddOfferTests.class, ListOfferTests.class, DeleteOffersTests.class,
		SearchOffersTests.class, BuyOfferTests.class, ListBoughtOffersTests.class, InternalizationTests.class,
		SecurityTests.class, SendMessageTests.class, ListMessageTests.class, DeleteMessageTests.class,
		AddOfferDestacadaTests.class })

public class AllTests {

}
