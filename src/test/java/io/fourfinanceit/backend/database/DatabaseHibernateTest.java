package io.fourfinanceit.backend.database;

import io.fourfinanceit.HwApplication;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {HwApplication.class})
public abstract class DatabaseHibernateTest {

    @Autowired
    protected SessionFactory sessionFactory;

}
