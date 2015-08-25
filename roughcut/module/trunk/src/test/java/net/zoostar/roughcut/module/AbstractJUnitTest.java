package net.zoostar.roughcut.module;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = {"classpath*:META-INF/applicationContext-*.xml"})
@Transactional
@ActiveProfiles({"standalone"})
public abstract class AbstractJUnitTest extends AbstractTransactionalJUnit4SpringContextTests {

	static final Logger log = LoggerFactory.getLogger(AbstractJUnitTest.class);
	
	@Rule
	public TestName testName = new TestName();
		
	@Before
	public void setUp() throws Exception {
		System.out.println();
		log.info("Executing Test: [{}]...", testName.getMethodName());
	}

}
