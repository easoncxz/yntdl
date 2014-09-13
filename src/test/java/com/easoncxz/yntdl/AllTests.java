package com.easoncxz.yntdl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestCRUD.class, TestHibernateDao.class, TestReflection.class,
		TestRest.class, TestService.class })
public class AllTests {

}
