package liquibase.parser.filter;

import liquibase.ChangeSet;
import liquibase.database.MySQLDatabase;
import static org.junit.Assert.*;
import org.junit.Test;

public class DbmsChangeSetFilterTest  {

//    @Test
//    public void emptyDbms() {
//        DbmsChangeSetFilter filter = new DbmsChangeSetFilter();
//
//        assertTrue(filter.accepts(new ChangeSet(null, null, false, false, null, null, "mysql")));
//        assertTrue(filter.accepts(new ChangeSet(null, null, false, false, null, null, "oracle")));
//        assertTrue(filter.accepts(new ChangeSet(null, null, false, false, null, null, "oracle, mysql")));
//        assertTrue(filter.accepts(new ChangeSet(null, null, false, false, null, null, null)));
//    }

    @Test
    public void singleDbms() {
        DbmsChangeSetFilter filter = new DbmsChangeSetFilter(new MySQLDatabase());

        assertTrue(filter.accepts(new ChangeSet(null, null, false, false, null,null,  null, "mysql")));
        assertTrue(filter.accepts(new ChangeSet(null, null, false, false, null,null,  null, "mysql, oracle")));
        assertFalse(filter.accepts(new ChangeSet(null, null, false, false, null,null,  null, "oracle")));
        assertTrue(filter.accepts(new ChangeSet(null, null, false, false, null, null, null, null)));
    }

//    @Test
//    public void multiContexts() {
//        DbmsChangeSetFilter filter = new DbmsChangeSetFilter("mysql", "oracle");
//
//        assertTrue(filter.accepts(new ChangeSet(null, null, false, false, null, null, "mysql")));
//        assertTrue(filter.accepts(new ChangeSet(null, null, false, false, null, null, "oracle")));
//        assertTrue(filter.accepts(new ChangeSet(null, null, false, false, null, null, "oracle, mysql")));
//        assertTrue(filter.accepts(new ChangeSet(null, null, false, false, null, null, "db2, oracle")));
//        assertFalse(filter.accepts(new ChangeSet(null, null, false, false, null, null, "db2")));
//        assertTrue(filter.accepts(new ChangeSet(null, null, false, false, null, null, null)));
//    }

}
